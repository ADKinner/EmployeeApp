Story for Employee Controller

Scenario: Get Request Scenario

Given Client and Employee Base with url http://localhost:8080/employees/
When Client perform get request to Employee Base with id = 5
Then Client get response with status 200
When Client perform get request to Employee Base with id = -10
Then Client get response with status 400

Scenario: Get All Request Scenario

Given Client and Employee Base with url http://localhost:8080/employees/
When Client perform get request to Employee Base
Then Client get response with status 200

Scenario: Delete Request Scenario

Given Client and Employee Base with url http://localhost:8080/employees/
When Client perform delete request to Employee Base with id = -10
Then Client get response with status 400
