package com.location.model;

import java.util.List;

public class LocationService {
    private LocationDAO_interface locationDAO;
    
    public LocationService() {
        locationDAO = new LocationDAO();
    } // LocationService()
    
    public LocationVO getOneLocation(String memID, String location) {
        return locationDAO.findByPrimaryKey(memID, location);
    } // getOneLocation()
    
    public LocationVO addLocation(String memID, String location) {
        LocationVO locationVO = new LocationVO();
        locationVO.setMemId(memID);
        locationVO.setLocation(location);
        locationDAO.insert(locationVO);
        return locationVO;
    } // addLocation()
    
    public LocationVO updateLocation(String memID, String location, String newLocation) {
        LocationVO locationVO = new LocationVO();
        locationVO.setMemId(memID);
        locationVO.setLocation(location);
        locationDAO.update(locationVO, newLocation);
        locationVO.setLocation(newLocation);
        return locationVO;
    } // updateLocation()
    
    public void deleteLocation(String memID, String location) {
        locationDAO.delete(memID, location);
    } // deleteLocation()
    
    public List<LocationVO> getAll(String memID) {   //阿君前台頁面使用
        return locationDAO.getAll(memID);
    } // getAll()
    
    public List<LocationVO> getAll() {
        return locationDAO.getAll();
    } // getAll()
}
