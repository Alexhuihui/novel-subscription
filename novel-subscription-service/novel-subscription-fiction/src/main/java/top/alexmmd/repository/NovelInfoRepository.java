package top.alexmmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.alexmmd.domain.NovelInfo;

/**
 * @author 汪永晖
 */
public interface NovelInfoRepository extends JpaRepository<NovelInfo, Long> {

}
