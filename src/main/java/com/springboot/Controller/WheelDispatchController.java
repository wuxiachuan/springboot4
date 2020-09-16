package com.springboot.Controller;

import com.springboot.dao.BearingTestDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.*;
import com.springboot.service.BearingTestService;
import com.springboot.service.WheelDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wheelDispatch")
public class WheelDispatchController {
    @Autowired
    private WheelDispatchService wheelDispatchService;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;

    @RequestMapping("/addWheelDispatch")
    @ResponseBody
    public Result addbearingCap(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatchService.addWheelDispatch(wheelDispatch);
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/modifyWheelDispatch")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatchService.updateWheelDispatch(wheelDispatch);
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/unFinishWheelDispatch")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = wheelDispatchDao.findWheelInfoToWheelDispatch();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findWheelDispatchById")
    @ResponseBody
    public Result findBearingCapById(String id){
        WheelDispatch wheelDispatch = wheelDispatchDao.findWheelDispatchByWheelId(Integer.parseInt(id));
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/deleteWheelDispatch")
    @ResponseBody
    public Result deleteBearingCap(String id){
        wheelDispatchService.deleteWheelDispatch(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoByconditionDispatch")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = wheelDispatchService.searchWheelInfoDispatch(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/dispatch")
    @ResponseBody
    public Result dodispatch(@RequestBody List<VehicleInfo> numlist){
        System.out.println(numlist);
        List<VehicleInfo> res = wheelDispatchService.dispatch(numlist);
        return new Result(res,"添加成功",100);
    }

    @RequestMapping("/getvehicleNum")
    @ResponseBody
    public Result getvehicleNum(){
        List<VehicleInfo> list = wheelDispatchDao.findvehicleNum();
        return new Result(list,"添加成功",100);
    }
    
    @RequestMapping("/find2match")
    @ResponseBody
    public Result find2match(@RequestBody VehicleInfo vehicleInfo){
        System.out.println(vehicleInfo);
        List<WheelDispatch> list = wheelDispatchService.find2match(vehicleInfo);
        return new Result(list,"添加成功",100);
    }

    @RequestMapping("/receiveResult")
    @ResponseBody
    public Result receiveResult(@RequestBody List<VehicleInfo> resultlist,String matcher){
        System.out.println(resultlist);
        System.out.println(matcher);
        wheelDispatchService.receiveResult(resultlist,matcher);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/adddata")
    @ResponseBody
    public Result adddata(){
         wheelDispatchService.adddata();
        return new Result(null,"添加成功",100);
    }
}
