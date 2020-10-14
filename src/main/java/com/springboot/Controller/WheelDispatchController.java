package com.springboot.Controller;

import com.springboot.dao.BearingTestDao;
import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.*;
import com.springboot.service.BearingTestService;
import com.springboot.service.WheelDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/wheelDispatch")
public class WheelDispatchController {
    @Autowired
    private WheelDispatchService wheelDispatchService;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/addWheelDispatch")
    @ResponseBody
    public Result addWheelDispatchRemeasure(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatch.setFinishTime(dateFormater.format(new Date()));
        wheelDispatchService.addWheelDispatchRemeasure(wheelDispatch);
        redisTemplate.opsForSet().add("preQualityCheck",wheelDispatch.getWheelId());
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/modifyWheelDispatch")
    @ResponseBody
    public Result modifyWheelDispatchRemeasure(@RequestBody WheelDispatch wheelDispatch){
        wheelDispatch.setFinishTime(dateFormater.format(new Date()));
        wheelDispatchService.updateWheelDispatchRemeasure(wheelDispatch);
        redisTemplate.opsForSet().add("preQualityCheck",wheelDispatch.getWheelId());
        return new Result(wheelDispatch,"添加成功",100);
    }

    @RequestMapping("/unFinishWheelDispatch")
    @ResponseBody
    public Result unFinishWheelDispatch(){
        List<WheelInfo> wheelInfoList = wheelDispatchDao.findWheelInfoToWheelDispatchRemeasure();
        return new Result(wheelInfoList,"添加成功",100);
    }

    @RequestMapping("/unFinishWheelDispatch2")
    @ResponseBody
    public Result unFinishBearing2(){
        List<WheelInfo> wheelInfoList = new ArrayList<>();
        Set<Integer> set = redisTemplate.opsForSet().members("preRemeasure");
        for (Integer id:set){
            WheelInfo wheelInfo = wheelDao.findWheelInfoById(id);
            wheelInfoList.add(wheelInfo);
        }
        return new Result(wheelInfoList,"添加成功",100);
    }
    @RequestMapping("/chooseWheel")
    @ResponseBody
    public Result chooseWheel(String id){
        Integer wheelId = Integer.parseInt(id);
        Boolean res = redisTemplate.opsForSet().isMember("preRemeasure",wheelId);
        if (res){
            redisTemplate.opsForSet().remove("preRemeasure",wheelId);
            return new Result(null,"添加成功",100);
        }else {
            return new Result(null,"添加失败",101);
        }
    }

    @RequestMapping("/turnBack")
    @ResponseBody
    public Result turnBack(String id){
        Integer wheelId = Integer.parseInt(id);
        redisTemplate.opsForSet().add("preRemeasure", wheelId);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/savedWheelInfo")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedRemeasureInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelInfo")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedRemeasureInfo");
        return new Result(data,"添加成功",100);
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
        List<VehicleInfo> res = wheelDispatchService.dispatch(numlist);
        return new Result(res,"添加成功",100);
    }

    @RequestMapping("/getvehicleNum")
    @ResponseBody
    public Result getvehicleNum(@RequestBody Map<String,String> map){
        String vnum = map.get("vehicleNum");
        String takeInDateFrom = map.get("takeInDateFrom");
        String takeInDateTo = map.get("takeInDateTo");
        List<VehicleInfo> list = wheelDispatchDao.findvehicleNum(vnum,takeInDateFrom,takeInDateTo);
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
