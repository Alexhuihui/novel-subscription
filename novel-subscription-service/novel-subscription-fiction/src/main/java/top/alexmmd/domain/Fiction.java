package top.alexmmd.domain;

import lombok.Data;

/**
 * @author 汪永晖
 */
@Data
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
