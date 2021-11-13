package com.lei.music.service.impl;

import com.lei.music.bean.Rank;
import com.lei.music.dao.RankMapper;
import com.lei.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankeServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;
    @Override
    public Boolean add(Integer songListId, Integer userId,Integer score) {
        return rankMapper.add(songListId,userId,score)>0;
    }

    @Override
    public Integer scoreAll(Integer songListId) {
        return rankMapper.scoreAll(songListId);
    }

    @Override
    public Integer numAll(Integer songListId) {
        return rankMapper.numAll(songListId);
    }

    @Override
    //根据歌单id和顾客id查询（在评分前，判断用户是否已经评价过该歌单）
    public Boolean queryBySId(Integer songListId, Integer userId) {
        return rankMapper.queryBySId(songListId,userId)>0;
    }
}
