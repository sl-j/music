package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class SongList implements Serializable {
    private Integer id;

    //歌单标题
    private String title;

    //歌单封面
    private String pic;

    //歌单简介
    private String introduction;

    //歌曲风格
    private  String style;
}
