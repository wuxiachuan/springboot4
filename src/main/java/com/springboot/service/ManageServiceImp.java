package com.springboot.service;

import com.github.pagehelper.PageHelper;
import com.springboot.dao.*;
import com.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageServiceImp implements ManageService{
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    private WheelRoundDao wheelRoundDao;
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;
    @Autowired
    private AxleInspectionDao axleInspectionDao;
    @Autowired
    private BearingTestDao bearingTestDao;
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private BearingLoadDao bearingLoadDao;
    @Autowired
    private BearingRepairDao bearingRepairDao;
    @Override
    public List<WheelAll> findWheelAllByCondition(SearchWheelParam param) {
        String id = param.getWheelId();
        String takeInDate = param.getTakeInDate();
        String dispatchDate = param.getDispatchDate();
        String takeInReason = param.getTakeInReason();
        String axleNumber = param.getAxleNumber();
        String axleType = param.getAxleType();
        String vehicleNumber = param.getVehicleNumber();
        String dipatchVehicleNumber = param.getDipatchVehicleNumber();
        String infoTakeFinishTime = param.getInfoTakeFinishTime();
        String status = param.getStatus();
        String isprocessFinish = param.getIsprocessFinish();
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoByCondition(
                                    param.getWheelId(),
                                    param.getTakeInDateFrom(),
                                    param.getTakeInDateTo(),
                                    param.getAxleNumber(),
                                    param.getVehicleNumber(),
                                    param.getInfoTakeFinishTimeFrom(),
                                    param.getInfoTakeFinishTimeTo(),
                                    param.getDispatchDateFrom(),
                                    param.getDispatchDateTo(),
                                    dipatchVehicleNumber,
                                    status,
                                    axleType,
                                    takeInReason,
                                    isprocessFinish);
        return findWheelAllByInfoList(wheelInfoList);
    }

    @Override
    public List<WheelAll> findWheelAllByCondition2Check(SearchWheelParam param) {
        String id = param.getWheelId();
        String takeInDate = param.getTakeInDate();
        String dispatchDate = param.getDispatchDate();
        String takeInReason = param.getTakeInReason();
        String axleNumber = param.getAxleNumber();
        String axleType = param.getAxleType();
        String vehicleNumber = param.getVehicleNumber();
        String dipatchVehicleNumber = param.getDipatchVehicleNumber();
        String infoTakeFinishTime = param.getInfoTakeFinishTime();
        String status = param.getStatus();
        String isprocessFinish = param.getIsprocessFinish();
        List<WheelInfo> wheelInfoList = wheelDao.findWheelInfoByCondition2Check(
                param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),
                param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo(),
                param.getDispatchDateFrom(),
                param.getDispatchDateTo(),
                dipatchVehicleNumber,
                status,
                axleType,
                takeInReason,
                isprocessFinish);
        return findWheelAllByInfoList(wheelInfoList);
    }

    @Override
    public List<WheelAll> findAllWheelAll() {
        List<WheelInfo> wheelInfoList = wheelDao.findAllWheelInfo();
        return findWheelAllByInfoList(wheelInfoList);
    }

    public List<WheelAll> findWheelAllByInfoList(List<WheelInfo> wheelInfoList){
        List<WheelAll> wheelAllList = new ArrayList<>();
        for (WheelInfo wh : wheelInfoList){
            WheelAll wheelAll = findWheelAllByWheelInfo(wh);
            if (wheelAll != null){
                wheelAllList.add(wheelAll);
            }
        }
        return wheelAllList;
    }
    public WheelAll findWheelAllByWheelInfo(WheelInfo wh){
        WheelRound wheelRound = null;
        WheelDispatch wheelDispatch = null;
        WheelMeasure wheelMeasure = null;
        AxleInspection axleInspection = null;
        WheelDispatch wheelDispatch1 = null;
        BearingTest bearingTest = null;
        BearingLoad bearingLoad = null;
        BearingRepair bearingRepair = null;
        BearingCap bearingCap = null;

        wheelRound = findWheelRoundById(wh);
        wheelDispatch = findWheelDispatchById(wh);
        wheelMeasure = findWheelMeasureById(wh);
        axleInspection = findAxleInspectionById(wh);
        wheelDispatch1 = findWheelDispatchById(wh);
        bearingTest = findBearingTestById(wh);
        bearingLoad = findBearingLoadById(wh);
        bearingRepair = findBearingRepairById(wh);
        bearingCap = findBearingCapById(wh);

        WheelAll wheelAll = new WheelAll();
        wheelAll.setAxleInspection(axleInspection);
        wheelAll.setBearingCap(bearingCap);
        wheelAll.setBearingLoad(bearingLoad);
        wheelAll.setBearingRepair(bearingRepair);
        wheelAll.setWheelDispatch(wheelDispatch);
        wheelAll.setWheelRound(wheelRound);
        wheelAll.setWheelDispatch(wheelDispatch1);
        wheelAll.setWheelMeasure(wheelMeasure);
        wheelAll.setBearingTest(bearingTest);
        wheelAll.setWheelInfo(wh);
        wheelAll.setWheelId(wh.getWheelId());
        return wheelAll;
    }

    @Override
    public WheelAll findWheelAllByWheelId(Integer id) {
        WheelInfo wh= wheelDao.findWheelInfoById(id);
        return findWheelAllByWheelInfo(wh);
    }


    private WheelRound findWheelRoundById(WheelInfo wh){
        //WheelRound wheelRound = new WheelRound();
        WheelRound wheelRound = null;
       Integer id = wh.getWheelId();
       String isFinish = wh.getIsWheelRoundingFinish();
        if ("1".equals(isFinish)){
            wheelRound = wheelRoundDao.findWheelRoundByWheelId(id);
        }
        return wheelRound;
    }
    private WheelMeasure findWheelMeasureById(WheelInfo wh){
       // WheelMeasure wheelMeasure = new WheelMeasure();
        WheelMeasure wheelMeasure = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsMeasureFinish();
        if ("1".equals(isFinish)){
            wheelMeasure = measureDao.findWheelMeasureByWheelId(id);
        }
        return wheelMeasure;
    }
    private AxleInspection findAxleInspectionById(WheelInfo wh){
       // AxleInspection axleInspection = new AxleInspection();
        AxleInspection axleInspection = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsaxleInspectionFinish();
        if ("1".equals(isFinish)){
            axleInspection = axleInspectionDao.findAxleInspectionByWheelId(id);
        }
        return axleInspection;
    }
    private BearingCap findBearingCapById(WheelInfo wh){
        //BearingCap bearingCap = new BearingCap();
        BearingCap bearingCap = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingCapFinish();
        if ("1".equals(isFinish)){
            bearingCap = bearingCapDao.findBearingCapByWheelId(id);
        }
        return bearingCap;
    }
    private BearingRepair findBearingRepairById(WheelInfo wh){
        //BearingRepair bearingRepair = new BearingRepair();
        BearingRepair bearingRepair = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingRepairFinish();
        if ("1".equals(isFinish)){
            bearingRepair = bearingRepairDao.findBearingRepairByWheelId(id);
        }
        return bearingRepair;
    }
    private BearingLoad findBearingLoadById(WheelInfo wh){
        //BearingLoad bearingLoad = new BearingLoad();
        BearingLoad bearingLoad = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingLoadFinish();
        if ("4".equals(isFinish)){
            bearingLoad = bearingLoadDao.findBearingLoadByWheelId(id);
        }
        return bearingLoad;
    }
    private BearingTest findBearingTestById(WheelInfo wh){
       // BearingTest bearingTest = new BearingTest();
        BearingTest bearingTest = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIsbearingrollTestFinish();
        if ("1".equals(isFinish)){
            bearingTest = bearingTestDao.findBearingTestByWheelId(id);
        }
        return bearingTest;
    }
    private WheelDispatch findWheelDispatchById(WheelInfo wh){
        //WheelDispatch wheelDispatch = new WheelDispatch();
        WheelDispatch wheelDispatch = null;
        Integer id = wh.getWheelId();
        String isFinish = wh.getIswheelDispatchFinish();
        if ("1".equals(isFinish)){
            wheelDispatch = wheelDispatchDao.findWheelDispatchByWheelId(id);
        }
        return wheelDispatch;
    }
}
