package com.springboot.service;

import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelAll;

import java.util.List;

public interface ManageService {
    List<WheelAll> findWheelAllByCondition(SearchWheelParam param);
    List<WheelAll> findAllWheelAll();
    WheelAll findWheelAllByWheelId(Integer id);
}
