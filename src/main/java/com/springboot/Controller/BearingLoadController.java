package com.springboot.Controller;

import com.springboot.dao.BearingLoadDao;
import com.springboot.domain.*;
import com.springboot.service.BearingLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bearingLoad")
public class BearingLoadController {
    @Autowired
    private BearingLoadService bearingLoadService;
    @Autowired
    private BearingLoadDao bearingLoadDao;

    @RequestMapping("/addBearingLoad")
    @ResponseBody
    public Result addBearingLoad(@RequestBody BearingLoad bearingLoad){
        bearingLoadService.addBearingLoad(bearingLoad);
        return new Result(bearingLoad,"添加成功",100);
    }
    @RequestMapping("/modifyBearingLoad")
    @ResponseBody
    public Result updateBearingLoad(@RequestBody BearingLoad bearingLoad ){
        bearingLoadService.updateBearingLoad(bearingLoad);
        return new Result(bearingLoad,"添加成功",100);
    }

    @RequestMapping("/unFinishBearingLoad")
    @ResponseBody
    public Result unFinishBearingLoad(){
        List<WheelInfo> wheelInfoList = bearingLoadDao.findWheelInfoToBearingLoad();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoBycondition")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingLoadService.searchWheelInfoBycondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }


    @RequestMapping("/findBearingLoadById")
    @ResponseBody
    public Result findBearingById(String id){
        BearingLoad bearingLoad = bearingLoadDao.findBearingLoadByWheelId(Integer.parseInt(id));
        if (bearingLoad == null){
            return new Result(null,"查找",101);
        }
        return new Result(bearingLoad,"添加成功",100);
    }

    @RequestMapping("/deleteBearingLoad")
    @ResponseBody
    public Result deleteBearingLoad(String id,String index){
        bearingLoadService.deleteBearing(id,index);
        return new Result(null,"删除成功",100);
    }
    @RequestMapping("/test")
    @ResponseBody
    public Result deleteBearingLoad(){
        BearingLoad bearingLoad = new BearingLoad();
        bearingLoad.setRepairProgress("222");
        bearingLoadDao.insertBearingLoad(bearingLoad);
        return new Result(bearingLoad,"删除成功",100);
    }
}
