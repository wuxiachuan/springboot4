package com.springboot.service;

import com.springboot.dao.MeasureDao;
import com.springboot.dao.WheelDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class WheelDispatchServiceImp implements WheelDispatchService{
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    WheelRepository wheelRepository;

    private SimpleDateFormat dateFormater;
    private SimpleDateFormat dateFormater2;
    private Random random ;

    public WheelDispatchServiceImp(){
        this.dateFormater = new SimpleDateFormat("yyyy-M-d");
        this.dateFormater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.random = new Random(47);
    }
    @Override
    //添加复测
    public void addWheelDispatchRemeasure(WheelDispatch wheelDispatch) {

        Integer count =  wheelDispatchDao.findWheelIdCount(wheelDispatch.getWheelId());
        if (count == 1){
            updateWheelDispatchRemeasure(wheelDispatch);
        }else{
            wheelDispatchDao.insertWheelDispatch(wheelDispatch);
        }
        flushWheelInfoRemeasure(wheelDispatch);
    }

    @Override
    //更新复测
    public void updateWheelDispatchRemeasure(WheelDispatch wheelDispatch) {
        wheelDispatchDao.updateWheelDispatch(wheelDispatch);
        fresh(wheelDispatch.getWheelId());
    }

    @Override
    public void flushWheelInfoRemeasure(WheelDispatch wheelDispatch) {
        wheelDispatchDao.flushWheelInfowheelRemeasureFinish(wheelDispatch.getWheelId());
    }

    @Override
    public void deleteWheelDispatch(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        wheelDispatchDao.deleteWheelDispatchByWheelId(id);
        wheelDispatchDao.rollbackWheelInfoWheelRemeasureFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoDispatch(SearchWheelParam param) {
        return wheelDispatchDao.searchWheelInfoByconditionWheelDispatch(param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }



    public void fresh(Integer id){
        //回滚质检
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        //回滚验收
        wheelDao.rollbackWheelInfoverifyFinish(id);
        //回滚完工
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }


    @Override
    public void receiveResult(List<VehicleInfo> resultlist,String matcher) {
            String dispatchFinishTime = dateFormater2.format(new Date());
            for (VehicleInfo vec : resultlist){
                int axlepos = 0;
                for (WheelDispatch des : vec.getAxleOut()){
                    wheelDispatchDao.updateDispatchStatus(
                            des.getWheelId(),
                            vec.getVehicleType(),
                            vec.getVehicleNumber(),
                            ++axlepos,
                            dispatchFinishTime,
                            matcher);
                    wheelDispatchDao.flushWheelInfoWheelDispatchFinish(
                            des.getWheelId(),
                            vec.getVehicleType(),
                            vec.getVehicleNumber(),
                            axlepos,
                            dispatchFinishTime);
                    wheelRepository.getAxleOutPosition(des.getWheelId().toString());
                }
            }
    }

    @Override
    public List<VehicleInfo> dispatch(List<VehicleInfo> veclist) {
        List<VehicleInfo> res = new ArrayList<>();
        for (VehicleInfo num : veclist){
            res.add(doDispatch(num));
        }
        setChooseMark();
        return res;
    }

    @Override
    public List<WheelDispatch> find2match(VehicleInfo vehicleInfo) {
        double low = vehicleInfo.getLow();
        double high = vehicleInfo.getHigh();
        String axleLife = vehicleInfo.getAxleLife();
        String bearingLife = vehicleInfo.getBearingLife();
        //查找
        List<WheelDispatch> res = findMatchedWheel(String.valueOf(low),String.valueOf(high),axleLife,bearingLife);
        return res;
    }

    //匹配
    public VehicleInfo doDispatch(VehicleInfo vehicleInfo) {
        //准备车辆信息
        if (vehicleInfo==null) return null;
        preparVehicleInfo(vehicleInfo);
        List<WheelDispatch> axleOut = new ArrayList<>();
        double low = vehicleInfo.getLow();
        double high = vehicleInfo.getHigh();
        String axleLife = vehicleInfo.getAxleLife();
        String bearingLife = vehicleInfo.getBearingLife();
        //查找
        List<WheelDispatch>res = findMatchedWheel(String.valueOf(low),String.valueOf(high),axleLife,bearingLife);
        if (res != null && res.size() >= 4){
            WheelDispatch choose = null;
            int size = res.size()/2-2;
            axleOut.add(choose = res.get(size));
            setChooseMark(choose.getWheelId());
            axleOut.add(choose = res.get(size+1));
            setChooseMark(choose.getWheelId());
            axleOut.add(choose = res.get(size+2));
            setChooseMark(choose.getWheelId());
            axleOut.add(choose = res.get(size+3));
            setChooseMark(choose.getWheelId());
        }else {
            return new VehicleInfo();
        }
        vehicleInfo.setAxleOut(axleOut);
        return vehicleInfo;
    }
    //按条件查找轮对
    public List<WheelDispatch> findMatchedWheel(String low,String high,String axleLife,String bearingLife){
        List<WheelDispatch> wheelDispatchList = null;
        wheelDispatchList = wheelDispatchDao.findTarget(low,high,axleLife,bearingLife);
        return wheelDispatchList;
    }
    //准备车辆车型、轮径、轴承寿命、轴寿命
    public void preparVehicleInfo(VehicleInfo vehicleInfo){
        List<WheelMeasure> axleIn = null;
        axleIn = wheelDispatchDao.findWheelOrgInfoOfVehicle(vehicleInfo.getVehicleNumber());
        if (axleIn == null) return;
        int avgDiameter = 0;
        int sum = 0;
        int num = 0;
        for(WheelMeasure w : axleIn){
            sum += Integer.parseInt(w.getWheelDiameterLeft());
            num++;
            sum += Integer.parseInt(w.getWheelDiameterRight());
            num++;
        }
        avgDiameter = sum/num;
        int low = avgDiameter - 15;
        int high = avgDiameter + 15;
        String axleLife = dateFormate(0,"axle");
        String bearingLife = dateFormate(0,"bearing");
        vehicleInfo.setAxleIn(axleIn);
        vehicleInfo.setAxleLife(axleLife);
        vehicleInfo.setBearingLife(bearingLife);
        vehicleInfo.setLow(low);
        vehicleInfo.setHigh(high);
    }
    //选中标记
    public void setChooseMark(Integer wheelId){
        wheelDispatchDao.setchooseMark(wheelId);
    }
    public void setChooseMark(){
        wheelDispatchDao.resetchooseMark();
    }
    //生成合法日期字符串
    public String dateFormate(int d,String flag){

        String[] date = dateFormater.format(new Date()).split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int ty = 0;
        int tm = 0;
        if ("axle".equals(flag)){
            tm = (year-1990)*12+month-22*12+d;
        }else if("bearing".equals(flag)){
            tm = (year-1990)*12+month-6*12+d;
        }else {

        }
        ty = 1990+tm/12;
        tm = tm%12;
        String res = "";
        if (tm<10) {
            res = ty+"-0"+tm +"-01";
        }else {
            res = ty+"-"+tm +"-01";
        }
        return  res;
    }


    @Override
    //数据库内添加测试数据
    public void adddata() {
        WheelInfo wheelInfo = null;
        WheelDispatch wheelDispatch = null;
        WheelMeasure wheelMeasure = null;
        String vtype = null;
        int vnum = 4567110;
        int anum = 10040;
        int wid = 154;
        int wheelDiam = 780;
        String axlemadeDate = null;
        String bearingdate = null;

        for (int i = 0;i < 100 ; i++){
            for (int j = 1 ; j <= 4 ;j++){
                wheelInfo =  new WheelInfo();
                wheelDispatch = new WheelDispatch();
                wheelMeasure = new WheelMeasure();

                wheelInfo.setVehicleNumber(String.valueOf(vnum+i));

                wheelInfo.setWheelId(wid);
                wheelDispatch.setWheelId(wid);
                wheelMeasure.setWheelId(wid++);

                wheelInfo.setAxleNumber(String.valueOf(anum++));
                wheelInfo.setAxlePosition(j+"");

                wheelMeasure.setWheelDiameterLeft(generatewheelDiam());
                wheelMeasure.setWheelDiameterRight(generatewheelDiam());

                wheelDispatch.setAxleMadeDate(generateAxledate());
                wheelDispatch.setBearingAssembleDateLeft(generateBearingdate());
                wheelDispatch.setBearingAssembleDateRight(generateBearingdate());
                wheelDispatch.setWheelDiameterLeft(generatewheelDiam());
                wheelDispatch.setWheelDiameterRight(generatewheelDiam());

                if (i<5){
                    wheelInfo.setAxleType("RD2");
                }else {
                    wheelInfo.setAxleType("RE2B");
                }

                wheelDao.insertWheelInfo(wheelInfo);
                wheelDispatchDao.insertWheelDispatch(wheelDispatch);
                measureDao.insertWheelMeasure(wheelMeasure);
            }
        }
    }

    public String generateAxledate(){
        String axlemadeDate = null;
        int y = 1998;
        int m = 1;
        int d = 1;
        y = y + random.nextInt(22);
        m = m + random.nextInt(11);
        d = d + random.nextInt(30);
        if (m<10){
            if (d<10){
                axlemadeDate = y+"-0"+m +"-0"+d;
            }else {
                axlemadeDate = y+"-0"+m +"-"+d;
            }
        }else {
            if (d<10){
                axlemadeDate = y+"-"+ m +"-0"+d;
            }else {
                axlemadeDate = y+"-"+ m +"-"+d;
            }
        }
        return axlemadeDate;
    }

    public String generateBearingdate(){
        String axlemadeDate = null;
        int y = 2014;
        int m = 1;
        int d = 1;
        y = y + random.nextInt(6);
        m = m + random.nextInt(11);
        d = d + random.nextInt(30);
        if (m<10){
            if (d<10){
                axlemadeDate = y+"-0"+m +"-0"+d;
            }else {
                axlemadeDate = y+"-0"+m +"-"+d;
            }
        }else {
            if (d<10){
                axlemadeDate = y+"-"+ m +"-0"+d;
            }else {
                axlemadeDate = y+"-"+ m +"-"+d;
            }
        }
        return axlemadeDate;
    }
    public String generatewheelDiam(){
        String axlemadeDate = null;
        int y = 780;
        y = y + random.nextInt(40);
        axlemadeDate = y + "";
        return axlemadeDate;
    }
}
