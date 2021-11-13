package com.lei.music.dao;

import com.lei.music.bean.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao层
 */
@Mapper
@Repository
public interface ConsumerMapper {

    //查询所有用户
    public List<Consumer> detail();

    //添加新用户
    public Integer add(Consumer consumer);

    //更新用户头像
    public Integer updateAvator(Consumer consumer);

    //更新用户信息
    public Integer update(Consumer consumer);

    //根据id查询单个用户
    public Consumer queryOne(Integer id);

    //删除单个用户
    public Integer delete(Integer id);

    //前端登录功能
    public Integer login(String username,String password);

    //根据用户名查询用户信息（用户名唯一）
    public Consumer queryByUsername(String username);


}
