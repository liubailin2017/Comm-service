package util;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
 
/**
 * java��ʱ����ÿ�춨ʱִ������
 * @author wls
 *
 */
public class TimerManager {
    //ʱ����
     private static final long PERIOD_DAY =  24*60*60*1000;
     public TimerManager() {
          Calendar calendar = Calendar.getInstance(); 
                 
          /*** ����ÿ��2:00ִ�з��� ***/
 
          calendar.set(Calendar.HOUR_OF_DAY, 0);
          calendar.set(Calendar.MINUTE, 30);
          calendar.set(Calendar.SECOND, 0);
           
          Date date=calendar.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
          System.out.println(date);
          System.out.println("before  �����Ƚϣ�"+date.before(new Date()));

          if (date.before(new Date())) {
              date = this.addDay(date, 1);
              System.out.println(date);
          }
           
          Timer timer = new Timer();
           
          NFDFlightDataTimerTask task = new NFDFlightDataTimerTask();
          //����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
          timer.schedule(task,date,PERIOD_DAY);

        }
 
         // ���ӻ��������
         public Date addDay(Date date, int num) {
	          Calendar startDT = Calendar.getInstance();
	          startDT.setTime(date);
	          startDT.add(Calendar.DAY_OF_MONTH, num);
	          return startDT.getTime();
         }
}