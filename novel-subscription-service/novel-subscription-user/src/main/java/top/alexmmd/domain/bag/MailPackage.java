package top.alexmmd.domain.bag;

import lombok.*;

/**
 * @author 汪永晖
 * @Date 2020/11/12 9:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MailPackage {

    /**
     * 内容
     */
    private String content;
}
