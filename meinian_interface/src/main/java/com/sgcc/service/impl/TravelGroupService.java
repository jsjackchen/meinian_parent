package com.sgcc.service.impl;

import com.sgcc.entity.PageResult;
import com.sgcc.pojo.TravelGroup;

import java.util.List;

public interface TravelGroupService {
    void add(TravelGroup travelGroup, Integer[] travelItemIds);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    TravelGroup findById(Integer travelGroupId);

    List<Integer> findTravelItemIdByTravelgroupId(Integer travelGroupId);

    void update(TravelGroup travelGroup, Integer[] travelItemIds);

    List<TravelGroup> findAll();
}
