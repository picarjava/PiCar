package com.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import com.activityToken.model.ActivityTokenService;
import com.activityToken.model.ActivityTokenVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.storeRecord.model.StoreRecordService;
import com.storeRecord.model.StoreRecordVO;

public class CountToken {
	List<String> errorMsgs = new LinkedList();

	public void countToken(String memID, Integer amount, String orderID) {

		
		
		ActivityTokenService atSvc = new ActivityTokenService();
		List<ActivityTokenVO> onesActivityToken = new LinkedList();
		onesActivityToken = atSvc.getOnesALL(memID);
		Collections.sort(onesActivityToken, new Comparator<ActivityTokenVO>() {

			@Override
			public int compare(ActivityTokenVO o1, ActivityTokenVO o2) {
				int result = (int) ((o1.getDeadline().getTime() - o2.getDeadline().getTime()));
				return result;
			}

		});

		Collections.sort(onesActivityToken, new Comparator<ActivityTokenVO>() {

			@Override
			public int compare(ActivityTokenVO o1, ActivityTokenVO o2) {
				int result = (int) ((o1.getTokenAmount() - o2.getTokenAmount()));
				return result;
			}

		});

		if (!onesActivityToken.isEmpty()) {
			if (onesActivityToken.get(0).getTokenAmount() != 0) {
				amount = -(amount - onesActivityToken.get(0).getTokenAmount());
				// 將第一筆資料歸零OR刪除?
				String memberIDNewest = onesActivityToken.get(0).getMemID();
				String activityIDNewest = onesActivityToken.get(0).getActivityID();
				atSvc.cancelToken(memberIDNewest, activityIDNewest);

			} else {
				amount = -amount;
			}
/***************************/
//			StoreRecordVO storeRecordVO = new StoreRecordVO();
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(memID);
			Integer count = memberVO.getToken() + amount;
			try {
				if (count < 0) {
					throw new Exception("總金額小於0");

				}
			} catch (Exception e) {
				System.out.println(e);
				return;
			}

			StoreRecordService storeRecordSvc = new StoreRecordService();
			storeRecordSvc.addOrdrID(memID, amount, orderID);
			memberSvc.updateToken(memID, count);
		}
/****************/
	}

	public static void main(String[] args) {

		CountToken ct = new CountToken();
		ct.countToken("M002", 100, "55");
	}

}
