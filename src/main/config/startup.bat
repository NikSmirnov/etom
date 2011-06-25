@echo off
java -Xms32M -Xmx500M -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.util.logging.config.file=".\conf\logging.properties" -Xdebug -classpath etomcat.jar ru.concerteza.etomcat.Launcher
