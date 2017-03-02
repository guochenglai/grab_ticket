## 重要类说明
### 1 BeanFactory 
   bean工厂，用来进行bean的创建<br />
   public static <T> T getSingletonBean(String beanName) 创建单例bean<br />
   public static <T> T getProtoTypeBean(String beanName) 创建原型bean
   
### 2 Dama2Client
   打码兔，自动打码客户端，用来自动破解12306的二维码<br />
   public String decodeQRCode(byte [] data) 传入二维码的二进制流表示，返回解码后的字符串
 
### 3 TicketHttpClient
   12306的https请求客户端，封装12306的GET和POST请求

### 4 OrderSubmitService
   核心类 主要用来进行用户登录操作

### 5 UserCookieService
    保存用户的cookie到本地sqllite数据库，并有离线线程刷新用户的登录状态
    
### 6 UserAccountService
    保存和获取用户的账户信息到本地数据库
### 7 Main
    程序的入口用来进行用户登录
    
### 4 PassengerService
    主要用来查询用户乘客信息
    

### 5 LoginService
   用来进行用户登录

### 6 QRCodeService
   封装二维码的破解代码
   
### 7 ConfUtil
   封装获取resource目录下conf.properties文件的内容

### 8 HttpWrapperUtil
   设置12306请求的相关信息
   
### 9 URLEntity
   12306各个请求的URL，这个是Google上搜到别人整理的，感谢大神，给我节省了很大的功夫

## 作者邮箱
   gcl272633743@163.com   
   
   
## 免责声明
   本代码是供大家学习交流使用，不作为商业用途使用。也不允许在没有得到作者授权的情况下，擅自进行商业活动。
   
   