package cn.zhanx.ke.cheng.controller.pri;

import cn.zhanx.ke.cheng.domain.User;
import cn.zhanx.ke.cheng.service.UserService;
import cn.zhanx.ke.cheng.vo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "userController", tags = {"需要登录接口"})
@RestController
@RequestMapping("api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据token查询用户信息", notes = "")
    @GetMapping("find-by-token")
    public Result<User> findByToken(HttpServletRequest request){
        Long userId=Long.parseLong(request.getAttribute("user_id").toString());
        if(userId==null){
            return Result.error("查询失败");
        }
        User user=userService.findByUserId(userId);
        return Result.success(user);
    }

    @ApiOperation(value = "根据用户id查用户", notes = "")
    @GetMapping("find-by-user-id")
    public Result<User> findByUserId(@RequestParam(value = "user_id",required = true) Long userId){
        User user=userService.findByUserId(userId);
        return Result.success(user);
    }
}
