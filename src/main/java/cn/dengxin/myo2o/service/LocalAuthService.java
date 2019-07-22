package cn.dengxin.myo2o.service;

import cn.dengxin.myo2o.dto.LocalAuthExecution;
import cn.dengxin.myo2o.entity.LocalAuth;
import cn.dengxin.myo2o.exceptions.LocalAuthOperationException;

/**
 * Created by Dengxin on 2019/7/20 3:36 PM
 */
public interface LocalAuthService {
    //通过账号和密码获取平台账号信息
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    //通过userId获取平台账号信息
    LocalAuth getLocalAuthByUserId(long userId);

    //绑定微信，生成平台的专属账号
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    //修改平台账号的登陆密码
    LocalAuthExecution modifyLocalAuth(Long userId,String username,String password,String newPassword) throws LocalAuthOperationException;
}
