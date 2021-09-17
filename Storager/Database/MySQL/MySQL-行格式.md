



MySQL 行格式

```
`InnoDB` 存储引擎支持四种行格式: `冗余`， `紧凑`， `动态`， `压缩`
```



| 行格式         | 紧凑的存储特性 | 增强的可变长度列存储 | 大索引键前缀的支持 | 压缩支持 | 支持表空间类型（V5.6） | 支持表空间类型（V8.0） | 文件格式要求 |
| :------------- | :------------- | :------------------- | :----------------- | :------- | :------------------ | :----------- | -------------- |
| REDUNDANT<br>`冗余`         | ×       | ×                | ×              | ×    | system,file-per-table | system, file-per-table, general | Antelope or Barracuda |
| COMPACT <br>`紧凑` | √          | ×                | ×              | ×    | system,file-per-table | system, file-per-table, general | Antelope or Barracuda |
| DYNAMIC<br>`动态`         | √          | √                | √              | ×    | file-per-table      | file-per-table, general | Barracuda |
| COMPRESSED<br>`压缩`         | √          | √                | √              | √    | file-per-table      | file-per-table, general | Barracuda |



```sql
-- 查看表状态
SHOW TABLE STATUS where name ='tb_name';
```



参考

[innodb-row-format](https://dev.mysql.com/doc/refman/8.0/en/innodb-row-format.html#innodb-row-format-compact)