package cn.team.onlinedisk.domain;

/**
 * @ClassName FileInfo
 * @Description 该类用来保存用户对应的文件表的信息。
 * @Author luoyanze
 * @Date 2020/7/30 8:52 下午
 * @Version 1.0
 */


public class FileInfo {
    private String filename;
    private String filename_encryption;

    public FileInfo() {
    }

    public FileInfo(String filename, String filename_encryption) {
        this.filename = filename;
        this.filename_encryption = filename_encryption;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filename='" + filename + '\'' +
                ", filename_encryption='" + filename_encryption + '\'' +
                '}';
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename_encryption() {
        return filename_encryption;
    }

    public void setFilename_encryption(String filename_encryption) {
        this.filename_encryption = filename_encryption;
    }
}
