package top.alexmmd.service;

import top.alexmmd.domain.NovelUser;
import top.alexmmd.domain.RespEntity;

/**
 * @author 汪永晖
 */
public interface SignUpService {
    RespEntity signUp(NovelUser user);
}
