package com.lei.music.service;

import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;

import java.util.List;

/**
 * 歌曲service层
 */
public interface SongService {

    //添加歌曲
    public Boolean add(Song song);

    //修改歌曲
    public Boolean update(Song song);

    //删除歌曲
    public Boolean delete(Integer id);

    //查询歌曲
    public Song queryOne(Integer id);

    //查询所有歌曲
    public List<Song> queryAll();

    //根据歌曲名字模糊查询歌曲
    public Song queryByName(String name);

    //根据歌手查询歌曲
    public List<Song> queryBySingerID(Integer id);

    //更新歌曲文件
    Boolean updateSong(Song song);

    //更新歌曲图片
    Boolean updateSongPic(Song song);


    //根据歌名模糊查询歌曲
    public List<Song> queryLikeOfName(String name);
}
