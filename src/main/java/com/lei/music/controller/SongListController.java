package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Song;
import com.lei.music.bean.SongList;
import com.lei.music.service.SongListServie;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

//歌单controller层
@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListServie songListServie;

    @PostMapping("/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title");//标题
        String introduction = request.getParameter("introduction");//简介
        String style = request.getParameter("style");//风格
        String pic = "img/songListPic/"+"defult.jpg";

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        songList.setPic(pic);


        Boolean flag = songListServie.add(songList);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"添加歌单成功");
            return  jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"添加歌单失败");
        return  jsonObject;
    }

    @GetMapping("/queryAll")
    public Object queryAll(){
        return songListServie.queryAll();
    }

    //更新歌单图片
    @PostMapping("/updateSongListPic")
    public Object updateSongPic(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile multipartFile){

        JSONObject jsonObject = new JSONObject();
        if(multipartFile.isEmpty()){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"未上传歌单图片");
            return jsonObject;
        }

        //上传歌单图片名
        String musicPicName =multipartFile.getOriginalFilename();

        //存放的项目中的文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img" +
                System.getProperty("file.separator")+"songListPic";

        //判断是否存在此路径，如果不存在则自己创建此文件
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件的实际位置
        File dest = new File(filePath + System.getProperty("file.separator") + musicPicName);

        //存放在数据库中pic字段的路径值
        String urlPathToData = "/img/songListPic/" + musicPicName;

        try {

            //更新歌单图片时，删除原歌单图片
            SongList OsongList = songListServie.queryOne(id);
            String url = OsongList.getPic();
            File oFile = new File(System.getProperty("user.dir") +url);
            boolean delete = oFile.delete();


            SongList songList = new SongList();
            songList.setPic(urlPathToData);
            songList.setId(id);

            Boolean flag = songListServie.updateSongListPic(songList);
            if(flag){
                //将文件存放在实际路径中
                multipartFile.transferTo(dest);
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"更新歌单图片成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"更新歌单图片失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"更新歌单图片失败:"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    //删除歌单
    @DeleteMapping("/delete")
    public Object delete(@RequestParam("id")Integer id){
        return songListServie.delete(id);
    }

    //编辑歌单
    @PostMapping("/update")
    public Object update(HttpServletRequest request,@RequestParam("id")Integer id){

        JSONObject jsonObject = new JSONObject();

        String title = request.getParameter("title");
        String introduction = request.getParameter("introduction");
        String style = request.getParameter("style");


        SongList songList = new SongList();
        songList.setId(id);
        songList.setStyle(style);
        songList.setIntroduction(introduction);
        songList.setTitle(title);

        Boolean flag = songListServie.update(songList);

        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"编辑成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"编辑失败");
        return jsonObject;
    }

    //根据歌单风格模糊查询歌单
    @GetMapping("/queryByStyle")
    public Object queryByStyle(@RequestParam("style")String style){
        List<SongList> songLists = songListServie.queryByStyle("%"+style+"%");
        return songLists;
    }

    //根据歌单名称模糊查询歌单
    @GetMapping("/queryByTitle")
    public Object queryByTitle(@RequestParam("title")String title){
        return songListServie.queryByTitle("%" + title + "%");
    }

    //跟俊歌单id查询歌单
    @GetMapping("/queryOneById")
    public Object queryOneById(@RequestParam("songListId")Integer id){
        return songListServie.queryOne(id);
    }

    /**
     * 根据歌单id查询歌单内的歌曲id
     */
    @GetMapping("/querySongId")
    public Object querySongId(@RequestParam("songListId")Integer id){
        return songListServie.querySongId(id);
    }

}
