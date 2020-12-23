package cn.zhanx.ke.cheng.service;

import cn.zhanx.ke.cheng.domain.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    int save(Long userId,Long videoId);

    List<VideoOrder> listOrder(Long userId);
}
