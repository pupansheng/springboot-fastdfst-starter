package util;

import exception.ChunckInitException;
import exception.ChunckUploadException;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import property.UploadResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author
 * @discription;
 * @time 2020/12/31 11:38
 */
public class FastdfsUtil {
    private static final String SPLIT="/";
    String urlPrefix;

    public FastdfsUtil(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public static StorageClient1 getClient(){
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getTrackerServer();
            StorageServer storageServer = tracker.getStoreStorage(trackerServer);
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }

    }
    public static String getExtName(String fileName){
        String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
        return  fileExtName;
    }


    public static NameValuePair[] convertNameValuePairs(Map<String,String> metaDatas){

        int size = metaDatas.keySet().size();
        NameValuePair[] metaList = new NameValuePair[size];
        int i[] = new int[1];
        metaDatas.keySet().forEach(k -> {
            metaList[i[0]++] = new NameValuePair(k, metaDatas.get(k));
        });
        return metaList;

    }
    public static String [] convert(String fileId){
        return fileId.split(SPLIT);
    }
    public void setUrl(UploadResult uploadResult){
        if(urlPrefix!=null){
            uploadResult.setUrl(urlPrefix+uploadResult.getFileId());
        }
    }

    /**
     * 单个文件上传
     * @param inputStream
     * @param fileName
     * @param metaDatas
     * @return
     */
    public UploadResult uploadFile(InputStream inputStream, String fileName, Map<String,String> metaDatas){

        try {
            byte[] fileBuff = inputStream.readAllBytes();
            String fileId = "";
            String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
            //建立连接
            StorageClient1 client = getClient();
            //设置元信息
            int size = metaDatas.keySet().size();
            NameValuePair[] metaList = new NameValuePair[size];
            int i []=new int[1];
            metaDatas.keySet().forEach(k->{
               metaList[i[0]++]=new NameValuePair(k, metaDatas.get(k));
            });
            //上传文件，获得fileId
            fileId = client.upload_file1(fileBuff, fileExtName, metaList);
            UploadResult uploadResult = new UploadResult();
            setUrl(uploadResult);
            uploadResult.setFileId(fileId);
            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new  RuntimeException(e);
        }
    }
    /**
     * 初始化分片上传
     * @param fileName 文件名 形如：/group1/000xc/dl。。。。这种方式的
     * @return fileId  分片上传id
     * @throws ChunckInitException
     */
   public String init_chunck(String fileName,Map<String,String> metaDatas) throws ChunckInitException {

       StorageClient1 client = getClient();
       try {
           String[] strings = client.upload_appender_file(new byte[0], getExtName(fileName), convertNameValuePairs(metaDatas));
           return  strings[0]+SPLIT+strings[1];
       } catch (Exception e) {
           throw new ChunckInitException(fileName+"：分片上传初始化失败！");
       }

   }

    /**
     * 分片上传文件
     * @param fileId 分片上传id 由第一步初始化分片上传方法得到的id而得到
     * @param data  分片上传的数组字节数组 建议小于2M
     * @param pageNumber 分片上传的页数（从第1页开始）
     * @param pageSzie 分片上传的大小
     * @throws ChunckUploadException
     */
   public void  chunckUpload(String fileId, byte [] data,int pageNumber,int pageSzie) throws ChunckUploadException{
       StorageClient1 client = getClient();
       StorageClient.UploadBuff buff=new StorageClient.UploadBuff(data,0,data.length);
       long start=(pageNumber-1)*pageSzie;
       try {
           client.modify_file1(fileId,start, data.length,buff);
       } catch (Exception e) {
           e.printStackTrace();
           throw  new ChunckUploadException(fileId+":分片："+pageNumber+"上传失败！");
       }
   }
    /**
     * 分片上传文件 带有回调方法
     * @param fileId 分片上传id 由第一步初始化分片上传方法得到的id而得到
     * @param data  分片上传的数组字节数组 建议小于2M
     * @param pageNumber 分片上传的页数（从第1页开始）
     * @param pageSzie 分片上传的大小
     * @throws ChunckUploadException
     */
    public void  chunckUpload(String fileId, byte [] data, int pageNumber, int pageSzie, Consumer<UploadResult> callback) throws ChunckUploadException {
        StorageClient1 client = getClient();
        StorageClient.UploadBuff buff=new StorageClient.UploadBuff(data,0,data.length);
        long start=(pageNumber-1)*pageSzie;
        try {
            int i1 = client.modify_file1(fileId,start, data.length,buff);
            UploadResult uploadResult=new UploadResult();
            uploadResult.setFileId(fileId);
            uploadResult.setStatus(i1==0);
            uploadResult.setPageNumber(pageNumber);
            uploadResult.setPageSize(pageSzie);
            setUrl(uploadResult);
            callback.accept(uploadResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new ChunckUploadException(fileId+":分片："+pageNumber+"上传失败！");
        }
    }

    /**
     * 删除文件
     * @param fileId
     * @return
     * @throws IOException
     * @throws MyException
     */
    public boolean deleteFile(String fileId) throws IOException, MyException {
        StorageClient1 client = getClient();
        int i = client.delete_file1(fileId);
        return i==0;
    }

    /**
     * 下载文件 不提供分片下载 若需要分片下载请研究api
     * @param fileId
     * @return
     * @throws IOException
     * @throws MyException
     */
    public byte[]  downFile(String fileId) throws IOException, MyException {
        StorageClient1 client = getClient();
        byte[] bytes = client.download_file1(fileId);
        return bytes;
    }

}
