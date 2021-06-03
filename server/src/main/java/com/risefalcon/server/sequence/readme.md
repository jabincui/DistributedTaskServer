# 任务分配
## 1 具体任务分配
### 1.1 密码破解
dictList \
dicLen \
pwdLen


#### 任务分配
任务约定

| 编号 | pwdLen | 密码量 | 备注 |
| --- | --- | --- | --- |
| 0 | <= 5 | 5^dicLen | 1-5位的全部组合 |
| 1 | 1+5 | dicLen^5 | 以dictList[0]开头的6位组合 |
| dicLen | 1+5 | dicLen^5 | 以dictList[dicLen-1]开头的6位组合 |
| dicLen+1 | 2+5 | dicLen^5 | 以[dictList[0]]*2开头的7位组合 |

