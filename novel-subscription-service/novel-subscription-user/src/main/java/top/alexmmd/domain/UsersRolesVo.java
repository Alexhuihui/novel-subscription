package top.alexmmd.domain;

import lombok.*;

/**
 * @author 汪永晖
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersRolesVo {

    private Long userId;

    private Long roleId;

    private String roleName;

    private Integer usersRolesId;

    // 用户名
    private String username;

    // email
    private String email;
}
