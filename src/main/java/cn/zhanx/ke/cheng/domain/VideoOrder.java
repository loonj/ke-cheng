package cn.zhanx.ke.cheng.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VideoOrder implements Serializable {
    private static final long serialVersionUID  = 1L;

    //@ApiModelProperty(value = "主键，系统自动生成",example = "0",hidden = true)
    private Long id;
    private String outTradeNo;
    private Long state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT=8")
    private Date createTime;
    private Long totalFee;
    private Long videoId;
    private String videoTitle;
    private String videoImg;
    private Long userId;

}
