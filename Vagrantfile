# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
	config.vm.define "rock-paper-scissors" do |c|
		c.vm.box = "ubuntu/xenial64"
  		c.vm.hostname = "rock-paper-scissors"
  		c.vm.synced_folder ".", "/data"
  		c.vm.network "private_network", ip: "192.168.56.157"
  		c.vm.provision "shell", inline: <<-SHELL
  			sudo su
	  		apt-get update

	  		# install dependencies
	  		apt-get install -y openjdk-8-jdk nginx

	  		# copy over project src
	  		mkdir -p /opt/application
	  		cp -r /data /opt/application
	  		cd /opt/application/data

	  		# build server application
	  		./mvnw package

	  		# copy and start systemd service
	  		cp /opt/application/data/resources/rps-server.service /etc/systemd/system/rps-server.service
	  		systemctl enable rps-server.service
	  		systemctl daemon-reload
	  		systemctl restart rps-server.service

	  		# configure nginx
	  		cp /opt/application/data/resources/default /etc/nginx/sites-available/default

	  		# copy cert and key
	  		mkdir /etc/nginx/ssl
	  		cp /opt/application/data/resources/server* /etc/nginx/ssl/

	  		# restart nginx
	  		systemctl restart nginx.service

  		SHELL
	end
end