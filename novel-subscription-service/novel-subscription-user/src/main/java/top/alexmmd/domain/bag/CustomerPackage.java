package top.alexmmd.domain.bag;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 新增顾客的前端传输对象
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerPackage {

    // 用户名
    @NotBlank(message = "用户名不能为空")
    private String username;

    // email
    @NotBlank(message = "email不能为空")
    private String email;

}
