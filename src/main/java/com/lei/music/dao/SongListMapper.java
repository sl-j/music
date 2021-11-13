package com.lei.music.dao;

import com.lei.music.bean.Singer;
import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongListMapper {
    //添加歌单
    public Integer add(SongList songList);

    //编辑歌单
    public Integer update(SongList songList);

    //删除歌单
    public Integer delete(Integer id);

    //查询所有歌单
    public List<SongList> queryAll();

    //查询一个歌单
    public SongList queryOne(Integer id);

    //更新歌单图片
    public Integer updateSongListPic(SongList songList);

    //根据歌单名称模糊查询歌单
    public List<SongList> queryByTitle(String title);

    //根据歌曲风格模糊查询歌单
    public List<SongList> queryByStyle(String style);

    //根据歌单id查询歌单内歌曲的id
    public List<Integer> querySongId(Integer id);
//
//    //根据性别查询歌手
//    public List<Singer> queryBySex(Byte sex);

}
