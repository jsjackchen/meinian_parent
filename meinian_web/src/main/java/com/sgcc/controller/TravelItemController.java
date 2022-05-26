package com.sgcc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sgcc.constant.MessageConstant;
import com.sgcc.entity.PageResult;
import com.sgcc.entity.QueryPageBean;
import com.sgcc.entity.Result;
import com.sgcc.pojo.TravelItem;
import com.sgcc.service.impl.TravelItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    @RequestMapping("/add")
    public Result add(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.add(travelItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        }
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = travelItemService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
            if (pageResult != null) {
                return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, pageResult);
            } else {
                return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
            }

        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            TravelItem travelItem = travelItemService.findById(id);
            if (travelItem != null) {
                return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, travelItem);
            } else {
                return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
            }

        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        try {
            travelItemService.edit(travelItem);
            return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            travelItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelItem> travelItemList = travelItemService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItemList);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

}
