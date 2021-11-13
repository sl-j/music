package com.lei.music.dao;

import com.lei.music.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    //添加评论
    public Integer add(Comment comment);

    //查询所有评论
    public List<Comment> queryAll(Byte type, Integer id);

    //更新
    public Integer update(Comment comment);

    //查询歌单的评论
    public List<Comment> queryComment(Integer id);

    //删除某个评论
    public Integer delete(Integer id);


}
