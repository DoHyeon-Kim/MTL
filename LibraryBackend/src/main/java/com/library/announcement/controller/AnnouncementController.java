package com.library.announcement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.library.announcement.dto.AnnouncementDTO;
import com.library.announcement.service.AnnouncementService;

import lombok.RequiredArgsConstructor;

/**
 * お知らせ(Announcement) Controller
 * 画面イメージ: 一覧ページ(内容・作成日) → 押下で詳細ページ(タイトル・内容欄・目録ボタン)
 */
@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    /** お知らせ一覧取得 */
    @GetMapping
    public List<AnnouncementDTO> list() {
        return announcementService.getAnnouncementList();
    }

    /** お知らせ詳細取得 */
    @GetMapping("/{id}")
    public AnnouncementDTO detail(@PathVariable Long id) {
        return announcementService.getAnnouncement(id);
    }

    /** お知らせ新規登録 */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementDTO create(@RequestBody AnnouncementDTO announcement) {
        return announcementService.createAnnouncement(announcement);
    }

    /** お知らせ更新 */
    @PutMapping("/{id}")
    public AnnouncementDTO update(@PathVariable Long id, @RequestBody AnnouncementDTO announcement) {
        return announcementService.updateAnnouncement(id, announcement);
    }

    /** お知らせ削除 */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
    }
}
