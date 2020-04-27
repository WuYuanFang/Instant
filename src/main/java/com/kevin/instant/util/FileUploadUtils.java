package com.kevin.instant.util;

import com.kevin.instant.config.WebConfig;
import com.kevin.instant.core.exception.DKException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @program: Instant
 * @create: 2020-04-26 23:03
 * @author: WYF
 * @description: 文件上传工具类
 * @version: 1.0
 **/

public class FileUploadUtils {

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = WebConfig.getProfile();

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    public static String uploadPicByDefaultDic(MultipartFile file) throws Exception {
        try {
            return uploadPicture("/Users/yuanfangwu/XiXi/Work/Download/", file);
        }catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadPicture(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadText(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, ".txt");
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir   相对应用的基目录
     * @param file      上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static final String  upload(String baseDir, MultipartFile file, String extension) throws IOException, RuntimeException{

        int fileNameLenght = file.getOriginalFilename().length();
        if (fileNameLenght > DEFAULT_FILE_NAME_LENGTH) {
            throw new DKException("文件名长度超过限定长度");
        }
        // 检查文件大小
        assertAllowed(file);

        String fileName = extractFilename(file, extension);
        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        file.transferTo(desc);
        return fileName;

    }

    public static String extractFilename(MultipartFile file, String extension) {
        String filename = file.getOriginalFilename();
        filename = DateUtil.datePath() + "/" + encodingFileName(filename) + extension;
        return filename;
    }

    private static File getAbsoluteFile(String uploadDir, String filename) throws IOException, RuntimeException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            if (!desc.createNewFile()) {
                throw new DKException("文件创建失败");
            }
        }
        return desc;
    }

    /**
     * 文件名进行md5编码
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    private static String encodingFileName(String fileName) {
        fileName = fileName.replace("-", "");
        fileName = MD5.md5(fileName + System.nanoTime() + counter++, "UTF-8");
        return fileName;
    }

    /**
     * 校验文件的大小
     * @param file 文件
     * @throws RuntimeException
     */
    public static void assertAllowed(MultipartFile file) throws RuntimeException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            // 此处进行异常处理
            System.out.println("文件大小超出最大限定大小");
            throw new DKException("文件大小不能超过50M");
        }
    }

    /**
     * 文件下载
     * @param url
     * @param response
     */
    public static void downloadFile(String url, HttpServletResponse response) {
        url = "/Users/yuanfangwu/XiXi/Work/Download/" + url;
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就进行异常处理
        if (!(file.exists() && file.canRead())) {
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", file.getName()));
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
