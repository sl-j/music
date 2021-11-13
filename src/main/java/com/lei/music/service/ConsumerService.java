package com.lei.music.service;

import com.lei.music.bean.Consumer;

import java.util.List;

public interface ConsumerService {

    //查询所有用户
    public List<Consumer> detail();

    //添加用户
    public Boolean add(Consumer consumer);

    //更新用户头像
    public Boolean updateAvator(Consumer consumer);

    //根据id查询单个用户
    public Consumer queryOne(Integer id);

    //删除单个用户
    public Boolean delete(Integer id);

    //更新用户信息
    public Boolean update(Consumer consumer);

    //前端登录功能
    public Boolean login(String username,String password);

    //根据用户名查询用户信息（用户名唯一）
    public Consumer queryByUsername(String username);
}
