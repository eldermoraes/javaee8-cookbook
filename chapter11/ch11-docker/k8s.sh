kubectl create -f app.yaml

kubectl expose deployment gf-jakartaee-cookbook --type=LoadBalancer --name=gf-jakartaee-cookbook-lb

kubectl delete services gf-jakartaee-cookbook-lb