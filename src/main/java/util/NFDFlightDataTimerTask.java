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
 * �� TimerManager ��������棬���һ��Ҫע�� ʱ�������⡣������趨���賿2��ִ�����񡣵�������2���Ժ�
 *�����ĳ����������������������������£����������ִ�У������ǵȵ��ڶ�����賿2��ִ�С�Ϊ�ˣ������������
 *������ֻ���ж�һ�£�������������������ʱ�����ڶ�ʱִ�������ʱ�䣬���ڴ˻����ϼ�һ�졣
 * @author wls
 *
 */
public class NFDFlightDataTimerTask extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        try {
            System.out.println("ִ�е�ǰʱ��"+formatter.format(Calendar.getInstance().getTime()));
            addKingDay();
        } catch (Exception e) {
            System.out.println(" -------------������Ϣ�����쳣--------------");
        }
    }
    /**
     * ��ɽÿ��һ��
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
			dyn.setTitle("ÿ��һ��");
			dyn.setImgBin(img);
			dyn.setStu_nmb(1314520);
			DynamicsDao dao = new DynamicsDaoImp();
					
			System.out.println("���붯̬����ɽÿ��һ�� " + new Date().toString());
	
			dao.addDynamics(1314520L, dyn);
		}
    }
    
    
}