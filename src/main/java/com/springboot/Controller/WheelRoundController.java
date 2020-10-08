package com.springboot.Controller;

import com.springboot.dao.WheelRoundDao;
import com.springboot.domain.*;
import com.springboot.service.WheelRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wheelRound")
public class WheelRoundController {
    @Autowired
    private WheelRoundDao wheelRoundDao;
    @Autowired
    private WheelRoundService wheelRoundService;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/addWheelRound")
    @ResponseBody
    public Result addbearingCap(@RequestBody WheelRound wheelRound){
        wheelRound.setFinishTime(dateFormater.format(new Date()));
        wheelRoundService.addWheelRound(wheelRound);
        return new Result(wheelRound,"添加成功",100);
    }

    @RequestMapping("/modifyWheelRound")
    @ResponseBody
    public Result modifyBearingCap(@RequestBody WheelRound wheelRound){
        wheelRound.setFinishTime(dateFormater.format(new Date()));
        wheelRoundService.updateWheelRound(wheelRound);
        return new Result(wheelRound,"添加成功",100);
    }

    @RequestMapping("/unFinishWheelRound")
    @ResponseBody
    public Result unFinishBearingCap(){
        List<WheelInfo> wheelInfoList = wheelRoundDao.findWheelInfoToWheelRound();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/findWheelRoundById")
    @ResponseBody
    public Result findWheelRoundById(String id){
        WheelRound wheelRound = wheelRoundDao.findWheelRoundByWheelId(Integer.parseInt(id));
        return new Result(wheelRound,"添加成功",100);
    }

    @RequestMapping("/deleteWheelRound")
    @ResponseBody
    public Result deleteBearingCap(String id){
        wheelRoundService.deleteWheelRound(id);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchWheelInfoByconditionWheelRound")
    @ResponseBody
    public Result searchWheelInfoBycondition(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = wheelRoundService.searchWheelInfoWheelRound(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/getoriginwheelround")
    @ResponseBody
    public Result getoriginwheelround(String id){
        System.out.println(id);
        WheelMeasure wheelMeasure = wheelRoundDao.getoriginwheelround(Integer.parseInt(id));
        if (wheelMeasure==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelMeasure,"添加成功",100);
    }
}
