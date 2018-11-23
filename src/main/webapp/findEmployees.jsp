<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="easyui-panel" data-options="region:'east',split:true,footer:'#footerFindEmployee'" title="Find an Employee By ID" style="width: 100%; padding: 30px 60px;">
	<form action="/A00911103/EmployeeServlet/action.do?method=findEmployee" method="post">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
		</div>
		<div align="right">
			<input type="submit" value="Search" />
		</div>
	</form>
	
	<c:choose>
	   <c:when test="${findName !=null}">
	       <div id="footerFineEmployee" style="padding:5px;">
				<p>Found ${findName}</p>
		   </div>
	   </c:when>
	   <c:otherwise>
	       
	   </c:otherwise>
	</c:choose>
	<p>Result Code:${findResponseCode} Description:${findResponseDescription}</p> 
	
</div>
