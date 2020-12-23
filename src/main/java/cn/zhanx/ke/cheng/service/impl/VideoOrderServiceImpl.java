package cn.zhanx.ke.cheng.service.impl;

import cn.zhanx.ke.cheng.domain.Episode;
import cn.zhanx.ke.cheng.domain.PlayRecord;
import cn.zhanx.ke.cheng.domain.Video;
import cn.zhanx.ke.cheng.domain.VideoOrder;
import cn.zhanx.ke.cheng.exception.CustomException;
import cn.zhanx.ke.cheng.mapper.*;
import cn.zhanx.ke.cheng.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;


    @Override
    @Transactional(rollbackFor=Exception.class)
    public int save(Long userId, Long videoId) {
        VideoOrder videoOrder=videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1L);
        if(videoOrder!=null){return 0;}

        Video video=videoMapper.findById(videoId);
        VideoOrder newVideoOrder=new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1L);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());
        newVideoOrder.setVideoId(videoId);
        int rows= videoOrderMapper.saveOrder(newVideoOrder);

        //生成播放记录
        if(rows==1){
            Episode episode=episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode==null){
                throw new CustomException(-1,"视频没有集信息,请运营人员检查");
            }
            PlayRecord playRecord=new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrder(Long userId) {
        return videoOrderMapper.listOrder(userId);
    }
}
