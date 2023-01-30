AES-JS
---------
AES分组密码算法和所有常见操作模式（CBC、CFB、CTR、ECB和OFB）的纯JavaScript实现。

### 特性
- 纯JavaScript实现（无依赖项） 
- 支持所有尺寸密钥（128位，192位和256位） 
- 支持所有常见的操作模式（CBC，CFB，CTR，ECB和OFB） 
- 在node.js或Web浏览器中均可

### 常用加密模式
#### CBC （推荐）

密码块链接 Cipher-Block Chaining (recommended)
先将明文切分成若干小段，然后每一小段与初始块或者上一段的密文段进行异或运算后，再与密钥进行加密。

 ![CBC](https://gitee.com/mingzijian/resources/raw/master/picgo/image-20210406163042149.png)

```javascript
// An example 128-bit key
var key = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 ];

// The initialization vector (must be 16 bytes)
var iv = [ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,35, 36 ];

// Convert text to bytes (text must be a multiple of 16 bytes)
var text = 'TextMustBe16Byte';
var textBytes = aesjs.utils.utf8.toBytes(text);

var aesCbc = new aesjs.ModeOfOperation.cbc(key, iv);
var encryptedBytes = aesCbc.encrypt(textBytes);


// To print or store the binary data, you may convert it to hex
var encryptedHex = aesjs.utils.hex.fromBytes(encryptedBytes);
console.log(encryptedHex);
// "104fb073f9a131f2cab49184bb864ca2"

// When ready to decrypt the hex string, convert it back to bytes
var encryptedBytes = aesjs.utils.hex.toBytes(encryptedHex);

// The cipher-block chaining mode of operation maintains internal
// state, so to decrypt a new instance must be instantiated.
var aesCbc = new aesjs.ModeOfOperation.cbc(key, iv);
var decryptedBytes = aesCbc.decrypt(encryptedBytes);

// Convert our bytes back into text
var decryptedText = aesjs.utils.utf8.fromBytes(decryptedBytes);
console.log(decryptedText);
// "TextMustBe16Byte"
```


#### ECB (安全性较差)
电码本 Electronic Codebook
将整个明文分成若干段相同的小段，然后对每一小段进行加密。

 ![ECB](https://gitee.com/mingzijian/resources/raw/master/picgo/image-20210406162408159.png)

ECB模式作为一种基本工作模式，具有操作简单，易于实现的特点。同时由于其分组的独立性，利于实现并行处理，并且能很好地防止误差传播。
另一方面由于所有分组的加密方式一致，明文中的重复内容会在密文中有所体现，因此难以抵抗统计分析攻击。
因此，ECB模式一般只适用于小数据量的字符信息的安全性保护，例如密钥保护。

```javascript
// the AES block cipher algorithm works on 16 byte bloca ks, no more, no less
var text = "ABlockIs16Bytes!";
var textAsBytes = aesjs.utils.utf8.toBytes(text)
console.log(textAsBytes);
// [65, 66, 108, 111, 99, 107, 73, 115, 49, 54, 66, 121, 116, 101, 115, 33]

// create an instance of the block cipher algorithm
var key = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3];
var aes = new aesjs.AES(key);

// encrypt...
var encryptedBytes = aes.encrypt(textAsBytes);
console.log(encryptedBytes);
// [136, 15, 199, 174, 118, 133, 233, 177, 143, 47, 42, 211, 96, 55, 107, 109] 

// To print or store the binary data, you may convert it to hex
var encryptedHex = aesjs.utils.hex.fromBytes(encryptedBytes);
console.log(encryptedHex);
// "880fc7ae7685e9b18f2f2ad360376b6d"

// When ready to decrypt the hex string, convert it back to bytes
var encryptedBytes = aesjs.utils.hex.toBytes(encryptedHex);

// decrypt...
var decryptedBytes = aes.decrypt(encryptedBytes);
console.log(decryptedBytes);
// [65, 66, 108, 111, 99, 107, 73, 115, 49, 54, 66, 121, 116, 101, 115, 33]


// decode the bytes back into our original text
var decryptedText = aesjs.utils.utf8.fromBytes(decryptedBytes);
console.log(decryptedText);
// "ABlockIs16Bytes!"
```

### 使用示例
在浏览器网页中使用aes-js
```html
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8" />
    <title>AES-JS Demo</title>
    <script src="./jquery-3.6.0.min.js"></script>
    <script src="./aes-3.1.2.js"></script>
    <script type="text/javascript">
    // 一个128位的密钥
    var key = [ 111, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 ];
    // 初始化向量
    var iv = [ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,35, 36 ];
    var aesEcb;
    var aesCbc;
    function encrypt(mode){
      var text = $('#input').val();
      var textBytes = aesjs.utils.utf8.toBytes(text);
      try {
        var aes = getAes(mode);
        var encryptedBytes = aes.encrypt(textBytes);
        var encryptedHex = aesjs.utils.hex.fromBytes(encryptedBytes);
        $('#result').val(encryptedHex);
      } catch(err) {
        alert(err);
      }
    }

    function decrypt(mode){
      // .text()方法只能够获取到textarea的初始化文本值
      var encryptedHex = $('#result').val();
      var encryptedBytes = aesjs.utils.hex.toBytes(encryptedHex);
      try {
        var aes = getAes(mode);
        var decryptedBytes = aes.decrypt(encryptedBytes);
        var decryptedText = aesjs.utils.utf8.fromBytes(decryptedBytes);
        $('#input').val(decryptedText);
      } catch(err) {
        alert(err);
      }
    }

    function getAes(mode){
      switch (mode){
        case 'cbc':
          // 密码块链接操作模式保持内部状态，因此必须实例化新实例来解密。
          aesCbc = new aesjs.ModeOfOperation.cbc(key, iv);
          return aesCbc;
        case 'ebc':
          if(!aesEcb){
            aesEcb = new aesjs.ModeOfOperation.ecb(key);
          }
          return aesEcb;
        default:
            throw new Error('未定义的 AES MODE:'+mode);
            break;
      }
    }
    </script>
  </head>
  <body>
    <h1>AES-JS Demo</h1>

    
    <form>
      <p>
        <label for="input">输入</label>
      </br>
        <textarea rows="6" cols="50" id="input">TextMustBe16Byte</textarea>
      </p>
      <p>
        <button
          type="button"
          onclick="encrypt('cbc')"
        >
          AES(CBC)加密
        </button>
        <button
          type="button"
          onclick="decrypt('cbc')"
        >
          AES(CBC)解密
        </button>
      </p>
      <p>
        <button
          type="button"
          onclick="encrypt('ebc')"
        >
          AES(EBC)加密
        </button>
        <button
          type="button"
          onclick="decrypt('ebc')"
        >
          AES(EBC)解密
        </button>
      </p>
      <p>
        <label for="result">结果</label>
      </br>
        <textarea rows="6" cols="50" id="result"></textarea>
      </p>
    </form>

</html>
```



### 参考
 - [aes-js --github](https://github.com/ricmoo/aes-js)
 - [aes-js --npmjs](https://www.npmjs.com/package/aes-js)
