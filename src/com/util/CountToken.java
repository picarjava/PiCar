package com.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import com.activityToken.model.ActivityTokenService;
import com.activityToken.model.ActivityTokenVO;
import com.storeRecord.model.StoreRecordService;

public class CountToken {
	List<String> errorMsgs = new LinkedList();

	public void countToken(String memID, Integer amount, String orderID) {

		ActivityTokenService atSvc = new ActivityTokenService();
		List<ActivityTokenVO> onesActivityToken = new LinkedList();
		onesActivityToken = atSvc.getOnesALL(memID);
		Collections.sort(onesActivityToken, new Comparator<ActivityTokenVO>() {

			@Override
			public int compare(ActivityTokenVO o1, ActivityTokenVO o2) {
				int result = (int) (o1.getDeadline().getTime() - o2.getDeadline().getTime());
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

			StoreRecordService storeRecordSvc = new StoreRecordService();
			storeRecordSvc.addOrdrID(memID, amount, orderID);
		}
	}

	public static void main(String[] args) {

		CountToken ct = new CountToken();
		ct.countToken("M002", 100, "55");
	}

}
