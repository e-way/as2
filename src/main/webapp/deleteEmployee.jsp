<div class="easyui-panel" data-options="region:'east',split:true,footer:'#footerRemoveEmployee'" title="Remove an Employee" style="width: 100%; padding: 30px 60px;">
	<form action="/A00911103/EmployeeServlet/action.do?method=removeEmployee" method="post">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
		</div>
		<div align="right">
			<input type="submit" value="Remove Employee" />
		</div>
	</form>
	<div id="footerRemoveEmployee" style="padding:5px;">
	      <p>Result Code:${deleteResponseCode} Description: ${deleteResponseDescription}</p>
	   </div>
</div>
