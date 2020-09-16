package com.springboot.service;

import com.springboot.dao.BearingCapDao;
import com.springboot.dao.BearingTestDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.BearingCap;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BearingCapServiceImp implements BearingCapService{
    @Autowired
    private BearingCapDao bearingCapDao;
    @Autowired
    private BearingTestDao bearingTestDao;
    @Autowired
    private WheelDao wheelDao;
    @Override
    public void addBearingCap(BearingCap bearingCap) {

        Integer count =  bearingCapDao.findWheelIdCount(bearingCap.getWheelId());
        if (count == 1){
            updateBearingCap(bearingCap);
        }else{
            bearingCapDao.insertBearingCap(bearingCap);
        }
        flushWheelInfo(bearingCap);
    }

    @Override
    public void updateBearingCap(BearingCap bearingCap) {
        bearingCapDao.updateBearingCap(bearingCap);
        fresh(bearingCap.getWheelId());
    }

    @Override
    public void flushWheelInfo(BearingCap bearingCap) {
        bearingCapDao.flushWheelInfoCapFinish(bearingCap.getWheelId());
    }

    @Override
    public void deleteBearingCap(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        bearingCapDao.deleteBearingCapByWheelId(id);
        bearingCapDao.rollbackWheelInfoCapFinish(id);
        fresh(id);
    }

    @Override
    public List<WheelInfo> searchWheelInfoBycondition(SearchWheelParam param) {
        String finishtime = param.getInfoTakeFinishTime();
        if (finishtime != null)  finishtime = "%"+finishtime+"%";
        return bearingCapDao.searchWheelInfoBycondition(param.getWheelId(),param.getTakeInDate(),
                param.getAxleNumber(),param.getVehicleNumber(),finishtime);
    }
    public void fresh(Integer id){
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
}
