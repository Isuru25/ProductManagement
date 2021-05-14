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
	    var status = validateProductForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidIdSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "ProductAPI", 
		 type : type, 
		 data : $("#formProduct").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onProductSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidIdSave").val($(this).data("pid"));
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#category").val($(this).closest("tr").find('td:eq(1)').text());
	$("#description").val($(this).closest("tr").find('td:eq(2)').text());
	$("#price").val($(this).closest("tr").find('td:eq(3)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "ProductAPI", 
	 type : "DELETE", 
	 data : "id=" + $(this).data("pid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onProductDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateProductForm()
	{
	// Product Name
	if ($("#name").val().trim() == "")
	{
	return "Insert Product Name.";
	}
	// is string value
	var tmpPName = $("#name").val().trim();
	if ($.isNumeric(tmpPName))
	{
	return "Product Name cannot be just a value.";
	}
	// Category
	if ($("#category").val().trim() == "")
	{
	return "Insert Category.";
	}
	// is string value
	var tmpCName = $("#category").val().trim();
	if ($.isNumeric(tmpCName))
	{
	return "Category cannot be just a value.";
	}
	// Description-------------------------------
	if ($("#description").val().trim() == "")
	{
	return "Insert Description.";
	}
	
	// Price-------------------------------
	if ($("#price").val().trim() == "")
	{
	return "Insert Price.";
	}
	// is string value
	var tmpPrice = $("#price").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
	return "Insert Price as numeric value.";
	}
	
	return true;
}

function onProductSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divProductsGrid").html(resultSet.data); 
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
	 $("#hidIdSave").val(""); 
	 $("#formProduct")[0].reset(); 
}

function onProductDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divProductsGrid").html(resultSet.data); 
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