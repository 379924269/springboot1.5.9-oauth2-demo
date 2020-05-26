## OAUTH2 遇到的问题
* [可以参考我的mydebug项目的OAuh2学习](https://github.com/379924269/my-debug/blob/master/commom/OAUTH2.md)

### checkToken访问不了 
* [处理参考地址](https://www.cnblogs.com/dalianpai/p/12423897.html)

### @order详解 

@order值越小，优先级越高，[参考地址](https://blog.csdn.net/yaomingyang/article/details/86649072)

* @Order(100) WebSecurityConfigurerAdapter
* SecurityProperties.ACCESS_OVERRIDE_ORDER - 1 ResourceServerProperties
  说明WebSecurityConfigurerAdapter`优先级大于`ResourceServerProperties
  
### 登录username和password字段在数据库中变化字段问题。
就是通过在自定义的userdetail中修改查询和user中的填充字段，具体参考可以看我的
CustomUserDetailsServiceConfig中的配置

### 自定义的userDetail的理解：
就相当于自己设置用户名和密码。然后添加userdetail中，然后和提交上来的用户名和
密码对比，（包括添加权限等操作）

### 注意：
* 1、如果同一个用户获取了token 再次去获取授权码就不会弹授权界面 
* 2、一个授权码只能用一次