package com.location.model;

import java.util.List;

public class LocationService {
    private LocationDAO_interface locationDAO;
    
    public LocationService() {
        locationDAO = new LocationDAO();
    } // LocationService()
    
    public LocationVO getOneLocation(String memId, String location) {
        return locationDAO.findByPrimaryKey(memId, location);
    } // getOneLocation()
    
    public LocationVO addLocation(String memId, String location) {
        LocationVO locationVO = new LocationVO();
        locationVO.setMemId(memId);
        locationVO.setLocation(location);
        locationDAO.insert(locationVO);
        return locationVO;
    } // addLocation()
    
    public LocationVO updateLocation(String memId, String location, String newLocation) {
        LocationVO locationVO = new LocationVO();
        locationVO.setMemId(memId);
        locationVO.setLocation(location);
        locationDAO.update(locationVO, newLocation);
        locationVO.setLocation(newLocation);
        return locationVO;
    } // updateLocation()
    
    public void deleteLocation(String memId, String location) {
        locationDAO.delete(memId, location);
    } // deleteLocation()
    
    public List<LocationVO> getAll() {
        return locationDAO.getAll();
    } // getAll()
}
