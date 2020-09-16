package com.springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelAll;
import com.springboot.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;
    @RequestMapping("/query")
    @ResponseBody
   public Result findInfo(@RequestBody SearchWheelParam param){
        Integer page = Integer.parseInt(param.getPage());
        Integer size = Integer.parseInt(param.getSize());
        List<WheelAll> data = null;
        data = manageService.findWheelAllByCondition(param);
        int total = (int) new PageInfo(data).getTotal();
        PageHelper.startPage(page,size);
        data = manageService.findWheelAllByCondition(param);
        PageInfo result = new PageInfo(data);
        result.setTotal(total);
        return  new Result(result,"添加成功",100);
    }
    @RequestMapping("/test")
    @ResponseBody
    public Result findINfo(Integer id){
        WheelAll data = manageService.findWheelAllByWheelId(id);
        return  new Result(data,"添加成功",100);
    }
}
