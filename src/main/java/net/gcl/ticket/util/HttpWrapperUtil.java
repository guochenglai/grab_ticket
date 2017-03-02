package net.gcl.ticket.util;

import java.util.Map;

import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 1/5/17.
 */
public class HttpWrapperUtil {
    public static void setHeader(HttpRequestBase http,Map<String,String> hederMap) {
        for (Map.Entry<String, String> headerEntry : hederMap.entrySet()) {
            http.setHeader(headerEntry.getKey(), headerEntry.getValue());
        }
    }

}
