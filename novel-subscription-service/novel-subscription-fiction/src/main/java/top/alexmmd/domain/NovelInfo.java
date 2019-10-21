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
 * novel_info 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "novel_info")
public class NovelInfo {

    // 数据表主键
    @Id
    @Column(name = "novel_id", nullable = false)
    private Long id;

    // 小说名称
    @Column(name = "novel_name", nullable = false)
    private String novelName;

    // 小说作者
    @Column(name = "novel_author", nullable = false)
    private String novelAuthor;

    // 小说类型
    @Column(name = "novel_type", nullable = false)
    private String novelType;

    // 小说图标
    @Column(name = "novel_icon", nullable = false)
    private String novelIcon;

    // 小说简介
    @Column(name = "novel_intro", nullable = false)
    private String novelIntro;

    // 小说链接
    @Column(name = "novel_url", nullable = false)
    private String novelUrl;

    // 小说被爬取状态
    @Column(name = "novel_status", nullable = false)
    private String novelStatus;

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
