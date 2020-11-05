package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alexmmd.domain.LargeFile;
import top.alexmmd.domain.RespEntity;
import top.alexmmd.service.LargeFileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Uploading Large Files (size unlimited)
 *
 * @author 汪永晖
 */
@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class FileController {

    private final LargeFileService largeFileService;


    public FileController(LargeFileService largeFileService) {
        this.largeFileService = largeFileService;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadLargeFile")
    public RespEntity uploadLargeFile(@RequestParam("file") MultipartFile file) throws IOException {
        String id = largeFileService.addLargeFile(file);
        return new RespEntity(100, "成功上传文件", id);
    }

    /**
     * 下载文件
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("/getLargeFile/{id}")
    public void getLargeFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        LargeFile file = largeFileService.getLargeFile(id);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(file.getTitle().getBytes("utf-8"), "ISO-8859-1"));

        FileCopyUtils.copy(file.getStream(), response.getOutputStream());
    }

    /**
     * 文件在线预览
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("/seeLargeFile/{id}")
    public void seeLargeFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        LargeFile file = largeFileService.getLargeFile(id);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(file.getTitle().getBytes("utf-8"), "ISO-8859-1"));
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        FileCopyUtils.copy(file.getStream(), response.getOutputStream());
    }
}
