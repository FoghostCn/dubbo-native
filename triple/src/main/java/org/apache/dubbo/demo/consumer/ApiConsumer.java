/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.demo.GreeterService;
import org.apache.dubbo.demo.hello.HelloReply;
import org.apache.dubbo.demo.hello.HelloRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ApiConsumer {

    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) throws IOException {

        System.setProperty("dubbo.application.logger", "logback");
        System.setProperty("native", "true");
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();

        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-api-consumer");
        applicationConfig.setQosEnable(false);
        applicationConfig.setCompiler("jdk");
        Map<String, String> m = new HashMap<>(1);
        m.put("proxy", "jdk");
        applicationConfig.setParameters(m);

        ReferenceConfig<GreeterService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(GreeterService.class);
        referenceConfig.setCheck(false);
        referenceConfig.setProtocol(CommonConstants.TRIPLE);
        referenceConfig.setLazy(true);
        referenceConfig.setTimeout(100000);
        referenceConfig.setRetries(1);
        referenceConfig.setParameters(new HashMap<>(){{put("serialization", "fastjson2");}});
        referenceConfig.setUrl("tri://127.0.0.1:50051");

        ProtocolConfig protocolConfig = new ProtocolConfig(CommonConstants.TRIPLE, -1);
        protocolConfig.setSerialization("fastjson2");
        bootstrap.application(applicationConfig)
            .registry(new RegistryConfig("N/A"))
            .protocol(protocolConfig)
            .reference(referenceConfig)
            .start();

        GreeterService greeterService = referenceConfig.get();
        System.out.println("dubbo referenceConfig started");
        try {
            final HelloReply reply = greeterService.sayHello(HelloRequest.newBuilder()
                .setName("triple")
                .build());
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Reply: " + reply.getMessage());

            CompletableFuture<String> sayHelloAsync = greeterService.sayHelloAsync("triple");
            System.out.println("Async Reply: "+sayHelloAsync.get());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
