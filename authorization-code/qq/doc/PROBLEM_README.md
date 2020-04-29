## OAUTH2 遇到的问题
* [可以参考我的mydebug项目的OAuh2学习](https://github.com/379924269/my-debug/blob/master/commom/OAUTH2.md)

### checkToken访问不了 
* [处理参考地址](https://www.cnblogs.com/dalianpai/p/12423897.html)

### @order详解 

@order值越小，优先级越高，[参考地址](https://blog.csdn.net/yaomingyang/article/details/86649072)

* @Order(100) WebSecurityConfigurerAdapter
* SecurityProperties.ACCESS_OVERRIDE_ORDER - 1 ResourceServerProperties
  说明WebSecurityConfigurerAdapter`优先级大于`ResourceServerProperties