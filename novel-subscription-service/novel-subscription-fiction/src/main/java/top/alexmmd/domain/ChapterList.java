package top.alexmmd.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 汪永晖
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChapterList implements Serializable {

    private static final long serialVersionUID = 4355589611976850715L;

    /**
     * 章节名称
     */
    private String chapterName;


    /**
     * 章节地址
     */
    private String chapterUrl;

    /**
     * 章节ID
     */
    private Long chapterId;


    /**
     * 小说ID
     */
    private Long fictionId;

    /**
     * 章节内容
     */
    private String chapterContent;

    /**
     * 上一章
     */
    private ChapterList last;

    /**
     * 下一章
     */
    private ChapterList next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChapterList)) return false;
        ChapterList that = (ChapterList) o;
        return getChapterId().equals(that.getChapterId()) &&
                getFictionId().equals(that.getFictionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChapterId(), getFictionId());
    }
}
