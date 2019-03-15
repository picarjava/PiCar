package Timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestTimer {
	ServletTimer2 st2 = new ServletTimer2();

	public static void main(String[] args) {
//		Date date = new Date();
//		
//		ServletTimer2 st1 = new ServletTimer2();
//		ServletTimer2 st2 = new ServletTimer2();
//		ServletTimer2 st3 = new ServletTimer2();
//		st1.dotime("1");
//		st2.dotime("2");
//		st3.dotime("3");
//		
//		
//		st1.cancel();
		
		
//		ServletTimer2[] st = new ServletTimer2[10];
//		for (int i = 0; i < st.length; i++) {
//			st[i] = new ServletTimer2();
//
//		}
//
//		st[0].dotime("0");
//		st[1].dotime("1");
//		st[2].dotime("2");
		
		Map<Integer, ServletTimer2> map = new TreeMap<Integer, ServletTimer2>();
		
		ServletTimer2 st2 = new ServletTimer2();
		
		st2.dotime("0");
		map.put(0, st2);
	
		
		st2.dotime("1");
		map.put(1, st2);
		
		st2.dotime("2");
		map.put(2, st2);
		
		st2.dotime("3");
		map.put(3, st2);
		
		st2.dotime("4");
		map.put(4, st2);
		
		
		map.get(0).cancel();
		
		
		
		
		
		

//		ServletTimer2 st2 = new ServletTimer2();
//		ServletTimer2 st3 = new ServletTimer2();
//		ServletTimer2 st4 = new ServletTimer2();
//		st2.doTimeCancle("a");
//		st2.doTimeCancle("b");

//		st2.dotime("b司機", date);	
//		st4.dotime("c司機", date);
//		st3.dotime("a司機", date);	
//		

//		st2.cancel();			
//		st3.cancel();

	}

}
