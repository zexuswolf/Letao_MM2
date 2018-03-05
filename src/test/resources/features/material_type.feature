Feature: REST - Material Type 
	
	Scenario:
		When call GET "/api/materialTypes"
		Then server should return status 200
