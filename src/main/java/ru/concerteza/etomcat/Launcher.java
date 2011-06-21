package ru.concerteza.etomcat;


import org.apache.catalina.LifecycleException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: alexey
 * Date: 6/21/11
 */
public class Launcher {

    public static void main(String[] args) throws LifecycleException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/etomcat-ctx.xml");
        ctx.getBean(EmbeddedTomcat.class).start();
        System.console().readLine();
    }
}
