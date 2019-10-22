package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * novel_chapter 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "novel_chapter")
public class NovelChapter {

    // 数据表主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id", nullable = false)
    private Long id;

    // novel_id 外键
    @Column(name = "novel_id", nullable = false)
    private Long novelId;

    // 章节地址
    @Column(name = "chapter_url", nullable = false)
    private String chapterUrl;

    // 章节的真实 id
    @Column(name = "chapter_real_id", nullable = false)
    private String chapterRealId;

    // 章节标题
    @Column(name = "chapter_title", nullable = false)
    private String chapterTitle;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    private Date updateTime;
}
