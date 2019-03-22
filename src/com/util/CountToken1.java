package com.util;

import java.util.ArrayList;
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

public class CountToken1 {
	List<String> errorMsgs = new LinkedList();

	public void countToken(String memID, Integer amount, String orderID, Integer i) throws Exception {

		System.out.println(memID);
		System.out.println(amount);
		System.out.println(orderID);

		ActivityTokenService atSvc = new ActivityTokenService();
		List<ActivityTokenVO> onesActivityToken = new ArrayList();
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
				int result = -(int) ((o1.getTokenAmount() - o2.getTokenAmount()));
				return result;
			}

		});

		MemberService memberSvc = new MemberService();
		Integer count = 0;
		String memberIDNewest = null;
		String activityIDNewest = null;
		if (!onesActivityToken.isEmpty()) {
			if (onesActivityToken.get(0).getTokenAmount() != 0) {
				amount = -(amount - onesActivityToken.get(0).getTokenAmount());
				// 將第一筆資料歸零OR刪除?
				memberIDNewest = onesActivityToken.get(0).getMemID();
				activityIDNewest = onesActivityToken.get(0).getActivityID();
//				atSvc.cancelToken(memberIDNewest, activityIDNewest);

			} else {
				amount = -amount;
			}
			/***************************/
//			StoreRecordVO storeRecordVO = new StoreRecordVO();

			MemberVO memberVO = memberSvc.getOneMember(memID);
			count = memberVO.getToken() + amount;
//
//			if (count < 0) {
//				System.out.println("總金額小於0");
//				throw new Exception("總金額小於0");
//
//			}else {
			StoreRecordService storeRecordSvc = new StoreRecordService();
//				atSvc.cancelToken(memberIDNewest, activityIDNewest);
//				memberSvc.updateToken(memID, count);
			try {
				memberSvc.updateTokenAndCancelTokenManyOrder(memberIDNewest, activityIDNewest, memID, count, i);
			} catch (RuntimeException e) {
				throw new Exception(e.getMessage() + "代幣餘額小於零");
			}
			// 以下不用rollback處理
			storeRecordSvc.addOrdrID(memID, amount, orderID);

//			}

		}

		/****************/
	}

	public static void main(String[] args) {

//		CountToken ct = new CountToken();
//		ct.countToken("M002", 100, "55");
	}

}
