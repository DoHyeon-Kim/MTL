package com.library.qanda.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Q&A DTO
 * 一覧画面: title(内容)・status(状態)・createdAt(作成日) を表示
 * 詳細画面: title/content(質問の内容欄)・answer(答えの内容欄) を表示
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QandaDTO {

    /** Q&A ID */
    private Long id;

    /** 質問タイトル（一覧の「内容」列、詳細画面の見出しに表示される） */
    private String title;

    /** 質問本文（詳細画面 質問側の「内容欄」に表示される） */
    private String content;

    /** 回答本文（詳細画面 答え側の「内容欄」に表示される。未回答の場合はnull） */
    private String answer;

    /** 作成日時 */
    private LocalDateTime createdAt;

    /**
     * 状態（DBには保存しない算出項目）
     * 回答が入っていれば「答え完了」、なければ「答え待機」
     */
    @JsonProperty("status")
    public String getStatus() {
        return (answer != null && !answer.isBlank()) ? "答え完了" : "答え待機";
    }
}
