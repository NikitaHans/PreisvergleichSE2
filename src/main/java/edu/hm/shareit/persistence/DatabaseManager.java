package edu.hm.shareit.persistence;


import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import edu.hm.shareit.models.Car;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import edu.hm.ShareitServletContextListener;

/**
 * @author Thomas Murschall
 *
 */
public class DatabaseManager implements DatabaseManagerFunctionality {

    private Session entityManager;
    private Transaction transaction;
    private final Logger log = Logger.getLogger(this.getClass());

    @Inject
    public DatabaseManager() {
        updateEntityManager();
    }

    @Override
    public void insertRequest(Car car) {
        Runnable insertCar = ()-> entityManager.persist(car);
        transactionWrapper(insertCar);
    }

    @Override
    public List<Car> getAllCars() {
        Callable<List<Car>> getCars = ()-> entityManager.createQuery("From Car").list();
        return transactionWrapper(getCars);
    }

    private void transactionWrapper(Runnable func) {
        updateEntityManager();
        transaction = entityManager.beginTransaction();
        try{
            func.run();
        } catch (Exception e) {
            log.warn("Encountered " + e.getClass());
        }
        transaction.commit();
    }

    private <T> T transactionWrapper(Callable<T> func) {
        T returnable = null;
        updateEntityManager();
        transaction = entityManager.beginTransaction();
        try{
            returnable = func.call();
        } catch (Exception e) {
            log.warn("Encountered " + e.getClass());
        }
        transaction.commit();
        return returnable;
    }

    private void updateEntityManager() {
        entityManager = ShareitServletContextListener
                .getInjectorInstance()
                .getInstance(SessionFactory.class)
                .getCurrentSession();
    }
}
