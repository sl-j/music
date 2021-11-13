package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Collect;
import com.lei.music.service.CollectSevice;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectSevice collectSevice;

    /**
     * 添加收藏
     */
    @PostMapping("/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String songId = null,songListId = null;
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        if(Integer.parseInt(type)== 0){
            songId = request.getParameter("songId");
        }
        else {
            songListId = request.getParameter("songListId");
        }


        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        if(songId != null){
            collect.setSongId(Integer.parseInt(songId));
        }
       else{
            collect.setSongListId(Integer.parseInt(songListId));
        }

        //判断用户是否已经收藏过
        Boolean isExist = collectSevice.isExist(collect);
       if(isExist){
           jsonObject.put(Consts.CODE,"2");
           jsonObject.put(Consts.MSG,"您已经收藏过该歌曲或歌单！");
           return jsonObject;
       }

        Boolean flag = collectSevice.add(collect);

        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;

    }

    /**
     * 查询用户的收藏列表
     */
    @GetMapping("/queryAllById")
    public Object queryAllById(@RequestParam("userId") Integer id){
        List<Collect> collects = collectSevice.queryAllByUserId(id);
        return collects;
    }
    /**
     * 删除收藏
     */
//    public Object delete(HttpServletRequest request){
//
//    }
}
