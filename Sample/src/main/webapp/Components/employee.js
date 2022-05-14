// CLIENT-MODEL================================================================
function validateEmployeeForm()
{
// CODE
if ($("#employeeCode").val().trim() == "")
{
return "Insert Employee Code.";
}
// NAME
if ($("#employeeName").val().trim() == "")
{
return "Insert Employee Name.";
}
// PHONE-------------------------------
if ($("#employeePhone").val().trim() == "")
{
return "Insert Employee Phone.";
}
// DESCRIPTION------------------------
if ($("#employeeDesc").val().trim() == "")
{
return "Insert employee Description.";
}
return true;
}


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
var status = validateEmployeeForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
$("#formEmployee").submit();
});







// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidEmployeeIDSave").val($(this).closest("tr").find('#hidEmployeeIDUpdate').val());
$("#employeeCode").val($(this).closest("tr").find('td:eq(0)').text());
$("#employeeName").val($(this).closest("tr").find('td:eq(1)').text());
$("#employeePhone").val($(this).closest("tr").find('td:eq(2)').text());
$("#employeeDesc").val($(this).closest("tr").find('td:eq(3)').text());
});
// CLIENT-MODEL================================================================
function validateEmployeeForm()
{
// CODE
if ($("#employeeCode").val().trim() == "")
{
return "Insert Employee Code.";
}
// NAME
if ($("#employeeName").val().trim() == "")
{
return "Insert Employee Name.";
}

// PHONE-------------------------------
if ($("#employeePhone").val().trim() == "")
{
return "Insert Employee Phone.";
}
// DESCRIPTION------------------------
if ($("#employeeDesc").val().trim() == "")
{
return "Insert Employee Description.";
}
return true;
}


$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateEmployeeForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "employeeAPI", 
 type : type, 
 data : $("#formEmployee").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEmployeeSaveComplete(response.responseText, status); 
 } 
 }); 
});



function onEmployeeSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divEmployeeGrid").html(resultSet.data); 
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
 
 $("#hidEmployeeIDSave").val(""); 
 $("#formEmployee")[0].reset(); 
}

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "employeeAPI", 
 type : "DELETE", 
 data : "employeeID=" + $(this).data("employeeid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEmployeeDeleteComplete(response.responseText, status); 
 } 
 }); 
});



function onEmployeeDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divEmployeeGrid").html(resultSet.data); 
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
