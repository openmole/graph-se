docker build . -t virus && \
(docker rm -f virus || true) && \
docker run -d --name virus virus sleep inf
