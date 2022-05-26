package com.sgcc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sgcc.dao.SetmealDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService  {

    @Autowired
    private SetmealDao setmealDao;
}
