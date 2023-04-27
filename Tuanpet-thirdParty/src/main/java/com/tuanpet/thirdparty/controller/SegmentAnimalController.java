package com.tuanpet.thirdparty.controller;


import com.alibaba.nacos.common.utils.UuidUtils;
import com.aliyun.imageseg20191230.Client;
import com.aliyun.imageseg20191230.models.SegmentAnimalAdvanceRequest;
import com.aliyun.imageseg20191230.models.SegmentAnimalRequest;
import com.aliyun.imageseg20191230.models.SegmentAnimalResponse;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import io.lettuce.core.output.BooleanOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.aliyun.teautil.Common.assertAsString;

@RefreshScope
@RestController
@RequestMapping("thirdParty/segmentAnimal")
public class SegmentAnimalController {


    @Value("${alibaba.cloud.access-key}")
    String accessId;


    @Value("${alibaba.cloud.secret-key}")
    String accessKeySecret;


    @Value("${alibaba.cloud.oss.endpoint}")
    String endpointWithHttps;//




    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    // 这里只是以ocr为例，其他能力请使用相应类目的包下面的Client类
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 2、访问的域名。注意：这个地方需要求改为相应类目的域名，参考：https://help.aliyun.com/document_detail/143103.html
        config.endpoint = "imageseg.cn-shanghai.aliyuncs.com";
        // 3、这里只是以ocr为例，其他能力请使用相应类目的包下面的Client类
        return new Client(config);
    }

    @GetMapping("/getImageMatting")
    public String GetImageMatting(String petPhoto) throws Exception {

        Client client = SegmentAnimalController.createClient(accessId, accessKeySecret);
        // 场景一，使用本地文件
        // InputStream inputStream = new FileInputStream(new File("/tmp/bankCard.png"));
        // 场景二，使用任意可访问的url
        URL url = new URL("https://tuanpet.oss-cn-guangzhou.aliyuncs.com/Snipaste_2023-03-26_21-57-27.png");
        InputStream inputStream = url.openConnection().getInputStream();

        SegmentAnimalAdvanceRequest SegmentAnimalAdvanceRequest = new SegmentAnimalAdvanceRequest()
                .setImageURLObject(inputStream);
        RuntimeOptions runtime = new RuntimeOptions();
        String fileString=null;
        try {
            SegmentAnimalResponse resp = client.segmentAnimalAdvance(SegmentAnimalAdvanceRequest, runtime);
            // 获取整体结果。部分能力会输出url链接，通过toJSONString转换后可能有编码问题，但是通过单个字段获取是没问题的。
            System.out.println(toJSONString(TeaModel.buildMap(resp)));

            String originUrl=resp.getBody().getData().getImageURL();
            fileString = uploadFileByServer(originUrl);

        } catch (TeaException teaException) {
            // 获取整体报错信息
            System.out.println(toJSONString(teaException));
            // 获取单个字段
            System.out.println(teaException.getCode());
        }
        return fileString;
    }

    /**
     * 从服务器上传图片
     * @param url
     * @return
     */
    public String uploadFileByServer(String url){
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "tuanpet";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //拼接路径
        String objectName = format+"/"+ UUID.randomUUID()+".png";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKeySecret);
        String returnString=null;
        try {
            InputStream inputStream = new URL(url).openStream();
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
            returnString="https://"+bucketName+"."+endpointWithHttps+"/"+objectName;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return returnString;

    }



}
