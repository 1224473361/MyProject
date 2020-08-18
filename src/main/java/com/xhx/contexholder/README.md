## 全局数据获取
###主体思路
仿org.springframework.security.core.context.SecurityContextHolder的逻辑，获取请求里面的参数

###预期效果
使用MyContextHolder.getContext().getDetail()获取request请求里面的参数

###思路分析
>1.使用过滤器拦截web请求，将获取的数据存放到MyContext---使用MyContextHolder.getContext().setDetail(new MyDetail(str))
>2.使用MyContextHolder.getContext().getDetail()方法获取数据

###核心逻辑
>1.MyContextStrategy采用策略模式将不同的存储算法进行分类
>2.GobalContextStrategy使用单例模式的思想实现全局共用数据的逻辑
>3.ThreadLocalContextStrategy使用ThreadLocal来进行线程间数据的隔离
>4.InheritableThreadLocalContextStrategy使用InheritableThreadLocal处理父子线程之间数据传递的问题








