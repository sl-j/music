package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Rank;
import com.lei.music.service.RankService;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rank")
public class RankController {
    
    @Autowired
    private RankService rankService;
    
    //添加评价
    @PostMapping("/add")
    public Object add(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId");
        String userId = request.getParameter("userId");
        String score = request.getParameter("score");



//        根据歌单id和顾客id查询（在评分前，判断用户是否已经评价过该歌单）
        Boolean aBoolean = rankService.queryBySId(Integer.parseInt(songListId), Integer.parseInt(userId));
        if(aBoolean){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"用户已经评分该歌单，不可重复评分");
            return jsonObject;
        }
        Boolean flag = rankService.add(Integer.parseInt(songListId),Integer.parseInt(userId),Integer.parseInt(score));
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"评分成功");
            return jsonObject;
        }
        else {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"评分失败");
            return jsonObject;
        }


    }
    
    //得到评价的的平均分
    @GetMapping("/avg")
    public Object avg(@RequestParam("id")Integer songListId){
        if(rankService.numAll(songListId) == 0){
            return 0;
        }else if(rankService.scoreAll(songListId) == 0){
            return 0;
        }
        else {
            return rankService.scoreAll(songListId)/ rankService.numAll(songListId);
        }



    }
}
