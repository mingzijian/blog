### 查看当前GitLab版本
$ cat /opt/gitlab/embedded/service/gitlab-rails/VERSION
```
10.0.0
```
 

### 编辑配置
$ vim /etc/gitlab/gitlab.rb

 

### 修改 gitlab_rails['time_zone'] 的值为 中国的东八区（亚洲-上海）时间
```
gitlab_rails['time_zone'] = 'Asia/Shanghai'
```
 

### 重新配置
$ gitlab-ctl reconfigure
```
Starting Chef Client, version 12.12.15
...
gitlab Reconfigured!
```
 

### 重启生效
$ gitlab-ctl restart
```
ok: run: gitaly: (pid 135398) 1s
ok: run: gitlab-monitor: (pid 135417) 0s
ok: run: gitlab-workhorse: (pid 135423) 0s
ok: run: logrotate: (pid 135442) 1s
ok: run: node-exporter: (pid 135483) 0s
ok: run: postgres-exporter: (pid 135489) 1s
ok: run: postgresql: (pid 135499) 0s
ok: run: prometheus: (pid 135507) 1s
ok: run: redis: (pid 135534) 0s
ok: run: redis-exporter: (pid 135539) 0s
ok: run: sidekiq: (pid 135545) 0s
ok: run: unicorn: (pid 135554) 1s
```