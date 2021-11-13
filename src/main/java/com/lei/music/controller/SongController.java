package com.lei.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Singer;
import com.lei.music.bean.Song;
import com.lei.music.service.SongService;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping("/add")
    public Object add(HttpServletRequest request, @RequestParam("file")MultipartFile multipartFile){
        JSONObject jsonObject = new JSONObject();

        //判断是否上传歌曲
        if(multipartFile.isEmpty()){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"未上传歌曲");
            return jsonObject;
        }

        String singerId = request.getParameter("singerId");//歌手id
        String name = request.getParameter("name");//歌曲名字
        String introduction = request.getParameter("introduction");//歌曲简介
        String pic = "/img/songPic/tubiao.jpg";//歌曲图片
        String lyric = request.getParameter("lyric");//歌词


        //上传歌曲名
        String musicName =multipartFile.getOriginalFilename();

        //存放的项目中的文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";

        //判断是否存在此路径，如果不存在则自己创建此文件
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件的实际位置
        File dest = new File(filePath + System.getProperty("file.separator") + musicName);

        //存放在数据库中url字段的路径值
        String urlPathToData = "/song/" + musicName;

        try {

            //将文件存放在实际路径中
            multipartFile.transferTo(dest);
            Song song = new Song();
            song.setUrl(urlPathToData);
            song.setIntroduction(introduction);
            song.setLyric(lyric);
            song.setName(name);
            song.setPic(pic);
            if(singerId != null){
                song.setSingerId(Integer.parseInt(singerId));
            }
            Boolean flag = songService.add(song);
            if(flag){
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"上传歌曲成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"上传歌曲失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"上传歌曲失败:"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    @GetMapping("/queryAll")
    public Object queryAll(){
        List<Song> songs = songService.queryAll();
        return songs;

    }


    //根据歌手id查询歌曲
    @GetMapping("queryById")
    public Object queryById(@RequestParam("singerId") Integer id){
        List<Song> songs = songService.queryBySingerID(id);
        return songs;
    }

    //修改歌曲
    @PostMapping("update")
    public Object update(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String introduction = request.getParameter("introduction");
        String lyric = request.getParameter("lyric");


        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);

        Boolean flag = songService.update(song);
        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"修改歌曲信息成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"修改歌曲信息失败");
        return jsonObject;
    }

    //删除歌曲
    @DeleteMapping("/delete")
    public Object delete(@RequestParam("id") Integer id){
        //文件中歌曲
        Song oSong = songService.queryOne(id);
        String url = oSong.getUrl();
        File oFile = new File(System.getProperty("user.dir") + url);
        oFile.delete();
        Boolean flag = songService.delete(id);
        return flag;
    }

    //更新歌曲
    @PostMapping("updateSong")
    public Object updateSong(@RequestParam("id") Integer id,@RequestParam("file")MultipartFile multipartFile){
        JSONObject jsonObject = new JSONObject();
        if(multipartFile.isEmpty()){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"未上传歌曲");
            return jsonObject;
        }

        //上传歌曲名
        String musicName =multipartFile.getOriginalFilename();

        //存放的项目中的文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";

        //判断是否存在此路径，如果不存在则自己创建此文件
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件的实际位置
        File dest = new File(filePath + System.getProperty("file.separator") + musicName);

        //存放在数据库中url字段的路径值
        String urlPathToData = "/song/" + musicName;

        try {

            //将文件存放在实际路径中
            multipartFile.transferTo(dest);
            //更新歌曲时，删除原歌曲
            Song oSong = songService.queryOne(id);
            String url = oSong.getUrl();
            File oFile = new File(System.getProperty("user.dir") + url);
            oFile.delete();


            Song song = new Song();
            song.setUrl(urlPathToData);
            song.setId(id);

            Boolean flag = songService.updateSong(song);
            if(flag){
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"更新歌曲成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"更新歌曲失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"更新歌曲失败:"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    //更新歌曲图片
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("id") Integer id,@RequestParam("file")MultipartFile multipartFile){

        JSONObject jsonObject = new JSONObject();
        if(multipartFile.isEmpty()){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"未上传歌曲图片");
            return jsonObject;
        }

        //上传歌曲图片名
        String musicPicName =multipartFile.getOriginalFilename();

        //存放的项目中的文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img" +
                System.getProperty("file.separator")+"songPic";

        //判断是否存在此路径，如果不存在则自己创建此文件
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件的实际位置
        File dest = new File(filePath + System.getProperty("file.separator") + musicPicName);

        //存放在数据库中pic字段的路径值
        String urlPathToData = "/img/songPic/" + musicPicName;

        try {

            //更新歌曲图片时，删除原歌曲图片
            Song oSong = songService.queryOne(id);
            String url = oSong.getPic();
            File oFile = new File(System.getProperty("user.dir") +url);
            boolean delete = oFile.delete();


            Song song = new Song();
            song.setPic(urlPathToData);
            song.setId(id);

            Boolean flag = songService.updateSongPic(song);
            if(flag){
                //将文件存放在实际路径中
                multipartFile.transferTo(dest);
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"更新歌曲图片成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"更新歌曲图片失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"更新歌曲图片失败:"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }


    //根据歌曲id查询歌曲信息，用在歌单管理中
    @GetMapping("/queryOne")
    public Object queryOne(@RequestParam("songId")Integer id){
        return songService.queryOne(id);
    }

    //根据歌曲名精确查询歌曲
    @GetMapping("/queryByName")
    public Object queryByName(@RequestParam("songName") String name){
        return songService.queryByName(name);
    }

    //根据歌名模糊查询歌曲
    @GetMapping("/queryLikeOfName")
    public List<Song> queryLikeOfName(@RequestParam("songName")String name){
        return songService.queryLikeOfName("%" +name+ "%");
    }

}
