package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.News;
import java.util.List;

public interface NewsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    List<News> selectAll();

    int updateByPrimaryKey(News record);
}