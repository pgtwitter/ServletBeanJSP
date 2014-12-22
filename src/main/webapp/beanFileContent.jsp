<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="text/plain;charset=UTF-8"%>
<%= application.getInitParameter("buildNumber") %> - <%= application.getInitParameter("mailAddress") %>
--
<jsp:useBean id="beanFileContent" scope="application"
	class="myGroup.ServletBeanJSP.SampleBeanFileContent" />
<jsp:getProperty name="beanFileContent" property="fileContent" />
--