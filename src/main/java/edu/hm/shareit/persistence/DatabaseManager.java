package edu.hm.shareit.persistence;


import java.util.LinkedList;
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
    private static boolean init = true;
    private final Logger log = Logger.getLogger(this.getClass());

    @Inject
    public DatabaseManager() {
        updateEntityManager();
    }

    public void init(){
        if(init){
            insertClimateZone(new ClimateZone("hot"));
            insertClimateZone(new ClimateZone("cold"));
            insertClimateZone(new ClimateZone("normal"));
            insertClimateZone(new ClimateZone("optional"));

            insertNation(new Nation("de", new ClimateZone("cold")));
            insertNation(new Nation("ch", new ClimateZone("cold")));
            insertNation(new Nation("gb", new ClimateZone("cold")));
            insertNation(new Nation("it", new ClimateZone("hot")));

            insertCar(new Car("BMW","7", 30000f));
            insertCar(new Car("BMW","i8", 80000f));
            insertCar(new Car("Audi","A8", 30000f));
            insertCar(new Car("Mercedes","SLS AMG", 120000f));
            insertCar(new Car("VW","Touran", 30000f));
            insertCar(new Car("VW","Tiguan", 30000f));

            CarAttribute attribute1 = new CarAttribute("Climacontrol", new ClimateZone("hot"), 10.000f);
            CarAttribute attribute2 = new CarAttribute("Navigation", new ClimateZone("optional"), 50.000f);
            CarAttribute attribute3 = new CarAttribute("Heating",  new ClimateZone("cold"), 10.000f);
            CarAttribute attribute4 = new CarAttribute("Window winder",  new ClimateZone("optional"), 10.000f);
            CarAttribute attribute5 = new CarAttribute("Snow chains",  new ClimateZone("cold"), 10.000f);
            CarAttribute attribute6 = new CarAttribute("Audio HiFi",  new ClimateZone("optional"), 20.000f);

            insertCarAttribute(attribute1);
            insertCarAttribute(attribute2);
            insertCarAttribute(attribute3);
            insertCarAttribute(attribute4);
            insertCarAttribute(attribute5);
            insertCarAttribute(attribute6);


            CarPackage p1 = new CarPackage("Sport");
            CarPackage p2 = new CarPackage("Normal");
            CarPackage p3 = new CarPackage("Premium");

            p1.addAttributes(attribute5);
            p2.addAttributes(attribute4);
            p3.addAttributes(attribute2);
            p3.addAttributes(attribute6);

            insertCarRackage(p1);
            insertCarRackage(p2);
            insertCarRackage(p3);
            init = false;
        }
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
    public void insertClimateZone(ClimateZone zone) {
        Runnable insertCar = ()-> entityManager.persist(zone);
        transactionWrapper(insertCar);
    }

    @Override
    public void insertNation(Nation nation) {
        Runnable insertCar = ()-> entityManager.persist(nation);
        transactionWrapper(insertCar);
    }

    public Car getCar(String brand, String modelName){
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
        transaction = entityManager.beginTransaction();
        Car req = (Car) entityManager.get(Car.class, new CompositeKey(brand, modelName));
        transaction.commit();
        return req;
    }

    public List<Order> getAllOrders (){
        Callable<List<Order>> getOrders = ()-> entityManager.createQuery("From Order").list();
        return transactionWrapper(getOrders);
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

    public void insertUser(User user) {
        Runnable insertUser = ()-> entityManager.persist(user);
        transactionWrapper(insertUser);
    }

    public Nation getNation (String nation){
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
        transaction = entityManager.beginTransaction();
        Nation req = (Nation) entityManager.get(Nation.class, nation);
        transaction.commit();
        return req;
    }

    public User getUser(String mail){
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
        transaction = entityManager.beginTransaction();
        User req = (User) entityManager.get(User.class, mail);
        transaction.commit();
        return req;
    }

    public CarAttribute getCarAttribute(String attributeName){
        entityManager = ShareitServletContextListener.getInjectorInstance().getInstance(SessionFactory.class).getCurrentSession();
        transaction = entityManager.beginTransaction();
        CarAttribute req = (CarAttribute) entityManager.get(CarAttribute.class, attributeName);
        transaction.commit();
        return req;
    }

    private void transactionWrapper(Runnable func) {
        updateEntityManager();
        transaction = entityManager.beginTransaction();
        try{
            func.run();
        } catch (Exception e) {
            log.warn("Encountered " + e.getClass());
            e.printStackTrace();
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
