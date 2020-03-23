Win10系统激活
-------------
##
1.以管理员身份打开命令行窗口（Win + X + A）
2.卸载原密钥
``` shell
slmgr.vbs /upk
```
3.安装新密钥
``` shell
slmgr /ipk W269N-WFGWX-YVC9B-4J6C9-T83GX
```
4.设置密钥管理服务计算机名
``` shell
slmgr /skms zh.us.to
```
5.激活Windows
``` shell
slmgr /ato
```