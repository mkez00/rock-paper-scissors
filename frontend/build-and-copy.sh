
#!/bin/sh
echo "Building Project"
yarn build 
cp -rf build/* ../src/main/resources/static/