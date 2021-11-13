package com.lei.music.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
* 管理员dao层
* */
@Mapper
@Repository
public interface AdminMapper {

    public Integer verityPassword(String name,String password);
}
