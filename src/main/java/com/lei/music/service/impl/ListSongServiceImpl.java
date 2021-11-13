package com.lei.music.service.impl;

import com.lei.music.bean.ListSong;
import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;
import com.lei.music.dao.ListSongMapper;
import com.lei.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    //查询歌单的所有歌曲
    @Override
    public List<SongList> queryByListId(Integer id) {
        return listSongMapper.queryByListId(id);
    }

    @Override
    public Boolean add(ListSong listSong) {
        return listSongMapper.add(listSong)>0;
    }

    @Override
    public Boolean delete(Integer songId,Integer songListId) {
        return listSongMapper.delete(songId,songListId)>0;
    }
}
