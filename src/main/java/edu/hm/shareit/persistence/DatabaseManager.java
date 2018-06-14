package edu.hm.shareit.persistence;


import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

import edu.hm.shareit.models.*;
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

    public void insertOrder(Order order){
        Runnable insertCar = ()-> entityManager.persist(order);
        transactionWrapper(insertCar);
    }

    @Override
    public void insertCar(Car car) {
        Runnable insertCar = ()-> entityManager.persist(car);
        transactionWrapper(insertCar);
    }

    @Override
    public void insertCarRackage(CarPackage carpackage) {
        Runnable insertCar = ()-> entityManager.persist(carpackage);
        transactionWrapper(insertCar);
    }

    @Override
    public void insertCarAttribute(CarAttribute attribute) {
        Runnable insertCar = ()-> entityManager.persist(attribute);
        transactionWrapper(insertCar);
    }

    @Override
    public void insertClimateZone(String zone) {
        Runnable insertCar = ()-> entityManager.persist(zone);
        transactionWrapper(insertCar);
    }

    @Override
    public List<Car> getAllCars() {
        Callable<List<Car>> getCars = ()-> entityManager.createQuery("From Car").list();
        return transactionWrapper(getCars);
    }

    public List<CarPackage> getAllPackages(){
        Callable<List<CarPackage>> getCars = ()-> entityManager.createQuery("From CarPackage").list();
        return transactionWrapper(getCars);
    }

    public List<CarAttribute> getAllCarAttributes(){
        Callable<List<CarAttribute>> getCars = ()-> entityManager.createQuery("From CarAttribute").list();
        return transactionWrapper(getCars);
    }

    public List<CarAttribute> getAllCarAttributes(String zone){
        Callable<List<CarAttribute>> getCars = ()-> entityManager.createQuery("From CarAttribute where zone=" + zone).list();
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
