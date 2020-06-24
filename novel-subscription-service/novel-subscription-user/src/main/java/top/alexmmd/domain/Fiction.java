package top.alexmmd.domain;

import lombok.*;

/**
 * 笔趣阁返回的小说信息
 *
 * @author 汪永晖
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Fiction {

    private Long id;

    private String novelName;

    private String novelDesc;

    private String author;

    private String category;

    private String updateTime;

    private String latestChapter;

    private String novelUrl;

    private String imgUrl;
}
