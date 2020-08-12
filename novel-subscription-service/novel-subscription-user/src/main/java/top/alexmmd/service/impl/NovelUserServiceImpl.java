package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.NovelUser;
import top.alexmmd.repository.NovelUserDao;
import top.alexmmd.service.NovelUserService;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class NovelUserServiceImpl implements NovelUserService {

    private final NovelUserDao userDao;

    public NovelUserServiceImpl(NovelUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public NovelUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
