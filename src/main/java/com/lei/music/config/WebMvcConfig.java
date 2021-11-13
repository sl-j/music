package com.lei.music.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //后端跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域访问的接口：/**表示所有端口
        registry.addMapping("/**")
                //允许哪些网站访问
                .allowedOriginPatterns("*")
                //允许发送cookie
                .allowCredentials(true)
                .allowedHeaders("*")
                //允许所有方法
                .allowedMethods("*")
                //探测请求的有效时间
                        .maxAge(3600);
    }

    //配置前端对后端的静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //对歌手头像的访问
        registry.addResourceHandler("/img/singerPic/**")//前端的访问路径
                .addResourceLocations("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator"));//映射到后端的路径

        //对歌曲头像访问
        registry.addResourceHandler("/img/songPic/**")//前端的访问路径
                .addResourceLocations("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"songPic"+System.getProperty("file.separator"));//映射到后端的路径

        //对歌单图片访问
        registry.addResourceHandler("/img/songListPic/**")//前端的访问路径
                .addResourceLocations("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"songListPic"+System.getProperty("file.separator"));//映射到后端的路径

        //对音乐文件的访问
        registry.addResourceHandler("/song/**")//前端的访问路径
                .addResourceLocations("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"song"+System.getProperty("file.separator"));//映射到后端的路径

        //对用户头像文件的访问
        registry.addResourceHandler("/avatorImages/**")//前端的访问路径
                .addResourceLocations("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages"+System.getProperty("file.separator"));//映射到后端的路径

    }
}
