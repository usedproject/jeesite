package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.RoleFunction;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleFunctionDao {
    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("funcid") Integer funcid);

    int insert(RoleFunction record);

    List<RoleFunction> selectAll();
}