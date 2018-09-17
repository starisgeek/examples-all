linux部署(非集群):</br>
1.安装jdk1.8+</br>
2.unzip rocketmq-all-4.3.0-source-release.zip</br>
mv rocketmq-all-4.3.0-bin-release /usr/local/rocketmq-all-4.3.0-bin-release</br>
cd /usr/local/rocketmq-all-4.3.0-bin-release/bin</br>
3.修改runserver.sh jvm配置</br>
4.修改bin/runbroker.sh jvm配置</br>
配置/etc/hosts 增加127.0.0.1  rocketmq-01(hostname)</br>
5../mqnamesrv</br>
./mqbroker -n localhost:9876 autoCreateTopicEnable=true</br>

