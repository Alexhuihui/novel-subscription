package top.alexmmd.domain;

import lombok.*;

import java.util.Date;

/**
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NovelUserFictionVo {

    // 数据表主键
    private Long id;

    // 用户 id
    private Long userId;

    // novel_id
    private Long novelId;

    // 用户名
    private String username;

    // email
    private String email;

    // 小说名称
    private String novelName;

    // 小说作者
    private String novelAuthor;

    // 小说类型
    private String novelType;

    // 小说图标
    private String novelIcon;

    // 小说简介
    private String novelIntro;
}
