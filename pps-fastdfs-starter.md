#基于fastdfs-client-java封装的一个springboot启动器 如果觉得好用请给我点赞
本jar包还未上传到maven中央仓库 所以需要自己本地安装 不在赘述

##特性：
* 使用yaml或者property配置fastdfs参数 
* 封装了普通上传方法，更简便 代码不再展示
* 封装了分片上传的细节 提供两个方法供分片上传 一个是初始化分片上传一个是分片上传
*  分片上传代码示例    
````     
        String uploadId = fastdnsUtil.init_chunck("test.png", map);
        fastdnsUtil.chunckUpload(uploadId,bytes, finalI,aDouble.intValue(),(r)->{
                              System.out.println(Thread.currentThread().getId()+"上传线程：结束");
                              System.out.println(r);
                          });
  ````    
# springboot使用示例 
* 引入依赖（需要本地安装）
* 配置文件录入
```
pps:
  netty:
    im:
      enable : true
      port: 9090
      websocketPath: /pps
  fastdfs:
    enable: true                         #开启自动配置
    racker_servers: 120.79.204.37:22122  #服务器地址 如果多个 逗号分隔
    urlPrefix: http://120.79.204.37:10000/ #访问地址前缀 可以为空
```
* 引入工具类
```
@Autowired
FastdfsUtil fastdfsUtil;
```
直接使用fastdfsUtil即可



