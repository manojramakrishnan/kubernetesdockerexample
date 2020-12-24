# kubernetesdockerexample

Spring boot Docker and Kubernetes complete application code and Steps

Pre-Requisites

Install Docker for desktop.
enable Kubernetes cluster in Docker for desktop.
install java 1.8.
create a dockerhub account.
install git in your system.

1) open git bash terminal
2) checkout the code from github using the following command  git clone https://github.com/manojramakrishnan/kubernetesdockerexample.git
3) Type the command cd kubernetesdockerexample and verify the data is downloaded.
4) import the above project in eclipse.
5) set the java buildpath, project facets and compiler in eclipse.
6) once project setup is done, right click the project select maven and update project.
7) Again right click the project, build the project by selecting Run as new configurations-> select the project by clicking the browse button. After that, mention the maven goal as clean install package and select the skip test option. Then click on the environment tab and select the java executable(java 8) and click on apply and run the project. It will build the project and create a jar file.

Docker image Creation

open the cmd and type the following command to build a docker image
docker image build -t springboot-k8s-mysql:1.0

kubernetes installation and verification

open docker for desktop and go to settings section. enable kubernetes by checking on enable kubernetes. It will enable kubernetes single cluster.

Deployment of application in kubernetes cluster

open cmd and go to projects folder and then cd src/main/resources and start the following steps.

All the db related information except sensitive information are configured in mysql-configmap.yml file. First step is to create the configmap.

kubectl apply -f mysql-configmap.yml

The above command will create the configmap. To verify the creation of configmap, execute the following command

kubectl get configmap

second step is to create encrypted username and password. the following commands will give the encrypted username and password.

echo -n 'root' | base64
echo -n 'my-super-secret-password' | base64

third step is to copy the encrypted username and password and paste it in secrets.yml file. After that, the secrets yml file needs to be created. the following is the command to create a secrets.

kubectl apply -f secrets.yml

To verify the creation of secrets, execute the following command.

kubectl get secrets

Fourth step is to install the mysql in kubernetes container. To do that, the following command will deploy mysql.

kubectl apply -f mysql-deployment.yml

to verify the deployment, execute the following command

kubectl get deployments

Fifth step is to create the service of mysql. To do that, execute the following command,

kubectl apply -f mysql-service.yml

to verify the service, execute the following command.

kubectl get services

Sixth step is to deployment the spring boot application in kubernetes containers. To do that, execute the following command,

kubectl apply -f deployment.yml

To verify the deployment, execute the following command,

kubectl get deployments

Seventh step is to create the service for the spring boot application. To do that, execute the following command

kubectl apply -f deployment-service.yml

To verify the service, execute the following command,

kubectl get services

Final step is to verify whether all three replicas for spring boot app and one replica for mysql is started successfully. To do that, execute the following command,

kubectl get pods

Test the spring boot application:-

To add a users data, the url is as follows. Please use postman for testing the same. In the body section, under raw option, Add the json request as mentioned below. It is a post request

http://localhost:30163/addUser

{
  "name" : "user1",
  "country" : "India"
}

To fetch the users data, the url is as follows. It is a get request.

http://localhost:30163/users

To fetch user data based on id, the url is as follows: It is a get request.

http://localhost:30163/users/1

That's all, if all the above urls works fine, You are done with spring boot application deployment and testing in dockerized kubernetes environment.






