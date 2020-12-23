package cn.zhanx.ke.cheng.service;


import cn.zhanx.ke.cheng.domain.User;


public interface UserService {


    int save(User user);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByPhone(String phone);

    User findByUserId(Long userId);
}
