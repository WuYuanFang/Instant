package com.kevin.instant.controller;

import com.kevin.instant.core.annotation.PassToken;
import com.kevin.instant.core.exception.DKException;
import com.kevin.instant.core.response.Response;
import com.kevin.instant.util.FileUploadUtils;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @program: Instant
 * @create: 2020-04-27 20:10
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@RestController
public class FileController {

    @PassToken
    @PostMapping("/uploadPic")
    public Response uploadPic(@RequestParam("picture") MultipartFile file) throws Exception {
        String fileName = FileUploadUtils.uploadPicByDefaultDic(file);
        return Response.getSuccessResult("上传成功", fileName);
    }

    @PassToken
    @GetMapping(value = "/showPic", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> showPic(@RequestParam("url") String url, HttpServletResponse response) throws FileNotFoundException, DKException {
        url = "/Users/yuanfangwu/XiXi/Work/Download/" + url;
        File file = new File(url);
        // 判断文件是否存在如果不存在就返回默认图片或者进行异常处理
        if ((!file.exists()) || (!file.canRead())) {
            throw new DKException("文件不存在");
        }
        InputStream inputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);

    }

    @PassToken
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam("url") String url, HttpServletResponse response) {
        FileUploadUtils.downloadFile(url, response);
    }

}
