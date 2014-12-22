package myGroup.ServletBeanJSP;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServletFileContent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		SampleBeanFileContent bean = (SampleBeanFileContent) sc
				.getAttribute("beanFileContent");
		if (bean == null) {
			bean = new SampleBeanFileContent();
			try {
				URL url = this.getClass().getClassLoader()
						.getResource("../text.txt");
				// "WEB-INF/classes"からの相対パス

				Path path = Paths.get(url.toURI());
				byte[] fileContentBytes = Files.readAllBytes(path);
				String fileContent = new String(fileContentBytes,
						StandardCharsets.UTF_8);
				bean.setFileContent(fileContent);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			sc.setAttribute("beanFileContent", bean); // servletcontextのスコープで有効
		}

		RequestDispatcher rd = req
				.getRequestDispatcher("./beanFileContent.jsp");
		rd.forward(req, resp);
	}
}
