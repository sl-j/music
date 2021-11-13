package com.lei.music.dao;

import com.lei.music.bean.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手dao
 */
@Mapper
@Repository
public interface SingerMapper {

    //添加歌手
    public Integer add(Singer singer);

    //修改歌手
    public Integer update(Singer singer);

    //删除歌手
    public Integer delete(Integer id);

    //查询歌手
    public Singer queryOne(Integer id);

    //查询所有歌手
    public List<Singer> queryAll();

    //根据姓名模糊查询歌手
    public List<Singer> queryByName(String name);

    //根据性别查询歌手
    public List<Singer> queryBySex(Byte sex);

}
