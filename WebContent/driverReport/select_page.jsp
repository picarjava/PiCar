<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM DriverReport: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM DriverReport: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM DriverReport: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllDriverReport.jsp'>List</a> all DriverReports.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="driverReport.do" >
        <b>��J���|�q���渹 (�pDR001):</b>
        <input type="text" name="dreportID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="driverReportSvc" scope="page" class="com.driverReport.model.DriverReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="driverReport.do" >
       <b>������|�q���渹:</b>
       <select size="1" name="dreportID">
         <c:forEach var="driverReportVO" items="${driverReportSvc.all}" > 
          <option value="${driverReportVO.dreportID}">${driverReportVO.dreportID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>

</ul>


<h3>���|�q���޲z</h3>

<ul>
  <li><a href='addDriverReport.jsp'>Add</a> a new DriverReport.</li>
</ul>

</body>
</html>