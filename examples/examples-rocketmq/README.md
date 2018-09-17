linux部署(非集群):
1.安装jdk1.8+
2.unzip rocketmq-all-4.3.0-source-release.zip
mv rocketmq-all-4.3.0-bin-release /usr/local/rocketmq-all-4.3.0-bin-release
cd /usr/local/rocketmq-all-4.3.0-bin-release/bin
3.修改runserver.sh jvm配置
./mqnamesrv
4.修改bin/runbroker.sh jvm配置
配置/etc/hosts 增加127.0.0.1  rocketmq-01(hostname)
./mqbroker -n localhost:9876 autoCreateTopicEnable=true

