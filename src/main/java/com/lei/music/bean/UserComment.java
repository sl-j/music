package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 用户id和评论id的关联表，多对多的关系,用于限制用户对同一评论的点赞次数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserComment implements Serializable {

    private BigInteger id;

    //用户id
    private Integer userId;

    //评论id
    private Integer commentId;

}
