FileSystem 文件系统
-------------------------------
操作系统中负责管理和存储文件信息的软件机构称为文件管理系统，简称文件系统。

严格地说，文件系统是一套实现了数据的存储、分级组织、访问和获取等操作的抽象数据类型（Abstract data type）。

## 概述
文件系统是一种用于向用户提供底层数据访问的机制。
它将设备中的空间划分为特定大小的块（或者称为簇），一般每块512字节。
数据存储在这些块中，大小被修正为占用整数个块。
由文件系统软件来负责将这些块组织为文件和目录，并记录哪些块被分配给了哪个文件，以及哪些块没有被使用。

不过，文件系统并不一定只在特定存储设备上出现。
它是数据的组织者和提供者，至于它的底层，可以是磁盘，也可以是其它动态生成数据的设备（比如网络设备）。

## 类型
### 磁盘文件系统
磁盘文件系统是一种设计用来利用数据存储设备来保存计算机文件的文件系统，最常用的数据存储设备是磁盘驱动器，可以直接或者间接地连接到计算机上。

例如：FAT、exFAT、NTFS、HFS、HFS+、ext2、ext3、ext4、ODS-5、btrfs、XFS、UFS、ZFS。

#### 文件限制

##### 文件名长度限制

1. Linux文件名（含后缀）的长度限制是255个字节(Byte)。（一个UTF-8 中文字符占3个字节）
2. windows下完全限定文件名必须少于260个字符，目录名必须小于248个字符。 
> [Windows 文件系统-最大路径长度限制](https://learn.microsoft.com/zh-cn/windows/win32/fileio/maximum-file-path-limitation?tabs=registry)
>
> [文件规范语法](https://www.ibm.com/docs/zh/tsm/7.1.4?topic=parameters-file-specification-syntax)

- ![AIX operating systems](https://www.ibm.com/docs/zh/SSGSG7_7.1.4/client/common/images/ngaix.gif)![HP-UX operating systems](https://www.ibm.com/docs/zh/SSGSG7_7.1.4/client/common/images/nghpux.gif)![Oracle Solaris operating systems](https://www.ibm.com/docs/zh/SSGSG7_7.1.4/client/common/images/ngsolaris.gif)![Mac OS X operating systems](https://www.ibm.com/docs/zh/SSGSG7_7.1.4/client/common/images/ngmacosx.gif)在 AIX、HP-UX、Solaris 和 Mac 上：文件名的最大字节数为 255。 文件名和路径名组合的最大长度是 1024 个字节。 字符的 Unicode 表示可能占据几个字节，所以文件名可包含的最大字符数会有所不同。

- ![Linux operating systems](https://www.ibm.com/docs/zh/SSGSG7_7.1.4/client/common/images/nglinux.gif)在 Linux 上：文件名的最大长度为 255 个字节。 文件名和路径名组合的最大长度为 4096 个字节。 此长度与操作系统支持的 PATH_MAX 匹配。 字符的 Unicode 说明可能占据几个字节，所以组成路径名和文件名最大字符数会有所不同。 实际限制是路径和文件组件中的字节数，可能对应于相同字符数。
针对归档或检索操作，您可指定的路径和文件名（组合)的最大长度仍然是 1024 个字节。

- ![Windows operating systems](https://www.ibm.com/docs/zh/SSGSG7_7.1.4/client/common/images/ngwin.gif)文件名和文件路径组合的最大字节数是 6255。 但是，文件名本身不能超过 255 个字符。 而且，路径中的目录名称（包含目录定界符)不能超过 255 个字符。 字符的 Unicode 表示可能占据几个字节，所以文件名可包含的最大字符数会有所不同。

  使用具有 VSS 的开放文件支持功能时，Tivoli Storage Manager 客户机会将快照卷名称添加至正在处理的对象的路径中。 生成的路径（快照卷名称加上对象路径）必须遵守以上限制。 快照卷名称可以为 1024 个字节。

##### 字节与字符

| 编码    | 单个英文字符占用字节数 | 单个中文字符占用字节数                         |
| ------- | ---------------------- | ---------------------------------------------- |
| ASCII   | 1                      | 2                                              |
| UTF-8   | 1                      | 3                                              |
| Unicode | 1                      | 2                                              |
| UTF-16  | 2                      | 2（Unicode 扩展区的一些汉字存储需要 4 个字节） |
| UTF-32  | 4                      | 4                                              |



### 闪存文件系统
在闪存上储存文件的文件系统。

### 数据库文件系统
基于数据库的文件系统。

不再（或者不仅仅）使用分层结构管理，文件按照他们的特征进行区分，如文件类型、专题、作者或者亚数据进行区分。于是文件检索就可以按照SQL风格甚至自然语言风格进行。

例如BFS和WinFS。

### 网络文件系统 （NFS，Network File System）
一种将远程主机上的分区（目录）经网络挂载到本地系统的一种机制。