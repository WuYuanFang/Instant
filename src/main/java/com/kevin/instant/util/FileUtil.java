package com.kevin.instant.util;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @program: Instant
 * @create: 2020-04-21 19:23
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

public class FileUtil {

    public static String getLocationPath() {
        String locationPath = "";
        try {
            locationPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return locationPath;
    }

    /**
     * 读取指定路径的文件，输出为byte[]
     *
     * @param locationPath
     * @param filePath 文件路径
     * @return 文件byte[]
     */
    public static byte[] getByteByPath(String locationPath, String filePath){
        byte[] buffer = null;
        try(FileInputStream fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024)) {
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 把byte[]文件流，指定路径和文件名保存
     * @param bfile 保存的文件流
     * @param filePath 保存路径
     * @param fileName 保存文件
     */
    public static void saveFile(byte[] bfile, String filePath,String fileName) {
        File dir = new File(filePath);
        if(!dir.exists()){//判断文件目录是否存在
            dir.mkdirs();
        }

        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(filePath + fileName)))) {
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
