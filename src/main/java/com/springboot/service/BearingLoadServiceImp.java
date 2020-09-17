package com.springboot.service;

import com.springboot.dao.BearingCapDao;
import com.springboot.dao.BearingLoadDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingLoad;
import com.springboot.domain.BearingRepair;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BearingLoadServiceImp implements BearingLoadService{
    @Autowired
    private BearingLoadDao bearingLoadDao;
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private WheelDao wheelDao;

    @Override
    public void addBearingLoad(BearingLoad bearingLoad) {
        Integer count =  bearingLoadDao.findWheelIdCount(bearingLoad.getWheelId());
        if (count == 1){
            updateBearingLoad(bearingLoad);
        }else{
            bearingLoadDao.insertBearingLoad(bearingLoad);
        }
        flushWheelInfo(bearingLoad);
    }

    @Override
    public void updateBearingLoad(BearingLoad bearingLoad) {
        bearingLoadDao.updateBearingLoad(bearingLoad);
        fresh(bearingLoad.getWheelId());
    }

    @Override
    public void flushWheelInfo(BearingLoad bearingLoad) {
        bearingLoadDao.flushWheelInfoLoadFinish(bearingLoad.getWheelId());
    }

    @Override
    public void deleteBearing(String wheelId,String index) {
        Integer id = Integer.parseInt(wheelId);
        bearingLoadDao.deleteBearingLoadByWheelId(id);
        bearingLoadDao.rollbackWheelInfoLoadFinish(id,index);
        fresh(id);

    }

    @Override
    public List<WheelInfo> searchWheelInfoBycondition(SearchWheelParam param) {
        String finishtime = param.getInfoTakeFinishTime();
        if (finishtime != null)  finishtime = "%"+finishtime+"%";
        return bearingLoadDao.searchWheelInfoBycondition(param.getWheelId(),
                param.getTakeInDateFrom(),
                param.getTakeInDateTo(),
                param.getAxleNumber(),param.getVehicleNumber(),
                param.getInfoTakeFinishTimeFrom(),
                param.getInfoTakeFinishTimeTo());
    }

    public void fresh(Integer id){
        bearingCapDao.rollbackWheelInfoCapFinish(id);
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
}
