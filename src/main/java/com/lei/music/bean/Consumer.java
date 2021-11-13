package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Consumer implements Serializable {

    //主键id
    private Integer id;

    //用户名
    private String username;

    //密码
    private String password;

    //性别
    private Byte sex;

    //电话号码
    private String phoneNum;

    //email
    private String email;

    //生日
    private Date birth;

    //简介
    private String introduction;

    //位置
    private String location;

    //头像
    private String avator;

    //账号创建时间
    private String createTime;

    //账号更新时间
    private String updateTime;
}
