package net.gcl.ticket.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.gcl.ticket.util.IOUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guochenglai on 1/9/17.
 */
public class Dama2HttpClient {
    private Logger logger = LoggerFactory.getLogger(Dama2HttpClient.class);
    private HttpClient httpClient = HttpClients.createDefault();


    public HttpResponse doGet(String requestUrl) {
        HttpGet httpGet = new HttpGet(requestUrl);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (Exception e) {
            logger.error("do get http cause exception request url is : {}",requestUrl,e);
        }
        return httpResponse;
    }

    public HttpResponse doPost(String requestUrl,List<NameValuePair> params) {
        HttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(requestUrl);
            if (params != null) {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
            }
            httpResponse = httpClient.execute(httpPost);

        } catch (Exception e) {
            logger.error("do post http cause exception request url is : {}", requestUrl, e);
        }
        return httpResponse;
    }

    public String doPostPic(String requestUrl, Map<String, String> params, byte[] data) {
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(requestUrl)).openConnection();
            conn.setUseCaches(false);
            conn.setConnectTimeout(15 * 1000);
            conn.setReadTimeout(40 * 1000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Charsert", "UTF-8");

            String boundary = "------WebKitFormBoundary";
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            OutputStream os = conn.getOutputStream();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append("\r\n--").append(boundary)
                        .append("\r\nContent-Disposition: form-data;name=\"").append(entry.getKey()).append("\";")
                        .append("\r\nContent-Type:plain/text\r\n\r\n").append(entry.getValue());
                os.write(sb.toString().getBytes());

            }

            StringBuilder sb = new StringBuilder();
            sb.append("\r\n--").append(boundary).append("\r\nContent-Disposition: form-data;name=\"data\";filename=\"pic.jpg\"\r\nContent-Type:image/jpg\r\n\r\n");
            os.write(sb.toString().getBytes());
            os.write(data);
            StringBuilder sbEnd = new StringBuilder();
            sbEnd.append("\r\n--").append(boundary).append("--\r\n");
            os.write(sbEnd.toString().getBytes());
            os.flush();
            conn.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            conn.disconnect();
            String message = stringBuilder.toString();
            return message;
        } catch (Exception e) {
            logger.error("decode qr code cause exception ", e);

        }

        return null;
    }

    public String doGetContent(String requestUrl) {
        HttpResponse httpResponse = doGet(requestUrl);
        if (httpResponse != null) {
            return IOUtil.readInputStream(httpResponse);
        }
        return null;
    }

    public String doPostContent(String requestUrl,List<NameValuePair> params) {
        HttpResponse httpResponse = doPost(requestUrl, params);
        if (httpResponse != null) {
            return IOUtil.readInputStream(httpResponse);
        }
        return null;
    }
}
