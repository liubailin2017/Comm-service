package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
public class NFDFlightDataTaskListener implements ServletContextListener {
	
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
      new TimerManager();
    }
	
}
