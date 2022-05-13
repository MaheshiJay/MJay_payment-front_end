$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

// If valid------------------------
var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "PaymentsAPI",
 type : type,
 data : $("#formPayment").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});

function validateItemForm()
{
	//Bill ID
	if ($("#billID").val().trim() == "")
	{
		return "Insert Bill ID.";
	}
   
    //paymentAmount
	var price = $("#paymentAmount").val();
	if ($("#paymentAmount").val().trim() == "")
	{
		return "Enter Payment Amount.";
	}
	if ((!$.isNumeric(price)))
	{
	return "Enter a Numberical Value.";
	}
	

	//creditCardType
	if ($("#creditCardType").val().trim() == "")
	{
		return "Enter Card Type.";
	}

	//cardNo
	if ($("#cardNo").val().trim() == "")
	{
		return "Enter Card No";
	}

	
	//expirationalMonth
	if ($("#expirationalMonth").val().trim() == "")
	{
		return "Enter Expire Month";
	}
	
	//expirationalYear
	if ($("#expirationalYear").val().trim() == "")
	{
		return "Enter Expirational Year";
	}

    //expirationalYear
	if ($("#cvn").val().trim() == "")
	{
		return "Enter cvn ";
	}

	return true;

}

function onItemSaveComplete(response, status)
{ 
    if (status == "success") 
    { 
        var resultSet = JSON.parse(response); 
        if (resultSet.status.trim() == "success") 
        { 
            $("#alertSuccess").text("Successfully saved."); 
            $("#alertSuccess").show(); 
            $("#divUsersGrid").html(resultSet.data); 
        } 
        else if (resultSet.status.trim() == "error") 
        { 
            $("#alertError").text(resultSet.data); 
            $("#alertError").show(); 
        } 
    } 
    else if (status == "error") 
    { 
        $("#alertError").text("Error while saving."); 
        $("#alertError").show(); 
    } else
    { 
        $("#alertError").text("Unknown error while saving.."); 
        $("#alertError").show(); 
    }
    $("#hidPaymentIDSave").val(""); 
    $("#formPayment")[0].reset(); 
}

////////////////////////////////////////////////////////////////////////////////

$(document).on("click", ".btnUpdate", function(event)
{ 
    $("#hidPaymentIDSave").val($(this).data("paymentid"));
    $("#billID").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#paymentAmount").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#creditCardType").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#cardNo").val($(this).closest("tr").find('td:eq(3)').text());
    $("#expirationalMonth").val($(this).closest("tr").find('td:eq(4)').text());
    $("#expirationalYear").val($(this).closest("tr").find('td:eq(5)').text());
    $("#cvn").val($(this).closest("tr").find('td:eq(6)').text());
   
    
});

$(document).on("click", ".btnRemove", function(event)
{ 
    $.ajax( 
    { 
        url : "PaymentsAPI", 
        type : "DELETE", 
        data : "paymentID=" + $(this).data("paymentid"),
        dataType : "text", 
        complete : function(response, status) 
        { 
            onItemDeleteComplete(response.responseText, status); 
        } 
    }); 
});


function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
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

















