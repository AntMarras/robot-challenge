# Instructions

To run the service: `./run.sh` or `./mvnw spring-boot:run`

## Note
I am using a in memory database so the data is cleaned every time the service is restarted.

# API
To get the ids of all the robot created so far:
`curl -H "Content-Type: application/json" -X GET http://localhost:8080/api/robot`

To place a robot:
`curl -H "Content-Type: application/json" -X POST -d '{"x":1, "y":2, "face":"NORTH"}' http://localhost:8080/api/robot`

To move a robot:
`curl -H "Content-Type: application/json" -X POST http://localhost:8080/api/robot/<ROBOT ID HERE>/move`

To rotate a robot:
`curl -H "Content-Type: application/json" -X POST -d '{"direction":"LEFT"}' http://localhost:8080/api/robot/<ROBOT ID HERE>/rotate`

To get the report about a robot
`curl -H "Content-Type: application/json" -X GET http://localhost:8080/api/robot/<ROBOT ID HERE>`

