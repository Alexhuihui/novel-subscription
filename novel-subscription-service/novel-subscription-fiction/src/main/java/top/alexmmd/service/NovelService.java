package top.alexmmd.service;

import org.springframework.stereotype.Service;
import top.alexmmd.domain.NovelInfo;

/**
 * @author 汪永晖
 */
@Service
public interface NovelService {

    NovelInfo findNovelById(Long id);
}