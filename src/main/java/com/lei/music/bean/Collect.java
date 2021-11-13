package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.TimerTask;

/**
 * 收藏列表bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Collect implements Serializable {

    private Integer id;

    private Integer userId;   //用户id

    private Byte type;    //收藏类型

    private Integer songId;   //收藏的歌曲id

    private Integer songListId;   //收藏的歌单id

    private Date createTime;   //收藏时间
}
