<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Admin Tool</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>

<body>
	<div class="easyui-layout" fit="true">
			<div data-options="region:'center',split:true" style="width: 70%;">
			    <div class="easyui-layout" fit="true">
			    	<div data-options="region:'North',title:'Sign Off',split:true" style="width: 100%;">
			    	   <jsp:include page="/Employees.jsp" flush="true" />
			    	</div>
			    	<div data-options="region:'South',title:'Sign Off',split:true" style="width: 100%;">
						<jsp:include page="/Logout.jsp" flush="true" />
					</div>
			    </div>
			</div>
	   <div data-options="region:'east',split:true" style="width: 30%; height:auto">
           <div class="easyui-layout" fit="true">
			 <div data-options="region:'center',split:true" style="width: 100%; height: auto">
			 
			 <div data-options="region:'center',title:'Find an Employee By ID',split:true" style="height: auto;">
					<jsp:include page="/findEmployees.jsp" flush="true" />
				</div>
				
			<c:if test="${pageContext.request.isUserInRole('admin')}">
			 	<div data-options="region:'north',title:'Add Employees',split:true" style="height: auto;">
					<jsp:include page="/addEmployees.jsp" flush="true" />
				</div>
				<div data-options="region:'south',title:'Remove an Employee',split:true" style="height: auto;">
					<jsp:include page="/deleteEmployee.jsp" flush="true" />
				</div>
			 </c:if>
		   </div>
		</div>
       </div>
	</div>
</body>
</html>
