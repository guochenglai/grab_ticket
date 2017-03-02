package net.gcl.ticket.model;

/**
 * Created by guochenglai on 2/16/17.
 */
public class CheckOrderInfoEntity {
    private boolean submitStatus;
    private boolean ifShowPassCode;
    private String message;

    public CheckOrderInfoEntity() {
    }

    public CheckOrderInfoEntity(boolean submitStatus, boolean ifShowPassCode) {
        this.submitStatus = submitStatus;
        this.ifShowPassCode = ifShowPassCode;
    }

    public CheckOrderInfoEntity(boolean submitStatus, boolean ifShowPassCode, String message) {
        this.submitStatus = submitStatus;
        this.ifShowPassCode = ifShowPassCode;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(boolean submitStatus) {
        this.submitStatus = submitStatus;
    }

    public boolean isIfShowPassCode() {
        return ifShowPassCode;
    }

    public void setIfShowPassCode(boolean ifShowPassCode) {
        this.ifShowPassCode = ifShowPassCode;
    }
}
