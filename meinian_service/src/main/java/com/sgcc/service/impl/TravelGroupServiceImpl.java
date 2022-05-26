package com.sgcc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.dao.TravelGroupDao;
import com.sgcc.entity.PageResult;
import com.sgcc.entity.Result;
import com.sgcc.pojo.TravelGroup;
import com.sgcc.pojo.TravelItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupDao travelGroupDao;

    @Override
    public void add(TravelGroup travelGroup, Integer[] travelItemIds) {
        try {
            travelGroupDao.add(travelGroup);
            setTravelGroupAndTravelItem(travelGroup.getId(), travelItemIds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        if(!StringUtils.isEmpty(queryString)){
            queryString="%"+queryString+"%";
        }
        Page<TravelItem> page = travelGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public TravelGroup findById(Integer travelGroupId) {
        return travelGroupDao.findById(travelGroupId);
    }

    @Override
    public List<Integer> findTravelItemIdByTravelgroupId(Integer travelGroupId) {
        return travelGroupDao.findTravelItemIdByTravelgroupId(travelGroupId);
    }

    @Override
    public void update(TravelGroup travelGroup, Integer[] travelItemIds) {
        try {
            travelGroupDao.update(travelGroup);
            travelGroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(travelGroup.getId());
            setTravelGroupAndTravelItem(travelGroup.getId(), travelItemIds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }


    private void setTravelGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        if (travelItemIds != null && travelItemIds.length > 0) {
            for (Integer travelItemId : travelItemIds) {
                travelGroupDao.setTravelGroupAndTravelItem(travelGroupId, travelItemId);
            }
        }

    }
}
