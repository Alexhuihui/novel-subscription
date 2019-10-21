package top.alexmmd.service;

import top.alexmmd.domain.Fiction;

import java.util.List;

/**
 * @author 汪永晖
 */
public interface FictionService {


    Fiction findFictionById(Long id);

    List<Fiction> searchByKeyword(String keyword);
}
