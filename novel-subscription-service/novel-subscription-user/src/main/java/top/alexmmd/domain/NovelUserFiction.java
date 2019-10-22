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
 * novel_user_fiction 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "novel_user_fiction")
public class NovelUserFiction {

    // 数据表主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 用户 id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // novel_id
    @Column(name = "novel_id", nullable = false)
    private Long novelId;


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
