package com.springboot.Controller;

import com.springboot.dao.BearingCapDao;
import com.springboot.domain.BearingCap;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.BearingCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bearingCap")
public class BearingCapController {
    @Autowired
    private BearingCapService bearingCapService;
    @Autowired
    private BearingCapDao bearingCapDao;

    @RequestMapping("/addbearingCap")
    @ResponseBody
    public Result addbearingCap(@RequestBody BearingCap bearingCap){
        bearingCapService.addBearingCap(bearingCap);
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/modifyBearingCap")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody BearingCap bearingCap){
        bearingCapService.updateBearingCap(bearingCap);
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/unFinishBearingCap")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = bearingCapDao.findWheelInfoToBearingCap();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findBearingCapById")
    @ResponseBody
    public Result findBearingCapById(String id){
        BearingCap bearingCap = bearingCapDao.findBearingCapByWheelId(Integer.parseInt(id));
        return new Result(bearingCap,"添加成功",100);
    }

    @RequestMapping("/deleteBearingCap")
    @ResponseBody
    public Result deleteBearingCap(String id){
        bearingCapService.deleteBearingCap(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingCapService.searchWheelInfoBycondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
