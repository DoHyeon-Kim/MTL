package com.library.qanda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.qanda.dto.QandaDTO;

/**
 * Q&A Mapper
 * 実際のSQLは resources/mapper/QandaMapper.xml に記述する。
 */
@Mapper
public interface QandaMapper {

    /** Q&A一覧を新しい順に全件取得する */
    List<QandaDTO> findAll();

    /** IDを指定して1件取得する */
    QandaDTO findById(@Param("id") Long id);

    /** 質問を新規登録する */
    int insert(QandaDTO qanda);

    /** 質問内容を更新する */
    int update(QandaDTO qanda);

    /** 回答を登録・更新する */
    int updateAnswer(@Param("id") Long id, @Param("answer") String answer);

    /** 削除する */
    int deleteById(@Param("id") Long id);
}
