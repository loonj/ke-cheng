package cn.zhanx.ke.cheng.controller.pub;

import cn.zhanx.ke.cheng.domain.User;
import cn.zhanx.ke.cheng.service.UserService;
import cn.zhanx.ke.cheng.vo.common.Result;
import cn.zhanx.ke.cheng.vo.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "publicController", tags = {"不需要登录接口"})
@RestController
@RequestMapping("public/user")
@Slf4j
public class PublicController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户", notes = "")
    @PostMapping("register")
    public Result<String> register(@RequestBody User user){
        int rows=userService.save(user);
        return rows==1? Result.success("注册成功"):Result.error("注册失败，请重试");
    }

    @ApiOperation(value = "登录", notes = "")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginRequest loginRequest){
        String token=
                userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
        return token==null?Result.error("登录失败，账号或密码错误"):Result.success(token);
    }

}
