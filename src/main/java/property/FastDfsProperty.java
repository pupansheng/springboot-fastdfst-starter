package property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author pps
 * @discription;
 * @time 2020/12/31 11:04
 */
@Configuration
@ConfigurationProperties("pps.fastdfs")
public class FastDfsProperty {


    private int DEFAULT_CONNECT_TIMEOUT = 5; //second
    private int DEFAULT_NETWORK_TIMEOUT = 30; //second
    private String DEFAULT_CHARSET = "UTF-8";
    private boolean DEFAULT_HTTP_ANTI_STEAL_TOKEN = false;
    private String DEFAULT_HTTP_SECRET_KEY = "FastDFS1234567890";
    private int DEFAULT_HTTP_TRACKER_HTTP_PORT = 80;

    private boolean DEFAULT_CONNECTION_POOL_ENABLED = true;
    private int DEFAULT_CONNECTION_POOL_MAX_COUNT_PER_ENTRY = 100;
    private int DEFAULT_CONNECTION_POOL_MAX_IDLE_TIME = 3600 ;//second
    private int DEFAULT_CONNECTION_POOL_MAX_WAIT_TIME_IN_MS = 1000 ;//millisecond

   private int onnect_timeout = DEFAULT_CONNECT_TIMEOUT * 1000; //millisecond
   private int network_timeout = DEFAULT_NETWORK_TIMEOUT * 1000; //millisecond
   private String charset = DEFAULT_CHARSET;
   private boolean anti_steal_token = DEFAULT_HTTP_ANTI_STEAL_TOKEN; //if anti-steal token
   private String secret_key = DEFAULT_HTTP_SECRET_KEY; //generage token secret key
   private int tracker_http_port = DEFAULT_HTTP_TRACKER_HTTP_PORT;

   private boolean connection_pool_enabled = DEFAULT_CONNECTION_POOL_ENABLED;
   private int connection_pool_max_count_per_entry = DEFAULT_CONNECTION_POOL_MAX_COUNT_PER_ENTRY;
   private int connection_pool_max_idle_time = DEFAULT_CONNECTION_POOL_MAX_IDLE_TIME * 1000; //millisecond
   private int connection_pool_max_wait_time_in_ms = DEFAULT_CONNECTION_POOL_MAX_WAIT_TIME_IN_MS; //millisecond
   private String  racker_servers;
   private String urlPrefix;
   private boolean enable;
    public int getOnnect_timeout() {
        return onnect_timeout;
    }

    public void setOnnect_timeout(int onnect_timeout) {
        this.onnect_timeout = onnect_timeout;
    }

    public int getNetwork_timeout() {
        return network_timeout;
    }

    public void setNetwork_timeout(int network_timeout) {
        this.network_timeout = network_timeout;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isAnti_steal_token() {
        return anti_steal_token;
    }

    public void setAnti_steal_token(boolean anti_steal_token) {
        this.anti_steal_token = anti_steal_token;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public int getTracker_http_port() {
        return tracker_http_port;
    }

    public void setTracker_http_port(int tracker_http_port) {
        this.tracker_http_port = tracker_http_port;
    }

    public boolean isConnection_pool_enabled() {
        return connection_pool_enabled;
    }

    public void setConnection_pool_enabled(boolean connection_pool_enabled) {
        this.connection_pool_enabled = connection_pool_enabled;
    }

    public int getConnection_pool_max_count_per_entry() {
        return connection_pool_max_count_per_entry;
    }

    public void setConnection_pool_max_count_per_entry(int connection_pool_max_count_per_entry) {
        this.connection_pool_max_count_per_entry = connection_pool_max_count_per_entry;
    }

    public int getConnection_pool_max_idle_time() {
        return connection_pool_max_idle_time;
    }

    public void setConnection_pool_max_idle_time(int connection_pool_max_idle_time) {
        this.connection_pool_max_idle_time = connection_pool_max_idle_time;
    }

    public int getConnection_pool_max_wait_time_in_ms() {
        return connection_pool_max_wait_time_in_ms;
    }

    public void setConnection_pool_max_wait_time_in_ms(int connection_pool_max_wait_time_in_ms) {
        this.connection_pool_max_wait_time_in_ms = connection_pool_max_wait_time_in_ms;
    }

    public String getRacker_servers() {
        return racker_servers;
    }

    public void setRacker_servers(String racker_servers) {
        this.racker_servers = racker_servers;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
