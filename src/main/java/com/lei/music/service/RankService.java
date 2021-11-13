package com.lei.music.service;

public interface RankService {
    //添加评价
    public Boolean add(Integer songListId,Integer userId,Integer score);

    //得到评价总分
    public Integer scoreAll(Integer songListId);

    //得到歌单的评价总人数
    public Integer numAll(Integer songListId);

    //根据歌单id和顾客id查询（在评分前，判断用户是否已经评价过该歌单）
    public Boolean queryBySId(Integer songListId,Integer userId);
}
