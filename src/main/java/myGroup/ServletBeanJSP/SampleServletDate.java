package myGroup.ServletBeanJSP;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SampleServletDate extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Date d = new Date();
		SampleBeanDate bean_req = (SampleBeanDate) req.getAttribute("bean_req");
		if (bean_req == null) {
			bean_req = new SampleBeanDate();
			bean_req.setDate(d.toString());
			req.setAttribute("bean_req", bean_req); // req(HttpServletRequest)のスコープで有効
		}

		HttpSession session = req.getSession();
		SampleBeanDate bean_session = (SampleBeanDate) session
				.getAttribute("bean_session");
		if (bean_session == null) {
			bean_session = new SampleBeanDate();
			bean_session.setDate(d.toString());
			session.setAttribute("bean_session", bean_session); // session(HttpSession)のスコープで有効
		}

		ServletContext sc = this.getServletContext();
		SampleBeanDate bean_sc = (SampleBeanDate) sc.getAttribute("bean_sc");
		if (bean_sc == null) {
			bean_sc = new SampleBeanDate();
			bean_sc.setDate(d.toString());
			sc.setAttribute("bean_sc", bean_sc); // sc(ServletContext)のスコープで有効
		}

		RequestDispatcher rd = req.getRequestDispatcher("./beanDate.jsp");
		rd.forward(req, resp);
	}
}
