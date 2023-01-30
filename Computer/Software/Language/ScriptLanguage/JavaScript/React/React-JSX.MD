 JSX （JavaScript syntax extension）
--------------------------------------------------------

JSX，是一个 JavaScript 的语法扩展。

JSX 可以很好地描述 UI 应该呈现出它应有交互的本质形式。
JSX 可能会使人联想到模版语言，但它具有 JavaScript 的全部功能。
JSX 可以生成 React “元素”。
React 不强制要求使用 JSX，但在 JavaScript 代码中将 JSX 和 UI 放在一起时，会在视觉上有一定辅助作用。

有趣的`JSX`标签语法(既不是字符串也不是 HTML)
```javascript
const element = <h1>Hello, world!</h1>;
```
在下面的示例中，我们将调用` JavaScript` 函数 `formatName(user)` 的结果，并将结果嵌入到` <h1> `元素中。

```javascript
function formatName(user) {
  return user.firstName + ' ' + user.lastName;
}

const user = {
  firstName: 'Harper',
  lastName: 'Perez'
};

const element = (
  <h1>
    Hello, {formatName(user)}!
  </h1>
);

ReactDOM.render(
  element,
  document.getElementById('root')
);
```


### 参考
- https://reactjs.org/docs/introducing-jsx.html
- https://react.docschina.org/docs/introducing-jsx.html