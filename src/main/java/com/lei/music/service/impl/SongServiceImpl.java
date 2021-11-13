package com.lei.music.service.impl;

import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;
import com.lei.music.dao.SongMapper;
import com.lei.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;
    @Override
    public Boolean add(Song song) {
        return songMapper.add(song)>0;
    }

    @Override
    public Boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    @Override
    public Boolean delete(Integer id) {
        return songMapper.delete(id)>0;
    }

    @Override
    public Song queryOne(Integer id) {
        return songMapper.queryOne(id);
    }

    @Override
    public List<Song> queryAll() {
        return songMapper.queryAll();
    }

    @Override
    public Song queryByName(String name) {
        return songMapper.queryByName(name);
    }

    @Override
    public List<Song> queryBySingerID(Integer id) {
        return songMapper.queryBySingerID(id);
    }

    //更新歌曲文件
    @Override
    public Boolean updateSong(Song song) {
        return songMapper.updateSong(song)>0;
    }

    @Override
    public Boolean updateSongPic(Song song) {
        return songMapper.updateSongPic(song)>0;
    }

    @Override
    public List<Song> queryLikeOfName(String name) {
        return songMapper.queryLikeOfName(name);
    }
}
