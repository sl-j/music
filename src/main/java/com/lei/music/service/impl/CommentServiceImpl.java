package com.lei.music.service.impl;


import com.lei.music.bean.Comment;
import com.lei.music.dao.CommentMapper;
import com.lei.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    //添加评论
    @Override
    public Boolean add(Comment comment) {
        return commentMapper.add(comment)>0;
    }

    //查询所有评论
    @Override
    public List<Comment> queryAll(Byte type, Integer id) {
        return commentMapper.queryAll(type, id);
    }

    //更新某一个评论
    @Override
    public Boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    @Override
    public List<Comment> queryComment(Integer id) {
        return commentMapper.queryComment(id);
    }

    @Override
    public Boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }


}
