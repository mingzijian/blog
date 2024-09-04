[TOC]




### 快速开始

#### 安装brew

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

```



#### 切换阿里云镜像



```shell
cd "$(brew --repo)"
 
git remote set-url origin https://mirrors.aliyun.com/homebrew/brew.git
 
cd "$(brew --repo)/Library/Taps/homebrew/homebrew-core"
 
git remote set-url origin https://mirrors.aliyun.com/homebrew/homebrew-core.git
 
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.aliyun.com/homebrew/homebrew-bottles' >> ~/.bash_profile


source ~/.bash_profile
```



#### 查看brew版本

```bash
brew -v 
```



#### 安装个wget试试

```bash
brew install wget
```



#### 查看已安装软件列表

```bash
brew list
```



#### 升个级

```bash
brew update
```



### 参考

- [brew 官网](brew.sh)
