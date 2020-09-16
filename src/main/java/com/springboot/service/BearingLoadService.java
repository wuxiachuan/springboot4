package com.springboot.service;

import com.springboot.domain.BearingLoad;
import com.springboot.domain.BearingRepair;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;

import java.util.List;

public interface BearingLoadService {
    void addBearingLoad(BearingLoad bearingLoad);
    void updateBearingLoad(BearingLoad bearingLoad);
    void flushWheelInfo(BearingLoad bearingLoad);
    void deleteBearing(String id,String index);
    List<WheelInfo>  searchWheelInfoBycondition(SearchWheelParam param);
}
