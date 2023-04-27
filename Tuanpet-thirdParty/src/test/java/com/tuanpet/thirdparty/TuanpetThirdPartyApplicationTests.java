package com.tuanpet.thirdparty;

import com.aliyun.imageseg20191230.Client;
import com.aliyun.imageseg20191230.models.SegmentAnimalRequest;
import com.aliyun.imageseg20191230.models.SegmentAnimalResponse;
import com.aliyun.imageseg20191230.models.SegmentAnimalAdvanceRequest;
import com.aliyun.imageseg20191230.models.SegmentAnimalResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.net.URL;

import static com.alibaba.fastjson.JSON.toJSONString;


@SpringBootTest
class TuanpetThirdPartyApplicationTests {

    static String accessId="LTAI5tME3PWYNuA45TQoLjrd";

    static String accessKeySecret="KeUZ4vexmN1uIaILhgXknISORqEOqH";

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

    public static void main(String[] args) throws Exception {

        Client client = createClient(accessId, accessKeySecret);
        // 场景一，使用本地文件
        // InputStream inputStream = new FileInputStream(new File("/tmp/bankCard.png"));
        // 场景二，使用任意可访问的url
        URL url = new URL("https://tuanpet.oss-cn-guangzhou.aliyuncs.com/Snipaste_2023-03-26_21-57-27.png");
        InputStream inputStream = url.openConnection().getInputStream();

        SegmentAnimalAdvanceRequest SegmentAnimalAdvanceRequest = new SegmentAnimalAdvanceRequest()
                .setImageURLObject(inputStream);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            SegmentAnimalResponse resp = client.segmentAnimalAdvance(SegmentAnimalAdvanceRequest, runtime);
            // 获取整体结果。部分能力会输出url链接，通过toJSONString转换后可能有编码问题，但是通过单个字段获取是没问题的。
            System.out.println(toJSONString(TeaModel.buildMap(resp)));
            // 获取单个字段，这里只是一个例子，具体能力下的字段需要看具体能力的文档
            System.out.println(resp.getBody().getData().getImageURL());
        } catch (TeaException teaException) {
            // 获取整体报错信息
            System.out.println(toJSONString(teaException));
            // 获取单个字段
            System.out.println(teaException.getCode());
        }
    }

}
