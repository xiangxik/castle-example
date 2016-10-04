# castle-example
The example of castle-platform.

## 运行方法

### 配置zookeeper
可查阅http://ken.whenling.com/2016/07/26/zookeeper/ 。配置完成后，启动zookeeper。

### 把castle-example引入到eclipse

### 启动Provider
打开example-provider. 打开文件com.whenling.example.provider.support.DubboProviderMain。  
采用java application方式启动。

### 启动website
使用支持servlet3.1的容器启动项目，采用最新的容器即可。如jetty9.3+、tomcat9+等。