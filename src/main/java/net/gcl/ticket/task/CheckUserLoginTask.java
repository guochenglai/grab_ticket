package net.gcl.ticket.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import net.gcl.ticket.factory.BeanFactory;
import net.gcl.ticket.model.AccountInfo;
import net.gcl.ticket.service.LoginService;
import net.gcl.ticket.service.UserAccountService;

/**
 * Created by guochenglai on 2/23/17.
 */
public class CheckUserLoginTask {
    private Logger logger = LoggerFactory.getLogger(CheckUserLoginTask.class);
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1,new LoginThreadFactory());
    private LoginService loginService = BeanFactory.getSingletonBean(LoginService.class.getName());
    private UserAccountService userAccountService = BeanFactory.getSingletonBean(UserAccountService.class.getName());

    public void checkUserLogin() {
        executorService.scheduleAtFixedRate(new LoginChecker(), 3, 30, TimeUnit.SECONDS);
    }

    class LoginThreadFactory implements ThreadFactory{
        private String threadName = "check-user-login-thread";
        private ThreadGroup threadGroup;

        public LoginThreadFactory() {
            SecurityManager securityManager = System.getSecurityManager();
            threadGroup = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(threadGroup, r, threadName);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }

    }

    class LoginChecker implements Runnable{
        @Override
        public void run() {
            boolean login = loginService.checkUserLogin();
            if (!login) {
                logger.error("用户已经掉线，尝试重新登录");
                AccountInfo accountInfo = userAccountService.queryAccountInfo();
                logger.info("auto login user info is : {}", JSON.toJSONString(accountInfo));
                loginService.autoLogin(accountInfo.getAccount(), accountInfo.getPassword());

            }

        }
    }

}
