package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.Replay;
import java.util.List;

public interface ReplayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Replay record);

    Replay selectByPrimaryKey(Integer id);

    List<Replay> selectAll();

    int updateByPrimaryKey(Replay record);
}