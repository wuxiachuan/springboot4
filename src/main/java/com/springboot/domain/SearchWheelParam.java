package com.springboot.domain;

public class SearchWheelParam {
    private String wheelId;
    private String takeInDate;
    private String dispatchDate;
    private String takeInReason;
    private String axleNumber;
    private String axleType;
    private String vehicleNumber;
    private String dipatchVehicleNumber;
    private String infoTakeFinishTime;
    private String status;
    private String isprocessFinish;
    private String page;
    private String size;

    public SearchWheelParam() {
        wheelId = "";
        takeInDate = "";
        dispatchDate = "";
        takeInReason = "";
        axleNumber = "";
        axleType = "";
        vehicleNumber = "";
        dipatchVehicleNumber = "";
        infoTakeFinishTime = "";
        status = "";
        isprocessFinish = null;
    }

    public String getWheelId() {
        return wheelId;
    }

    public void setWheelId(String wheelId) {
        this.wheelId = wheelId;
    }

    public String getTakeInDate() {
        return takeInDate;
    }

    public void setTakeInDate(String takeInDate) {
        this.takeInDate = takeInDate;
    }

    public String getAxleNumber() {
        return axleNumber;
    }

    public void setAxleNumber(String axleNumber) {
        this.axleNumber = axleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getInfoTakeFinishTime() {
        return infoTakeFinishTime;
    }

    public void setInfoTakeFinishTime(String infoTakeFinishTime) {
        this.infoTakeFinishTime = infoTakeFinishTime;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getTakeInReason() {
        return takeInReason;
    }

    public void setTakeInReason(String takeInReason) {
        this.takeInReason = takeInReason;
    }

    public String getAxleType() {
        return axleType;
    }

    public void setAxleType(String axleType) {
        this.axleType = axleType;
    }

    public String getDipatchVehicleNumber() {
        return dipatchVehicleNumber;
    }

    public void setDipatchVehicleNumber(String dipatchVehicleNumber) {
        this.dipatchVehicleNumber = dipatchVehicleNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIsprocessFinish() {
        return isprocessFinish;
    }

    public void setIsprocessFinish(String isprocessFinish) {
        this.isprocessFinish = isprocessFinish;
    }

    @Override
    public String toString() {
        return "SearchWheelParam{" +
                "wheelId='" + wheelId + '\'' +
                ", takeInDate='" + takeInDate + '\'' +
                ", dispatchDate='" + dispatchDate + '\'' +
                ", takeInReason='" + takeInReason + '\'' +
                ", axleNumber='" + axleNumber + '\'' +
                ", axleType='" + axleType + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", dipatchVehicleNumber='" + dipatchVehicleNumber + '\'' +
                ", infoTakeFinishTime='" + infoTakeFinishTime + '\'' +
                ", status='" + status + '\'' +
                ", page='" + page + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
