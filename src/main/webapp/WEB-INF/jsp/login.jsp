<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>

<body>
<div>
   <h1>Login</h1>
   <h1>Sorry, you must log in before accessing this resource.</h1>
</div>
	<div id="win" class="easyui-dialog" title="Login" closable="false" shadow="false" draggable="false" style="width:500px;height:250px;padding:10px;">
		<form id="loginForm" action="j_security_check" method="post" style="padding: 10px 20px 10px 40px;">
			<p>
				 <input class="easyui-textbox" style="width: 100%" data-options="label:'UserName:',required:true" name="j_username">
			</p>
			<p>
				 <input class="easyui-passwordbox" style="width: 100%" data-options="label:'Password:',required:true" name="j_password">
			</p>
			<p>
			    <input name="login" type="submit" value="Submit">
			</p>
		</form>
	</div>
	
</body>
</html>