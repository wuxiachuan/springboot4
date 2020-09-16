package com.springboot.dao;

import com.springboot.domain.AxleInspection;
import com.springboot.domain.BearingTest;
import com.springboot.domain.WheelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AxleInspectionDao {
    void insertAxleInspection(AxleInspection axleInspection);
    void updateAxleInspection(AxleInspection axleInspection);
    List<WheelInfo> findWheelInfoToAxleInspection();
    List<WheelInfo> searchWheelInfoByconditionAxleInspection(@Param("wheelId") String wheelId, @Param("takeInDate") String takeInDate, @Param("axleNumber") String axleNumber,
                                                   @Param("vehicleNumber")String vehicleNumber, @Param("infoTakeFinishTime")String infoTakeFinishTime);

    AxleInspection findAxleInspectionByWheelId(Integer id);
    void deleteAxleInspectionByWheelId(Integer wheelId);

    void flushWheelInfoAxleInspectionFinish(Integer wheelId);
    void rollbackWheelInfoAxleInspectionFinish(Integer wheelId);

    Integer findWheelIdCount(Integer wheelId);

    void insertMagInspection(AxleInspection axleInspection);
    void updateMagInspection(AxleInspection axleInspection);
    List<WheelInfo> findWheelInfoToMagInspection();
    List<WheelInfo> searchWheelInfoByconditionMagInspection(@Param("wheelId") String wheelId, @Param("takeInDate") String takeInDate, @Param("axleNumber") String axleNumber,
                                                             @Param("vehicleNumber")String vehicleNumber, @Param("infoTakeFinishTime")String infoTakeFinishTime);

    AxleInspection findMagInspectionByWheelId(Integer id);
    void deleteMagInspectionByWheelId(Integer wheelId);

    void flushWheelInfoMagInspectionFinish(Integer wheelId);
    void rollbackWheelInfoMagInspectionFinish(Integer wheelId);

}
