package top.alexmmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * File 文档类
 *
 * @author 汪永晖
 */
@Data
@Document
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class File {

    @Id  // 主键
    private String id;
    private String name; // 文件名称
    private String contentType; // 文件类型
    private long size;
    private Date uploadDate;
    private String md5;
    private Binary content; // 文件内容
    private String path; // 文件路径
    private String viewPath;

    public File(String originalFilename, String contentType, long size, Binary binary) {
        this.name = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.content = binary;
    }

    public File() {

    }
}
