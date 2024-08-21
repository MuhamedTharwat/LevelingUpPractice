package utils.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {
    private static final Logger logger = LogManager.getLogger();

    private static void info(String message) {
        logger.info(message);
    }

    private static void error(String message) {
        logger.error(message);
    }

    private static void debug(String message) {
        logger.debug(message);
    }

    private static void warn(String message) {
        logger.warn(message);
    }
}
