# Java 学习之路

## 项目结构

```
<root>
  |-- config: 配置文件
  |-- images: 图片资源
  |-- src/main/java: 源码
      |-- baseapi: 基础的API使用
      |-- core: 小工具
      |-- dp: 设计模式
      |-- gui: GUI应用
      |-- jdbc: 操作数据库
      |-- security: 加密解密
      |-- thread: 多线程
  |-- src/test/java: 测试用例
```

## GUI应用(gui)

### 贪吃蛇(snake)

> [查看源码](src/main/java/gui/snake/Yard.java)

- [x] 游戏的参数提取到成员变量/类常量
- [x] 游戏界面加一定距离的边框
- [ ] 快速切换相反的方向会结束游戏
- [ ] 按 F2 重新开始游戏
- [ ] 随机位置出现
- [ ] 提取配置到文件
- [ ] 死亡的时候头在边界外面
- [ ] 吃果实的时候不是很顺畅

### 坦克大战(tankWar)

> [查看源码](src/main/java/gui/tankwar/TankClient.java)

- [x] 坦克死亡爆炸效果,优化爆炸的位置
- [x] 加入敌方坦克,并且坦克可以移动,可以发射炮弹
- [x] 坦克不能穿墙,不能互相撞击
- [x] 自己的坦克加入生命值,加入超级子弹,加入补血的道具
- [x] 加入爆炸/坦克/子弹图片
- [x] 加入配置文件
- [ ] 坦克自动随机生成
- [ ] 加入堡垒
- [ ] 多人游戏
- [ ] 网络版

### 聊天(chat)

> [服务端源码](src/main/java/gui/chat/ChatServer.java)
>
> [客户端源码](src/main/java/gui/chat/ChatClient.java)

- [x] 多线程支持
- [x] 常量提取
- [x] 服务器和客户端交互

## 设计模式(dp)

> ——感谢马士兵老师的设计模式系列视频

- [ ] 动态代理
  - [ ] 动态代理指定返回值类型
  - [ ] 动态代理反射方法传参
  - [ ] 头一次运行总会报 ClassNotFound
