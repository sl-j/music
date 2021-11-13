package com.lei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.lei.music.bean.Comment;
import com.lei.music.service.CommentService;
import com.lei.music.service.UserCommentService;
import com.lei.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserCommentService userCommentService;

    //添加评论
    @PostMapping("/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content");

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        //判断评论的类型
        if (new Byte(type) == 0) {
            comment.setSongId(Integer.parseInt(songId));
        }
        else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);

        Boolean flag = commentService.add(comment);

        if(flag){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"评论成功！");
            return jsonObject;
        }
        else {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "评论失败！");
            return jsonObject;
        }
    }

    //查询所有评论
    @GetMapping("/queryAll")
    public Object queryAll(@RequestParam("type") Byte type,@RequestParam("id") Integer id){

        return commentService.queryAll(type,id);
    }

    //查询歌单或者

    //给某个评论点赞
    @PostMapping("/like")
    public Object like(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();

        String id = request.getParameter("id");       //主键id
        String up = request.getParameter("up");        //点赞
        String userId = request.getParameter("userId");  //点赞用户id

        //判断用户是否已经给该评论点过赞
        Integer commendId = Integer.parseInt(id);
        Boolean isLike = userCommentService.queryOne(Integer.parseInt(userId), commendId);
        if(isLike){
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"您已经点过赞了");
            return jsonObject;
        }

        Comment comment = new Comment();

        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        Boolean flag = commentService.update(comment);
        if(flag){
            //如果没有点过赞，则添加记录
            userCommentService.insert(Integer.parseInt(userId), commendId);
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"点赞成功");
            return jsonObject;
        }
        else {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"点赞失败");
            return jsonObject;
        }
    }

    //查询歌单的评论
    @GetMapping("queryComment")
    public Object queryComment(@RequestParam("songListId") Integer id){
        return commentService.queryComment(id);
    }

    //删除某个评论
    @DeleteMapping("/delete")
    public Object delete(@RequestParam("id") Integer id){
        return commentService.delete(id);
    }
}
