package com.lei.music.dao;

import com.lei.music.bean.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲dao
 */
@Mapper
@Repository
public interface SongMapper {

    //添加歌曲
    public Integer add(Song song);

    //修改歌曲
    public Integer update(Song song);

    //更新歌曲文件
    Integer updateSong(Song song);

    //更新歌曲图片文件
    Integer updateSongPic(Song song);

    //删除歌曲
    public Integer delete(Integer id);

    //查询歌曲
    public Song queryOne(Integer id);

    //查询所有歌曲
    public List<Song> queryAll();

    //根据歌曲名字精确查询歌曲
    public Song queryByName(String name);

    //根据歌手查询歌曲
    public List<Song> queryBySingerID(Integer id);

    //根据歌名模糊查询歌曲
    public List<Song> queryLikeOfName(String name);

}
