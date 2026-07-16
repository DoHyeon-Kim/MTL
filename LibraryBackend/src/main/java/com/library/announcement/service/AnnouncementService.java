package com.library.announcement.service;

import java.util.List;

import com.library.announcement.dto.AnnouncementDTO;

/**
 * お知らせ(Announcement) Service
 */
public interface AnnouncementService {

    /** お知らせ一覧を取得する（新しい順） */
    List<AnnouncementDTO> getAnnouncementList();

    /** IDを指定してお知らせ詳細を取得する */
    AnnouncementDTO getAnnouncement(Long id);

    /** お知らせを新規登録する */
    AnnouncementDTO createAnnouncement(AnnouncementDTO announcement);

    /** お知らせを更新する */
    AnnouncementDTO updateAnnouncement(Long id, AnnouncementDTO announcement);

    /** お知らせを削除する */
    void deleteAnnouncement(Long id);
}
