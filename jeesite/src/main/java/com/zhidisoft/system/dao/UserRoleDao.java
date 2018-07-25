package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDao {
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    int insert(UserRole record);

    List<UserRole> selectAll();
}