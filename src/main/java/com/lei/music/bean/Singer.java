package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌手bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Singer implements Serializable {

    private Integer id;

    //歌手姓名
    private String name;

    //歌手性别
    private Byte sex;

    //歌手图片
    private String pic;

    //歌手生日
    private Date birth;

    //歌手籍贯
    private String location;

    //歌手简介
    private String introduction;
}
