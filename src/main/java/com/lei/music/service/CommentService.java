package com.lei.music.service;

import com.lei.music.bean.Comment;

import java.util.List;

public interface CommentService {

    //添加评论
    public Boolean add(Comment comment);

    //查询所有评论
    public List<Comment> queryAll(Byte type,Integer id);

    //更新
    public Boolean update(Comment comment);

    //查询歌单的评论
    public List<Comment> queryComment(Integer id);

    //删除某个评论
    public Boolean delete(Integer id);
}
