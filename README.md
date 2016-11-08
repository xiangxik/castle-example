# castle-example
The example of castle-platform.

## 运行方法

### 1. 配置zookeeper
可查阅http://ken.whenling.com/2016/07/26/zookeeper/ 。配置完成后，启动zookeeper。
![Alt management](http://ken.whenling.com/img/castle/zookeeper.jpg)

### 2. 把castle-example引入到eclipse

### 3. 配置数据库
创建数据库，并且修改工程文件里面的config.properties中的数据库链接。

### 4. 启动管理后台
使用jetty9.3.6+、或者tomcat8.5+ 的容器启动项目 example-management-webapp-extjs.
![Alt management_login](http://ken.whenling.com/img/castle/management_login.jpg)
![Alt management](http://ken.whenling.com/img/castle/management.jpg)

### 5. 启动Provider
打开example-provider. 打开文件com.whenling.example.provider.support.DubboProviderMain。  
采用java application方式启动。
真实环境采用打包后的/bin/start.sh运行。  

当出现 Dubbo service server started! 表示启动成功。

### 6. 启动website
使用jetty9.3.6+、或者tomcat8.5+ 的容器启动项目 example-website.
![Alt website](http://ken.whenling.com/img/castle/website.jpg)