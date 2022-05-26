package com.sgcc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sgcc.constant.MessageConstant;
import com.sgcc.entity.PageResult;
import com.sgcc.entity.QueryPageBean;
import com.sgcc.entity.Result;
import com.sgcc.pojo.TravelGroup;
import com.sgcc.service.impl.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/travelgroup")
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds) {
        try {
            travelGroupService.add(travelGroup, travelItemIds);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = travelGroupService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
            if (pageResult != null) {
                return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS, pageResult);
            } else {
                return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
            }

        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer travelGroupId) {
        try {
            TravelGroup travelGroup = travelGroupService.findById(travelGroupId);
            if (travelGroup != null) {
                return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS, travelGroup);
            } else {
                return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
            }
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public Result findTravelItemIdByTravelgroupId(Integer travelGroupId) {
        try {
            List<Integer> travelItemList = travelGroupService.findTravelItemIdByTravelgroupId(travelGroupId);
            if (travelItemList != null) {
                return new Result(true, MessageConstant.QUERY_TRAVELITEMLIST_SUCCWSS, travelItemList);
            } else {
                return new Result(false, MessageConstant.QUERY_TRAVELITEMLIST_FAIL);
            }
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELITEMLIST_FAIL);
        }
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds) {
        try {
            travelGroupService.update(travelGroup, travelItemIds);
            return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }

    }
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelGroup> travelGroupList = travelGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroupList);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

}
