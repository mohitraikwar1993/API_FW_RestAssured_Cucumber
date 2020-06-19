Feature: Validating Place API's

@regression @addPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	| name 	| language 	 | address	 	|
	| Mohit	| English 	 | Bangalore	|
#	| Beauty| English UK | Bhopal		|  


@regression @deletePlace
Scenario: Verify if delete place functionality is woking
	Given DeletePlace payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"