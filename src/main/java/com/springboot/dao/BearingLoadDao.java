package com.springboot.dao;

import com.springboot.domain.BearingLoad;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BearingLoadDao {
    void insertBearingLoad(BearingLoad bearingLoad);
    void updateBearingLoad(BearingLoad bearingLoad);

    List<WheelInfo> findWheelInfoToBearingLoad();
    List<WheelInfo> searchWheelInfoBycondition(@Param("wheelId") String wheelId,@Param("takeInDate") String takeInDate,@Param("axleNumber") String axleNumber,
                                                            @Param("vehicleNumber")String vehicleNumber, @Param("infoTakeFinishTime")String infoTakeFinishTime);

    BearingLoad findBearingLoadByWheelId(Integer id);
    void deleteBearingLoadByWheelId(Integer wheelId);

    void flushWheelInfoLoadFinish(Integer wheelId);
    void rollbackWheelInfoLoadFinish(@Param("wheelId") Integer wheelId,@Param("index") String index);
    Integer findWheelIdCount(Integer wheelId);
}