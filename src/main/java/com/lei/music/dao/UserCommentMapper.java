package com.lei.music.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 用户 评论表  mapper层
 */
@Mapper
@Repository
public interface UserCommentMapper {

    //查询一条记录，用来限制用户点赞次数
    public Integer queryOne(Integer userId,Integer commentId);

    //插入一条记录
    public Integer insert(Integer userId,Integer commentId);
}
