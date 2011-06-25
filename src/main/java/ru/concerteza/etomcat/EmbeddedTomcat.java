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

	private final String contextClass = "org.apache.catalina.core.StandardContext";
	private int port = 8080;
	private String springApp = "";
	private String defaultHostName = "";
	private String hostName = "";

	public boolean start() throws Exception {
		System.setProperty("catalina.home", ".");
		Embedded embedded = new Embedded();
		Engine engine = embedded.createEngine();
		engine.setDefaultHost(defaultHostName);
		Host host = embedded.createHost(defaultHostName, hostName);
		engine.addChild(host);

		Context springContext = (Context) Class.forName(contextClass)
				.newInstance();
		if (springContext instanceof Lifecycle) {
			Class clazz = Class.forName(host.getConfigClass());
			LifecycleListener listener = (LifecycleListener) clazz
					.newInstance();
			((Lifecycle) springContext).addLifecycleListener(listener);
		}
		springContext.setPath("/" + springApp);
		springContext.setDocBase(springApp);
		host.addChild(springContext);

		embedded.addEngine(engine);
		Connector connector = embedded.createConnector((String) null, port,
				false);
		connector.setURIEncoding("UTF-8");
		embedded.addConnector(connector);
		embedded.start();

		return springContext.getAvailable();
	}

	public void stop() {
		// todo
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setSpringApp(String springApp) {
		this.springApp = springApp;
	}

	public String getSpringApp() {
		return springApp;
	}

	public void setDefaultHostName(String defaultHostName) {
		this.defaultHostName = defaultHostName;
	}

	public String getDefaultHostName() {
		return defaultHostName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
