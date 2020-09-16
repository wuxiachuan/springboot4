package com.springboot.dao;

import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WheelDao {
    void insertWheelInfo(WheelInfo wheelInfo);
    List<WheelInfo> findWheelInfo(@Param("wheelId") String wheelId,@Param("takeInDate") String takeInDate,@Param("axleNumber") String axleNumber,
                                  @Param("vehicleNumber")String vehicleNumber, @Param("infoTakeFinishTime")String infoTakeFinishTime);
    void updateWheelInfo(WheelInfo wheelInfo);
    void deleteWheelInfo(Integer id);
    List<WheelInfo> findWheelInfoToMeasure();
    List<WheelInfo> findWheelInfoToBearing();

    WheelInfo findWheelInfoById(Integer wheelId);
    List<WheelInfo> findAllWheelInfo();
    List<WheelInfo> findWheelInfoByCondition(@Param("wheelId") String wheelId,
                                             @Param("takeInDate") String takeInDate,
                                             @Param("axleNumber") String axleNumber,
                                             @Param("vehicleNumber")String vehicleNumber,
                                             @Param("infoTakeFinishTime")String infoTakeFinishTime,
                                             @Param("dispatchDate")String dispatchDate,
                                             @Param("dipatchVehicleNumber")String dipatchVehicleNumber,
                                             @Param("status")String status,
                                             @Param("axleType")String axleType,
                                             @Param("takeInReason")String takeInReason,
                                             @Param("isprocessFinish") String isprocessFinish);

    void robackWheelInfoMeasureFinish(Integer wheelId);
    void rollbackWheelInfoMagInspectionFinish(Integer wheelId);
    void rollbackWheelInfoAxleInspectionFinish(Integer wheelId);
    void rollbackWheelInfoWheelRoundingFinish(Integer wheelId);
    void rollbackWheelInfoRepairFinish(Integer wheelId);
    void rollbackWheelInfoisbearingLoadFinish(Integer wheelId);
    void rollbackWheelInfobearingCapFinish(Integer wheelId);
    void rollbackWheelInforollTestFinish(Integer wheelId);
    void rollbackWheelInfowheelRemeasureFinish(Integer wheelId);
    void rollbackWheelInfowheelDispatchFinish(Integer wheelId);
    void rollbackWheelInfoqualityInspectionFinish(Integer wheelId);
    void rollbackWheelInfoverifyFinish(Integer wheelId);
    void rollbackWheelInfoprocessFinish(Integer wheelId);
}