package top.alexmmd.service;

import top.alexmmd.domain.NovelUser;

/**
 * @author 汪永晖
 */
public interface NovelUserService {
    NovelUser findByUsername(String userName);
}
