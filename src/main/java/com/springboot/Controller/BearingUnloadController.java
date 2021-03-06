package com.springboot.Controller;

import com.springboot.dao.BearingRepairDao;
import com.springboot.dao.BearingUnLoadDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.*;
import com.springboot.service.BearingLoadService;
import com.springboot.service.BearingRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping("/bearingUnLoad")
public class BearingUnloadController {
    @Autowired
    private BearingRepairService bearingRepairService;
    @Autowired
    private BearingLoadService bearingLoadService;
    @Autowired
    private BearingRepairDao bearingRepairDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private BearingUnLoadDao bearingUnLoadDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/add")
    @ResponseBody
    public Result addBearingUnLoad(@RequestBody List<BearingUnLoad> list){
        for (BearingUnLoad bearingUnLoad : list){
            if (bearingUnLoad == null) continue;
            bearingUnLoad.setUnloadDate(dateFormater.format(new Date()));
            bearingLoadService.addBearingUnLoad(bearingUnLoad);
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(bearingUnLoad.getWheelId());
            if("0".equals(wheelInfo.getIsmagnetInspectionFinish())){
                redisTemplate.opsForSet().add("preMagInspection",wheelInfo.getWheelId());
            }else {

            }
        }
        return new Result(null,"添加成功",100);
    };

    @RequestMapping("/unFinishBearingUnCap")
    @ResponseBody
    public Result unFinishBearing(){
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoToBearing();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/unFinish")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preBearingUnLoad");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            if (wheelInfo!=null){
                wheelInfoList.add(wheelInfo);
            }
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheel")
    @ResponseBody
    public Result chooseWheel(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preBearingUnLoad",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preBearingUnLoad",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preBearingUnLoad", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result searchWheelInfoByconditionRepair(@RequestBody SearchWheelParam param){
        List<WheelInfo> wheelInfoList = bearingRepairService.searchWheelInfoRepairCondition(param);
        if (wheelInfoList==null){
            return new Result(null,"添加失败",101);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
}
