package model;

public class LocationVO {
    private String memId;
    private String location;

    public LocationVO() {}
    
    public LocationVO(String memId, String location) {
        setMemId(memId);
        setLocation(location);
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
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
        result = prime * result + ((memId == null) ? 0 : memId.hashCode());
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
        if (memId == null) {
            if (other.memId != null)
                return false;
        } else if (!memId.equals(other.memId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LocationVO [memId=" + memId + ", location=" + location + "]";
    }
}
