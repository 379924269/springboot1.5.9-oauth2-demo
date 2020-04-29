### 授权码模式:/oauth/authorize
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

### 密码模式:/oauth/token
* [密码模式连接](http://localhost:8082/oauth/token?username=250577914&password=123456&grant_type=password&scope=get_user_info&client_id=aiqiyi&client_secret=secret)，参数如下：


### 验证token：/oauth/check_token
[验证token连接](http://localhost:8082/oauth/check_token?token=31bc71a6-060b-4e9d-901b-b927a5037e64)，参数如下：
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


[ClientDetailsServiceConfigurer数据库解释：]() 