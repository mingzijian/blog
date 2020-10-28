## Win10系统激活

### 激活步骤

1. 以管理员身份打开命令行窗口（快捷键：==Win + X + A==）

2. **卸载**原密钥

```shell
slmgr.vbs /upk
```

3. **安装**新密钥

```shell
slmgr /ipk W269N-WFGWX-YVC9B-4J6C9-T83GX
```

   <!--其中“W269N-WFGWX-YVC9B-4J6C9-T83GX”为密钥-->

- 企业版：XGVPP-NMH47-7TTHJ-W3FW7-8HV2C

- 教育版：YNMGQ-8RYV3-4PGQ3-C8XTP-7CFBY
- 专业版N：2B87N-8KFHP-DKV6R-Y2C8J-PKCKT
- 单语言版：BT79Q-G7N6G-PGBYW-4YWX6-6F4BT

4. **设置**密钥管理服务计算机名

```shell
slmgr /skms zh.us.to
```

5. **激活**Windows

```shell
slmgr /ato
```

6. **成功**地激活了产品

![Windows已激活](https://images.gitee.com/uploads/images/2020/0520/151548_3fff1171_536895.png "Windows已激活.png")