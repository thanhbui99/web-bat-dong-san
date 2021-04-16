package com.laptrinhjavaweb.dto.request;

public class StaffRequest {
    private Long[] ids;

    public StaffRequest(Long[] ids, Long buildingId) {
        this.ids = ids;
        this.buildingId = buildingId;
    }

    private Long buildingId;

    public StaffRequest() {
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
