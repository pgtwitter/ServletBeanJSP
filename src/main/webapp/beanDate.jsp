<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="text/plain;charset=UTF-8"%>
- scope request -
<jsp:useBean id="bean_req" scope="request"
	class="myGroup.ServletBeanJSP.SampleBeanDate" />
<jsp:getProperty name="bean_req" property="date" />
--

- scope session -
<jsp:useBean id="bean_session" scope="session"
	class="myGroup.ServletBeanJSP.SampleBeanDate" />
<jsp:getProperty name="bean_session" property="date" />
--

- scope application -
<jsp:useBean id="bean_sc" scope="application"
	class="myGroup.ServletBeanJSP.SampleBeanDate" />
<jsp:getProperty name="bean_sc" property="date" />
--