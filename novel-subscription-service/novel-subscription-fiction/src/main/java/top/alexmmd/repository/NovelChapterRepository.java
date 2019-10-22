package top.alexmmd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import top.alexmmd.domain.NovelChapter;

/**
 * @author 汪永晖
 */
public interface NovelChapterRepository extends JpaRepository<NovelChapter, Long> {
}
