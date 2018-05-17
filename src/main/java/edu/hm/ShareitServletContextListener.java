package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.shareit.Services.CarServiceFunctionality;
import edu.hm.shareit.Services.CarService;
import edu.hm.shareit.Services.CarServiceMock;
import edu.hm.shareit.persistence.DatabaseManager;
import edu.hm.shareit.persistence.DatabaseManagerFunctionality;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Context Listener to enable usage of google guice together with jersey.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */

public class ShareitServletContextListener extends GuiceServletContextListener {

    private static final Injector injector = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(CarServiceFunctionality.class).to(CarServiceMock.class);
            bind(DatabaseManagerFunctionality.class).to(DatabaseManager.class);
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
        }
    });

    @Override
    protected Injector getInjector() {
        return injector;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     * @return Injector instance.
     */

    public static Injector getInjectorInstance() {
        return injector;
    }

}