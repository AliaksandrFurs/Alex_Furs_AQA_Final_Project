package utils;

import org.apache.log4j.Logger;

public class Logging {

    static Logger logger = Logger.getRootLogger();

    private Logging(){}

    public static void logInfo(String message){

        logger.info(message);
    }

    public static void logError(String message){

        logger.error(message);
    }

    public static void logWarn(String message){

        logger.warn(message);
    }
}
