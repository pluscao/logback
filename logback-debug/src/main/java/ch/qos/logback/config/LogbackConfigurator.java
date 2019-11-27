package ch.qos.logback.config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.spi.ContextAwareBase;

public class LogbackConfigurator extends ContextAwareBase implements Configurator {
    @Override
    public void configure(LoggerContext loggerContext) {
        addInfo("Setting up default configuration.");

        /*创建appender*/
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("/var/testLogback/logback.log");
        fileAppender.setName("file");
        fileAppender.setContext(loggerContext);

        /*定义编码方式*/
        LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<ILoggingEvent>();
        encoder.setContext(loggerContext);

        /*定义编码格式*/
        SelfLayout selfLayout = new SelfLayout();
        selfLayout.setContext(loggerContext);
        selfLayout.start();// 这里启用这


        encoder.setLayout(selfLayout);
        fileAppender.setEncoder(encoder);
        fileAppender.start();

        Logger logger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(fileAppender);
    }

}
