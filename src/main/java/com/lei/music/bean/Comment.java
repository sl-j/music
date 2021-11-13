package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;

/**
 * 评论bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment implements Serializable {

    private Integer id;

    //用户id
    private Integer userId;

    //对歌单或歌曲评价
    private Byte type;

    //歌曲id
    private Integer songId;

    //歌单id
    private Integer songListId;

    //评论时间
    private String createTime;

    //评论内容
    private String content;

    //点赞数
    private Integer up;
}
