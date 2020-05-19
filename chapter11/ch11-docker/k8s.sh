kubectl create -f app.yaml

kubectl expose deployment gf-jakartaee-cookbook --type=LoadBalancer --name=gf-jakartaee-cookbook-lb

# IF YOU WANT TO DELETE THE LOAD BALANCER
# 
# kubectl delete services gf-jakartaee-cookbook-lb