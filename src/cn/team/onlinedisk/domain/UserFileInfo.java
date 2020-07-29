package cn.team.onlinedisk.domain;

/**
 * @ClassName UserFileInfo
 * @Description 用于保存从web发来的request请求的文件信息
 * @Author luoyanze
 * @Date 2020/7/29 9:39 下午
 * @Version 1.0
 */


public class UserFileInfo {
    private String username;
    private String filename;

    public UserFileInfo(String username, String filename) {
        this.username = username;
        this.filename = filename;
    }

    public UserFileInfo() {
    }

    @Override
    public String toString() {
        return "UserFileInfo{" +
                "username='" + username + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
