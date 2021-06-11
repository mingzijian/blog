HashMap
---------------

### 数据结构

|  JDK版本    |   数据结构    |
| ---- | ---- |
|  1.8之前    |  数组+单向链表     |
|  1.8及之后  |  数组+单向链表+红黑树  （当单向链表的节点数>=8时，转为红黑树；当红黑树的节点数<=6时，转为单向链表）  |

![HashMap-JDK1.8](https://images.gitee.com/uploads/images/2020/0704/150850_20b8829d_536895.png "HashMap-JDK1.8.png")

#### 红黑树
红黑树(Red-Black Tree，简称R-B Tree)，是一种特殊的二叉查找树。

##### 特征：
1. 节点颜色非红即黑
2. 根节点为黑色
3. 父子节点之间不能出现两个连续的红色节点（红色节点的子节点为黑色）
4. 空叶子节点为黑色
5. 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等。

### 常见问题
#### 什么时候需要重写 `HashMap` 的 `hashcode()` 方法

```java
class Car implements Serializable{
  @Getter
  @Setter
  private String id;
  @Getter
  @Setter
  private String name;
  
  public Car( String id,String name ){
    this.id=id;
    this.name=name;
  }
  
  @Override
  public int hashCode() {
    return id.hashCode() + name.hashCode();
  }
  
  @Override
  public boolean equals(Object obj) { 
    if(obj==null)
      return false;
      
    if(obj==this)
      return true;
    
    if (!(obj instanceof Car))  
      return false; 
    
    // 安全地向下转型
    Car car = (Car) obj;
    
    if(car.id == null && this.id == null && car.name == null && this.name == null)
      return true;
    
    if((car.id != null && this.id == null) || (car.id == null && this.id != null) || (car.name == null && this.name != null) || (car.name == null && this.name != null))
      return false;
    
    if(car.id.hashcode()!=this.id.hashcode() || car.name.hashcode()!=this.name.hashcode())
      return false;
      
    if(!car.id.equals(this.id) || !car.name.equals(this.name))
      return false;
    
    return true;  
  }  
  
}

// 逻辑上认为 c1 和 c2 是同一个 Car
Car c1=new Car("c-001","xxx");
Car c2=new Car("c-001","xxx");

// HashMap 判断两个key是否一致，不光要判断hashcode还要判断equals
// if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
Map<Car,Object> map = new HashMap<>(4);
map.put(c1,1);
map.put(c2,2);

// HashSet 内部就是用的 HashMap
Set<Car> set = new HashSet<>();
// static final Object PRESENT = new Object(); 
set.add(c1);//map.put(c1,PRESENT);
set.add(c2);//map.put(c2,PRESENT);

```