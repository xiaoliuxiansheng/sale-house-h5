package com.example.wxgzh.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class PictureUtil {
    /**
     *
     * @param file 文件
     * @param pathRoot 应用路径
     */
    public static String create(MultipartFile file, String pathRoot) throws IOException {
        String path="";
        if (!file.isEmpty()) {
            //生成uuid作为文件名称
            String uuid = UUID.random32();
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名称
            String imageName = contentType.substring(contentType.indexOf("/") + 1);
            path = "f:/picture/" + uuid + "." + imageName;
            file.transferTo(new File(path));
            return uuid;
        }
        return null;
    }

    /**
     * 迭代删除文件夹
     * @param dirPath 文件夹路径
     */
    public static void deleteDir(String dirPath)
    {
        File file = new File(dirPath);
        if(file.isFile())
        {
            file.delete();
        }else
        {
            File[] files = file.listFiles();
            if(files == null)
            {
                file.delete();
            }else
            {
                for (int i = 0; i < files.length; i++)
                {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

}
