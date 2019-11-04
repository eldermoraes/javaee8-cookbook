curl -H "Content-Type: application/json" -X POST -d '{"manager":"manager","managed":"analyst"}' http://localhost:8080/ch01-jnosql-graph/resources/role/create

curl http://localhost:8080/ch01-jnosql-graph/resources/role/findByName/intern
