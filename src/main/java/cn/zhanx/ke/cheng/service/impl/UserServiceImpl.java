package cn.zhanx.ke.cheng.service.impl;

import cn.zhanx.ke.cheng.domain.User;
import cn.zhanx.ke.cheng.mapper.UserMapper;
import cn.zhanx.ke.cheng.service.UserService;
import cn.zhanx.ke.cheng.utils.CommonUtils;
import cn.zhanx.ke.cheng.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        user.setCreateTime(new Date());
        if(!StringUtils.isEmpty(user.getPwd())) {
            user.setPwd(CommonUtils.MD5(user.getPwd()));
        }
        if(StringUtils.isEmpty(user.getHeadImg())){
            user.setHeadImg(getRandomImg());
        }
        if(user!=null){
            return userMapper.save(user);
        }
        return -1;
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user=userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
        if(user==null){
            return null;
        }else{
            return JWTUtils.generateJsonWebToken(user);
        }
    }

    @Override
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }

    @Override
    public User findByUserId(Long userId) {
        User user=userMapper.findByUserId(userId);
        return user;
    }

    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        Random random=new Random();
        int index=random.nextInt(headImg.length);
        return headImg[index];
    }
}
