package com.springboot.Controller;

import com.springboot.dao.MeasureDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import com.springboot.service.WheelMeasureService;
import com.springboot.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wheelTakein")
public class WheelMeasureController {
    @Autowired
    private WheelMeasureService wheelMeasureService;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private MeasureDao measureDao;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/addMeasure")
    @ResponseBody
    public Result addMeasure(@RequestBody WheelMeasure wheelMeasure){
        wheelMeasure.setFinishTime(dateFormater.format(new Date()));
        wheelMeasureService.addMeasure(wheelMeasure);
        return new Result(wheelMeasure,"添加成功",100);
    }

    @RequestMapping("/unFinishMeasure")
    @ResponseBody
    public Result findUnFinishMeasure(){
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToMeasure();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findMeasureById")
    @ResponseBody
    public Result findMeasureById(String id){
        WheelMeasure wheelMeasure = wheelMeasureService.findWheelMeasureByWheelId(Integer.parseInt(id));
        if (wheelMeasure==null){
            return new Result(null,"查找失败",101);
        }
        return new Result(wheelMeasure,"查找成功",100);
    }
    //废弃
    @RequestMapping("/flushWheelInfo")
    @ResponseBody
    public Result flushWheelInfo(String id,String index){
        return new Result(null,"添加失败",101);
    }

    @RequestMapping("/modifyMeasure")
    @ResponseBody
    public Result modifyMeasure(@RequestBody WheelMeasure wheelMeasure){
        wheelMeasure.setFinishTime(dateFormater.format(new Date()));
        wheelMeasureService.updateWheelMeasure(wheelMeasure);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/deleteMeasure")
    @ResponseBody
    public Result deleteMeasure(String id){
        wheelMeasureService.deleteWheelMeasure(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoByconditionMeasure")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = wheelMeasureService.searchWheelInfoMeasure(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
