package cn.team.onlinedisk.domain;

import cn.team.onlinedisk.service.UserFileInfoService;
import cn.team.onlinedisk.service.impl.UserFileInfoServiceImpl;

import java.io.File;

/**
 * @ClassName UserFileInfo
 * @Description 用于保存从web发来的request请求的文件信息,
 * @Author luoyanze
 * @Date 2020/7/29 9:39 下午
 * @Version 1.0
 */


public class UserFileInfo {
    private String username;
    private String filename;
    private File file;

    public UserFileInfo(User user, String filename) {
        UserFileInfoServiceImpl userFileInfoService = new UserFileInfoServiceImpl();
        String filePath = userFileInfoService.getFilePath(filename, user);
        File file = new File(filePath);
        int count = 1;
        while (file.exists()){
            filename = count == 1 ?
                    filename.replaceAll("[.]", "(" + (count++) + ").")
                    :filename.replaceAll("[(][0-9][)][.]", "(" + (count++) + ").");
            filePath = userFileInfoService.getFilePath(filename, user);
            file = new File(filePath);
        }
        this.username = user.getUsername();
        this.filename = filename;
        this.file = file;
    }

    public UserFileInfo(String username, String filename, File file) {
        this.username = username;
        this.filename = filename;
        this .file = file;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
