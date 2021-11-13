package com.lei.music;

import com.lei.music.bean.Song;
import com.lei.music.dao.SongMapper;
import com.lei.music.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicApplicationTests {

    @Autowired
    private SongService songService;

    @Test
    void contextLoads() {
        Song song = new Song();

//        song.setIntroduction("introduction");
//        song.setLyric("lyric");
        song.setName("name");
//        song.setPic("pic");
//        song.setSingerId(Integer.parseInt("1"));
        Boolean add = songService.add(song);
        System.out.println(add);

    }

}
