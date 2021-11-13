package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;

/**
 * 评价表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rank implements Serializable {
    private Integer id;

    //歌单表id，外键
    private Integer songListId;

    //顾客表id。外键
    private Integer userId;

    //评分
    private Integer score;
}
