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
var status = validateSolarForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidSolarIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "SolarAPI",
 type : type,
 data : $("#formSolar").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onSolarSaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidSolarIDSave").val($(this).data("solarid"));
 $("#customerName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#panelType").val($(this).closest("tr").find('td:eq(1)').text());
 $("#generatePower").val($(this).closest("tr").find('td:eq(2)').text());
 $("#noOfPanels").val($(this).closest("tr").find('td:eq(3)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "SolarAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("solarid"),
 dataType : "text",
 complete : function(response, status)
 {
 onSolarDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateSolarForm()
{
	
//empName
if ($("#customerName").val().trim() == "")
 {
 return "Insert customername.";
 }

// empNIC
if ($("#panelType").val().trim() == "")
 {
 return "Insert paneltype.";
 } 

// empBdate-------------------------------
if ($("#generatePower").val().trim() == "")
 {
 return "Insert generatepower.";
 }

// empDep-------------------------------
if ($("#noOfPanels").val().trim() == "")
 {
 return "Insert noOfPanels.";
 }
 
return true;
}

function onSolarSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divSolarGrid").html(resultSet.data);
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
$("#hidSolarIDSave").val("");
 $("#formSolar")[0].reset();
}

function onSolarDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divSolarGrid").html(resultSet.data);
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
