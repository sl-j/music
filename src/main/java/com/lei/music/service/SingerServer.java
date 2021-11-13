package com.lei.music.service;

import com.lei.music.bean.Singer;

import java.util.List;

/**
 * 歌手server接口类
 */
public interface SingerServer {
    //添加歌手
    public boolean add(Singer singer);

    //修改歌手
    public boolean update(Singer singer);

    //删除歌手
    public boolean delete(Integer id);

    //查询歌手
    public Singer queryOne(Integer id);

    //查询所有歌手
    public List<Singer> queryAll();

    //根据姓名模糊查询歌手
    public List<Singer> queryByName(String name);

    //根据性别查询歌手
    public List<Singer> queryBySex(Byte sex);
}
