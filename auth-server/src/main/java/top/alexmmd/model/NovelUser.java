package top.alexmmd.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-06-04 15:25:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NovelUser implements Serializable {
    private static final long serialVersionUID = -59001326894668296L;
    
    private Integer id;
    
    private String email;
    
    private String password;

    private String username;

    private Date createTime;
    
    private Date updateTime;

}