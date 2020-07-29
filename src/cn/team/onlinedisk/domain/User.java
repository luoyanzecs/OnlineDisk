package cn.team.onlinedisk.domain;

/**
 * @ClassName User
 * @Description 用户信息{@code username password tel}
 * @Author luoyanze
 * @Date 2020/7/29 9:25 下午
 * @Version 1.0
 */


public class User {
    private String username;
    private String password;
    private String tel;

    public User(String username, String password, String tel) {
        this.username = username;
        this.password = password;
        this.tel = tel;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                '}';
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
