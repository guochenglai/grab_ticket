package net.gcl.ticket.test.service;

import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import net.gcl.ticket.service.LoginService;
import net.gcl.ticket.service.UserAccountService;

import org.junit.Test;

/**
 * Created by guochenglai on 1/6/17.
 */
public class LoginServiceTest {
    private LoginService loginService = BeanFactory.getSingletonBean(LoginService.class.getName());
    private UserAccountService accountService = BeanFactory.getSingletonBean(UserAccountService.class.getName());

    @Test
    public void checkUserLogin() {
        loginService.checkUserLogin();
    }

    @Test
    public void tesAutoLogin() {
        AccountInfo accountInfo = accountService.queryAccountInfo();
        String message = loginService.autoLogin(accountInfo.getAccount(), accountInfo.getPassword());
        System.out.println("=====================");
        System.out.println(message);
        System.out.println("=====================");
    }

    @Test
    public void testSout() {
        System.out.println("================");
    }
}
