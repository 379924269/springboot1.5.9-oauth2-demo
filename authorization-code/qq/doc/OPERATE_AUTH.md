### 1、授权码模式:/oauth/authorize
`注：`授权码模式只能在web界面上进行
* [获取授权码连接](http://localhost:8082/oauth/authorize?client_id=aiqiyi&response_type=code&redirect_uri=http://localhost:8081/aiqiyi/qq/redirect)，参数如下：
  * client_id：客户端id
  * response_type：code 固定的
  * redirect_uri：重定向地址必须和注册的重定向地址相同，不然会报错误，`重定向到的地址带上了授权码参数的`
  
* [通过授权码获取token连接](http://localhost:8082/oauth/token?grant_type=authorization_code&code=vnumxN&client_id=aiqiyi&client_secret=secret&redirect_uri=http://localhost:8081/aiqiyi/qq/redirect)，参数如下：
  * grant_type：授权类型authorization_code，固定的
  * code：上一步获取的授权码
  * client_id： 客户端id
  * client_secret：客户端密码
  * redirect_uri：重定向地址必须和注册的重定向地址相同，不然会报错误，`重定向到的地址带上了授权码参数的`

### 2、简化模式
[简化模式参考地址](https://www.cnblogs.com/lexiaofei/p/6934447.html)
* [简化模式请求](http://localhost:8082/oauth/authorize?client_id=aiqiyi&response_type=token&redirect_uri=http://localhost:8081/aiqiyi/qq/redirect&state=xxx)，参数如下：
    * response_type：表示授权类型，此处的值固定为"token"，必选项。
    * client_id：表示客户端的ID，必选项。
    * redirect_uri：表示重定向的URI，可选项。
    * scope：表示权限范围，可选项。
    * state：表示客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值。

### 3、密码模式:/oauth/token
* [密码模式连接](http://localhost:8082/oauth/token?username=admin&password=123456&grant_type=password&scope=get_user_info&client_id=aiqiyi&client_secret=secret)，参数如下：


### 验证token：/oauth/check_token
[验证token连接](http://localhost:8082/oauth/check_token?token=0901662c-b41e-4ffa-a7db-3f720daa7cb2)，参数如下：
返回信息 
````
{
    "aud":["qq"],
    "user_name":"250577914",
    "scope":["get_user_info"],
    "active":true,
    "exp":1588165077,
    "authorities":["USER"],
    "client_id":"aiqiyi"
}
````

### 刷新token
[测试刷新token连接](http://localhost:8082/oauth/token?grant_type=refresh_token&refresh_token=6c6c4d32-a274-4378-a0cd-b43720f152dd&client_id=aiqiyi&client_secret=secret)


### 简单的通过token调用接口
[调用接口：qq/info/250577914](http://localhost:8082/qq/info/250577914?access_token=9f54d26f-5545-4eba-a124-54e6355dbe69)

### 4、客户端模式
* [客户端模式请求](http://localhost:8082/oauth/token?grant_type=client_credentials&client_id=aiqiyi&client_secret=secret&redirect_uri=http://localhost:8081/aiqiyi/qq/redirect)，参数如下：
    * grant_type：表示授权类型，必选项，此处的值固定为"client_credentials"
    * client_id：表示客户端的ID，必选项
    * client_secret：客户端的密码，可选项
    * scope：表示申请的权限范围，可选项
