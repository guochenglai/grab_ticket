package net.gcl.ticket.model;

/**
 * Created by guochenglai on 2/23/17.
 */
public class SubmitOrderInfo {
    private boolean status;//ture 表示下单成功
    private String message;// 失败消息

    public SubmitOrderInfo() {
    }

    public SubmitOrderInfo(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
