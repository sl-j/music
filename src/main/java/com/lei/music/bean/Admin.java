package com.lei.music.bean;


import lombok.*;

import java.io.Serializable;

/*
* 管理员bean
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin implements Serializable {

    private Integer id;

    //账户名
    private String name;

    //密码
    private String password;
}
