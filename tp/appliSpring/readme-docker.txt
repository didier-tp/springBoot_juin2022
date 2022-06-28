#se placer (cd) dans répertoire appliSpring 
#(où est situé Dockerfile et target/appliSpring.jar construit par maven)
su
docker image build -t xyz/appliSpring  .
docker image ls
docker run -p 8080:8080 -d --name appliSpring-container --network mynetwork --network-alias=appliSpring.host xyz/appliSpring
docker container ls
