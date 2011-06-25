package ru.concerteza.etomcat;

import org.apache.catalina.ContainerListener;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Embedded;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.startup.Embedded;
import org.apache.catalina.Container;

/**
 * User: alexey Date: 6/21/11
 */

@Service
public class EmbeddedTomcat {

	protected String contextClass = "org.apache.catalina.core.StandardContext";

	public boolean start() throws Exception {
		System.setProperty("catalina.home", ".");
		Embedded embedded = new Embedded();
		Engine engine = embedded.createEngine();
		engine.setDefaultHost("localhost");
		Host host = embedded.createHost("localhost", "webapps");
		engine.addChild(host);

		Context springContext = (Context) Class.forName(contextClass)
				.newInstance();
		if (springContext instanceof Lifecycle) {
			Class clazz = Class.forName(host.getConfigClass());
			LifecycleListener listener = (LifecycleListener) clazz
					.newInstance();
			((Lifecycle) springContext).addLifecycleListener(listener);
		}
		springContext.setPath("/airplane");
		springContext.setDocBase("airplane");
		host.addChild(springContext);

		embedded.addEngine(engine);
		Connector connector = embedded.createConnector((String) null, 8080,
				false);
		connector.setURIEncoding("UTF-8");
		embedded.addConnector(connector);
		embedded.start();

		return springContext.getAvailable();
	}

	public void stop() {
		// todo
	}
}
