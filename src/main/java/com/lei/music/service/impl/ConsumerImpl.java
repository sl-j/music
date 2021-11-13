package com.lei.music.service.impl;

import com.lei.music.bean.Consumer;
import com.lei.music.dao.ConsumerMapper;
import com.lei.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;
    @Override
    public List<Consumer> detail() {
        return consumerMapper.detail();
    }

    @Override
    public Boolean add(Consumer consumer) {
        return consumerMapper.add(consumer)>0;
    }

    @Override
    public Boolean updateAvator(Consumer consumer) {
        return consumerMapper.updateAvator(consumer)>0;
    }

    @Override
    public Consumer queryOne(Integer id) {
        return consumerMapper.queryOne(id);
    }

    @Override
    public Boolean delete(Integer id) {
        return consumerMapper.delete(id)>0;
    }

    @Override
    public Boolean update(Consumer consumer) {
        return consumerMapper.update(consumer)>0;
    }

    @Override
    public Boolean login(String username, String password) {
        return consumerMapper.login(username,password)>0;
    }

    @Override
    public Consumer queryByUsername(String username) {
        return consumerMapper.queryByUsername(username);
    }
}
