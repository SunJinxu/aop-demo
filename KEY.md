# 一 AOP的产生背景

* AOP是对OOP设计思想的补充，解决了不同继承体系之间重复代码的问题



# 二 AOP的原理、分类

### 1 AOP的原理
* 基于JDK或CGlib动态代理技术实现，

### 2 AOP的重要定义及分类

##### 2.1 AOP的重要定义
* target 目标对象：被增强的对象
* joinpoint 连接点：方法执行的某个特定位置，如某个方法调用的前后（目标对象中，需要被增强的位置标记）
* pointcut 切点：每个类都有多个连接点，可以理解为连接点的集合（需要增强的标记集合）
* advice 通知：织入到目标类连接点上的一段代码，即增强到了哪里，增强了什么内容（不同增强位置标记上，进行增强的方法）
* aspect 切面： 由切点和通知构成，包括横切逻辑定义和连接点定义（可以理解为一个做满增强标记和增强方法的模板）
* weaving 织入：将增强添加到目标类的具体连接过程（实现增强的过程）
* aop proxy 代理对象：增强后的对象
##### 2.2 AOP的级别
* Before advice : 前置通知，在目标方法调用前执行，无论是否成功
* After returning advice : 后置通知，在目标方法完成后执行，前提是方法未发生异常
* After throwing advice : 异常通知，在目标方法发生异常时执行
* After finally advice : 最终通知，在方法执行后执行，无论结果是否成功
* Around advice : 环绕通知，最强大的to通知类型，可以控制目标方法的执行（调用ProceedingJoinPoint().proceed()）,可以在目标执行全过程中执行



# 三 AOP的实现步骤
* 定义切面类Aspect: 即在声明的类增加@Component和@Aspect两个注解，springboot需要引入spring-boot--aop包
* 定义切点Pointcut: 定义切点，并定义切点在哪些地方切入，采用@PointCut注解完成（如@PointCut(public * com.xxx.*.*(..)).
规则是修饰符可以不写（不能用*）+ 返回类型 +哪些包下的类 + 哪些方法 + 方法参数 "*"表示不限，".."表示方法参数不限
* 定义Advice通知: 利用通知的5种注解类型，@Before，@After，@AfterReturning，@AfterThrowing，@Around来完成某些切点的增强动作。
如Before("myPointCut()""),其中myPointCut()是第二步中定义的切点。



