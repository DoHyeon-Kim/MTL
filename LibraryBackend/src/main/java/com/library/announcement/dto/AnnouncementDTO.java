package com.library.announcement.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * お知らせ(Announcement) DTO
 * 一覧画面: title(内容) と createdAt(作成日) を表示
 * 詳細画面: title(タイトル) と content(内容欄) を表示
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {

    /** お知らせID */
    private Long id;

    /** タイトル（一覧の「内容」列に表示される） */
    private String title;

    /** 本文（詳細画面の「内容欄」に表示される） */
    private String content;

    /** 作成日時 */
    private LocalDateTime createdAt;
}
