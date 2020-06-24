package top.alexmmd.domain;

import lombok.*;

import java.util.Date;

/**
 * novel_user_fiction 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NovelUserFiction {

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
