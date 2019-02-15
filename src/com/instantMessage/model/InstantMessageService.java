package com.instantMessage.model;

import java.sql.Date;
import java.util.List;

public class InstantMessageService {
    private InstantMessageInterface instantMessageDAO;
    
    public InstantMessageService() {
        instantMessageDAO = new InstantMessageDAO();
    } // InstantMessageService()
    
    public InstantMessageVO getOneInstantMessage(String memId, Date startTime) {
        return instantMessageDAO.findByPrimaryKey(memId, startTime);
    } // getOneInstantMessage()
    
    public InstantMessageVO addInstantMessage(String memId, Date startTime, String msgRecord) {
        InstantMessageVO instantMessageVO = new InstantMessageVO();
        instantMessageVO.setMemId(memId);
        instantMessageVO.setStartTime(startTime);
        instantMessageVO.setMsgRecord(msgRecord);
        instantMessageDAO.insert(instantMessageVO);
        return instantMessageVO;
    } // addInstantMessage()
    
    public InstantMessageVO updateInstantMessage(String memId, Date startTime, String msgRecord) {
        InstantMessageVO instantMessageVO = new InstantMessageVO();
        instantMessageVO.setMemId(memId);
        instantMessageVO.setStartTime(startTime);
        instantMessageVO.setMsgRecord(msgRecord);
        instantMessageDAO.update(instantMessageVO);
        return instantMessageVO;
    } // updateInstantMessage()
    
    public List<InstantMessageVO> getAll() {
        return instantMessageDAO.getAll();
    } // getAll()
} // class InstantMessageService
