package cn.zhanx.ke.cheng.mapper;

import cn.zhanx.ke.cheng.domain.VideoBanner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoBannerMapper {

    List<VideoBanner> listVideoBanner();
}
