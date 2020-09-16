package com.springboot.dao;

import com.springboot.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface WheelDispatchDao {
    void insertWheelDispatch(WheelDispatch wheelDispatch);
    void updateWheelDispatch(WheelDispatch wheelDispatch);

    List<WheelInfo> findWheelInfoToWheelDispatch();
    List<WheelInfo> searchWheelInfoByconditionWheelDispatch(@Param("wheelId") String wheelId,
                                                            @Param("takeInDate") String takeInDate,
                                                            @Param("axleNumber") String axleNumber,
                                                            @Param("vehicleNumber")String vehicleNumber,
                                                            @Param("infoTakeFinishTime")String infoTakeFinishTime);

    WheelDispatch findWheelDispatchByWheelId(Integer id);
    void deleteWheelDispatchByWheelId(Integer wheelId);

    void flushWheelInfowheelRemeasureFinish(Integer wheelId);
    void rollbackWheelInfoWheelRemeasureFinish(Integer wheelId);

    void flushWheelInfoWheelDispatchFinish(Integer wheelId);
    void rollbackWheelInfoWheelDispatchFinish(Integer wheelId);

    void flushWheelInfoqualityInspectionFinish(Integer wheelId);

    void finishInspection(@Param("inspector") String inspector,
                          @Param("wheelId") Integer wheelId,
                          @Param("time") String time);

    Integer findWheelIdCount(Integer wheelId);

    List<WheelMeasure> findWheelOrgInfoOfVehicle(String number);
    List<WheelDispatch> findTarget(@Param("low") String low,
                                   @Param("high")String high,
                                   @Param("axleLife")String axleLife,
                                   @Param("bearingLife")String bearingLife);
    List<VehicleInfo> findvehicleNum();
    void setchooseMark(Integer wheelId);
    void resetchooseMark();
    void updateDispatchStatus(@Param("wheelId") Integer wheelId,
                              @Param("vehicleType") String vehicleType,
                              @Param("vehicleNumber") String vehicleNumber,
                              @Param("axleDispatchPosition") Integer axleDispatchPosition,
                              @Param("matcher") String matcher);
}
