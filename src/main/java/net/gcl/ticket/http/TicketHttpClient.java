package net.gcl.ticket.http;

import java.util.List;
import java.util.Map;

import net.gcl.ticket.http.factory.HttpClientFactory;
import net.gcl.ticket.util.HttpWrapperUtil;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Created by guochenglai on 1/5/17.
 * 12306请求客户端，实例化的时候回进行初始化，以后的请求都会带上相应的cookie信息
 */
public class TicketHttpClient {
    private static Logger logger = LoggerFactory.getLogger(TicketHttpClient.class);
    private static HttpClient httpClient = HttpClientFactory.buildHttpClient();

    public HttpResponse doPost(String requestUrl,List<NameValuePair> params,Map<String,String>headerMap){
        try {
            HttpPost httpPost = new HttpPost(requestUrl);
            HttpWrapperUtil.setHeader(httpPost, headerMap);
            if (params != null) {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }
            logger.info("do http post request url is : {} param is : {}", requestUrl, JSON.toJSONString(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            return httpResponse;
        } catch (Exception e) {
            logger.error("http post cause exception request url is : {} param is : {} ", requestUrl, JSON.toJSONString(params), e);
        }
        return null;
    }

    public HttpResponse doGet(String requestUrl, Map<String, String> headerMap) {
        try {
            HttpGet httpGet = new HttpGet(requestUrl);
            HttpWrapperUtil.setHeader(httpGet, headerMap);

            logger.info("do http get request url is : {} ", requestUrl);
            HttpResponse httpResponse = httpClient.execute(httpGet);

            return httpResponse;
        } catch (Exception e) {
            logger.error("http get cause exception request url is : {} ", requestUrl, e);
        }
        return null;
    }
}
