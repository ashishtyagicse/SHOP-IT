package model;

import java.util.Date;
import java.util.Timer;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 * Servlet implementation class loadsqootdata
 */
@WebServlet("/loadsqootdata")
public class loadsqootdata extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		long interval = Long.parseLong(config.getInitParameter("interval")) * 60 * 1000;
		sqoot_to_elasticsearch loaddata = new sqoot_to_elasticsearch();
		Timer timer = new Timer();
		timer.schedule(loaddata, new Date(), interval);
	}
}
