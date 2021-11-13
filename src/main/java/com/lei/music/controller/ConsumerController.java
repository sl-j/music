package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Consumer;
import com.lei.music.service.ConsumerService;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 用户controller层
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    //查询所有用户
    @GetMapping("/detail")
    public Object detail(){
        return consumerService.detail();
    }

    //添加用户
    @PostMapping("/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();


        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String introduction = request.getParameter("introduction");
        String location = request.getParameter("location");
        String avator = System.getProperty("file.separator")+"avatorImages"+System.getProperty("file.separator")+"hhh.jpg";
        Consumer consumer1 = consumerService.queryByUsername(userName);
        if(consumer1!=null){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"用户名已存在");
            return jsonObject;
        }



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

        Consumer consumer = new Consumer();

        consumer.setUsername(userName);
        consumer.setPassword(passWord);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);


        Boolean flag = consumerService.add(consumer);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"注册成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"注册失败");
        return jsonObject;
    }

    //更新用户头像
    @PostMapping("/updateAvator")
    public Object updateAvator(@RequestParam("id")Integer id, @RequestParam("file")MultipartFile multipartFile){
        JSONObject jsonObject = new JSONObject();

        //判断是否上传头像
        if(multipartFile.isEmpty()){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"未上传图片");
            return jsonObject;
        }

        //上传文件名为当时时间的毫秒+原始文件名，为了防止文件名重复的问题
        String fileName = System.currentTimeMillis() + multipartFile.getOriginalFilename();

        //存放的项目中的文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";

        //判断是否存在此路径，如果不存在则自己创建此文件
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件的实际位置
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        //获取存放在数据库中pic字段的路径值
        String picPathToData = "/avatorImages/" + fileName;
        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setAvator(picPathToData);


        try {
            multipartFile.transferTo(dest);

            //删除原图片
            Consumer consumer1 = consumerService.queryOne(id);
            String avator = consumer1.getAvator();
            File oFile = new File(System.getProperty("user.dir") +avator);
            boolean delete = oFile.delete();
            System.out.println(delete);


            Boolean flag = consumerService.updateAvator(consumer);
            if(flag){
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"上传用户头像成功");
                jsonObject.put("userMsg",consumerService.queryOne(id));
                return jsonObject;
            }
            else {
                jsonObject.put(Consts.CODE,"0");
                jsonObject.put(Consts.MSG,"上传用户头像失败");
                return jsonObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return jsonObject;
        }

    }

    //根据id查询单个用户
    @GetMapping("/queryOne")
    public Object queryOne(@RequestParam("id")Integer id){
       return consumerService.queryOne(id);
    }

    //删除单个用户
    @DeleteMapping("/delete")
    public Object delete(@RequestParam("id")Integer id){
        return consumerService.delete(id);
    }

    //更新用户信息
    @PostMapping("/update")
    public Object update(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();


        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String introduction = request.getParameter("introduction");
        String location = request.getParameter("location");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Date birthDate = null;
        try {
            birthDate = simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Consumer consumer = new Consumer();

        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);

        Boolean flag = consumerService.update(consumer);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"编辑成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"编辑失败");
        return jsonObject;
    }

    //前端登录功能
    @PostMapping("/login")
    public Object login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session){
//        username = username.replace(" ","");
//        password = password.replace(" ","");
        JSONObject jsonObject = new JSONObject();
        if(username == ""){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password == ""){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        Boolean flag = consumerService.login(username, password);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"登录成功");
            jsonObject.put("userMsg",consumerService.queryByUsername(username));

            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
