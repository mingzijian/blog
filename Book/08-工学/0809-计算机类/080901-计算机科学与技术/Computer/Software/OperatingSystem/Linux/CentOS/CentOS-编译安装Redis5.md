1. **安装依赖**：

   ```bash
   sudo yum install gcc make jemalloc-devel
   ```

2. **下载 Redis 5.x 的源代码**：

   您可以从 Redis 的官方网站下载特定版本的 Redis 5.x。以下示例下载 Redis 5.0.14：

   ```bash
   cd /usr/local/src
   curl -O http://download.redis.io/releases/redis-5.0.14.tar.gz
   ```

3. **解压下载的文件**：

   ```bash
   tar xzvf redis-5.0.14.tar.gz
   cd redis-5.0.14
   ```

4. **编译 Redis**：

   ```bash
   make
   ```

5. **安装 Redis**：

   ```bash
   sudo make install
   ```

6. **创建配置目录**：

   ```bash
   sudo mkdir /etc/redis
   sudo mkdir /var/lib/redis
   ```

7. **复制配置文件**：

   ```bash
   sudo cp redis.conf /etc/redis
   ```

8. **编辑配置文件**：

   使用文本编辑器（如 `nano` 或 `vi`）打开 `/etc/redis/redis.conf`，根据需要进行修改，例如设置持久化、绑定地址等。

   ```bash
   sudo vim /etc/redis/redis.conf
   ```

   - 修改 `supervised` 行的值为 ` systemd`

     ```bash
     supervised systemd
     ```

9. **创建 Redis 服务文件**：

   创建一个 systemd 服务文件 `/etc/systemd/system/redis.service`，内容如下：

   ```ini
   [Unit]
   Description=Redis In-Memory Data Store
   After=network.target
   
   [Service]
   ExecStart=/usr/local/bin/redis-server /etc/redis/redis.conf
   ExecStop=/usr/local/bin/redis-cli shutdown
   User=redis
   Group=redis
   Restart=always
   
   [Install]
   WantedBy=multi-user.target
   ```
   
10. **创建 Redis 用户和组**：

    ```bash
    sudo useradd --system --no-create-home --user-group redis
    ```

11. **设置权限**：

    ```bash
    sudo chown redis:redis /etc/redis/redis.conf
    sudo chown redis:redis /var/lib/redis
    ```

12. **启动 Redis 服务**：

    ```bash
    sudo systemctl start redis
    ```

13. **设置 Redis 开机自启**：

    ```bash
    sudo systemctl enable redis
    ```

14. **检查 Redis 服务状态**：

    ```bash
    sudo systemctl status redis
    ```

15. **测试 Redis 是否正常工作**：

    ```bash
    redis-cli ping
    ```

    如果返回 `PONG`，则表示 Redis 正常运行。