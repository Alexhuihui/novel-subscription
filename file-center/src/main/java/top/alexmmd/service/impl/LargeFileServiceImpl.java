package top.alexmmd.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.alexmmd.domain.LargeFile;
import top.alexmmd.service.LargeFileService;

import java.io.IOException;

/**
 * @author 汪永晖
 */
@Service
public class LargeFileServiceImpl implements LargeFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    /**
     * 新增文件
     *
     * @param file
     * @return
     */
    @Override
    public String addLargeFile(MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", file.getContentType());
        metaData.put("title", file.getOriginalFilename());
        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);
        return id.toString();
    }

    /**
     * 获取文件
     *
     * @param id
     * @return
     */
    @Override
    public LargeFile getLargeFile(String id) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        LargeFile largeFile = new LargeFile();
        largeFile.setTitle(file.getMetadata().get("title").toString());
        largeFile.setStream(operations.getResource(file.getFilename()).getInputStream());
        return largeFile;
    }
}
