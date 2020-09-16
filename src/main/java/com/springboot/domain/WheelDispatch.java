package com.springboot.domain;

import java.io.Serializable;

public class WheelDispatch implements Serializable {
    private Integer  	id	;
    private Integer  	wheelId	;
    private String  	axleDiameter	;
    private String  	wheelDiameterLeft	;
    private String  	wheelDiameterRight	;
    private String  	treadWearLeft	;
    private String  	treadWearRight	;
    private String  	rimWideLeft	;
    private String  	rimWideRight	;
    private String  	flangeThickLeft	;
    private String  	flangeThickRight	;
    private String  	rimThickLeft	;
    private String  	rimThickRight	;
    private String  	internalDistance1	;
    private String  	internalDistance2	;
    private String  	internalDistance3	;
    private String  	axleNumber	;
    private String  	axleType	;
    private String  	axleMaterial	;
    private String  	axleMadeDate	;
    private String  	axleMadeIn	;
    private String  	wheelType	;
    private String  	wheelMaterial	;
    private String  	wheelMadeIn	;
    private String  	wheelAssemblefirst	;
    private String  	wheelAssemblefirstIn	;
    private String  	wheelAssemblelast	;
    private String  	wheelAssemblelastIn	;
    private String      bearingTypeLeft;
    private String      bearingAssembleDateLeft;
    private String      bearingMadeInLeft;
    private String      bearingTypeRight;
    private String      bearingAssembleDateRight;
    private String      bearingMadeInRight;
    private String  	isFinish	;
    private String  	finishTime	;
    private String  	worker	;

    public WheelDispatch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWheelId() {
        return wheelId;
    }

    public void setWheelId(Integer wheelId) {
        this.wheelId = wheelId;
    }

    public String getAxleDiameter() {
        return axleDiameter;
    }

    public void setAxleDiameter(String axleDiameter) {
        this.axleDiameter = axleDiameter;
    }

    public String getWheelDiameterLeft() {
        return wheelDiameterLeft;
    }

    public void setWheelDiameterLeft(String wheelDiameterLeft) {
        this.wheelDiameterLeft = wheelDiameterLeft;
    }

    public String getWheelDiameterRight() {
        return wheelDiameterRight;
    }

    public void setWheelDiameterRight(String wheelDiameterRight) {
        this.wheelDiameterRight = wheelDiameterRight;
    }

    public String getTreadWearLeft() {
        return treadWearLeft;
    }

    public void setTreadWearLeft(String treadWearLeft) {
        this.treadWearLeft = treadWearLeft;
    }

    public String getTreadWearRight() {
        return treadWearRight;
    }

    public void setTreadWearRight(String treadWearRight) {
        this.treadWearRight = treadWearRight;
    }

    public String getRimWideLeft() {
        return rimWideLeft;
    }

    public void setRimWideLeft(String rimWideLeft) {
        this.rimWideLeft = rimWideLeft;
    }

    public String getRimWideRight() {
        return rimWideRight;
    }

    public void setRimWideRight(String rimWideRight) {
        this.rimWideRight = rimWideRight;
    }

    public String getFlangeThickLeft() {
        return flangeThickLeft;
    }

    public void setFlangeThickLeft(String flangeThickLeft) {
        this.flangeThickLeft = flangeThickLeft;
    }

    public String getFlangeThickRight() {
        return flangeThickRight;
    }

    public void setFlangeThickRight(String flangeThickRight) {
        this.flangeThickRight = flangeThickRight;
    }

    public String getRimThickLeft() {
        return rimThickLeft;
    }

    public void setRimThickLeft(String rimThickLeft) {
        this.rimThickLeft = rimThickLeft;
    }

    public String getRimThickRight() {
        return rimThickRight;
    }

    public void setRimThickRight(String rimThickRight) {
        this.rimThickRight = rimThickRight;
    }

    public String getInternalDistance1() {
        return internalDistance1;
    }

    public void setInternalDistance1(String internalDistance1) {
        this.internalDistance1 = internalDistance1;
    }

    public String getInternalDistance2() {
        return internalDistance2;
    }

    public void setInternalDistance2(String internalDistance2) {
        this.internalDistance2 = internalDistance2;
    }

    public String getInternalDistance3() {
        return internalDistance3;
    }

    public void setInternalDistance3(String internalDistance3) {
        this.internalDistance3 = internalDistance3;
    }

    public String getAxleNumber() {
        return axleNumber;
    }

    public void setAxleNumber(String axleNumber) {
        this.axleNumber = axleNumber;
    }

    public String getAxleType() {
        return axleType;
    }

    public void setAxleType(String axleType) {
        this.axleType = axleType;
    }

    public String getAxleMaterial() {
        return axleMaterial;
    }

    public void setAxleMaterial(String axleMaterial) {
        this.axleMaterial = axleMaterial;
    }

    public String getAxleMadeDate() {
        return axleMadeDate;
    }

    public void setAxleMadeDate(String axleMadeDate) {
        this.axleMadeDate = axleMadeDate;
    }

    public String getAxleMadeIn() {
        return axleMadeIn;
    }

    public void setAxleMadeIn(String axleMadeIn) {
        this.axleMadeIn = axleMadeIn;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public String getWheelMaterial() {
        return wheelMaterial;
    }

    public void setWheelMaterial(String wheelMaterial) {
        this.wheelMaterial = wheelMaterial;
    }

    public String getWheelMadeIn() {
        return wheelMadeIn;
    }

    public void setWheelMadeIn(String wheelMadeIn) {
        this.wheelMadeIn = wheelMadeIn;
    }

    public String getWheelAssemblefirst() {
        return wheelAssemblefirst;
    }

    public void setWheelAssemblefirst(String wheelAssemblefirst) {
        this.wheelAssemblefirst = wheelAssemblefirst;
    }

    public String getWheelAssemblefirstIn() {
        return wheelAssemblefirstIn;
    }

    public void setWheelAssemblefirstIn(String wheelAssemblefirstIn) {
        this.wheelAssemblefirstIn = wheelAssemblefirstIn;
    }

    public String getWheelAssemblelast() {
        return wheelAssemblelast;
    }

    public void setWheelAssemblelast(String wheelAssemblelast) {
        this.wheelAssemblelast = wheelAssemblelast;
    }

    public String getWheelAssemblelastIn() {
        return wheelAssemblelastIn;
    }

    public void setWheelAssemblelastIn(String wheelAssemblelastIn) {
        this.wheelAssemblelastIn = wheelAssemblelastIn;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getBearingTypeLeft() {
        return bearingTypeLeft;
    }

    public void setBearingTypeLeft(String bearingTypeLeft) {
        this.bearingTypeLeft = bearingTypeLeft;
    }

    public String getBearingAssembleDateLeft() {
        return bearingAssembleDateLeft;
    }

    public void setBearingAssembleDateLeft(String bearingAssembleDateLeft) {
        this.bearingAssembleDateLeft = bearingAssembleDateLeft;
    }

    public String getBearingMadeInLeft() {
        return bearingMadeInLeft;
    }

    public void setBearingMadeInLeft(String bearingMadeInLeft) {
        this.bearingMadeInLeft = bearingMadeInLeft;
    }

    public String getBearingTypeRight() {
        return bearingTypeRight;
    }

    public void setBearingTypeRight(String bearingTypeRight) {
        this.bearingTypeRight = bearingTypeRight;
    }

    public String getBearingAssembleDateRight() {
        return bearingAssembleDateRight;
    }

    public void setBearingAssembleDateRight(String bearingAssembleDateRight) {
        this.bearingAssembleDateRight = bearingAssembleDateRight;
    }

    public String getBearingMadeInRight() {
        return bearingMadeInRight;
    }

    public void setBearingMadeInRight(String bearingMadeInRight) {
        this.bearingMadeInRight = bearingMadeInRight;
    }

    public String getworker() {
        return worker;
    }

    public void setworker(String worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "WheelDispatch{" +
                "id=" + id +
                ", wheelId=" + wheelId +
                ", axleDiameter='" + axleDiameter + '\'' +
                ", wheelDiameterLeft='" + wheelDiameterLeft + '\'' +
                ", wheelDiameterRight='" + wheelDiameterRight + '\'' +
                ", treadWearLeft='" + treadWearLeft + '\'' +
                ", treadWearRight='" + treadWearRight + '\'' +
                ", rimWideLeft='" + rimWideLeft + '\'' +
                ", rimWideRight='" + rimWideRight + '\'' +
                ", flangeThickLeft='" + flangeThickLeft + '\'' +
                ", flangeThickRight='" + flangeThickRight + '\'' +
                ", rimThickLeft='" + rimThickLeft + '\'' +
                ", rimThickRight='" + rimThickRight + '\'' +
                ", internalDistance1='" + internalDistance1 + '\'' +
                ", internalDistance2='" + internalDistance2 + '\'' +
                ", internalDistance3='" + internalDistance3 + '\'' +
                ", axleNumber='" + axleNumber + '\'' +
                ", axleType='" + axleType + '\'' +
                ", axleMaterial='" + axleMaterial + '\'' +
                ", axleMadeDate='" + axleMadeDate + '\'' +
                ", axleMadeIn='" + axleMadeIn + '\'' +
                ", wheelType='" + wheelType + '\'' +
                ", wheelMaterial='" + wheelMaterial + '\'' +
                ", wheelMadeIn='" + wheelMadeIn + '\'' +
                ", wheelAssemblefirst='" + wheelAssemblefirst + '\'' +
                ", wheelAssemblefirstIn='" + wheelAssemblefirstIn + '\'' +
                ", wheelAssemblelast='" + wheelAssemblelast + '\'' +
                ", wheelAssemblelastIn='" + wheelAssemblelastIn + '\'' +
                ", bearingTypeLeft='" + bearingTypeLeft + '\'' +
                ", bearingAssembleDateLeft='" + bearingAssembleDateLeft + '\'' +
                ", bearingMadeInLeft='" + bearingMadeInLeft + '\'' +
                ", bearingTypeRight='" + bearingTypeRight + '\'' +
                ", bearingAssembleDateRight='" + bearingAssembleDateRight + '\'' +
                ", bearingMadeInRight='" + bearingMadeInRight + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", worker='" + worker + '\'' +
                '}';
    }
}
