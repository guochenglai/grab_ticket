package net.gcl.ticket.factory;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenglai.guo on 2017/1/7.
 */
public class BeanFactory {
    private static Logger logger = LoggerFactory.getLogger(BeanFactory.class);
    private static Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    public static <T> T getSingletonBean(String beanName) {
        T bean = (T)beanMap.get(beanName);
        synchronized (BeanFactory.class) {
            if (bean == null) {
                try {
                    Class clazz = Class.forName(beanName);
                    T beanInstance = (T) clazz.newInstance();
                    beanMap.put(beanName, beanInstance);
                    return beanInstance;
                } catch (Exception e) {
                    logger.error("init singleton bean cause exception beanName is : {} ", beanName, e);
                }
            }
        }
        return bean;
    }

    public static <T> T getProtoTypeBean(String beanName) {
        try {
            Class clazz = Class.forName(beanName);
            T beanInstance = (T) clazz.newInstance();
            beanMap.put(beanName, beanInstance);
            return beanInstance;
        } catch (Exception e) {
            logger.error("init prototype bean cause exception beanName is : {} ", beanName, e);
        }
        return null;
    }

}
