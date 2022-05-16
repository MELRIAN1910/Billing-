 $(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 
// Form validation-------------------
var status = validateBillingForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "BillingAPI",
 type : type,
 data : $("#formBilling").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onBillingSaveComplete(response.responseText, status);
 }
 });
});

function onBillingSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divBillingsGrid").html(resultSet.data);
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
 }  $("#hidBillingIDSave").val("");
 $("#formBilling")[0].reset();
}


function onBillingDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divBillingsGrid").html(resultSet.data);
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

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "BillingAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("Billingid"),
 dataType : "text",
 complete : function(response, status)
 {
 onBillingDeleteComplete(response.responseText, status);
 }
 });
});


$(document).on("click", ".btnUpdate", function(event)
{
$("#hidBillingIDSave").val($(this).data("itemid"));
 $("#CID").val($(this).closest("tr").find('td:eq(0)').text());

 $("#BN").val($(this).closest("tr").find('td:eq(2)').text());
 $("#PR").val($(this).closest("tr").find('td:eq(3)').text());
 $("#CR").val($(this).closest("tr").find('td:eq(3)').text());
 $("#DA").val($(this).closest("tr").find('td:eq(3)').text());
 $("#BD").val($(this).closest("tr").find('td:eq(3)').text());
});


function validateBillingForm()
{
// CODE
if ($("#CID").val().trim() == "")
 {
 return "Fields cannot be empty.";
 }
// NAME
if ($("#BN").val().trim() == "")
 {
 return "Fields cannot be empty.";
 } 
 // PRICE-------------------------------
if ($("#PR").val().trim() == "")
 {
 return "Fields cannot be empty.";
 }

if ($("#CR").val().trim() == "")
 {
 return "Fields cannot be empty.";
 }
 if ($("#DA").val().trim() == "")
 {
 return "Fields cannot be empty.";
 }
 if ($("#BD").val().trim() == "")
 {
 return "Fields cannot be empty.";
 }
 return true;
}