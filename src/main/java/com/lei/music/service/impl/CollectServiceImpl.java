package com.lei.music.service.impl;

import com.lei.music.bean.Collect;
import com.lei.music.dao.CollectMapper;
import com.lei.music.service.CollectSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectSevice {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Boolean add(Collect collect) {
        return collectMapper.add(collect)>0;
    }

    @Override
    public Boolean delete(Collect collect) {
        return collectMapper.delete(collect)>0;
    }

    @Override
    public List<Collect> queryAllByUserId(Integer id) {
        return collectMapper.queryAllByUserId(id);
    }

    @Override
    public Boolean isExist(Collect collect) {
        return collectMapper.isExist(collect)>0;
    }
}
