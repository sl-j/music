package com.lei.music.service.impl;

import com.lei.music.dao.AdminMapper;
import com.lei.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* 管理员service接口实现类
* */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    //验证密码是否正确

    @Override
    public Boolean verityPassword(String name, String password) {
        return adminMapper.verityPassword(name, password)>0;
    }
}
