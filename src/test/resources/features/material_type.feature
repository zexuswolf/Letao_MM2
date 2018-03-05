Feature: REST - Material Type 
	
	Scenario:
		When call GET "/api/materialTypes"
		Then server should return status 200

	Scenario:
		When call GET "/api/materialTypes/1"
		Then server should return status 200