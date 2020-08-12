package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * novel_user 表对应的实体类
 *
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NovelUser {

    // 数据表主键
    private Long id;

    // 用户名
    @NotBlank(message = "用户名不能为空")
    private String username;

    // password
    @NotBlank(message = "密码不能为空")
    private String password;

    // email
    @NotBlank(message = "email不能为空")
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
