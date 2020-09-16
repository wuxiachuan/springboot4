package com.springboot.dao;

import com.springboot.domain.BearingCap;
import com.springboot.domain.BearingLoad;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BearingCapDao {
    void insertBearingCap(BearingCap bearingCap);
    void updateBearingCap(BearingCap bearingCap);

    List<WheelInfo> findWheelInfoToBearingCap();
    List<WheelInfo> searchWheelInfoBycondition(@Param("wheelId") String wheelId, @Param("takeInDate") String takeInDate, @Param("axleNumber") String axleNumber,
                                               @Param("vehicleNumber")String vehicleNumber, @Param("infoTakeFinishTime")String infoTakeFinishTime);

    BearingCap findBearingCapByWheelId(Integer id);
    void deleteBearingCapByWheelId(Integer wheelId);

    void flushWheelInfoCapFinish(Integer wheelId);
    void rollbackWheelInfoCapFinish(Integer wheelId);
    Integer findWheelIdCount(Integer wheelId);
}
