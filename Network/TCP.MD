TCP (传输控制协议)
---------------------------
传输控制协议（TCP，Transmission Control Protocol）是一种面向连接的、可靠的、基于字节流的传输层通信协议。


### 名称解释
#### SYN
同步序列编号（Synchronize Sequence Numbers）。是TCP/IP建立连接时使用的握手信号。
#### ACK
确认 (Acknowledge），表示发来的数据已确认接收无误。
#### PSH
传送(push)
#### FIN
结束（finish）。是TCP/IP断开连接时使用的挥手信号。
#### RST
复位(reset)，用于复位相应的TCP连接。
#### URG
紧急(urgent) ，紧急标志为"1"表明该位有效。
#### ESTABLISHED 
表示TCP连接已经成功建立。

### 三次握手（建立连接）
建立连接协议
- 客户端发送syn包（seq=j）到服务器，并进入SYN_SENT状态，等待服务器确认
- 服务器收到syn包，必须确认客户的SYN（ack=j+1），同时自己也发送一个SYN包（seq=k），即SYN+ACK包，此时服务器进入SYN_RECV状态。
- 客户端收到服务器的SYN+ACK包，向服务器发送确认包ACK(ack=k+1），此包发送完毕，客户端和服务器进入ESTABLISHED（TCP连接成功）状态，完成三次握手。
### 四次挥手 （断开连接）
断开连接协议
-  TCP客户端发送一个FIN，用来关闭客户到服务器的数据传送。
-  服务器收到这个FIN，它发回一个ACK，确认序号为收到的序号加1。和SYN一样，一个FIN将占用一个序号。
-  服务器关闭客户端的连接，发送一个FIN给客户端。
-  客户端发回ACK报文确认，并将确认序号设置为收到序号加1。
