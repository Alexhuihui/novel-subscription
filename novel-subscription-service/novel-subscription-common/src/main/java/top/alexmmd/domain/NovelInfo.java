package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 数据库返回的小说信息
 *
 * @author 汪永晖
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NovelInfo {

    private Long id;

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

    // 小说链接
    private String novelUrl;

    // 小说被爬取状态
    private String novelStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
