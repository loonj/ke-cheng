package cn.zhanx.ke.cheng.mapper;

import cn.zhanx.ke.cheng.domain.Episode;
import cn.zhanx.ke.cheng.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeMapper {

    Episode findFirstEpisodeByVideoId(Long videoId);

}
