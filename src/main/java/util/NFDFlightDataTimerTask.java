package util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;

import bean.Dynamics;
import comm.dao.DynamicsDao;
import comm.dao.imp.DynamicsDaoImp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.json.DayBean;
 
/**
 * 在 TimerManager 这个类里面，大家一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
 *发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
 *发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
 * @author wls
 *
 */
public class NFDFlightDataTimerTask extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        try {
            System.out.println("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
            addKingDay();
        } catch (Exception e) {
            System.out.println(" -------------解析信息发生异常--------------");
        }
    }
    /**
     * 金山每日一句
     * 
     */
    public void addKingDay() {
    	OkHttpClient httpClient = new OkHttpClient.Builder().build();
    	 Request request = new Request.Builder().url("http://open.iciba.com/dsapi/")
                 .addHeader("Content-Type", "application/x-www-form-urlencoded")
                 .build();
    	Response response;
    	String res = null ;
		try {
			response = httpClient.newCall(request).execute();
			res = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(res != null) {
	    	DayBean daybean= JSON.parseObject(res,DayBean.class);
	    	request = new Request.Builder().url(daybean.getPicture2()).build();
	    	byte[] img = null;
	    	try {
				response = httpClient.newCall(request).execute();
				img = response.body().bytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	Dynamics dyn = new Dynamics();
			dyn.setTime(new Date());
			dyn.setContent(daybean.getContent()+"\n"+daybean.getNote());
			dyn.setTitle("每日一句");
			dyn.setImgBin(img);
			dyn.setStu_nmb(1314520);
			DynamicsDao dao = new DynamicsDaoImp();
					
			System.out.println("插入动态：金山每日一句 " + new Date().toString());
	
			dao.addDynamics(1314520L, dyn);
		}
    }
    
    
}