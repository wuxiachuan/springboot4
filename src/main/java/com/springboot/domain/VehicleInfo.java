package com.springboot.domain;

import java.io.Serializable;
import java.util.List;

public class VehicleInfo implements Serializable {
    private Integer id;
    private String vehicleNumber;
    private String vehicleType;
    private String preSessionRepair;
    private String SessionRepair;
    private String preOverhaul;
    private String overhaul;
    private List<WheelMeasure> axleIn;
    private List<WheelDispatch> axleOut;
    private String axleLife;
    private String bearingLife;
    private double low;
    private double high;
    private boolean isShow;
    private Integer offset;
    private String  axleType;

    public VehicleInfo() {
        this.isShow = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPreSessionRepair() {
        return preSessionRepair;
    }

    public void setPreSessionRepair(String preSessionRepair) {
        this.preSessionRepair = preSessionRepair;
    }

    public String getSessionRepair() {
        return SessionRepair;
    }

    public void setSessionRepair(String sessionRepair) {
        SessionRepair = sessionRepair;
    }

    public String getPreOverhaul() {
        return preOverhaul;
    }

    public void setPreOverhaul(String preOverhaul) {
        this.preOverhaul = preOverhaul;
    }

    public String getOverhaul() {
        return overhaul;
    }

    public void setOverhaul(String overhaul) {
        this.overhaul = overhaul;
    }

    public List<WheelMeasure> getAxleIn() {
        return axleIn;
    }

    public void setAxleIn(List<WheelMeasure> axleIn) {
        this.axleIn = axleIn;
    }

    public List<WheelDispatch> getAxleOut() {
        return axleOut;
    }

    public void setAxleOut(List<WheelDispatch> axleOut) {
        this.axleOut = axleOut;
    }

    public String getAxleLife() {
        return axleLife;
    }

    public void setAxleLife(String axleLife) {
        this.axleLife = axleLife;
    }

    public String getBearingLife() {
        return bearingLife;
    }

    public void setBearingLife(String bearingLife) {
        this.bearingLife = bearingLife;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getAxleType() {
        return axleType;
    }

    public void setAxleType(String axleType) {
        this.axleType = axleType;
    }

    @Override
    public String toString() {
        return "VehicleInfo{" +
                "id=" + id +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", preSessionRepair='" + preSessionRepair + '\'' +
                ", SessionRepair='" + SessionRepair + '\'' +
                ", preOverhaul='" + preOverhaul + '\'' +
                ", overhaul='" + overhaul + '\'' +
                ", axleIn=" + axleIn +
                ", axleOut=" + axleOut +
                ", axleLife='" + axleLife + '\'' +
                ", bearingLife='" + bearingLife + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", isShow=" + isShow +
                ", offset=" + offset +
                '}';
    }
}
