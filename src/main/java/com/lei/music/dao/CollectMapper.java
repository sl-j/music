package com.lei.music.dao;


import com.lei.music.bean.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper {

    //添加收藏
    public Integer add(Collect collect);

    //删除收藏
    public Integer delete(Collect collect);

    //查看用户的收藏列表
    public List<Collect> queryAllByUserId(Integer id);

    //查询某个用户是否已经收藏了某个歌曲
    public Integer isExist(Collect collect);
}
