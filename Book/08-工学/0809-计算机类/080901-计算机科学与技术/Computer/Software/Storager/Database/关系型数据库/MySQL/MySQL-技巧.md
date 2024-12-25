

## 查询技巧

### 表容量统计

```sql
select table_schema                            as '数据库',
       table_name                              as '表名',
       TABLE_COMMENT                           as '表注释',
       table_rows                              as '记录数',
       truncate(data_length / 1024 / 1024, 2)  as '数据容量(MB)',
       truncate(index_length / 1024 / 1024, 2) as '索引容量(MB)'
from information_schema.tables
where table_schema = 'db_xxx'
order by table_rows desc, index_length desc;
```





### 行转列

```sql
-- insert into mytable (id,content) values (1,',0,1,2,3,,')
select id,content from mytable where id = 1;

-- 行转列
select a.id,substring_index(substring_index(a.content,',',b.help_topic_id+1),',',-1) as v
from mytable a left join mysql.help_topic b
 on b.help_topic_id < (length(a.content)-length(replace(a.content,',',''))+1)
where id = 1 and length(substring_index(substring_index(a.content,',',b.help_topic_id+1),',',-1)) >0;

```

 ![image-20210916103919311](https://gitee.com/mingzijian/resources/raw/master/picgo/2021-09/image-20210916103919311.png)

 ![image-20210916103934971](https://gitee.com/mingzijian/resources/raw/master/picgo/2021-09/image-20210916103934971.png)



### 列转行



```sql
select sum(case audit_status when '00' then total end) as '00',
       sum(case audit_status when '01' then total end) as '01',
       sum(case audit_status when '02' then total end) as '02',
       sum(case audit_status when '06' then total end) as '06'
from (
         select '00' as audit_status, count(1) as total
         from supplier_enroll_correction
         where audit_status = '00'
         union all
         select '01', count(1)
         from supplier_enroll_correction
         where audit_status = '01'
         union all
         select '02', count(1)
         from supplier_enroll_correction
         where audit_status = '02'
         union all
         select '06', count(1)
         from supplier_enroll_correction
         where audit_status = '06'
     ) t
;
```
