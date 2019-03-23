package Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.driver.model.*;

public class countDownTimer {

    final Timer timer = new Timer();
    final int minutes;
    String driverID;

    public countDownTimer(int minutes) {
        this.minutes = minutes;
    }

    public void start(String driverID) {    //driverID是從DriverReport丟出來的參數
        Date now = new Date();
        timer.schedule(new RemindTask() , now, minutes * 1 * 1000);   //每秒倒數       
        this.driverID = driverID;
    }

    class RemindTask extends TimerTask {
        int runtimer= 60;  //30分鐘

        public void run(){
            if (runtimer> 0){
                System.out.println("倒數"+runtimer+":"+ new java.util.Date());
                runtimer--;
            } else{
                System.out.println(runtimer+" Time''s up!!  "+ new java.util.Date());
                DriverService driverService = new DriverService();
                driverService.updateBannedBack(driverID);
                System.out.println("是誰啊?" + driverID);
                timer.cancel(); 
            }
        }
    }

}