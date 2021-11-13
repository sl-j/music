package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌曲bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Song implements Serializable {

    private Integer id;

    private Integer singerId;  //歌手id

    private String name;  //歌曲名

    private String introduction; //歌曲简介

    private Date createTime; //歌曲创建时间

    private Date updateTime; //歌曲更新时间

    private String pic; //歌曲图片

    private String lyric; //歌词

    private String url; //歌曲路径

}
