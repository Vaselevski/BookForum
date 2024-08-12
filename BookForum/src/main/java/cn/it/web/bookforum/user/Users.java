package cn.it.web.bookforum.user;

import java.sql.Timestamp;

public class Users {
    private int id;
    private String username;
    private String password;
    private Timestamp lastLoginTime;
    private Timestamp createAt;
    private boolean isAdmin;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public Timestamp getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public boolean GetIsAdmin() {
        return isAdmin;
    }
    @Override
    public String toString() {
        return "Users {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createAt=" + createAt +
                ", isAdmin=" + isAdmin +
                '}';
    }

}
