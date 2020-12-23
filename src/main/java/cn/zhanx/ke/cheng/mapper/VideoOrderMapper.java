package cn.zhanx.ke.cheng.mapper;

import cn.zhanx.ke.cheng.domain.VideoOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoOrderMapper {

    //查询用户是否购买过此商品
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") Long userId, @Param("video_id") Long videoId, @Param("state") Long state);

    int saveOrder(VideoOrder videoOrder);

    List<VideoOrder> listOrder(Long userId);
}
