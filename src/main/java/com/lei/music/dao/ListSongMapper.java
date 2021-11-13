package com.lei.music.dao;

import com.lei.music.bean.ListSong;
import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单dao接口
 */
@Mapper
@Repository
public interface ListSongMapper {

    //查询歌单的所有歌曲
    public List<SongList> queryByListId(Integer id);

    //添加歌单歌曲
    public Integer add(ListSong listSong);

    //删除歌单歌曲
    public Integer delete(Integer songId,Integer songListId);
}
