### 项目介绍

该项目是一个组建话的小栗子，关于组件化这边就不详谈了，网上有很多介绍
这里给大家推荐一位大佬的两篇文章学习一下
<https://blog.csdn.net/guiying712/article/details/55213884/>
<https://blog.csdn.net/guiying712/article/details/78057120/>

### 项目中代码

项目中主要分为这几个模块
1.app:组件化最终合并起来的编译模块
2.common:是常用的一些Android开发的工具类，可以查看这篇github的介绍<https://github.com/llayjun/common/>
3.fist,second,third,fourth四个模块则为普通app项目下面的四个切换模块，使用模块化开发可以单独编译运行
4.main是将四个模块拼接起来的主模块
5.pay微信支付和支付宝支付模块
6.push阿里云推送模块
7.scan二维码扫描功能模块
8.share第三方分享模块，share sdk
9.same是common的上一层，当项目中有一些通用的类和功能，可以写在这个里面，还有一些Arouter的跳转，要和common中的区别开来

### 其他

项目中使用的框架 Rxjava2 + Retrofit2 + mvp 的框架，使开发更加快捷
