package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.ListSong;
import com.lei.music.service.ListSongService;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    //查询歌单的所有歌曲
    @GetMapping("/queryByListId")
    public Object queryByListId(@RequestParam("songListId")Integer id){
        return listSongService.queryByListId(id);
    }

    //添加歌单歌曲
    @PostMapping("add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        if(songId.equals("undefined")){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"歌名有误");
            return jsonObject;
        }

        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));

        Boolean flag = listSongService.add(listSong);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    //删除歌单中的歌曲
    @DeleteMapping("/delete")
    public Object delete(@RequestParam("songId")Integer songId,@RequestParam("songListId") Integer songListId){
        return listSongService.delete(songId,songListId);
    }
}
