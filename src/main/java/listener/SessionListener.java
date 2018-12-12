package listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import bean.User;

/**
 * 这个listener主要是用来记住所有登录过的session
 * @author liubailin
 *
 */
public class SessionListener implements HttpSessionAttributeListener {

    public SessionListener() {
    }


    public void attributeAdded(HttpSessionBindingEvent event)  {
         Object obj = event.getValue();
         if(obj instanceof User) {
        	 Map<Long,HttpSession> map = (Map) event.getSession().getServletContext().getAttribute("sessionmap");
        	 if(map == null) {
        		 map = new HashMap();
        		 event.getSession().getServletContext().setAttribute("sessionmap",map);
        	 }
        	 User u = (User) obj;
         	 HttpSession session = map.put(u.getStu_Nmb(), event.getSession());
	       	 if(!session.equals(event.getSession())) {
	       		 session.invalidate();
	       		 System.out.println("SessionListener.class : logout");
	       	 }
        	 System.out.println("SessionListener.class : login:"+u.toString());
         }
    }

    public void attributeRemoved(HttpSessionBindingEvent event)  {

    }

    public void attributeReplaced(HttpSessionBindingEvent event)  { 
	        Object obj = event.getValue();
	        if(obj instanceof User) {
	       	 Map<Long,HttpSession> map = (Map) event.getSession().getServletContext().getAttribute("sessionmap");
	       	 if(map == null) {
	       		 map = new HashMap();
	       		 event.getSession().getServletContext().setAttribute("sessionmap",map);
	       	 }
	       	 User u = (User) obj;
	       	 
	       	 HttpSession session = map.put(u.getStu_Nmb(), event.getSession());
	       	 if(!session.equals(event.getSession())) {
	       		 session.invalidate();
	       		 System.out.println("SessionListener.class : logout");
	       	 }
	       	 System.out.println("SessionListener.class : login2:"+u.toString());
        }
    }
	
}
