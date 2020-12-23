package cn.zhanx.ke.cheng.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VideoBanner implements Serializable {
    private static final long serialVersionUID  = 1L;

    //@ApiModelProperty(value = "主键，系统自动生成",example = "0",hidden = true)
    private Long id;
    private String url;
    private String img;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT=8")
    private Date createTime;
    private Long weight;
}
