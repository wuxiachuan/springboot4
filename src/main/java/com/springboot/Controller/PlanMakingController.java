package com.springboot.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.VehicleInfoDao;
import com.springboot.domain.Result;
import com.springboot.domain.VehicleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/plan")
public class PlanMakingController {
    @Autowired
    private VehicleInfoDao vehicleInfoDao;

    @RequestMapping("/getVehicleInfoByCond")
    @ResponseBody
    public Result getVehicleInfoByCond(@RequestBody Map<String,String> map,String page,String size){
        String Id = map.get("Id");
        String vehicleNumber = map.get("vehicleNumber");
        String vehicleType = map.get("vehicleType");
        String RepairDateFrom= map.get("RepairDateFrom");
        String RepairDateTo= map.get("RepairDateTo");
        String finishTimeFrom= map.get("finishTimeFrom");
        String finishTimeTo= map.get("finishTimeTo");
        String isFinish= map.get("isFinish");
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<VehicleInfo> list = vehicleInfoDao.findVehicleInfoByCondition(
                                                    Id,
                                                    vehicleNumber,
                                                    vehicleType,
                                                    RepairDateFrom,
                                                    RepairDateTo,
                                                    finishTimeFrom,
                                                    finishTimeTo,
                                                    isFinish);
        PageInfo res = new PageInfo(list);
        return new Result(res,"获取成功",100);
    }
    @RequestMapping("/addPlan")
    @ResponseBody
    public Result getVehicleInfoByCond(@RequestBody List<VehicleInfo> vehicleList){
        System.out.println(vehicleList);
        for (VehicleInfo vehicle : vehicleList){
            if (vehicle!=null){
                vehicleInfoDao.insertVehicleInfo(vehicle);
            }
        }
        return new Result(vehicleList,"获取成功",100);
    }

    @RequestMapping("/getVehicleNum")
    @ResponseBody
    public Result getVehicleNum(String date){
        System.out.println(date);
        List<VehicleInfo> vehicles = vehicleInfoDao.findVehicleInfoByDate(date);
        List<String> numList = new ArrayList<>();
        for (VehicleInfo vehicle : vehicles){
            numList.add(vehicle.getVehicleNumber());
        }
        System.out.println(numList);
        return new Result(numList,"获取成功",100);
    }
}
