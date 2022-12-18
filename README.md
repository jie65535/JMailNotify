# J Mail Notify
基于 Mirai-Console 的插件，在你的机器人上线、下线时向你发送邮件通知。

# 指令集

```shell
/jmn reload    # 重载配置
/jmn setDisabled <isEnabled>    # 设置关闭通知
/jmn setLogin <isEnabled>    # 设置登录通知
/jmn setLogout <isEnabled>    # 设置登出通知
/jmn setSendTo <email>    # 设置收件人邮箱地址
/jmn setSender <email> <smtpPassword>    # 设置SMTP发件人邮箱
/jmn setSmtpServer <host> <port>    # 设置SMTP服务器
/jmn test [title] [message]    # 发送测试邮件
```

# 示例
```shell
> jmn setSender me@qq.com Authorization-code
OK
> jmn setSendTo target@qq.com
OK
> jmn test
邮件已发送
```
![Test mail](docs/TestMail.png)