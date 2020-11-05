package top.alexmmd.service;

import org.springframework.web.multipart.MultipartFile;
import top.alexmmd.domain.LargeFile;

import java.io.IOException;

/**
 * @author 汪永晖
 */
public interface LargeFileService {

    /**
     * 新增文件
     *
     * @param file
     * @return
     */
    String addLargeFile(MultipartFile file) throws IOException;

    /**
     * 获取文件
     *
     * @param id
     * @return
     */
    LargeFile getLargeFile(String id) throws IOException;
}
