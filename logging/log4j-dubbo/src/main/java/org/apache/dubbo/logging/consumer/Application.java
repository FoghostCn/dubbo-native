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
package org.apache.dubbo.logging.consumer;

public class Application {

    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {

//        System.setProperty("dubbo.application.logger", "logback");
//        System.setProperty("native", "true");
//        System.setProperty("dubbo.json-framework.prefer", "fastjson2");
//        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
//
//        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-api-consumer");
//        applicationConfig.setQosEnable(false);
//        applicationConfig.setCompiler("jdk");
//        Map<String, String> m = new HashMap<>(1);
//        m.put("proxy", "jdk");
//        applicationConfig.setParameters(m);
//
//        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
//        reference.setInterface(DemoService.class);
//        reference.setGeneric("false");
//        reference.setUrl("dubbo://127.0.0.1:20880");
//        reference.setParameters(new HashMap<>(){{put("serialization", "fastjson2");}});
//
//        ProtocolConfig protocolConfig = new ProtocolConfig(CommonConstants.DUBBO, -1);
//        protocolConfig.setSerialization("fastjson2");
//        bootstrap.application(applicationConfig)
////                .registry(new RegistryConfig(REGISTRY_URL))
//                .protocol(protocolConfig)
//                .reference(reference)
//                .start();
//
//        DemoService demoService = bootstrap.getCache().get(reference);
//        String message = demoService.sayHello("Native");
//        System.out.println(message);
    }
}
