## DWEB 使用

* 安装 Storm 的相关环境： Python、Zookeeper、Zeromq、jzmq等；

* 使用Storm DWEB 分支([Storm dweb](https://github.com/thettian/storm)) 的Release jar包安装Storm 集群；

* DWEB storm.yaml 配置

  1，dweb.port 			8089 		Web Server 端口
  2，dweb.worker.threads	64		RPC Server 进程数
  3，dweb.queue.size		128		请求队列大小
  4，dweb.invocation.port	3775		RPC Server 端口
  5，dweb.request.timeout.secs	60		Web Server 请求无效时间
  6，dweb.childopts		"-Xmx768m"	JAVA 内存相关配置
  7，dweb.servers		-"dweb1.foo.com
				-"dweb2.fo.com	Web Server列表
* DWEB Toplogy 开发，上传

  1, 使用Storm DWEB 分支([Storm dweb](https://github.com/thettian/storm)) 的Release jar包进行开发；
  2, 使用 storm jar 命令上传 Web Topology 的jar包到 Storm集群；
  3, 访问http://{dweb.server}:{dweb.port}/{ApplicationName}/...

