package com.library.qanda.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.qanda.dto.QandaDTO;
import com.library.qanda.mapper.QandaMapper;
import com.library.qanda.service.QandaService;

import lombok.RequiredArgsConstructor;

/**
 * Q&A Service実装
 */
@Service
@RequiredArgsConstructor
public class QandaServiceImpl implements QandaService {

    private final QandaMapper qandaMapper;

    @Override
    public List<QandaDTO> getQandaList() {
        return qandaMapper.findAll();
    }

    @Override
    public QandaDTO getQanda(Long id) {
        QandaDTO qanda = qandaMapper.findById(id);
        if (qanda == null) {
            throw new IllegalArgumentException("Q&Aが見つかりません。ID=" + id);
        }
        return qanda;
    }

    @Override
    public QandaDTO createQanda(QandaDTO qanda) {
        qanda.setId(null);
        qanda.setAnswer(null);
        qanda.setCreatedAt(LocalDateTime.now());
        qandaMapper.insert(qanda);
        return qanda;
    }

    @Override
    public QandaDTO updateQanda(Long id, QandaDTO qanda) {
        QandaDTO existing = getQanda(id);
        existing.setTitle(qanda.getTitle());
        existing.setContent(qanda.getContent());
        qandaMapper.update(existing);
        return existing;
    }

    @Override
    public QandaDTO answerQanda(Long id, String answer) {
        QandaDTO existing = getQanda(id);
        qandaMapper.updateAnswer(id, answer);
        existing.setAnswer(answer);
        return existing;
    }

    @Override
    public void deleteQanda(Long id) {
        qandaMapper.deleteById(id);
    }
}
