package com.jiehuihui.media.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jiehuihui.common.entity.Images;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.media.mapper.ImagesMapper;
import com.jiehuihui.media.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/***
 *
 * @Author:shenkunlin
 * @Description:itheima
 * @date: 2019/3/6 17:55
 *  文件上传,上传到FastDFS
 ****/
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class UpdateImageController {

    @Autowired
    private ImagesMapper imagesMapper;

    //trackerserver地址
    @Value("${TRACKER_SERVER}")
    private String tracker_server;

    //文件上传拼接的域名
    @Value("${UPLOAD_IMAGE_DOMAIN}")
    private String upload_image_domain;


    /*****
     * 文件上传方法
     * URL：/upload.shtml    POST
     * 调用写好的FastDFS文件上传工具类方法实现文件上传
     * 拼接文件的路径，响应页面
     */
    @PostMapping(value = "/upload")
    public RResult upload(@RequestParam("file") MultipartFile file){
        RResult result = new RResult();

        try {
            //获取文件字节数组
            byte[] buffer = file.getBytes();
            if (buffer.length <= 0) {
                result.setMessage("上传的文件不能为空");
                return result;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(buffer);
            String hashString = new BigInteger(1, digest).toString(16);

//            LogUtil.intoLog(hashString);

            //判断图片的md5是否已经存在，如果已经存在，就不上传了，直接返回地址
            UpdateWrapper<Images> ew = new UpdateWrapper<>();
            ew.eq("md", hashString);

            List<Images> imagesList = imagesMapper.selectList(ew);
            if (imagesList.size() > 0) {
                result.changeToTrue(imagesList.get(0).getImgurl());
                return result;
            }


            //如果不存在，就上传完，然后插入数据库

            //文件后缀
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());

            //调用远程文件上传，传入到FastDFS
            String[] uploads = UploadUtil.upload(tracker_server, buffer, ext);
            //uploads[0]:文件组名
            //uploads[1]:文件存储路径
            //拼接文件访问路径
            //String url = "http://192.168.211.128/"+uploads[0]+"/"+uploads[1];
            String url = upload_image_domain+uploads[0]+"/"+uploads[1];
            if (uploads.length > 0 && !StringUtils.isEmpty(url)) {

                Images images = new Images();
                images.setImgname(file.getOriginalFilename());
                images.setImgurl(url);
                images.setPathurl(uploads[1]);
                images.setMd(hashString);
                images.setImgsize(file.getSize());
                images.setSsid(IdWorker.get32UUID());

                int insert = imagesMapper.insert(images);
                if (insert > 0) {
                    result.changeToTrue(url);
                }else{
                    result.setMessage("新增图片失败！");
                }
            }

        } catch (Exception e) {
            LogUtil.intoLog(4, this.getClass(), e.toString());
        }


        return result;
    }



}
