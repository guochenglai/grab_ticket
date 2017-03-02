package net.gcl.ticket.http.factory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by guochenglai on 1/6/17.
 */
public class TicketHostVerifier implements HostnameVerifier{
    @Override
    public boolean verify(String s, SSLSession sslSession) {
        //直接返回true表示接受所有的HTTPS证书
        return true;
    }
}
