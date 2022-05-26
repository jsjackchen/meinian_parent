package com.sgcc.service.impl;

import com.sgcc.entity.PageResult;
import com.sgcc.pojo.TravelItem;

import java.util.List;

public interface TravelItemService {
    void add(TravelItem travelItem);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    void delete(Integer id);

    List<TravelItem> findAll();
}
