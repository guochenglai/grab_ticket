package net.gcl.ticket.model;

/**
 * Created by guochenglai on 2/16/17.
 */
public class AccountInfo {
    private String account;
    private String password;

    public AccountInfo() {
    }

    public AccountInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
