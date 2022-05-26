package com.sgcc.dao;

import com.github.pagehelper.Page;
import com.sgcc.pojo.TravelItem;

import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    void delete(Integer id);

    long findCountByTravelItemItemId(Integer id);

    List<TravelItem> findAll();
}
