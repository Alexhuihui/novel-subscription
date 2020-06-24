package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * novel_chapter_detail 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovelChapterDetail {

    // 数据表主键
    private Long contentId;

    // novel_id 外键
    private Long novelId;

    // 章节 id
    private Long chapterId;

    // 章节链接
    private String contentUrl;

    // 章节标题
    private String title;

    // 章节内容
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
