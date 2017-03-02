package net.gcl.ticket.http.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.X509TrustManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * Created by guochenglai on 1/6/17.
 */
public class TicketTrustManager implements X509TrustManager{
    private Logger logger = LoggerFactory.getLogger(TicketTrustManager.class);

    private Certificate certificate;

    public TicketTrustManager() {
        String certificateFilePath = this.getClass().getResource(File.separator + "srca.cer").getPath();
        try (InputStream inputStream = new FileInputStream(certificateFilePath); BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            while (bufferedInputStream.available() > 0) {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                certificate = certificateFactory.generateCertificate(bufferedInputStream);
            }
        } catch (Exception e) {
            logger.error("build ticket root certificate cause exception ", e);
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        for (X509Certificate cert : x509Certificates) {
            if (cert.toString().equals(this.certificate.toString()))
                return;
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[] { (X509Certificate) certificate };
    }
}
