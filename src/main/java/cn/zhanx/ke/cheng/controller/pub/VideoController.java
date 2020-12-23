package cn.zhanx.ke.cheng.controller.pub;

import cn.zhanx.ke.cheng.domain.Video;
import cn.zhanx.ke.cheng.service.VideoService;
import cn.zhanx.ke.cheng.vo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "videoController", tags = {"不需要登录接口"})
@RestController
@RequestMapping("public/video")
@Slf4j
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "查询Video", notes = "")
    @GetMapping("list")
    public Result<List<Video>> list(){
        return Result.success(videoService.listVideo());
    }

    @ApiOperation(value = "查询Video详情", notes = "")
    @GetMapping("find-detail-by-id")
    public Result<Video> findDetailById(@RequestParam(value = "video_id",required = true) int videoId){
        //int i=1/0;
        Video video=videoService.findDetailById(videoId);
        return Result.success(video);
    }

}
