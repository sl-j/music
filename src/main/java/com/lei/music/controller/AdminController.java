package com.lei.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.lei.music.service.AdminService;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
* 管理员controller类
* */
@RestController()
public class AdminController {

    @Autowired
   private AdminService adminService;

    /*
    * 验证密码是否正确，正确则返回1，错误则返回0
    *     * */
    @PostMapping("/admin/login/status")
    public Object verityPassword(HttpServletRequest request, HttpSession session) {

        JSONObject jsonObject = new JSONObject();

        /*
        * 获取前端传来的用户名和密码
        * */
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Boolean flag = adminService.verityPassword(name, password);

        //登录成功则将用户名信息存入session中
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }

        //失败则返回失败信息给前端
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;

    }
}
