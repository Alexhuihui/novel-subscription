package top.alexmmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.alexmmd.domain.NovelUserFiction;

/**
 * @author 汪永晖
 */
public interface NovelUserFictionRepository extends JpaRepository<NovelUserFiction, Long> {
}
