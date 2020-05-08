package server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserProfile {
    private final DateFormat df = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
    private String username;
    private String uuid;
    private String ip;
    private long loginTime;
    private long lastSeenTime;

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", uuid='" + uuid + '\'' +
                ", ip='" + ip + '\'' +
                ", loginAtTime=" + df.format(loginTime) +
                ", lastSeenTime=" + df.format(lastSeenTime) +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(long lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

}
