# ModelScope-Agent: 基于开源大语言模型的可定制Agent系统

## 简介

Modelscope-Agent是一个可定制的、可扩展的Agent代码框架。单Agent具有角色扮演、LLM调用、工具使用、规划、记忆等能力。 主要具有以下特点：

- 简单的Agent实现流程：仅需指定角色描述、LLM名称、工具名列表，即可实现一个Agent应用，框架内部自动实现工具使用、规划、记忆等工作流的编排。
- 丰富的模型和工具：框架内置丰富的LLM接口，例如Dashscope和Modelscope模型接口，OpenAI模型接口等。内置丰富的工具，例如**代码运行**、**天气查询**、**文生图**、**网页解析**等，方便定制专属Agent。
- 统一的接口和高扩展性：框架具有清晰的工具、LLM注册机制，方便用户扩展能力更加丰富的Agent应用。
- 低耦合性：开发者可以方便的直接使用内置的工具、LLM、记忆等组件，而不需要绑定更上层的Agent。



## 安装



克隆repo并安装依赖：

```shell
git clone https://github.com/modelscope/modelscope-agent.git
cd modelscope-agent && pip install -r requirements.txt
```



### 使用ModelScope提供的notebook环境【推荐】



ModelScope(魔搭社区)提供给新用户初始的免费计算资源，参考[ModelScope Notebook](https://modelscope.cn/my/mynotebook/preset)

Notebook环境使用简单，您只需要按以下步骤操作（注意：目前暂不提供永久存储，实例重启后数据会丢失）：

```shell
# Step1: 我的notebook -> PAI-DSW -> GPU环境

# Step2: 下载[demo文件](https://github.com/modelscope/modelscope-agent/blob/master/demo/demo_qwen_agent.ipynb)并把它上传到打开的notebook机器上

# Step3: 按顺序执行demo里面的代码块
```