package cn.zhanx.ke.cheng.service.impl;

import cn.zhanx.ke.cheng.config.CacheKeyManager;
import cn.zhanx.ke.cheng.domain.VideoBanner;
import cn.zhanx.ke.cheng.mapper.VideoBannerMapper;
import cn.zhanx.ke.cheng.service.VideoBannerService;
import cn.zhanx.ke.cheng.utils.BaseCache;
import cn.zhanx.ke.cheng.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoBannerServiceImpl implements VideoBannerService {

    @Autowired
    private VideoBannerMapper videoBannerMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<VideoBanner> listVideoBanner(){
        try{
            Object cacheObj=
            baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {
                List<VideoBanner> bannerList= videoBannerMapper.listVideoBanner();
                System.out.println("从数据库里找轮播图列表");
                return bannerList;
            });
            if(cacheObj instanceof List){
                List<VideoBanner> bannerList=(List<VideoBanner>) cacheObj;
                return bannerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
