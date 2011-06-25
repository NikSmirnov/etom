package ru.concerteza.etomcat;


import org.apache.catalina.LifecycleException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: alexey
 * Date: 6/21/11
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/etomcat-ctx.xml");
		if (!ctx.getBean(EmbeddedTomcat.class).start()) {
		  return;	
		}

        System.console().readLine();
    }
}
