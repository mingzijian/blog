

## 查询技巧

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