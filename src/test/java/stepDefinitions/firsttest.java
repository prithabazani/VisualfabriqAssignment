package stepDefinitions;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.*;				


public class firsttest {
	private static final String BASE_URL = "https://lx8ssktxx9.execute-api.eu-west-1.amazonaws.com";
	private static Response response;

	@Given("I set endpoint as {string} with {string} and {string}")
	public void i_set_endpoint(String endpoint,String qp1,String qp2) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		if(endpoint.contains("qp1"))
			endpoint = endpoint.replace("qp1", qp1);
		if(endpoint.contains("qp2"))
			endpoint = endpoint.replace("qp2", qp2);
		response = request.get(endpoint);
	}
	
	@Given("I set endpoint as {string} with {string} and {string} with {string}")  
	public void i_set_endpoint_with_invalid_method(String endpoint,String qp1,String qp2,String method) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		if(endpoint.contains("qp1"))
			endpoint = endpoint.replace("qp1", qp1);
		if(endpoint.contains("qp2"))
			endpoint = endpoint.replace("qp2", qp2);
		if(method.contentEquals("patch"))
		  response = request.patch(endpoint);

		if(method.contentEquals("put"))
			  response = request.put(endpoint);
	}
	
	@And("I validated the response with respect to {string} and {string}")
	public void validated_the_response(String dob, String unit) throws ParseException {
		LocalDate dateOfBirth = LocalDate.parse(dob);
		LocalDateTime now = LocalDateTime.now(); 
		        int curYear= now.getYear();
				int birthYear = dateOfBirth.getYear();
				int birthDate = dateOfBirth.getDayOfMonth();
				int curDate = now.getDayOfMonth();
				Month birthMonth = dateOfBirth.getMonth();		
				Month curMonth = now.getMonth();
				if(curYear<birthYear) {
					   assertFalse("DOB is in future", curYear<birthYear);			  
				}else if(curYear==birthYear) {
					 if(curMonth.compareTo(birthMonth)<0) {
						   assertFalse("DOB is in future", curMonth.compareTo(birthMonth)<0);
					 }else if(curMonth.compareTo(birthMonth)==0) {
						   if(curDate<birthDate)
						      assertFalse("DOB is in future", curDate<birthDate);
					 }					 
				}
				  
				int birthDayOfYear = dateOfBirth.getDayOfYear();
				int curDayOfYear = now.getDayOfYear();
				System.out.println("birthDate="+birthDayOfYear);
				System.out.println("curDayOfYear="+curDayOfYear);
				int remainingDays = birthDayOfYear - curDayOfYear;
				System.out.println("remainingDays="+remainingDays);
				if(remainingDays<0) {
					remainingDays = birthDayOfYear + 365 - curDayOfYear;
					if(curYear%4==0 & birthMonth.getValue()<2)
						remainingDays = remainingDays + 1;
					if((curYear+1)%4==0 & birthMonth.getValue()>2)
						remainingDays = remainingDays + 1;						
				}
					
	    ResponseBody actual_response = response.getBody();
	    actual_response.prettyPrint();
		String jsonString = actual_response.asString();
	    String message[] = jsonString.replaceAll("\"","").split(" ");
	    int remainingNum = Integer.parseInt(message[1]);
	  
		switch(unit){
		case "day":
			assertEquals(remainingDays,remainingNum);
			assert(jsonString.contains(remainingDays+" days left"));
			break;
		case "hour":
			//get hours difference
			int curHrs = now.getHour();
			int remaining_hrs = (24-curHrs)+(remainingDays-1)*24;
			assertEquals(remaining_hrs,remainingNum);
			assert(jsonString.contains(remaining_hrs+" hours left"));
			break;
		case "week":
			int remaining_weeks = remainingDays/7;
			assertEquals(remaining_weeks,remainingNum);
			assert(jsonString.contains(remaining_weeks+" weeks left"));
			break;
		case "month":
			//get month difference
			int monthDiff = birthMonth.compareTo(curMonth);
			if(monthDiff<0)
				monthDiff = monthDiff + 12;
			assertEquals(monthDiff,remainingNum);
			assert(jsonString.contains(monthDiff+" months left"));
		    break;
		}		    					 
	}
	
	
    @And("^I validated the status as (\\d+)$") 
    public void i_validated_the_status(int expected_statusCode) {
        assertEquals(expected_statusCode,response.statusCode());	
    }
    
    @Then("I validated the response message as {string}")
    public void i_validated_the_response_message(String responseMsg) {
    	ResponseBody actual_response = response.getBody();
    	actual_response.prettyPrint();
    	assert(actual_response.asString().contains(responseMsg));
    }
    
    @Given("I set endpoint as {string} with {string}")
    public void i_set_endpoint_as_with(String endpoint, String qp) {
    	RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		endpoint = endpoint.replace("qp", qp);
		System.out.println(endpoint);
		response = request.get(endpoint);
    }
}
