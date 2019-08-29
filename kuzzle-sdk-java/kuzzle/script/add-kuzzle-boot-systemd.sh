#!/bin/bash
echo -e "[Unit]\n
Description=Kuzzle Service\n
After=docker.service\n
Requires=docker.service\n
[Service]\n
Type=simple\n
WorkingDirectory=/home/shiranuit/Documents/Kuzzle/SDK/sdk-java/kuzzle-sdk-java/kuzzle\n
ExecStart=/usr/bin/docker-compose -f /home/shiranuit/Documents/Kuzzle/SDK/sdk-java/kuzzle-sdk-java/./kuzzle/docker-compose.yml up\n
ExecStop=/usr/bin/docker-compose -f /home/shiranuit/Documents/Kuzzle/SDK/sdk-java/kuzzle-sdk-java/./kuzzle/docker-compose.yml stop\n
Restart=on-abort\n
[Install]\n
WantedBy=multi-user.target" > /etc/systemd/system/kuzzle.service
systemctl enable kuzzle
