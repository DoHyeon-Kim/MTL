package com.library.announcement.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.announcement.dto.AnnouncementDTO;
import com.library.announcement.mapper.AnnouncementMapper;
import com.library.announcement.service.AnnouncementService;

import lombok.RequiredArgsConstructor;

/**
 * お知らせ(Announcement) Service実装
 */
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementMapper announcementMapper;

    @Override
    public List<AnnouncementDTO> getAnnouncementList() {
        return announcementMapper.findAll();
    }

    @Override
    public AnnouncementDTO getAnnouncement(Long id) {
        AnnouncementDTO announcement = announcementMapper.findById(id);
        if (announcement == null) {
            throw new IllegalArgumentException("お知らせが見つかりません。ID=" + id);
        }
        return announcement;
    }

    @Override
    public AnnouncementDTO createAnnouncement(AnnouncementDTO announcement) {
        announcement.setId(null);
        announcement.setCreatedAt(LocalDateTime.now());
        announcementMapper.insert(announcement);
        return announcement;
    }

    @Override
    public AnnouncementDTO updateAnnouncement(Long id, AnnouncementDTO announcement) {
        AnnouncementDTO existing = getAnnouncement(id);
        existing.setTitle(announcement.getTitle());
        existing.setContent(announcement.getContent());
        announcementMapper.update(existing);
        return existing;
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementMapper.deleteById(id);
    }
}
