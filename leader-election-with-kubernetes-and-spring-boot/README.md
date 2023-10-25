# Leader Election Pattern with Kubernetes and Spring Boot Demo Project
This project is a demonstration of the leader election pattern with kubernetes and spring boot as described in [this article](https://blog.ush-network.de/software-engineering/leader-election-pattern-with-kubernetes-and-spring-boot/).

### How to compile and run

```bash
mvn clean package && java \
  -Dkubernetes.leader-election.namespace=default \
  -Dkubernetes.leader-election.lockHolderIdentityName=demo-instance-1 \
  -jar target/leader-election-with-kubernetes-and-spring-boot-0.0.1-SNAPSHOT.jar
```