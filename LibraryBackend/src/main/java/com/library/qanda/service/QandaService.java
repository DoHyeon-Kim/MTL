package com.library.qanda.service;

import java.util.List;

import com.library.qanda.dto.QandaDTO;

/**
 * Q&A Service
 */
public interface QandaService {

    /** Q&A一覧を取得する（新しい順） */
    List<QandaDTO> getQandaList();

    /** IDを指定してQ&A詳細を取得する */
    QandaDTO getQanda(Long id);

    /** 質問を新規登録する */
    QandaDTO createQanda(QandaDTO qanda);

    /** 質問内容を更新する */
    QandaDTO updateQanda(Long id, QandaDTO qanda);

    /** 回答を登録する */
    QandaDTO answerQanda(Long id, String answer);

    /** 削除する */
    void deleteQanda(Long id);
}
