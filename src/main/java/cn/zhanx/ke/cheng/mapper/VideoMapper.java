package cn.zhanx.ke.cheng.mapper;

import cn.zhanx.ke.cheng.domain.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {

    List<Video> listVideo();

    Video findDetailById(@Param("video_id") int videoId);

    Video findById(@Param("video_id") Long videoId);
}
