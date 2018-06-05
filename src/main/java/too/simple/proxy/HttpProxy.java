package too.simple.proxy;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class HttpProxy implements Serializable {

    private static final long serialVersionUID = -2283119947346223109L;
    private String scheme = "http";
    private String username;
    private String password;
    private String ip;
    private int port;

    public HttpProxy() {
        super();
    }

    public HttpProxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public HttpProxy(String username, String password, String ip, int port) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public boolean validate() {
        if (StringUtils.isEmpty(ip)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HttpProxy{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

}
