package property;

/**
 * @author
 * @discription;
 * @time 2020/12/31 11:46
 */
public class UploadResult {
    private String url;
    private String fileId;
    private boolean status;
    private int pageSize;
    private int pageNumber;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "url='" + url + '\'' +
                ", fileId='" + fileId + '\'' +
                ", status=" + status +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
