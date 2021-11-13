package com.lei.music.service.impl;

import com.lei.music.dao.UserCommentMapper;
import com.lei.music.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Autowired
    private UserCommentMapper userCommentMapper;

    /**
     * 查询单单条记录
     * @param userId
     * @param commentId
     * @return
     */
    @Override
    public Boolean queryOne(Integer userId, Integer commentId) {
        return userCommentMapper.queryOne(userId, commentId)>0;
    }

    @Override
    public Boolean insert(Integer userId, Integer commentId) {
        return userCommentMapper.insert(userId, commentId)>0;
    }
}
