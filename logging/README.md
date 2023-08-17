### log4j 1.2.17
**可以支持**
需要添加 reflect-config 及 resource-config，demo 见 [https://github.com/FoghostCn/dubbo-native/tree/master/logging/log4j-dubbo](https://github.com/FoghostCn/dubbo-native/tree/master/logging/log4j-dubbo)

### jcl 1.2
**可以支持**
native-maven-plugin 0.9.24 版本插件已增加 commons-logging 1.2 的支持，只需要使用最新版本的插件即可

### logback
**可以支持**
1. 在使用 springboot 3.x 的环境下可以完美支持，springboot 对 logback 做了单独支持
2. 单独使用 logback 只支持 console appender，其他 appender 未支持，原因为 native-maven-plugin 对 logback 的支持不完善

### log4j2
- **2.17.0 可以支持**，需要配置 reflect-config，和 springboot 3.x 不兼容，(demo 见 https://github.com/FoghostCn/dubbo-native/tree/master/logging/log4j2)
- **2.18.0 及以上不不支持** native，springboot 文档明确表示 log4j2 不支持 nativeimage

参考资料：

https://issues.apache.org/jira/browse/LOG4J2-2649

https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-with-GraalVM#logging

log4j2 2.20.0 报错：
```
 ERROR StatusLogger Unable to load services for service class org.apache.logging.log4j.spi.Provider
 java.lang.InternalError: com.oracle.svm.core.jdk.UnsupportedFeatureError: Defining hidden classes at runtime is not supported.
        at java.base@17.0.6/java.lang.invoke.InnerClassLambdaMetafactory.generateInnerClass(InnerClassLambdaMetafactory.java:413)
        at java.base@17.0.6/java.lang.invoke.InnerClassLambdaMetafactory.spinInnerClass(InnerClassLambdaMetafactory.java:315)
        at java.base@17.0.6/java.lang.invoke.InnerClassLambdaMetafactory.buildCallSite(InnerClassLambdaMetafactory.java:228)
        at java.base@17.0.6/java.lang.invoke.LambdaMetafactory.metafactory(LambdaMetafactory.java:341)
        at org.apache.logging.log4j.util.ServiceLoaderUtil.callServiceLoader(ServiceLoaderUtil.java:110)
        at org.apache.logging.log4j.util.ServiceLoaderUtil$ServiceLoaderSpliterator.<init>(ServiceLoaderUtil.java:146)
        at org.apache.logging.log4j.util.ServiceLoaderUtil.loadClassloaderServices(ServiceLoaderUtil.java:101)
        at org.apache.logging.log4j.util.ServiceLoaderUtil.loadServices(ServiceLoaderUtil.java:83)
        at org.apache.logging.log4j.util.ServiceLoaderUtil.loadServices(ServiceLoaderUtil.java:77)
        at org.apache.logging.log4j.util.ProviderUtil.<init>(ProviderUtil.java:67)
        at org.apache.logging.log4j.util.ProviderUtil.lazyInit(ProviderUtil.java:145)
        at org.apache.logging.log4j.util.ProviderUtil.hasProviders(ProviderUtil.java:129)
        at org.apache.logging.log4j.LogManager.<clinit>(LogManager.java:90)
        at org.apache.dubbo.logging.provider.Application.<clinit>(Application.java:37)
Caused by: com.oracle.svm.core.jdk.UnsupportedFeatureError: Defining hidden classes at runtime is not supported.
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:89)
        at java.base@17.0.6/java.lang.ClassLoader.defineClass0(ClassLoader.java:338)
        at java.base@17.0.6/java.lang.System$2.defineClass(System.java:2307)
        at java.base@17.0.6/java.lang.invoke.MethodHandles$Lookup$ClassDefiner.defineClass(MethodHandles.java:2439)
        at java.base@17.0.6/java.lang.invoke.MethodHandles$Lookup$ClassDefiner.defineClassAsLookup(MethodHandles.java:2420)
        at java.base@17.0.6/java.lang.invoke.MethodHandles$Lookup.defineHiddenClass(MethodHandles.java:2127)
        at java.base@17.0.6/java.lang.invoke.InnerClassLambdaMetafactory.generateInnerClass(InnerClassLambdaMetafactory.java:407)
        ... 13 more
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
```
