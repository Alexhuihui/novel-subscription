package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.alexmmd.repository.NovelUserDao;
import top.alexmmd.repository.RoleDao;
import top.alexmmd.repository.UsersRolesDao;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.domain.Role;
import top.alexmmd.domain.UsersRoles;
import top.alexmmd.service.SignUpService;

import java.util.Date;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private final NovelUserDao userDao;

    private final RoleDao roleDao;

    private final UsersRolesDao usersRolesDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpServiceImpl(NovelUserDao userDao, RoleDao roleDao, UsersRolesDao usersRolesDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.usersRolesDao = usersRolesDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public RespEntity signUp(NovelUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.insert(user);
        Role role = roleDao.queryByRoleName("ROLE_USER");
        UsersRoles usersRoles = UsersRoles.builder()
                .roleId(role.getId())
                .userId(Long.valueOf(user.getId()))
                .build();
        usersRolesDao.insert(usersRoles);
        return new RespEntity(101, "注册成功");
    }
}
