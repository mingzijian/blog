

### 简介
    Oh My Zsh is a delightful, open source, community-driven framework for managing your Zsh configuration.
    
    Oh My Zsh 是一个宜人、开源、社区驱动的用来管理 Zsh 配置的框架。
###  快速上手

#### 当前使用的 shell

```bash
echo $SHELL
```

#### 当前支持的 shell

```bash
cat /etc/shells
```

#### 安装 ohmyzsh

```bash
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
```

#### 安装 ohmyzsh 插件

```bash
# 下载语法高亮插件 zsh-syntax-highlighting
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting

# 下载自动提示插件
git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions
```

```shell
# 编辑配置文件 （zsh runtime configuration）
vi ~/.zshrc
```

```bash
# 原配置插件（自带 git）
plugins=(git)
# 新增配置插件 zsh-syntax-highlighting zsh-autosuggestions
plugins=(git zsh-syntax-highlighting zsh-autosuggestions)
```

```bash
# 重新加载配置，使配置立即生效
source ~/.zshrc
```

### 参考

https://ohmyz.sh/

https://github.com/ohmyzsh/ohmyzsh