#Author: Pritha Bazani
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: VisualFabriq Assignment
  I want to validate API endpoint that calculates the amount of time left before next birthday of user
   
  @test1 @runall
  Scenario Outline: Validate if endpoints are giving valid response
    Given I set endpoint as "/Prod/next-birthday?dateofbirth=qp1&unit=qp2" with "<dateofbirth>" and "<unit>"
    And I validated the status as 200
    And I validated the response with respect to "<dateofbirth>" and "<unit>"
    
    Examples:
    |dateofbirth|unit|
		|1990-05-24|hour|
		|1990-05-14|hour|
		|1990-05-24|day|
		|1990-05-24|week|
		|1990-05-24|month|
		
		
	@test2 @runall
  Scenario Outline: Validate if endpoints are giving valid response for birthYear Leapyear
    Given I set endpoint as "/Prod/next-birthday?dateofbirth=qp1&unit=qp2" with "<dateofbirth>" and "<unit>"
    And I validated the status as 200
    And I validated the response with respect to "<dateofbirth>" and "<unit>"
    Examples:
    |dateofbirth|unit|
    |2004-02-28|hour|
		|2004-02-28|week|
		|2004-02-28|day|
		|2004-02-28|month|
		|2004-02-29|hour|
		|2004-02-29|week|
		|2004-02-29|day|
		|2004-02-29|month|	
		
	@test3 @runall
	Scenario Outline: Validate endpoints with invalid request type
    Given I set endpoint as "/Prod/next-birthday?dateofbirth=qp1&unit=qp2" with "<dateofbirth>" and "<unit>" with "<method>"   
    And I validated the status as 403
    Then I validated the response message as "<responseMessage>"
    Examples:
    |dateofbirth|unit|method|responseMessage|
		|1990-05-24|hour|patch|Missing Authentication Token|
		|1990-05-24|day|put|Missing Authentication Token|
		|1990-05-24|week|patch|Missing Authentication Token|
		|1990-05-24|month|put|Missing Authentication Token|
		
	@test4 @runall	
	Scenario Outline: Validate endpoints with invalid dateofBirthFormat
		Given I set endpoint as "/Prod/next-birthday?dateofbirth=qp1&unit=qp2" with "<dateofbirth>" and "<unit>"
    And I validated the status as 200
    Then I validated the response message as "<responseMessage>"
    Examples:
    |dateofbirth|unit|dateofBirthFormat|responseMessage|
		|1990-24-05|hour|YYYY-DD-MM|Please specify dateofbirth in ISO format YYYY-MM-DD|
		|1990/24/05|day|YYYY/DD/MM|Please specify dateofbirth in ISO format YYYY-MM-DD|
		|90-24-05|week|YY-DD-MM|Please specify dateofbirth in ISO format YYYY-MM-DD|
		|05-1990-24|month|MM-YYYY-DD|Please specify dateofbirth in ISO format YYYY-MM-DD|
		
	@test5 @runall	
	Scenario Outline: Validate endpoints with invalid unit and valid dob format
		Given I set endpoint as "/Prod/next-birthday?dateofbirth=qp1&unit=qp2" with "<dateofbirth>" and "<unit>"
    And I validated the status as 200
    Then I validated the response message as "<responseMessage>"
    Examples:
    |dateofbirth|unit|responseMessage|
		|1990-05-24|hours|null|
		|1990-05-24|days|null|
		|1990-05-24|weeks|null|
		|1990-05-24|months|null|
	
	@test6 @runall	
	Scenario Outline: Validate endpoints with invalid endpoint
		Given I set endpoint as "test/Prod/next-birthday?dateofbirth=qp1&unit=qp2" with "<dateofbirth>" and "<unit>"
    And I validated the status as 403
    Then I validated the response message as "<responseMessage>"
    Examples:
    |dateofbirth|unit|responseMessage|
		|1990-05-24|hour|Forbidden|
		|1990-05-24|day|Forbidden|
		|1990-05-24|week|Forbidden|
		|1990-05-24|month|Forbidden|
 			
 	@test7 @runall
 		Scenario Outline: Validate endpoints with invalid queryParam
		Given I set endpoint as "/Prod/next-birthday?age=qp1&unit=qp2" with "<age>" and "<unit>"
    And I validated the status as 400
    Then I validated the response message as "<responseMessage>"
    Examples:
    |age|unit|responseMessage|
		|30|hour|Please specify both query parameter dateofbirth and unit|
		|34|day|Please specify both query parameter dateofbirth and unit|
		|50|week|Please specify both query parameter dateofbirth and unit|
		|10|month|Please specify both query parameter dateofbirth and unit|
 			
 		
		
		