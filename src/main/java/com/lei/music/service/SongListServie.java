package com.lei.music.service;

import com.lei.music.bean.Singer;
import com.lei.music.bean.SongList;

import java.util.List;

public interface SongListServie {
    //添加歌单
    public Boolean add(SongList songList);

    //编辑歌单
    public Boolean update(SongList songList);

    //更新歌单图片
    public Boolean updateSongListPic(SongList songList);

    //删除歌单
    public Boolean delete(Integer id);

    //查询所有歌单
    public List<SongList> queryAll();

    //查询一个歌单
    public SongList queryOne(Integer id);

    //根据歌曲风格模糊查询歌单
    public List<SongList> queryByStyle(String style);

    //根据歌单名称模糊查询歌单
    public List<SongList> queryByTitle(String title);

    //根据歌单id查询歌单内歌曲的id
    public List<Integer> querySongId(Integer id);

}
