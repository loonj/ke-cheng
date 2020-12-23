package cn.zhanx.ke.cheng.service.impl;

import cn.zhanx.ke.cheng.config.CacheKeyManager;
import cn.zhanx.ke.cheng.domain.Video;
import cn.zhanx.ke.cheng.mapper.VideoMapper;
import cn.zhanx.ke.cheng.service.VideoService;
import cn.zhanx.ke.cheng.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {

        try{
            Object cacheObj=
            baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST, () -> videoMapper.listVideo());
            if(cacheObj instanceof List){
                return (List<Video>)cacheObj;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Video findDetailById(int videoId) {
        String videoCacheKey=String.format(CacheKeyManager.VIDEO_DETAIL,videoId);
        try{
            Object cacheObj=baseCache.getOneHourCache().get(videoCacheKey,()->videoMapper.findDetailById(videoId));
            if(cacheObj instanceof Video){
                return (Video)cacheObj;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
