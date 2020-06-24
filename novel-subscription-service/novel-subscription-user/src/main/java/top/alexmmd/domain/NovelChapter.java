package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * novel_chapter 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovelChapter {

    // 数据表主键
    private Long chapterId;

    // novel_id 外键
    private Long novelId;

    // 章节地址
    private String chapterUrl;

    // 章节的真实 id
    private String chapterRealId;

    // 章节标题
    private String chapterTitle;

    // 章节被爬取状态
    private Integer chapterStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
