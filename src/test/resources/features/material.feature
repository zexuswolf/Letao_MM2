Feature: REST - Material 
	
	Scenario: List all materials
		When call GET "/api/materials"
		Then server should return status 200

	Scenario: Get material by ID
		When call GET "/api/materials/1"
		Then server should return status 200
		
	Scenario: Create material
		When call POST "/api/materials" with material data
			| 3 | เค้กสตรอเบอร์รี่ | Strawberry cake | 4 |
		Then server should return status 201
		
	Scenario: Delete material
		When call DELETE "/api/materials/1"
		Then server should return status 200
		
	Scenario: Update material
		When call PUT "/api/materials" with material data
			| 3 | เค้กสตรอเบอร์รี่ | Strawberry cake | 4 |
		Then server should return status 200