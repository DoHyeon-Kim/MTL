package com.library.announcement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.announcement.dto.AnnouncementDTO;

/**
 * お知らせ(Announcement) Mapper
 * 実際のSQLは resources/mapper/AnnouncementMapper.xml に記述する。
 */
@Mapper
public interface AnnouncementMapper {

    /** お知らせを新しい順に全件取得する */
    List<AnnouncementDTO> findAll();

    /** IDを指定して1件取得する */
    AnnouncementDTO findById(@Param("id") Long id);

    /** 新規登録する */
    int insert(AnnouncementDTO announcement);

    /** 更新する */
    int update(AnnouncementDTO announcement);

    /** 削除する */
    int deleteById(@Param("id") Long id);
}
