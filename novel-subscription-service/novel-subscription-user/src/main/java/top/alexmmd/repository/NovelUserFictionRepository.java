package top.alexmmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.alexmmd.domain.NovelUserFiction;

import java.util.List;

/**
 * @author 汪永晖
 */
public interface NovelUserFictionRepository extends JpaRepository<NovelUserFiction, Long> {

    /**
     * 发送邮件
     *
     * @param novelId
     * @return
     */
    List<NovelUserFiction> findAllByNovelId(Long novelId);

    /**
     * 查看订阅小说列表
     *
     * @param id user_id
     * @return
     */
    List<NovelUserFiction> findAllByUserId(Long id);
}
