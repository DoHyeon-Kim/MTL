package com.library.qanda.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.library.qanda.dto.QandaDTO;
import com.library.qanda.service.QandaService;

import lombok.RequiredArgsConstructor;

/**
 * Q&A Controller
 * 画面イメージ: 一覧ページ(内容・状態・作成日) → 押下で詳細ページ(質問の内容欄・答えの内容欄・目録ボタン)
 */
@RestController
@RequestMapping("/api/qanda")
@RequiredArgsConstructor
public class QandaController {

    private final QandaService qandaService;

    /** Q&A一覧取得 */
    @GetMapping
    public List<QandaDTO> list() {
        return qandaService.getQandaList();
    }

    /** Q&A詳細取得 */
    @GetMapping("/{id}")
    public QandaDTO detail(@PathVariable Long id) {
        return qandaService.getQanda(id);
    }

    /** 質問の新規登録 */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QandaDTO create(@RequestBody QandaDTO qanda) {
        return qandaService.createQanda(qanda);
    }

    /** 質問内容の更新 */
    @PutMapping("/{id}")
    public QandaDTO update(@PathVariable Long id, @RequestBody QandaDTO qanda) {
        return qandaService.updateQanda(id, qanda);
    }

    /** 回答の登録（管理者が回答を入力する想定） */
    @PatchMapping("/{id}/answer")
    public QandaDTO answer(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return qandaService.answerQanda(id, body.get("answer"));
    }

    /** 削除 */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        qandaService.deleteQanda(id);
    }
}
