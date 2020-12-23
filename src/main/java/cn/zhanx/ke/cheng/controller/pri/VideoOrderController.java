package cn.zhanx.ke.cheng.controller.pri;

import cn.zhanx.ke.cheng.domain.User;
import cn.zhanx.ke.cheng.domain.VideoOrder;
import cn.zhanx.ke.cheng.service.UserService;
import cn.zhanx.ke.cheng.service.VideoOrderService;
import cn.zhanx.ke.cheng.vo.common.Result;
import cn.zhanx.ke.cheng.vo.request.VideoOrderRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "videoOrderController", tags = {"需要登录接口"})
@RestController
@RequestMapping("api/video-order")
@Slf4j
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    @ApiOperation(value = "下单接口", notes = "")
    @PostMapping("save-order")
    public Result<String> saveOrder(@RequestBody VideoOrderRequest videoOrderRequest,HttpServletRequest request)
    {
        Long userId=(Long)request.getAttribute("user_id");
        int rows=videoOrderService.save(userId,videoOrderRequest.getVideoId());

        return rows==0?Result.error("下单失败"):Result.success();
    }

    @ApiOperation(value = "查询订单", notes = "")
    @GetMapping("list")
    public Result<List<VideoOrder>> list(HttpServletRequest request)
    {
        Long userId=(Long)request.getAttribute("user_id");
        List<VideoOrder> videoOrderList=videoOrderService.listOrder(userId);
        return Result.success(videoOrderList);
    }



}
