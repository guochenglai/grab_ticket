package net.gcl.ticket.test.dama2;

import com.alibaba.fastjson.JSON;
import net.gcl.ticket.dama2.Dama2Client;
import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.DecodeQRInfo;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by guochenglai on 1/9/17.
 */
public class Dama2ClientTest {
    private Dama2Client dama2Client = BeanFactory.getSingletonBean(Dama2Client.class.getName());

    @Test
    public void testDecodeQr() {
        String qrFilePath = this.getClass().getResource("/qr/test_qr_code.jpeg").getFile();
        File qrFile = new File(qrFilePath);
        try (InputStream inputStream = new FileInputStream(qrFile)) {
            byte date[] = new byte[inputStream.available()];
            inputStream.read(date);
            System.out.println("============================" + System.currentTimeMillis());
            DecodeQRInfo decodeInfo = dama2Client.decodeQRCode(date);
            System.out.println(JSON.toJSONString(decodeInfo));
            System.out.println("============================" + System.currentTimeMillis());
        } catch (Exception e) {

        }
    }

    @Test
    public void testQueryBalance() {
        long balance = dama2Client.queryBalance();
        System.out.println("===============================");
        System.out.println(balance);
        System.out.println("===============================");
    }

    @Test
    public void testQueryQRContentById() {
        Long qrId = 144781249l;
        String qrContent = dama2Client.doGetQRContentById(qrId);
        System.out.println("====================================="+System.currentTimeMillis());
        System.out.println(qrContent);
        System.out.println("=====================================" + System.currentTimeMillis());
    }
}
