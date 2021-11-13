package com.lei.music.bean;

import lombok.*;

import java.io.Serializable;

/**
 * 歌单歌曲
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListSong implements Serializable {
    private Integer id;
    private Integer songId;
    private Integer songListId;
}
