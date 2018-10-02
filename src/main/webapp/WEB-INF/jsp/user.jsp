<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

    <script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript"
	src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css">
	
    <div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li>User Master</li>
	</ol>
	<div class="clearfix"></div>
<!-- Body starts here -->
	<!-- <div class="main-content"> -->
		<div class="main-content-inner">
       <div class="clearfix"></div>
		<br><br>
		<div class="row" id="moveTo">
		<div class="col-md-12 col-xs-12">
					
					<div class="col-md-12">
					<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 id="emp"><i class="fa  fa-user-plus "> Add Users</i>	</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					
					<div class="page-body">
							<form:form modelAttribute="userf" action="users" class="form-horizontal" enctype="multipart/form-data" method="Post" >
							 <form:hidden path="id"/>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Username<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="username" class="form-control validate" placeholder="Username"/>
									</div>
								</div>
								
								</div>
								<div class="col-md-6">
								<div class="form-group" id="passwordDiv">
									<label class="col-md-3 control-label no-padding-right">Password<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:password path="password" class="form-control validate" placeholder="Password"/>
									</div>
								</div>
								
								</div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">First Name<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="firstname" class="form-control validate onlyCharacters" placeholder="Firstname"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Last Name<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:input path="lastname" class="form-control validate onlyCharacters" placeholder="Lastname"/>
									</div>
								</div>
								</div>
								
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"> Contact MobileNo<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="contactno" class="form-control validate numericOnly" maxlength="10" placeholder="Contactnumber"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">WhatsApp MobileNo<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="whatsappno" class="form-control validate numericOnly" maxlength="10" placeholder="whatsappnumber"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Personal Email<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="email" class="form-control" placeholder="Personalemail"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"> Company Email<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="cemail" class="form-control" placeholder="Companyemail"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Designation<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:select path="designation" class="form-control " >
											<form:option value="">-- Select Designation --</form:option>
											<form:options items="${roles}"/>
										</form:select>
									</div>
								</div></div>
								
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Date Of Birth<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="dob" class="form-control" placeholder="Date Of Birth"/>
									</div>
								</div></div>
								
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Permanent Address<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:textarea path="paddress" class="form-control" placeholder="Permanentaddress"/>
									</div>
								</div></div>
								
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Communication Address<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:textarea path="caddress" class="form-control" placeholder="Communicationaddress"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Photo Upload<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input type="file" name="file1" id="file1"  multiple="multiple">
									</div>
								</div></div>
								
								<div class="col-md-12" style="text-align:center;">
								<div class="form-group">
									<div class="col-md-offset-3 col-md-6">
										<input type="submit" id="submit1"  class="btn btn-primary" value="Submit"/>
										<input class="btn-default btn reset"  type="reset"  value="Reset" />
									</div>
								</div></div>
								</form:form>
								</div>
								</div>
								
								</div>
					
					</div>
				</div>
			</div>
			</div>
			<!-- /.page-content -->
		<!-- /.main-content-inner -->
	<!-- /.main-content -->
	
	<div class="main-content container">
		<div class="main-content-inner">
		<br>
			
			<div class="page-content">
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<!-- <div class="page-header">
							<h1>All Employees</h1>
							<a href="createUser" style="float: right;color: white;"> Add User</a>
						</div> -->
						<div class="panel panel-primary">
					<div class="panel-heading">
						<h4><i class="fa  fa-users "> Users List</i>	</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="page-body">
							<c:if test="${not empty msg}">
								<div class="row">
									<div class="col-sm-4 col-sm-offset-4">
					                	<div class="form-group">
					                    	<div class="msgcss alert alert-${cssMsg} fadeIn animated" style="text-align: center;">${msg}</div>
					                    </div>
									</div>
								</div>
				            </c:if>
			            	<div id="assigned" class="widget-box widget-color-blue2">
								<div style="display: block;overflow:auto; padding: 20px;"  class="widget-body">
									<div class="widget-main no-padding">
										<div class="table-responsive"  id="tableId">
											<table  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
												<thead >
													<tr>
														<th>Emp ID</th>
														<th>Firstname</th>
														<th>Lastname</th>
														<th>Username</th>
														<th>Personal Email</th>
														<th>Company Email</th>
														<th>Contact Number</th>
														<th>Whatsapp Number</th>
														<th>Designation</th>
														<th>Date Of Birth</th>
														<th>Emp Images</th>													
														<th>Permanent Address</th>
														<th>Communication Address</th>
														<th>Options</th>
													</tr>
													</thead>
													<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>	
						</div></div></div>
					</div>
				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content-inner -->
	</div>
	<!-- /.main-content -->

<!-- Body ends here -->

<script type="text/javascript">
//$(".viewUsers").addClass("active");
$("#pageName").text("User Details");
$(".createUser").addClass("active");
 
</script>

<!-- Body ends here -->

<script type="text/javascript">

$('#dob').datetimepicker({

	useCurrent : false,
	format : 'DD-MMM-YYYY',
	showTodayButton : true,
	sideBySide : true,
	
	toolbarPlacement : 'top',
	focusOnShow : false,

});

var editFields =0;

var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Emp Id</th><th>First Name</th><th>Last Name</th><th>Personal Email</th><th>Company Email</th><th>Designation</th><th>User Name</th><th>Contact Number</th><th>Whatsapp Number</th><th>Date Of Birth</th><th>Emp Images</th><th>Permanent Address</th><th>Communication Address</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.files==undefined) orderObj.files='';
		else
			{
				var list=orderObj.files.split('*');
				var files='';
				for(var i=0;i<list.length;i++)
				{
					files=files+'<a href="reportDocuments/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="reportDocuments/'+list[i]+'" style="height:42px; width:42px"></a>';
				}
				orderObj.files=files;
			} 
/* 	$.each(listOrders,function(i, orderObj) { */
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteUser("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>";
			var cls="activecss";
		}else{  
			var deleterow = "<a class='activate' onclick='deleteUser("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>";
			var cls="inactivecss";
		}
		var edit = "<a class='edit editIt' onclick='editUser("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr class='"+ cls +"'>"
			/* + "<td title='"+orderObj.id+"'>"+ orderObj.id + "</td>" */
			+ "<td title='"+orderObj.empId+"'>"+ orderObj.empId + "</td>"	
			/* + "<td title='"+orderObj.reportto+"'>"+ orderObj.reportName + "</td>" */
			+ "<td title='"+orderObj.firstname+"'>"+ orderObj.firstname + "</td>"
			+ "<td title='"+orderObj.lastname+"'>"+ orderObj.lastname + "</td>"
			+ "<td title='"+orderObj.email+"'>"+ orderObj.email + "</td>"
			+ "<td title='"+orderObj.cemail+"'>"+ orderObj.cemail + "</td>"
			+ "<td title='"+orderObj.designationname+"'>"+ orderObj.designationname + "</td>"
			+ "<td title='"+orderObj.username+"'>"+ orderObj.username + "</td>"
			+ "<td title='"+orderObj.contactno+"'>"+ orderObj.contactno + "</td>"
			+ "<td title='"+orderObj.whatsappno+"'>"+ orderObj.whatsappno + "</td>"
			+ "<td title='"+orderObj.dob+"'>"+ orderObj.dob + "</td>"
			+ "<td title='"+orderObj.files+"'>"+ orderObj.files + "</td>"
			+ "<td title='"+orderObj.paddress+"'>"+ orderObj.paddress + "</td>"
			+ "<td title='"+orderObj.caddress+"'>"+ orderObj.caddress + "</td>"
			
			/* + "<td title='"+orderObj.branchName+"'>"+ orderObj.branchName + "</td>" */
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			/* + "<td ><a style='cursor:pointer' onclick='getPasswordModal("+ orderObj.id +")'>Change Password</a></td>" */ 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}
/* function changePasswordModal(){

	
	var id=$("#userid").val();
	var npassword=$("#npassword").val();
	var cpassword=$("#cpassword").val();
	
	if(npassword==null || npassword == "" || npassword == undefined){
		
		$("#passwordErrormsg").text("Please Confirm New Password");
		return false;
	}
	if(npassword == cpassword ){
		var formData = new FormData();
		formData.append('id', id);
		formData.append('npassword', npassword);
		
		$.fn.makeMultipartRequest('POST', 'adminChangePassword', false,
				formData, false, 'text', function(data) {
			alert("password changed successfully");
			$("#passwordModal").modal('toggle');
			
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1); 
			
			
			$.each(JSON.parse(data),function(key,value) {
				console.log(value);
				alert(value);
				$("#npassword").val('');
	           $("#cpassword").val('');
				$(".msgcss1").css('visibility', 'visible');
				$(".msgcss1").show();
				$("#msg1").text(value);
				$("#msg1").fadeOut(5000);
				
			});
			
				});
		
	}else{
		$("#passwordErrormsg").text("Password Doesn't match");
	}
		
}*/
/*var userData="";
function getPasswordModal(id)
{
	userData=$('#userid').val(id);
	$('#password_modal').trigger('click');
} */


function editUser(id) {
	editFields =id;
	$("#emp").text("Edit User");
	$("#id").val(serviceUnitArray[id].id);
	$("#username").val(serviceUnitArray[id].username);
	$("#password").val(serviceUnitArray[id].password);
	$("#firstname").val(serviceUnitArray[id].firstname);
	$("#lastname").val(serviceUnitArray[id].lastname);
	$("#contactno").val(serviceUnitArray[id].contactno);
	$("#whatsappno").val(serviceUnitArray[id].whatsappno);
	$("#designation").val(serviceUnitArray[id].designation);
	/* $("#designationname").val(serviceUnitArray[id].designationname); */
	$("#email").val(serviceUnitArray[id].email);
	$("#cemail").val(serviceUnitArray[id].cemail);
	$("#dob").val(serviceUnitArray[id].dob);
	$("#paddress").val(serviceUnitArray[id].paddress);
	$("#caddress").val(serviceUnitArray[id].caddress);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
	document.getElementById("username").readOnly  = true;
	//document.querySelector("password").required = false;
    $("#passwordDiv").hide(); 
    var idArray = $.makeArray($('.validate').map(function() {
    	return this.id;
    }));
    console.log(idArray);
} 

function deleteUser(id,enabled){
	var checkstr=null;
	if(enabled == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('id', id);
	    formData.append('enabled', enabled);
		$.fn.makeMultipartRequest('POST', 'deleteUser', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips(); //calling tool tips defined at header
			$('#inActive').prop('checked', false);
		});
	}
} 



$('#username').blur(function() {
	var username=$('#username').val();

	$.ajax({
				type : "GET",
				url : "getUsername",
				data : {"username":username},
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					//alert(data);
					if(data ==='true')
						{
						alert("username already exists")
	 					$('#username').css('border-color', 'red');
						 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#username').css('border-color', 'none');
						$('#submit1').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});

		});
		

/*$('#email').blur(function() {

	var cemail=$(this).val();
	
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  if( regex.test(cemail))
		  {
		  $('#submit1').prop('disabled', false);
	
	
	$.ajax({
				type : "POST",
				url : "checkEmpExstbyemail",
				data :"cemail="+cemail+"&editFields="+editFields,
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#email').css('border-color', 'red');
	 					 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#email').css('border-color', 'none');
						 $('#submit1').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});
		  }
	  else
		  
	{
		  $('#email').css('border-color', 'red');
		  $('#submit1').prop('disabled', true);
		  
	}

		}); 




$('#mobilenumber').blur(function() {
	var cmobile=$(this).val();
	
	
	 
	 if(cmobile.length != 10 )
		 {
		 alert("Mobile Number Length Must Be 10 Digits")
		 $('#mobilenumber').css('border-color', 'red');
		 
			 $('#submit1').prop('disabled', true);
		 
		 }
	 
	
	 else
		 {
	
	
	$.ajax({
				type : "POST",
				url : "checkEmpExst",
				data :"cmobile="+cmobile+"&editFields="+editFields,
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						alert("Mobile Number already exists")
	 					$('#mobilenumber').css('border-color', 'red');
	 					 $('#submit1').prop('disabled', true);
	 					subValidation =false;
						}
					 else
						{
						$('#mobilenumber').css('border-color', 'none');
						 $('#submit1').prop('disabled', false);
						 subValidation =true;
						} 
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});
	
		 }

		}); 
		 */



		 function validate(id, errorMessage)
		 {
		 	var styleBlock = '.placeholder-style.placeholder-style::-moz-placeholder {color: #cc0000;} .placeholder-style::-webkit-input-placeholder {color: #cc0000;}';
		 	if($('#'+id).val() ==  null || $('#'+id).val() == ""  || $('#'+id).val()=="undefined" ) {
		 		$('style').append(styleBlock);
		 		$('#'+id).css('border-color','#cc0000');
		 		$('#'+id).css('color','#cc0000');
		 		$('#'+id).attr('placeholder',errorMessage);
		 		$('#'+id).addClass('placeholder-style your-class');
//		 			$('#'+id).css('color','#cc0000');
//		 			$('#'+id+'Error').text(errorMessage);
		 	}else{
		 		$('#'+id).css('border-color','');
		 		$('#'+id).removeClass('placeholder-style your-class');
//		 			$('#'+id).css('color','');
//		 			$('#'+id+'Error').text("");
		 	}
		 	
		 }
function inactiveData() {
	var enabled="0";
	if($('#inActive').is(":checked") == true){
		enabled="0";
	}else{
		enabled="1";
	}
		var formData = new FormData();
		formData.append('enabled', enabled);
		
		$.fn.makeMultipartRequest('POST', 'inActiveUser', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			//console.log(jsonobj.allOrders1);
			 toolTips();
				});
		
}
 	
document.getElementById("file1").onchange = function () {
    var reader = new FileReader();
    
    for(var i=0; i<=this.files.length; i++)
    {
     
    /* if(this.files[i].size>528385){
        alert("Image Size should not be greater than 528Kb");
        $("#file1").attr("src","blank");
       // $("#file1").hide();  
        $('#file1').wrap('<form>').closest('form').get(0).reset();
        $('#file1').unwrap();     
        return false; 
    }  */
    if(this.files[i].type.indexOf("image")==-1){
        alert("Invalid Type");
        $("#file1").attr("src","blank");
        //$("#file1").hide();  
       $('#file1').wrap('<form>').closest('form').get(0).reset();
      //  $('#file1').unwrap();         
        return false;
    }   
    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("menu_image").src = e.target.result;
        $("#file1").show(); 
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
    

    }
}	
</script>