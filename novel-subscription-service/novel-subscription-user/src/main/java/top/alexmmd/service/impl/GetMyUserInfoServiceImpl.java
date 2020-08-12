package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.*;
import top.alexmmd.repository.RoleDao;
import top.alexmmd.repository.UsersRolesDao;
import top.alexmmd.service.GetMyUserInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class GetMyUserInfoServiceImpl implements GetMyUserInfoService {

    private final UsersRolesDao usersRolesDao;

    public GetMyUserInfoServiceImpl(UsersRolesDao usersRolesDao) {
        this.usersRolesDao = usersRolesDao;
    }

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @Override
    public RespEntity queryRoles() {
        // 获取当前登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 根据 username 查询角色信息
        List<UsersRolesVo> usersRolesVoList = usersRolesDao.queryRoles(username);
        GetMyUserInfoVo getMyUserInfoVo = new GetMyUserInfoVo();
        List<Role> roleList = new ArrayList<>();
        for (UsersRolesVo usersRolesVo : usersRolesVoList) {
            roleList.add(Role.builder()
                    .id(usersRolesVo.getRoleId())
                    .name(usersRolesVo.getRoleName())
                    .build());
            getMyUserInfoVo.setEmail(usersRolesVo.getEmail());
            getMyUserInfoVo.setUserId(usersRolesVo.getUserId());
            getMyUserInfoVo.setUsername(usersRolesVo.getUsername());
        }
        getMyUserInfoVo.setRoleList(roleList);
        return new RespEntity(100, "成功查询所有角色信息", getMyUserInfoVo);
    }
}
