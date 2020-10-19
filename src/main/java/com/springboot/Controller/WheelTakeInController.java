package com.springboot.Controller;

import com.springboot.dao.QRcodeDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.*;
import com.springboot.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wheelTakein")
public class WheelTakeInController {
    @Autowired
    private WheelService wheelService;
    @Autowired
    private QRcodeDao qRcodeDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private RedisTemplate redisTemplate;

    private SimpleDateFormat dateFormater;

    public WheelTakeInController(){
         this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addWheel(@RequestBody WheelInfo wheelInfo){
        wheelInfo.setInfoTakeFinishTime(dateFormater.format(new Date()));
        WheelInfo result = null;
        String path = null;
        try{
            result = wheelService.insertWheelInfo(wheelInfo);
            redisTemplate.opsForSet().add("preMeasure",result.getWheelId());
            path = wheelService.generateQRcode(result.getWheelId().toString());
        }catch(Exception e){
            System.out.println(e);
            return new Result(null,"添加失败",101);
        }
        return new Result(result,"添加成功",100);
    }

    @RequestMapping("/savedWheelTakein")
    @ResponseBody
    public Result savedWheelInfo(@RequestBody Map<String,String> map){
        String name = map.get("name");
        String data = map.get("data");
        redisTemplate.opsForValue().set(name+"savedWheelTakeinInfo",data);
        return new Result(null,"添加成功",100);
    }

    @RequestMapping("/getSavedWheelTakein")
    @ResponseBody
    public Result getSavedWheelInfo(String name){
        String data = (String) redisTemplate.opsForValue().get(name+"savedWheelTakeinInfo");
        return new Result(data,"添加成功",100);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result findwheelTakeinById(String id){
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(Integer.parseInt(id));
        if (wheelInfo == null){
            return new Result(null,"查找",101);
        }
        return new Result(wheelInfo,"添加成功",100);
    }

    @RequestMapping("/searchBycondition")
    @ResponseBody
    public Result findWheelInfo(@RequestBody SearchWheelParam param){
        List<WheelInfo> list = null;
        System.out.println(param);
        try{
            list = wheelService.findWheelInfo(param);
        }catch (Exception e){
            return new Result(null,"查询失败",100);
        }
         return new Result(list,"查询成功",100);
    }
    @RequestMapping("/modify")
    @ResponseBody
    public Result modifyWheel(@RequestBody WheelInfo wheelInfo){
        wheelInfo.setInfoTakeFinishTime(dateFormater.format(new Date()));
        //信息采集 0 未完成，1已完成
        wheelInfo.setInfoTakeFinish("1");
        //轮对测量 0 未完成，1已完成
        wheelInfo.setIsMeasureFinish("0");
        //轴承检查 0 未完成，1已完成
        wheelInfo.setIsbearingRepairFinish("0");
        //超声波探伤 -1不需要，0未完成，1已完成
        wheelInfo.setIsaxleInspectionFinish("-1");
        //车轮旋面 -1不需要，0未完成，1已完成
        wheelInfo.setIsWheelRoundingFinish("-1");
        //轴承压装 -1不需要，1左端压装，2右端压装，3两端压装，4已完成
        wheelInfo.setIsbearingLoadFinish("-1");
        //磁粉探伤 -1不需要，0未完成，1已完成
        wheelInfo.setIsmagnetInspectionFinish("-1");
        wheelInfo.setIsbearingCapFinish("-1");
        //旋修关盖 -1不需要，0未完成，1已完成
        wheelInfo.setIsbearingCapFinishW("-1");
        //推卸关盖 -1不需要，0未完成，1已完成
        wheelInfo.setIsbearingCapFinishL("-1");
        //磨合 0 未完成，1已完成
        wheelInfo.setIsbearingrollTestFinish("0");
        //支出 0 未完成，1已完成
        wheelInfo.setIsreMeasureFinish("0");
        //支出 0 未完成，1已完成
        wheelInfo.setIsWheelRoundingFinish("0");
        //质检 0 未完成，1已完成
        wheelInfo.setIsqualityInspectionFinish("0");
        //验收 0 未完成，1已完成
        wheelInfo.setIsverifyFinish("0");
        //是否完工 0 未完成，1已完成
        wheelInfo.setIsprocessFinish("0");
        //状态 0检修中，1良好存放，2支出，3报废存放，4已送厂
        wheelInfo.setState("0");
        WheelInfo result = null;
        result = wheelService.updateWheelInfo(wheelInfo);
        redisTemplate.opsForSet().add("preMeasure",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingRepair",result.getWheelId());
        redisTemplate.opsForSet().remove("preMagInspection",result.getWheelId());
        redisTemplate.opsForSet().remove("preAxleInspection",result.getWheelId());
        redisTemplate.opsForSet().remove("preWheelRounding",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingLoad",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrCap",result.getWheelId());
        redisTemplate.opsForSet().remove("preBearingrollTest",result.getWheelId());
        redisTemplate.opsForSet().remove("preRemeasure",result.getWheelId());
        redisTemplate.opsForSet().remove("preQualityCheck",result.getWheelId());
        return new Result(result,"修改成功",100);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteWheel(String id){
        try{
            wheelService.deleteWheelInfo(id);
        }catch(Exception e){
            return new Result(null,"删除失败",101);
        }
        return new Result(null,"删除成功",100);
    }
    @RequestMapping("/getQRcode")
    @ResponseBody
    public Result generateQRcode(String id){
        String path = null;
        path = qRcodeDao.findQRcode(Integer.parseInt(id));
        try {
            if (path == null){
                path = wheelService.generateQRcode(id);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(null,"二维码生成",101);
        }
        return new Result(path,"二维码生成",100);
    }
}
