package configure;

import org.csource.fastdfs.ClientGlobal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import property.FastDfsProperty;
import util.FastdfsUtil;

/**
 * @author pps
 * @discription;
 * @time 2020/12/31 11:12
 */
@EnableConfigurationProperties({FastDfsProperty.class})//注入该类进入容器
@ConditionalOnProperty(prefix = "pps.fastdfs",value = "enable",havingValue = "true")
public class FastDfsAutoConfigure implements InitializingBean {

    @Autowired
    FastDfsProperty fastDfsProperty;

    @Bean
    public FastdfsUtil fastdfsUtil(){
        return  new FastdfsUtil(fastDfsProperty.getUrlPrefix());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化 fastdns 配置-------------");
        ClientGlobal.init(fastDfsProperty);
        System.out.println("初始化 fastdns 配置完成-------------");
    }
}
