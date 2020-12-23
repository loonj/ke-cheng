package cn.zhanx.ke.cheng.service;

import cn.zhanx.ke.cheng.domain.Video;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    Video findDetailById(int videoId);
}
