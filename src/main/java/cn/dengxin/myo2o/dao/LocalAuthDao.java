package cn.dengxin.myo2o.dao;

import cn.dengxin.myo2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Dengxin on 2019/7/20 2:33 PM
 */
public interface LocalAuthDao {
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    int insertLocalAuth(LocalAuth localAuth);

    int updateLocalAuth(@Param("userId") long userId, @Param("username") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
