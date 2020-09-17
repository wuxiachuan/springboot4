package com.springboot.Controller;

import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wheelTakein")
public class WheelTakeInController {
    @Autowired
    private WheelService wheelService;
    @RequestMapping("/addWheel")
    @ResponseBody
    public Result addWheel(@RequestBody WheelInfo wheelInfo){
        WheelInfo result = null;
        try{
            result = wheelService.insertWheelInfo(wheelInfo);
        }catch(Exception e){
            return new Result(null,"添加失败",101);
        }
        return new Result(result,"添加成功",100);
    }

    @RequestMapping("/sesrchinfo")
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
    @RequestMapping("/modifyWheel")
    @ResponseBody
    public Result modifyWheel(@RequestBody WheelInfo wheelInfo){
        WheelInfo result = null;
        try{
            result = wheelService.updateWheelInfo(wheelInfo);
        }catch(Exception e){
            return new Result(null,"修改失败",101);
        }
        return new Result(result,"修改成功",100);
    }
    @RequestMapping("/deleteWheel")
    @ResponseBody
    public Result deleteWheel(String id){
        try{
            wheelService.deleteWheelInfo(id);
        }catch(Exception e){
            return new Result(null,"删除失败",101);
        }
        return new Result(null,"删除成功",100);
    }
}
