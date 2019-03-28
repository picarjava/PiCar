<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 
<!-- Required meta tags -->
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- Material Kit CSS -->
<link href="assets/css/material-dashboard.css" rel="stylesheet" />
<style>
table, tr, td, th {
	background-color: white;
	border: 1px solid #aaa;
	text-align: center;
	padding: 5px;
	text-align: center;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

table {
	width: 100%;
}

.col-9 {
	margin-top: -15px;
	margin-left: -55px;
	margin-bottom: 1rem;
}

#error {
	margin-left: 20px;
}

#s3 {
	width: 186px;
}

.footer {
  position: absolute;
  left: 1280px;
  top:600px;
}

#p1 {
	margin-top:12px;
	margin-right:12px;

}

</style>

<script type="text/javascript">
	        function test(){
	        var f = document.form1;
	        var newPsw1 = f.password.value;
	        var newPsw2 = f.password2.value;
	        if(newPsw1 == "" || newPsw2 == ""){
	        alert("密碼請勿空白");
	        return false;
	        }
	        if(newPsw1.length<6){
		   	alert("密碼不得低於6個英數字");
		    return false;
		    }
	        if(newPsw1.length>12){
			   	alert("密碼不得超過12個英數字");
			    return false;
			}
	        if(newPsw1 != newPsw2){
	        alert("新密碼輸入不一致");
	        return false;
	        }
	        return true;
	        }
	
</script>