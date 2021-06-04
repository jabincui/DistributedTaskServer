# 任务分配
## 1 具体任务分配
### 1.1 密码破解
#### 名词解释


#### 变量约定
dictList \
dicLen \
pwdLen
#### 任务分配

#### 任务约定

| 十进制索引 | pwdLen | 密码量 | 备注 |
| --- | --- | --- | --- |
| 0 | <= suffixLen | suffixLen^dicLen | 1->suffixLen位的全部组合 |
| 1 | 1+suffixLen | dicLen^suffixLen | 以dictList[0]开头的1+suffixLen位组合 |
| dicLen | 1+suffixLen | dicLen^suffixLen | 以dictList[dicLen-1]开头的1+suffixLen位组合 |
| dicLen+1 | 2+suffixLen | dicLen^suffixLen | 以[dictList[0]]*2开头的2+suffixLen位组合 |

子任务数

#### 任务索引迭代器

#### 索引->明文转换器

#### 子任务进度

#### 小结：具体任务中抽象出框架
任务指的是可以划分成N个相互独立（不互相调用结果的）子任务

## 2 框架的描述
(i) 表示实例 (m) 表示成员变量 (x)表示不在程序中
### Task
#### 字母表 alphabet (m)
字母表是一个集合，由字母表的元素或元素组合可以唯一确定一个原子任务
#### 进制 radix (m)
字母表的长度
#### 碎片 fragment (m)
Fragment 类对象，一段连续的原子任务

### Iterable
#### <T implements Iterable> next()
#### boolean hasNext()

### AtomTask
#### 子任务序列 subtask sequence (x)
子任务序列是一个任务以集合形式的描述，有限，有序，完整。
#### 原子任务 atomTask (i)
保持任务之间独立的，不可再分的子任务序列元素，它以字母表的元素或元素组合表示
#### 索引 index (i)

### Fragment
### head: AtomTask
### tail: AtomTask
