package net.gcl.ticket.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 1/8/17.
 */
public class ConfUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfUtil.class);
    private static PropertiesConfiguration propertiesConfiguration;
    static {
        propertiesConfiguration = new PropertiesConfiguration();
        try {
            propertiesConfiguration.setEncoding("utf8");
            propertiesConfiguration.load("conf.properties");
            propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (Exception e) {
            logger.error("conf.properties init cause exception ",e);
        }
    }

    public static String getString(String propertyKey) {
        return propertiesConfiguration.getString(propertyKey,null);
    }

    public static Double getDouble(String propertyKey) {
        return propertiesConfiguration.getDouble(propertyKey,null);
    }

    public static Integer getInteger(String propertyKey) {
        return propertiesConfiguration.getInteger(propertyKey,null);
    }

    public static Long getLong(String propertyKey) {
        return propertiesConfiguration.getLong(propertyKey, null);
    }
    public static Float getFloat(String propertyKey) {
        return propertiesConfiguration.getFloat(propertyKey, null);
    }
}
