package com.gas.util;

import cn.jiguang.common.utils.StringUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ProjectName: Beauty_Salon
 * @Package: com.bs.util
 * @ClassName: OOS_Util
 * @Author: gwl
 * @Description:
 * @Date: 2021/4/30 16:00
 * @Version: 1.0
 */


public class OOS_Util {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static final String endpoint = "https://oss-cn-zhangjiakou.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static final String accessKeyId = "LTAI4GFR9SLSirMpwEgoGrDo";
    private static final String accessKeySecret = "Tsr5ADYk1c2ggRjVB4PO0yj7tq8H1u";
    private static final String bucketName = "lslongyu";
    private static final String urlPic = "https://www.lslongyu.com/";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //格式化时间

    public static OSSClient getOSSClient() {
        //创建一个OSSClient对象
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if (ossClient.doesBucketExist(bucketName)) {
            //System.out.println("success");
            //关闭oss的连接 此处不要关闭，后面会调用，不然会报错
        } else { //通过api接口来创建bucket
           // System.out.println("fail to find ,so to get a new one");
            //创建一个oss仓库
            CreateBucketRequest bucketRequest = new CreateBucketRequest(null);
            bucketRequest.setBucketName(bucketName);
            bucketRequest.setCannedACL(CannedAccessControlList.PublicRead); //设置访问权限 --公共读
            ossClient.createBucket(bucketRequest); //映射原理，会重新生成一个ossClient并覆盖当前的ossClient
        }
        return ossClient;
    }

    /**
     * 上传文件
     *
     * @param multipartFile 需要上传的文件
     * @param fileType      文件类型
     * @return
     */
    public static String uploadDocuments(MultipartFile multipartFile, String fileType) throws IOException {
        //获取oss连接
        OSSClient ossClient = OOS_Util.getOSSClient();
        //获取文件的后缀名称
        String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fileName = getFileName(fileType, ext);
        String url = null;
        //通过ossclient获取上传文件后返回的url
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(multipartFile.getBytes()));
        url = urlPic + fileName;
        //System.out.println("------>>上传文件成功，url：" + url);
        ossClient.shutdown();
        return url;
    }

    private static String getFileName(String fileType, String ext) {
        //上传至oss的那个文件夹 通过filename来指定 生成规则 // www.liangye123.com/img/20200814/jdg.png
        String date = sdf.format(new Date());
        if (StringUtils.isEmpty(fileType)) {
            fileType = "default";
        }
        //为了避免图片重名，使用UUID来命名图片
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //组合filename
        String filename = fileType + "/" +date + "/" + uuid + ext;
        return filename;
    }


    /*public static void main(String[] args) throws IOException {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "003.png";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        //String content = "Hello OSS";

        //File file = new  File("F:\\华研工作\\墨刀素材\\001.png");
        //byte[] fileByte = Files.readAllBytes(file.toPath());

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("F:\\华研工作\\墨刀素材\\001.png");

        PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName,inputStream);
        System.err.println(putObjectResult.toString());

        // 关闭OSSClient。
        ossClient.shutdown();

    }*/

    public static String uploadPic(MultipartFile file) throws IOException {
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "xxx.png";
        objectName = UUID.randomUUID().toString();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, file.getInputStream());
            return urlPic + "bs/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return null;
    }

    public static String deleteimg(String pic_name) {
        // <yourObjectName>表示删除OSS文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        //String objectName = "aaa.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除文件。
            ossClient.deleteObject(bucketName, pic_name);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return "fail";
    }

    public static void main(String[] args) {
        String deleteimg = deleteimg("sp/20210503/b744f6f31ea945ceaa013a402f01bb08.jpg");
        System.out.println(deleteimg);
    }
}
