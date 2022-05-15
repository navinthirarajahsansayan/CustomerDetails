$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateCustomerForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidPIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "CustomerAPI", 
type : type, 
data : $("#formCus").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onCustomerSaveComplete(response.responseText, status); 
} 
}); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidPIDSave").val($(this).data("pid")); 
 $("#wiringMethod").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#fullName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#phoneNo").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#electricID").val($(this).closest("tr").find('td:eq(3)').text()); 
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "CustomerAPI", 
		 type : "DELETE", 
		 data : "PID=" + $(this).data("pid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onCustomerDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


//CLIENT-MODEL================================================================
function validateCustomerForm() 
{ 
// CODE
if ($("#wiringMethod").val().trim() == "") 
 { 
 return "Insert Method."; 
 } 
// NAME
if ($("#fullName").val().trim() == "") 
 { 
 return "Insert full Name."; 
 } 
//PRICE-------------------------------
if ($("#phoneNo").val().trim() == "") 
 { 
 return "Insert phoneNo."; 
 } 

// is numerical value
var tmpPrice = $("#phoneNo").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for phoneNo."; 
 } 
// convert to decimal price
 $("#phoneNo").val(parseFloat(tmpPrice).toFixed(2)); 
 
// DESCRIPTION------------------------
if ($("#electricID").val().trim() == "") 
 { 
 return "Insert electricID."; 
 } 
return true; 
}

function onCustomerSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidPIDSave").val(""); 
$("#formCus")[0].reset(); 
}


function onCustomerDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
