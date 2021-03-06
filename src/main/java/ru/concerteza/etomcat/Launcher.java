package ru.concerteza.etomcat;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * User: alexey
 * Date: 6/21/11
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/etomcat-ctx.xml");
		EmbeddedTomcat bean = (EmbeddedTomcat) ctx.getBean("eTomcat");
		if (!bean.start()) {
		  return;	
		}

        System.console().readLine();
    }
}
