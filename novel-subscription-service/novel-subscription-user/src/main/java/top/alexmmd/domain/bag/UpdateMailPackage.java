package top.alexmmd.domain.bag;

import lombok.*;

/**
 * 发送更新邮件的前端传输对象
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateMailPackage {

    /**
     * 更新内容
     */
    private String updateContent;

    /**
     * 商品 id
     */
    private Integer itemId;
}
