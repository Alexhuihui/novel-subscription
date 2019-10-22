package top.alexmmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.alexmmd.domain.NovelUser;

/**
 * @author 汪永晖
 */
public interface NovelUserRepository extends JpaRepository<NovelUser, Long> {
}
