	<table id="tt" class="easyui-datagrid" style="width: 100%; height: auto" url="/A00911103/EmployeeServlet/action.do?method=paginate"
		title="Employees" iconCls="icon-save" rownumbers="true"
		pagination="true" data-options="pageSize:5,pageList:[5],fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th field="id" width="80">ID</th>
				<th field="firstName" width="120" sortable="true">FirstName</th>
				<th field="lastName" width="80" align="right">LastName</th>
				<th field="dob" width="80" align="right" sortable="true">DOB</th>
			</tr>
		</thead>
	</table>
