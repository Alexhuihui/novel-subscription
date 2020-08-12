package top.alexmmd.domain;

import lombok.*;

import java.util.List;

/**
 * @author 汪永晖
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetMyUserInfoVo {

    private Long userId;

    // 用户名
    private String username;

    // email
    private String email;

    private List<Role> roleList;
}
