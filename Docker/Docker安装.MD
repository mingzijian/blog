Docker 安装
----------------------------------------------------------

### 在 Linux 上安装
#### 在 Deepin 上安装
##### 前期准备
 - Deepin 镜像下载 https://www.deepin.org/mirrors/releases/
 - Deepin 镜像烧录与系统安装
 - Deepin 开放远程端口访问
   - 添加华为源（当无法正常 `apt install` 时）
     ```shell
     # 备份 source.list (养成备份习惯，以防万一)
     sudo cp /etc/apt/source.list /etc/apt/source.list.original

     # 编辑 source.list
     vim /etc/apt/source.list
     # 添加华为源配置  保存退出 （shift ZZ / :wq ） 
     deb [trusted=yes] https://mirrors.huaweicloud.com/deepin stable main contrib non-free
     
     # 更新
     sudo apt update
     ```
  - 安装 openssh-server
    ```shell
    apt install openssh-server
    ```

#### 在 CentOS 上安装
##### 前期准备
 - CentOS 镜像下载 http://isoredirect.centos.org/

##### 安装 Docker
 - 依赖检查
 - 获取并安装 Dockers 容器
 - 配置


### 在 Windows 上安装


### 在 Mac 上安装