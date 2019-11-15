# 1.安装itellij idea

# 2.了解什么是数据结构以及算法
数据结构是一种组织数据的方式，对不同的数据采用不同的组织方式可以有效的提高对数据存储以及查询的效率
算法的特点是有一些输入，一定有输出，且有出口（会终止）的
# 3.java中的数组
声明方便直接 int[] arr = new int[capacity];就可以新建一个容量为capacity的数组
缺陷是静态的，长度固定，要在一开始给定capacity值，且无法自动扩容
# 4.自己封装的数组
在数组类中加入两个成员变量，分别是int[] data; int size;
其中data作为储存数据的一个数组，size记录当前存储元素的个数，容量则可以用data.length快速得到，所以不用再添加一个capacity属性记录容量。
加入一个带参的构造函数，可以初始化data数组。

## 4.1新增操作
add方法，首先判断给的索引值是否在0-（size-1）的范围内，不在就是非法的输入，抛出异常，存在就将size-1到索引处的数据分别向后移动一格，然后在索引处加入所要添加的数据，最后将当前容量size加一，就完成了新增操作。
	

```
public void add(int index,int e){
    if(index<0 || index>size){
        throw new IllegalArgumentException("Add require index<0 || index>size");
    }

    if(size==data.length){
        throw new IllegalArgumentException("容量已经达到上限");
    }

    for(int i = size-1 ;i>=index ;i--){
        data[i+1]=data[i];
    }
    data[index] = e;
    size ++;

}
```

## 4.2查找操作	
比较容易，判断索引是否正确，然后根据索引直接返回data[index]就可以找出数据

```
int get(int index){
    if(index < 0 || index >= size){
        throw new IllegalArgumentException("get failed. index is illegal.");
    }
    return data[index];
}
```

## 4.3更新操作
也比较简单，在索引位置直接将元素替换就好了，当然要先判断索引的正确性

```
void set(int index , int e){
    if(index < 0 || index >= size){
        throw new IllegalArgumentException("set failed. index is illegal.");
    }
    data[index] = e;
}
```

## 4.4检查数组中是否有元素e
遍历一遍数组，如果匹配到就返回true 否则返回false

```
public boolean contains(E e){
    for(int i = 0 ; i<size ; i++){
        if(data[i].equals(e))
            return true;
    }
    return false;
}
```

## 4.5查找数组中的元素e并返回下标（下标不存在时返回-1）
同上，修改返回值就行

```
public int find(int e){
    for(int i = 0 ; i<size ; i++){
        if(data[i].equals(e))
            return i;
    }
    return -1;
}
```
## 4.6删除数组元素
首先判断索引的正确性，然后将index+1到size-1的元素按顺序依次向前挪一位，然后将当前大小size-1，此时虽然size所指向的数组索引不为空，但是访问不到，所以可以不做处理

```
public int remove(int index){
    if(index < 0 || index >= size){
        throw new IllegalArgumentException("remove failed. index is illegal.");
    }
    int ret=data[index];
    for(int i = index + 1; i<size ; i++){
        data[i-1]=data[i];
    }
    size--;
    return ret;
}
```

# 5.使用泛型
当前数组指定了int型，所以只能储存int型数据，这样每个类型的数据都要定义一遍数组，显然不合理，我们可以使用泛型：
首先在类后面加上尖括号和类型名称（只是一个符号）`public class Array<E>`然后将此类中对应data的int都改成E
例如`private E[] data;`
这样做之后，在调用这个类的时候只要声明一下使用的是哪种类型就可以储存哪种类型的数据，十分方便Array<Integer> arr = new Array<Integer>();在jdk8中可以不用写后面的Integer了。***Ps：泛型只支持类，不支持基本类型，基本类型要用包装类***

# 6.将数组升级成为动态数组（可自动扩容缩容）
**扩容**：在add操作时，当容量达到上限的时候，新建一个容量为当前数组容量两倍（具体可以自己指定，ArrayList中用的是1.5倍），然后将所有元素遍历拷贝过去。再将data指向新数组就完成了一次扩容

```
    public void add(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add require index<0 || index>size");
        }

        if(size==data.length){
            resize(2 * data.length);
        }

        for(int i = size-1 ;i>=index ;i--){
            data[i+1]=data[i];
        }
        data[index] = e;
        size ++;

    }
```

**缩容**：在remove操作时，当size小于一个值的时候，就新建一个capacity/2容量的数组，再将元素遍历过去，将data指向新数组，完成一次缩容。至于什么时候进行缩容，如果在size=capacity/2的时候就进行缩容，再添加元素就又要扩容，这种缩容是着急（Eager）的，所以我们要采用更为懒惰（Lazy）的缩容，当size=capacity/4的时候再进行缩容，但是只缩一半，这样就可以防止复杂度震荡。

```
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("remove failed. index is illegal.");
        }
        E ret=data[index];
        for(int i = index + 1; i<size ; i++){
            data[i-1]=data[i];
        }
        size--;
        data[size]=null;
        //Lazy缩容
        if(size == data.length/4 && data.length/2!=0){
            resize(data.length/2);
        }

        return ret;
    }
```

具体用到的方法为resize（）方法

```
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
```
