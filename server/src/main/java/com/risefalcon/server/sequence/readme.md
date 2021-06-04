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

| 编号 | pwdLen | 密码量 | 备注 |
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