package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Billing;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Billing")
public class BillingServices {
	
	
	//Billing object 
	Billing BillingObj = new Billing(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)   //output as html 
	public String readBillings(){
	 return BillingObj.readBilling();
	 
  }

//******************************************************************************************************//	
		
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)   //@Consumes specify the input type; here as form data
	@Produces(MediaType.TEXT_PLAIN)					   //@Produces status message; here output as plain text	
	public String insertBilling(@FormParam("CID") String CustomerID,
			@FormParam("BN") String BillNumber,
			@FormParam("PR") String PrevReading,
			@FormParam("CR") String CurReading,
			@FormParam("DA") String DueAmount,
			@FormParam("BD") String BillDate)
	{
		String output = BillingObj.insertBilling(CustomerID,  BillNumber, PrevReading, CurReading, DueAmount, BillDate );
		return output;
  }


//*******************************************************************************************************//
//----used json to update operation-----------------	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String BillingData){
		
		//Convert the input string to a JSON object
		JsonObject BillingObject = new JsonParser().parse(BillingData).getAsJsonObject();
		
		//Read the values from the JSON object
		String CustomerID = BillingObject.get("CID").getAsString();
		String BillNumber = BillingObject.get("BN").getAsString();
		String PrevReading = BillingObject.get("PR").getAsString();
		String CurReading = BillingObject.get("CR").getAsString();
		String DueAmount = BillingObject.get("DA").getAsString();
		String BillDate = BillingObject.get(" BD").getAsString();
		String output = BillingObj.updateBilling(CustomerID, BillNumber, PrevReading, CurReading, DueAmount, BillDate ) ;
	
		return output;
  }


//*********************************************************************************************************//
//----------------used xml to delete operation--------------------	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String BillingData){
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(BillingData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String BillID = doc.select("BID").text();
	 String output = BillingObj.deleteBilling(BillID);
	
	 return output;
  }

}




