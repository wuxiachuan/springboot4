package com.springboot.Controller;

import com.springboot.dao.BearingRepairDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingRepair;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.BearingRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bearingRepair")
public class BearingRepairController {
    @Autowired
    private BearingRepairService bearingRepairService;
    @Autowired
    private BearingRepairDao bearingRepairDao;
    @Autowired
    private WheelDao wheelDao;

    @RequestMapping("/addBearing")
    @ResponseBody
   public Result addBearing(@RequestBody BearingRepair bearingRepair){
        BearingRepair result = bearingRepairService.addBearingRepair(bearingRepair);
        return new Result(result,"添加成功",100);
    };
   @RequestMapping("/unFinishBearing")
   @ResponseBody
    public Result unFinishBearing(){
       List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToBearing();
       return new Result(wheelInfoList,"添加成功",100);
   }

   @RequestMapping("/modifyBearing")
   @ResponseBody
   public Result modifyBearing(@RequestBody BearingRepair bearingRepair){
       bearingRepairService.updateBearingRepair(bearingRepair);
       return new Result(bearingRepair,"添加成功",100);
   }

   @RequestMapping("/findBearingById")
    @ResponseBody
    public Result findBearingById(String id){
       BearingRepair bearingRepair = bearingRepairDao.findBearingRepairByWheelId(Integer.parseInt(id));
      if (bearingRepair == null){
          return new Result(null,"查找",101);
      }
       return new Result(bearingRepair,"添加成功",100);
   }

   @RequestMapping("/deleteBearing")
    @ResponseBody
    public Result deleteBearing(String id){
        bearingRepairService.deleteBearing(id);
        return new Result(null,"删除成功",100);
   }
    //废弃
    @RequestMapping("/flushWheelInfo")
    @ResponseBody
    public Result flushWheelInfo(String id,String index){
        return new Result(null,"添加失败",101);
    }

    @RequestMapping("/searchWheelInfoByconditionRepair")
    @ResponseBody
    public Result searchWheelInfoByconditionRepair(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingRepairService.searchWheelInfoRepairCondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
