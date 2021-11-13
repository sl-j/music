package com.lei.music.service;

import com.lei.music.bean.Collect;

import java.util.List;

public interface CollectSevice {

    //添加收藏
    public Boolean add(Collect collect);

    //删除收藏
    public Boolean delete(Collect collect);

    //查看用户的收藏列表
    public List<Collect> queryAllByUserId(Integer id);

    //查询某个用户是否已经收藏了某个歌曲
    public Boolean isExist(Collect collect);


}
