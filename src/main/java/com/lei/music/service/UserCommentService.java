package com.lei.music.service;

public interface UserCommentService {


    //查询一条记录，用来限制用户点赞次数
    public Boolean queryOne(Integer userId,Integer commentId);

    //插入一条记录
    public Boolean insert(Integer userId,Integer commentId);
}
