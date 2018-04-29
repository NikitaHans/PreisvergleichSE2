package edu.hm.shareit.persistence;


import java.util.List;

import javax.inject.Inject;

import edu.hm.shareit.models.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import edu.hm.ShareitServletContextListener;

/**
 * Implementation of interface DatabaseManager.
 * @author Thomas Murschall
 *
 */
public class DatabaseManagerImpl implements DatabaseManager {

    private Session entityManager;
    private Transaction tx;

    /**
     * 
     */
    @Inject
    public DatabaseManagerImpl() {
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
    }


    @Override
    public void insertRequest(Car car) {
        try {
            entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
            tx = entityManager.beginTransaction();
            entityManager.persist(car);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error occured");
        }
    }

    //Sample Method
    /*@Override
    public Book getBook(String isbn) {
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
        tx = entityManager.beginTransaction();
        Book out = entityManager.get(Book.class, isbn);
        tx.commit();
        return out;
    }*/


    @Override
    public List<Car> getAllCars() {
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
        tx = entityManager.beginTransaction();
        List<Car> out = entityManager.createQuery("From Car").list();
        tx.commit();
        return out;
    }
}
