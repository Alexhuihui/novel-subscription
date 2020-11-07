package top.alexmmd.domain.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 商品分类 (Category)实体类
 *
 * @author 汪永晖
 * @since 2020-11-06 14:26:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category implements Serializable {
    private static final long serialVersionUID = -60974706845659010L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类类型
     */
    private Integer type;
    /**
     * 父id
     */
    private Integer fatherId;
    /**
     * 图标
     */
    private String logo;
    /**
     * 口号
     */
    private String slogan;
    /**
     * 分类图
     */
    private String catImage;
    /**
     * 背景颜色
     */
    private String bgColor;

}