package com.sgcc.dao;

import com.github.pagehelper.Page;
import com.sgcc.pojo.TravelGroup;
import com.sgcc.pojo.TravelItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void setTravelGroupAndTravelItem(@Param("travelGroupId") Integer travelGroupId, @Param("travelItemId")Integer travelItemId);

    Page<TravelItem> findPage(String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer travelGroupId);

    void update(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    List<TravelGroup> findAll();
}
