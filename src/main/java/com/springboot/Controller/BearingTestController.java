package com.springboot.Controller;

import com.springboot.dao.BearingTestDao;
import com.springboot.domain.*;
import com.springboot.service.BearingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bearingTest")
public class BearingTestController {
    @Autowired
    private BearingTestService bearingTestService;
    @Autowired
    private BearingTestDao bearingTestDao;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/addbearingTest")
    @ResponseBody
    public Result addbearingCap(@RequestBody BearingTest bearingTest){
        bearingTest.setFinishTime(dateFormater.format(new Date()));
        bearingTestService.addBearingTest(bearingTest);
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/modifyBearingTest")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody BearingTest bearingTest){
        bearingTest.setFinishTime(dateFormater.format(new Date()));
        bearingTestService.updateBearingTest(bearingTest);
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/unFinishBearingTest")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = bearingTestDao.findWheelInfoToBearingTest();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findBearingTestById")
    @ResponseBody
    public Result findBearingCapById(String id){
        BearingTest bearingTest = bearingTestDao.findBearingTestByWheelId(Integer.parseInt(id));
        return new Result(bearingTest,"添加成功",100);
    }

    @RequestMapping("/deleteBearingTest")
    @ResponseBody
    public Result deleteBearingCap(String id){
        bearingTestService.deleteBearingTest(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoByconditionTest")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingTestService.searchWheelInfoTest(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
