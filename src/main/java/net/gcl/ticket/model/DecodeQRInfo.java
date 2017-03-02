package net.gcl.ticket.model;

/**
 * Created by guochenglai on 2/14/17.
 */
public class DecodeQRInfo {
    private Long qrcodeId;
    private String qrContent;

    public DecodeQRInfo() {
    }

    public DecodeQRInfo(Long qrcodeId, String qrContent) {
        this.qrcodeId = qrcodeId;
        this.qrContent = qrContent;
    }

    public Long getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Long qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getQrContent() {
        return qrContent;
    }

    public void setQrContent(String qrContent) {
        this.qrContent = qrContent;
    }
}
