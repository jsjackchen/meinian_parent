package com.sgcc.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


public class QiNiuTest {
    public static void main(String[] args) {
        new QiNiuTest().testUploadFile();
    }

    public void testUploadFile(){
//构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());//华南
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "14GmkH_U1HmCwfUr1jWjVyCbrz87fD6LCI7oit3z";
        String secretKey = "hMeHmK-L5quXcrPA7EvywW93M2V-Y8suV43SOSSq";
        String bucket = "meiniansg";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png，可支持中文
        String localFilePath = "D:/1.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);// FspfyEyKfuHZ0kcbXRIc5T9YhCax
            System.out.println(putRet.hash);// FspfyEyKfuHZ0kcbXRIc5T9YhCax
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    public void testDeleteFile(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        String accessKey = "14GmkH_U1HmCwfUr1jWjVyCbrz87fD6LCI7oit3z";
        String secretKey = "hMeHmK-L5quXcrPA7EvywW93M2V-Y8suV43SOSSq";
        String bucket = "meiniansg";
        String key = "Fu3Ic6TV6wIbJt793yaGeBmCkzTX";//文件名称
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
