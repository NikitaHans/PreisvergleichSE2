package edu.hm;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Start the application without an AppServer like tomcat.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8085;
    public static final String WEBAPP_DIR = "./src/main/webapp/";
    private static final Logger log = Logger.getLogger(JettyStarter.class);

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {
        BasicConfigurator.configure();
        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        log.info("Jetty listening on port " + PORT);
        jetty.join();
    }

}
