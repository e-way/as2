	<div class="easyui-panel" data-options="region:'east',split:true,footer:'#footerAddEmployee'" title="Add Employees" style="width: 100%; padding: 30px 60px;" >
		<form action="/A00911103/EmployeeServlet/action.do?method=addEmployee" method="post">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
			</div>
			
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="firstName" style="width: 100%" data-options="label:'FirstName:',required:true">
			</div>
			
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="lastName" style="width: 100%" data-options="label:'LastName:',required:true">
			</div>
			
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="dob" style="width: 100%" data-options="prompt:'YYYY/MM/DD',label:'DOB',required:false">
			</div>

			<div align="right">
				<input type="submit" value="Add Employee" />
			</div>
		</form>
		
		
		<div id="footerAddEmployee" style="padding:5px;">
	      <p>Result Code:${addResponseCode} Description: ${addResponseDescription}</p>
	   </div>

	</div>
