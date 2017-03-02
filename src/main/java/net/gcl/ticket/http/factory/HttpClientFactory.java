package net.gcl.ticket.http.factory;

import javax.net.ssl.*;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 1/6/17.
 */
public class HttpClientFactory {
    private static Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);
    private static  final BasicCookieStore cookieStore = new BasicCookieStore();

    public static BasicCookieStore getCookieStore() {
        return cookieStore;
    }

    public static HttpClient buildHttpClient() {
        try {

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TicketTrustManager()}, new java.security.SecureRandom());
            HttpClient httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .setSSLHostnameVerifier(new TicketHostVerifier())
                    .setSSLContext(sslContext)
                    .build();
            return httpClient;
        } catch (Exception e) {
            logger.error("build http client cause exception ",e);
        }
        logger.error("build http client cause exception and return default http client");
        return HttpClients.createDefault();
    }
}
