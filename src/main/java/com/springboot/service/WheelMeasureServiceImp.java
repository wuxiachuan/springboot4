package com.springboot.service;

import com.springboot.dao.MeasureDao;
import com.springboot.dao.WheelDao;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.domain.WheelMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WheelMeasureServiceImp implements WheelMeasureService{
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private WheelDao wheelDao;
    @Override
    public void addMeasure(WheelMeasure wheelMeasure) {
        Integer count =  measureDao.findWheelIdCount(wheelMeasure.getWheelId());
        if (count == 1){
            updateWheelMeasure(wheelMeasure);
        }else{
            measureDao.insertWheelMeasure(wheelMeasure);
        }
        measureDao.flushWheelInfoMeasureFinish(wheelMeasure.getWheelId());
        flushWheelInfo(wheelMeasure);
    }

    @Override
    public WheelMeasure findWheelMeasureByWheelId(Integer wheelId) {
      return measureDao.findWheelMeasureByWheelId(wheelId);
    }

    @Override
    public void updateWheelMeasure(WheelMeasure wheelMeasure) {
        measureDao.updateWheelMeasure(wheelMeasure);
        fresh(wheelMeasure.getWheelId());
        flushWheelInfo(wheelMeasure);
    }

    @Override
    public void deleteWheelMeasure(String wheelId) {
        Integer id = Integer.parseInt(wheelId);
        measureDao.deleteWheelMeasure(id);
//        measureDao.rollbackWheelInfoRepairFinish(id);
//        measureDao.rollbackWheelInfoInspectionFinish(id);
//        measureDao.rollbackWheelInfoMeasureFinish(id);
//        measureDao.rollbackWheelInfoWheelRounding(id);
//        measureDao.rollbackWheelInfoMagInspectionFinish(id);
        wheelDao.robackWheelInfoMeasureFinish(id);
        fresh(id);
    }

    @Override
    public void flushWheelInfo(WheelMeasure wheelMeasure) {
        Integer wheelId = wheelMeasure.getWheelId();
        measureDao.rollbackWheelInfoInspectionFinish(wheelId);
        measureDao.rollbackWheelInfoMagInspectionFinish(wheelId);
        measureDao.rollbackWheelInfoWheelRounding(wheelId);
        String index = wheelMeasure.getRepairProcess();
        if (index.equals("1")){
            measureDao.flushWheelInfoWheelRounding(wheelId);
        }
       if (index.equals("2")){
            measureDao.flushWheelInfoInspectionFinish(wheelId);
            measureDao.flushWheelInfoMagInspectionFinish(wheelId);
       }
        if (index.equals("3")){
            measureDao.flushWheelInfoWheelRounding(wheelId);
            measureDao.flushWheelInfoInspectionFinish(wheelId);
            measureDao.flushWheelInfoMagInspectionFinish(wheelId);
        }
    }

    @Override
    public List<WheelInfo> searchWheelInfoMeasure(SearchWheelParam param) {
        String finishtime = param.getInfoTakeFinishTime();
        if (finishtime != null)  finishtime = "%"+finishtime+"%";
        return measureDao.searchWheelInfoByconditionMeasure(param.getWheelId(),param.getTakeInDate(),
                param.getAxleNumber(),param.getVehicleNumber(),finishtime);
    }
    private void fresh(Integer id){
        wheelDao.rollbackWheelInfoRepairFinish(id);
        wheelDao.rollbackWheelInfoMagInspectionFinish(id);
        wheelDao.rollbackWheelInfoAxleInspectionFinish(id);
        wheelDao.rollbackWheelInfoWheelRoundingFinish(id);
        wheelDao.rollbackWheelInfoisbearingLoadFinish(id);
        wheelDao.rollbackWheelInfobearingCapFinish(id);
        wheelDao.rollbackWheelInforollTestFinish(id);
        wheelDao.rollbackWheelInfowheelDispatchFinish(id);
        wheelDao.rollbackWheelInfoqualityInspectionFinish(id);
        wheelDao.rollbackWheelInfoverifyFinish(id);
        wheelDao.rollbackWheelInfoprocessFinish(id);
    }
}
