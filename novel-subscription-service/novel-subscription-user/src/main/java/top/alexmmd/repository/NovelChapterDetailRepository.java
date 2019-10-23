package top.alexmmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.alexmmd.domain.NovelChapterDetail;

import java.util.List;

/**
 * @author 汪永晖
 */
public interface NovelChapterDetailRepository extends JpaRepository<NovelChapterDetail, Long> {

    List<NovelChapterDetail> findAllByNovelIdAndChapterId(Long novelId, Long id);
}
