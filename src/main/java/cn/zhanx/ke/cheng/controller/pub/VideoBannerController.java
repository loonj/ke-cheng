package cn.zhanx.ke.cheng.controller.pub;

import cn.zhanx.ke.cheng.config.CacheKeyManager;
import cn.zhanx.ke.cheng.domain.VideoBanner;
import cn.zhanx.ke.cheng.service.VideoBannerService;
import cn.zhanx.ke.cheng.vo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "videoBannerController", tags = {"不需要登录接口"})
@RestController
@RequestMapping("public/video-banner")
@Slf4j
public class VideoBannerController {
    @Autowired
    private VideoBannerService videoBannerService;



    @ApiOperation(value = "查询VideoBanner", notes = "")
    @GetMapping("list")
    public Result<List<VideoBanner>> list(){
        return Result.success(videoBannerService.listVideoBanner());
    }

}
