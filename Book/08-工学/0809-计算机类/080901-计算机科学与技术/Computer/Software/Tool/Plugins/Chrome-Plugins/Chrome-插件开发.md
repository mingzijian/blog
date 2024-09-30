

# 目录结构

```shell
.
├── background
│   └── service_worker.js
├── icons
│   └── icon.png
├── manifest.json
└── popup
    ├── index.css
    ├── index.html
    └── index.js
```

## 权限

https://segmentfault.com/a/1190000044554941

## 必需的文件：`manifest.json`

示例：

```json
{
  "manifest_version": 3,
  "name": "My Chrome Extension",
  "version": "0.0.1",
  "description": "My Chrome Extension Description",
  "icons": {
    "16": "icons/icon_16.png",
    "32": "icons/icon_32.png",
    "48": "icons/icon_48.png",
    "128": "icons/icon_128.png"
  },
  "action": {
    "default_icon": "icons/icon.png",
    "default_title": "Popup Title",
    "default_popup": "popup/index.html"
  },
  "background": {
    "service_worker": "background/service_worker.js",
    "type": "module"
  },
  "permissions": [
    "notifications",
    "activeTab",
    "contextMenus",
    "storage"
  ]
}
```

### 必需的字段

1. `manifest_version`：用于指定扩展程序使用的清单文件格式版本，当前（2024年9月26日）是 3
2. `name`：插件名称，一般情况下 `hover` 插件图标展示的文案也是 `name`
3. `version`：插件版本

### 发布 `Chrome` 应用商店需要的字段

1. `description`：插件描述
2. `icons`：图标

### 操作项（`Action`）

1. `default_icon`：工具栏展示的图片
2. `default_title`：`hover` 插件图标展示的文案
3. `default_popup`：点击图标弹出的页面

 ![image-20240926190530742](images\image-20240926190530742.png)





### 后台服务 (`Service Worker`)

```json
{
  "background": {
    "service_worker": "service_worker.js",
    "type": "module"
  }
}
```

如果有多个 JS 需要引入，比如还需要引入 `module_1.js` 和 `module_2.js`
可以在`service_worker.js`中通过 `import` 引入

#### service_worker.js
```js
import './module_1.js'
import './module_2.js'

```
#### module_1.js
```js
console.log('module_1 loaded') 
```
#### module_2.js
```js
console.log('module_2 loaded') 
```


### 内容脚本

```json
{
  "content_scripts": [
   {
    "matches": ["https://google.com/"],
    "js": ["content_scripts.js"]
   }
  ]
}
```



## 加载插件

1，浏览器输入：`chrome://extensions`

2，点击 “加载已解压的扩展程序”

3，选中`manifest.json`文件所在的文件夹

 ![image-20240926192405012](images\image-20240926192405012.png)



# 参考

[Chrome Extensions](https://developer.chrome.google.cn/docs/extensions?hl=zh-cn)

[API 参考](https://developer.chrome.google.cn/docs/extensions/reference/api?hl=zh-cn)

[两万字大章：从 0 到 1 带你开发 Chrome 浏览器插件（Manifest V3 版本）](https://zhuanlan.zhihu.com/p/678535335)

[插件权限](https://segmentfault.com/a/1190000044554941)
