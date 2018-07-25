package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.Message;
import java.util.List;

public interface MessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);
}