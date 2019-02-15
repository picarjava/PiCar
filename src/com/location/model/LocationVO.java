package com.location.model;

public class LocationVO {
    private String memID;
    private String location;

    public LocationVO() {} 

    public String getMemID() {
        return memID;
    }

    public void setMemId(String memID) {
        this.memID = memID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((memID == null) ? 0 : memID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocationVO other = (LocationVO) obj;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (memID == null) {
            if (other.memID != null)
                return false;
        } else if (!memID.equals(other.memID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LocationVO [memId=" + memID + ", location=" + location + "]";
    }
}
