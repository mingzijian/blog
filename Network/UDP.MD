UDP (User Datagram Protocol)
-----------------------------------------------
User Datagram Protocol
用户数据报协议

UDP是传输层的协议，功能即为在IP的数据报服务之上增加了最基本的服务：复用和分用以及差错检测。

### 报文格式
UDP传输的段（segment）由8个字节的报头和有效载荷字段构成。

 ![UDP](https://gitee.com/mingzijian/resources/raw/master/picgo/%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_161701031741.png)

#### UDP报头（首部）
UDP报头由4个域组成，其中每个域各占用2个字节，具体包括源端口号、目标端口号、数据包长度、校验和。
- 源端口 16位
	源端口号，需要对方回信时选用，不需要时全部置0
- 目的端口 16位
	目的端口号，在终点交付报文的时候需要用到
- 长度 16位
	UDP的数据报的长度（包括首部和数据）其最小值为8（只有首部）
- 校验和 16位
	检测UDP数据报在传输中是否有错，有错则丢弃。该字段是可选的，当源主机不想计算校验和，则直接令该字段全为0

#### 有效载荷（数据）
数据（若有）

#### UDP校验（伪首部）
在计算校验和的时候，需要在UDP数据报之前增加12字节的伪首部，伪首部并不是UDP真正的首部。只是在计算校验和，临时添加在UDP数据报的前面，得到一个临时的UDP数据报。
校验和就是按照这个临时的UDP数据报计算的。
伪首部既不向下传送也不向上递交，而仅仅是为了计算校验和。
这样的校验和，既检查了UDP数据报，又对IP数据报的源IP地址和目的IP地址进行了检验。

 ![udp-l](https://gitee.com/mingzijian/resources/raw/master/picgo/v2-69663f6ce218a12153a5a4fe5a92a78e_r.jpg)

 ![udp-t](https://gitee.com/mingzijian/resources/raw/master/picgo/v2-fa0fa846a1c792845356f590a25f5574_r.jpg)