JavaScript-MD5
------------------------

 MD5 的 JavaScript 实现。



```html
<script src="js/md5.min.js"></script>
```



### API

#### 计算给定字符串值的（十六进制编码的）MD5哈希值：
```javascript
var hash = md5('value') // "2063c1608d6e0baf80249c42e2be5804"
```

#### 计算给定字符串值和密钥的（十六进制编码）HMAC-MD5哈希：
```javascript
var hash = md5('value', 'key') // "01433efd5f16327ea4b31144572c67f6"
```

#### 计算给定字符串值的原始MD5哈希值：
```javascript
var hash = md5('value', null, true)
```

#### 计算给定字符串值和密钥的原始HMAC-MD5哈希：
```javascript
var hash = md5('value', 'key', true)
```


### 参考

- [JavaScript-MD5 --github](https://github.com/blueimp/JavaScript-MD5)
- [JavaScript MD5 Demo](https://blueimp.github.io/JavaScript-MD5/)