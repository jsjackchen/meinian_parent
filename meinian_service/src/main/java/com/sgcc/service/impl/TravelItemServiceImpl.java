package com.sgcc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.dao.TravelItemDao;
import com.sgcc.entity.PageResult;
import com.sgcc.pojo.TravelItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    private TravelItemDao travelItemDao;

    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        if(!StringUtils.isEmpty(queryString)){
            queryString="%"+queryString+"%";
        }
        Page<TravelItem> page = travelItemDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public TravelItem findById(Integer id) {
        return travelItemDao.findById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public void delete(Integer id) {
        long count = travelItemDao.findCountByTravelItemItemId(id);
        if(count > 0){
            throw new RuntimeException("不允许删除");
        }
        travelItemDao.delete(id);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }
}
