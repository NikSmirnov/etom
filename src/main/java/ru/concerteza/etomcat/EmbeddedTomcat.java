package ru.concerteza.etomcat;

import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Embedded;
import org.springframework.stereotype.Service;

/**
 * User: alexey
 * Date: 6/21/11
 */

@Service
public class EmbeddedTomcat {
    public void start() throws LifecycleException {
        System.setProperty("catalina.home", ".");
        Embedded embedded = new Embedded();
        Engine engine = embedded.createEngine();
        engine.setDefaultHost("localhost");
        Host host = embedded.createHost("localhost", "webapps");
        engine.addChild(host);
        StandardContext context = (StandardContext) embedded.createContext("", "test");
        host.addChild(context);
        embedded.addEngine(engine);
        Connector connector = embedded.createConnector((String) null, 8080, false);
        connector.setURIEncoding("UTF-8");
        embedded.addConnector(connector);
        embedded.start();
    }

    public void stop() {
        // todo
    }
}
