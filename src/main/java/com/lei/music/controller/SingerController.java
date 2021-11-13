package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Singer;
import com.lei.music.bean.Song;
import com.lei.music.service.SingerServer;
import com.lei.music.util.Consts;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerServer singerServer;


    //添加歌手
    @PostMapping("/add")
    public Object add(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String pic = "/img/singerPic/hhh.jpg";
        String birth = request.getParameter("birth");
        String location = request.getParameter("location");
        String introduction = request.getParameter("introduction");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        /**
         * 日期格式化
         */
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        boolean flag = singerServer.add(singer);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }


    //修改歌手
    @PostMapping("/update")
    public Object update(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String location = request.getParameter("location");
        String introduction = request.getParameter("introduction");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        /**
         * 日期格式化
         */
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        boolean flag = singerServer.update(singer);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    //删除歌手
    @DeleteMapping("/delete")
    public Object delete(Integer id) {
        JSONObject jsonObject = new JSONObject();


        boolean flag = singerServer.delete(id);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    //查询一个歌手
    @GetMapping("/queryOne")
    public Object queryOne(Integer id) {
        Singer singer = singerServer.queryOne(id);
        return singer;
    }

    //查询所有歌手
    @GetMapping("/queryAll")
    public Object queryOne() {
        List<Singer> singers = singerServer.queryAll();
        return singers;
    }

    //根据姓名模糊拆查询歌手
    @GetMapping("/queryByName")
    public Object queryOne(String name) {
        List<Singer> singers = singerServer.queryByName("%"+name+"%");
        return singers;
    }

    //根据性别查询歌手
    @GetMapping("/queryBySex")
    public Object queryBySex(@RequestParam("sex")String sex){
        return singerServer.queryBySex(new Byte(sex));

    }

    //更新歌手图片
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("id") Integer id,@RequestParam("file") MultipartFile multipartFile){ //MultipartFile专用来上传文件

        JSONObject jsonObject = new JSONObject();

        //判断是否上传图片
        if(multipartFile.isEmpty()){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"未上传图片");
            return jsonObject;
        }

        //上传文件名为当时时间的毫秒+原始文件名，为了防止文件名重复的问题
        String fileName = System.currentTimeMillis() + multipartFile.getOriginalFilename();

        //存放的项目中的文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                System.getProperty("file.separator")+"singerPic";

        //判断是否存在此路径，如果不存在则自己创建此文件
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件的实际位置
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        //获取存放在数据库中pic字段的路径值
        String picPathToData = "/img/singerPic/" + fileName;

        try {

            //将文件存放在实际路径中
            multipartFile.transferTo(dest);

            //更新歌手图片时，删除原歌手图片
            Singer singer1 = singerServer.queryOne(id);
            String url = singer1.getPic();
            File oFile = new File(System.getProperty("user.dir") + url);
            boolean delete = oFile.delete();


            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(picPathToData);
            boolean flag = singerServer.update(singer);
            if(flag){
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"上传图片成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"上传图片失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"上传图片失败:"+e.getMessage());
            return jsonObject;
        }
    }


}
