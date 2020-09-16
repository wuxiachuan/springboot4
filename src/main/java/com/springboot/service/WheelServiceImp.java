package com.springboot.service;

import com.springboot.dao.WheelDao;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WheelServiceImp implements WheelService{
    @Autowired
    private WheelDao wheelDao;
    @Override
    public WheelInfo insertWheelInfo(WheelInfo wheelInfo) {
        wheelDao.insertWheelInfo(wheelInfo);
        return wheelInfo;
    }

    @Override
    public List<WheelInfo> findWheelInfo(SearchWheelParam param) {
        String finishtime = param.getInfoTakeFinishTime();
        if (finishtime != null)  finishtime = "%"+finishtime+"%";
        return wheelDao.findWheelInfo(param.getWheelId(),param.getTakeInDate(),
                param.getAxleNumber(),param.getVehicleNumber(),finishtime);
    }

    @Override
    public WheelInfo updateWheelInfo(WheelInfo wheelInfo) {
        wheelDao.updateWheelInfo(wheelInfo);
        return wheelInfo;
    }

    @Override
    public void deleteWheelInfo(String id) {
        wheelDao.deleteWheelInfo(Integer.parseInt(id));
    }
}
