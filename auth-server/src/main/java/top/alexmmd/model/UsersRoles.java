package top.alexmmd.model;

import lombok.*;

import java.io.Serializable;

/**
 * (UsersRoles)实体类
 *
 * @author 汪永晖
 * @since 2020-04-07 16:16:01
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersRoles implements Serializable {
    private static final long serialVersionUID = 914793322033058850L;

    private Long userId;

    private Long roleId;

    private Integer usersRolesId;

}