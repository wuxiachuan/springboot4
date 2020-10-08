package com.springboot.Controller;

import com.springboot.dao.AxleInspectionDao;
import com.springboot.domain.*;
import com.springboot.service.AxleInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/axleInspection")
public class AxleInspectionController {
    @Autowired
    private AxleInspectionDao axleInspectionDao;
    @Autowired
    private AxleInspectionService axleInspectionService;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //超探
    @RequestMapping("/addaxleInspection")
    @ResponseBody
    public Result magneticInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setUltfinishTime(dateFormater.format(new Date()));
        axleInspection.setReultfinishTime(dateFormater.format(new Date()));
        axleInspectionService.addAxleInspection(axleInspection);
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/modifyAxleInspection")
    @ResponseBody
    public Result modifyAxleInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setUltfinishTime(dateFormater.format(new Date()));
        axleInspection.setReultfinishTime(dateFormater.format(new Date()));
        axleInspectionService.updateAxleInspection(axleInspection);
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/unFinishAxleInspection")
    @ResponseBody
    public Result unFinishAxleInspection(){
        List<WheelInfo> wheelInfoList = axleInspectionDao.findWheelInfoToAxleInspection();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findAxleInspectionById")
    @ResponseBody
    public Result findAxleInspectionById(String id){
        AxleInspection axleInspection = axleInspectionDao.findAxleInspectionByWheelId(Integer.parseInt(id));
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/deleteAxleInspection")
    @ResponseBody
    public Result deleteAxleInspection(String id){
        axleInspectionService.deleteBearingAxleInspection(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoByconditionAxleInspection")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = axleInspectionService.searchWheelInfoAxleInspection(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }

    //磁探
    @RequestMapping("/addMagInspection")
    @ResponseBody
    public Result addMagInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setMagfinishTime(dateFormater.format(new Date()));
        axleInspectionService.addMagInspection(axleInspection);
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/modifyMagInspection")
    @ResponseBody
    public Result modifyMagInspection(@RequestBody AxleInspection axleInspection){
        axleInspection.setMagfinishTime(dateFormater.format(new Date()));
        axleInspectionService.updateMagInspection(axleInspection);
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/unFinishMagInspection")
    @ResponseBody
    public Result unFinishMagInspection(){
        List<WheelInfo> wheelInfoList = axleInspectionDao.findWheelInfoToMagInspection();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findMagInspectionById")
    @ResponseBody
    public Result findMagInspectionById(String id){
        AxleInspection axleInspection = axleInspectionDao.findMagInspectionByWheelId(Integer.parseInt(id));
        return new Result(axleInspection,"添加成功",100);
    }

    @RequestMapping("/deleteMagInspection")
    @ResponseBody
    public Result deleteMagInspection(String id){
        axleInspectionService.deleteBearingMagInspection(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoByconditionMagInspection")
    @ResponseBody
    public Result searchWheelInfoByconditionMagInspection(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = axleInspectionService.searchWheelInfoMagInspection(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }

}
