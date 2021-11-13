package com.lei.music.service.impl;

import com.lei.music.bean.Singer;
import com.lei.music.dao.SingerMapper;
import com.lei.music.service.SingerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手server实现类
 */
@Service
public class SingerServerImpl implements SingerServer {
    @Autowired
    private SingerMapper singerMapper;
    @Override
    public boolean add(Singer singer) {    //添加歌手
        return singerMapper.add(singer)>0;
    }

    @Override
    public boolean update(Singer singer) {    //修改歌手
        return singerMapper.update(singer)>0;
    }

    @Override
    public boolean delete(Integer id) {    //删除歌手
        return singerMapper.delete(id)>0;
    }

    @Override
    public Singer queryOne(Integer id) {    //查询一个歌手
        return singerMapper.queryOne(id);
    }

    @Override
    public List<Singer> queryAll() {    //查询所有歌手
        return singerMapper.queryAll();
    }

    @Override
    public List<Singer> queryByName(String name) {    //根据姓名模糊查询歌手
        return singerMapper.queryByName(name);
    }

    @Override
    public List<Singer> queryBySex(Byte sex) {
        return singerMapper.queryBySex(sex);
    }
}
