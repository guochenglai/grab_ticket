package net.gcl.ticket.util;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Created by guochenglai on 1/6/17.
 */
public class IOUtil {
    private static Logger logger = LoggerFactory.getLogger(IOUtil.class);

    public static String readInputStream(HttpResponse httpResponse) {
        Header[] allHeaders = httpResponse.getAllHeaders();
        boolean isGzip = false;
        for (int i = 0; i < allHeaders.length; i++) {
            if ("Content-Encoding".equals(allHeaders[i].getName()) && "gzip".equals(allHeaders[i].getValue())) {
                isGzip = true;
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = httpResponse.getEntity().getContent()) {
            if (isGzip) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream, "UTF-8"))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }

                }
            } else {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("read http response cause exception ",e);
        }
        return stringBuilder.toString();
    }

}
