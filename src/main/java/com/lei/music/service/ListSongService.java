package com.lei.music.service;

import com.lei.music.bean.ListSong;
import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;

import java.util.List;

public interface ListSongService {

    //查询歌单的所有歌曲
    public List<SongList> queryByListId(Integer id);

    //添加歌单歌曲
    public Boolean add(ListSong listSong);

    //删除歌单歌曲
    public Boolean delete(Integer songId,Integer songListId);
}
