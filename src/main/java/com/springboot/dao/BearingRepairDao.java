package com.springboot.dao;

import com.springboot.domain.BearingRepair;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BearingRepairDao {
    void insertBearingRepair(BearingRepair bearingRepair);
    void updateBearingRepair(BearingRepair bearingRepair);
    void flushWheelInfoRepairFinish(Integer wheelId);
    void rollbackWheelInfoRepairFinish(Integer wheelId);
    void flushWheelInfoLoadFinish(@Param("wheelId") Integer wheelId,@Param("loadindex") String loadindex,@Param("capindex") String capindex);
    void rollbackWheelInfoLoadFinish(Integer wheelId);
    BearingRepair findBearingRepairByWheelId(Integer wheelId);
    void deleteBearingRepairByWheelId(Integer wheelId);
    List<WheelInfo> searchWheelInfoByconditionRepair(@Param("wheelId") String wheelId, @Param("takeInDate") String takeInDate, @Param("axleNumber") String axleNumber,
                                                   @Param("vehicleNumber")String vehicleNumber, @Param("infoTakeFinishTime")String infoTakeFinishTime);
    Integer findWheelIdCount(Integer wheelId);
}