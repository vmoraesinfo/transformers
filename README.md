# transformers
Init application: ./gradlew bootRun
Run tests: ./gradlew test


API:
 List all trasnformers: localhost:8080/transformer -> GET
 
 List one trasnformer by id: localhost:8080/transformer/{id} -> GET
 
 Delete one trasnformer by id: localhost:8080/transformer/{id} -> DELETE
 
 Update one trasnformer by id: localhost:8080/transformer/{id} -> PUT 
 JSON Example{
	"strength": 20,
	"intelligence": 15,
	"speed": 45,
	"endurance": 87,
	"rank": 100,
	"courage": -1,
	"firepower": 20
 }
 
 Create one trasnformer: localhost:8080/transformer -> POST
	JSON Example{
		"strength": 20,
		"intelligence": 15,
		"speed": 45,
		"endurance": 87,
		"rank": 100,
		"courage": -1,
		"firepower": 20
	}
	
Missing parts: Add Swagger and Logger

Assumptions: 
	Update I can update one field per time.
	Batles will not be saved
	Each time I starts application it starts from zero