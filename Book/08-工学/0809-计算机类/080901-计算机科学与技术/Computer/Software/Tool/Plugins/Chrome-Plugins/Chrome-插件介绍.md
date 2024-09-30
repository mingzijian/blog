### 一、什么是 `Chrome` [浏览器插件](https://zhida.zhihu.com/search?content_id=238915759&content_type=Article&match_order=1&q=浏览器插件&zhida_source=entity)？

`Chrome` 浏览器插件可通过自定义界面、观察浏览器事件和修改网络来提升浏览体验。

### 二、`Chrome` 浏览器插件是如何构建的？

使用 `Web` 技术开构建 `Chrome` 插件：`HTML、CSS、JS`。

### 三、`Chrome` 浏览器插件可以做什么？

### 1. 设计界面

大多数[扩展程序](https://zhida.zhihu.com/search?content_id=238915759&content_type=Article&match_order=1&q=扩展程序&zhida_source=entity)都需要某种类型的用户互动才能正常运行。

扩展程序平台提供了多种方式来向扩展程序添加互动。这些方法包括从 `Chrome` 工具栏、侧边栏、上下文菜单等触发的弹出式窗口：

1. 侧边栏（`Side panel`）
2. 操作项（`Action`）
3. 菜单项（`Menus`）

### 2. 控制浏览器

借助 `Chrome` 的扩展程序 `API`，可以改变浏览器的工作方式：

1. 覆盖 `Chrome` 页面和设置项：`Manifest.json` 配置 `chrome_settings_overrides`
2. 扩展[开发者](https://zhida.zhihu.com/search?content_id=238915759&content_type=Article&match_order=1&q=开发者&zhida_source=entity)工具：`Manifest.json` 配置 `devtools_page`
3. 显示通知：`chrome.notifications API`
4. 管理历史记录：`chrome.history API`
5. 控制标签页和窗口：`chrome.tabs、chrome.tabGroups` 和 `chrome.windows` 等 `API`
6. 键盘快捷键：`chrome.commands API`
7. 身份认证：`chrome.identity API`
8. 管理插件：`chrome.management API`
9. 提供建议：`chrome.omnibox API`
10. 更新 `Chrome` 设置：`chrome.proxy API`
11. 下载管理：`chrome.downloads API`
12. 书签：`chrome.bookmarks API`
13. ...

### 3. 控制网络

可以通过注入脚本、拦截网络请求以及使用 `Web API` 与网页进行交互，来控制和修改 `Web`：

1. 注入 `JS` 和 `CSS` 文件
2. 访问当前 `Tab` 页
3. 控制 `Web` 请求
4. 录音和屏幕截图
5. 修改网站设置