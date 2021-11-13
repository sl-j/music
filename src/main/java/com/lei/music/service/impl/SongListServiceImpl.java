package com.lei.music.service.impl;

import com.lei.music.bean.Singer;
import com.lei.music.bean.SongList;
import com.lei.music.dao.SongListMapper;
import com.lei.music.service.SongListServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListServie {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public Boolean add(SongList songList) {
        return songListMapper.add(songList)>0;
    }

    @Override
    public Boolean update(SongList songList) {
        return songListMapper.update(songList)>0;
    }

    @Override
    public Boolean updateSongListPic(SongList songList) {
        return songListMapper.updateSongListPic(songList)>0;
    }

    @Override
    public Boolean delete(Integer id) {
        return songListMapper.delete(id)>0;
    }

    @Override
    public List<SongList> queryAll() {
        return songListMapper.queryAll();
    }

    @Override
    public SongList queryOne(Integer id) {
        return songListMapper.queryOne(id);
    }

    @Override
    public List<SongList> queryByStyle(String style) {
        return songListMapper.queryByStyle(style);
    }

    @Override
    public List<SongList> queryByTitle(String title) {
        return songListMapper.queryByTitle(title);
    }

    @Override
    public List<Integer> querySongId(Integer id) {
        return songListMapper.querySongId(id);
    }

}
