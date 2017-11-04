
#!/bin/sh
echo "Building Project"
yarn build 
rm -rf ../src/main/resources/static/static/*
cp -rf build/* ../src/main/resources/static/