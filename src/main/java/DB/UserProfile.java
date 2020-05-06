package DB;

public class UserProfile {
    private String username;
    private String ip;
    private long loginAtTime;
    private long lastSeenTime;

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", loginAtTime=" + loginAtTime +
                ", lastSeenTime=" + lastSeenTime +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getLoginAtTime() {
        return loginAtTime;
    }

    public void setLoginAtTime(long loginAtTime) {
        this.loginAtTime = loginAtTime;
    }

    public long getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(long lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }


}
