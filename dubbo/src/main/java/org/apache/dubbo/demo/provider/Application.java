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
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.demo.DemoService;

import java.util.HashMap;
import java.util.Map;

public class Application {

    private static String REGISTRY_URL = "N/A";

    public static void main(String[] args) throws Exception {
        if (args != null && args.length > 0) {
            REGISTRY_URL = args[0];
        }
        System.setProperty("dubbo.application.logger", "logback");
        System.setProperty("native", "true");
        System.setProperty("org.graalvm.nativeimage.imagecode", "true");
        System.setProperty("dubbo.json-framework.prefer", "fastjson2");
//        String buildDirectory = "/Users/foghost/Documents/IdeaProjects/dubbo-native/target";
//        AotProcessor.main(new String[]{"", buildDirectory + "/dubbo-aot/main/sources",
//                buildDirectory + "/dubbo-aot/main/resources",
//                buildDirectory + "/dubbo-aot/main/classes",
//                "org.example", "dubbo-native"});
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();

        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-api-provider");
        applicationConfig.setQosEnable(false);
        applicationConfig.setCompiler("jdk");
        Map<String, String> m = new HashMap<>(1);
        m.put("proxy", "jdk");
        applicationConfig.setParameters(m);

        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());

        ProtocolConfig protocolConfig = new ProtocolConfig(CommonConstants.DUBBO, 20880);
        protocolConfig.setSerialization("fastjson2");
        bootstrap.application(applicationConfig)
                .registry(new RegistryConfig(REGISTRY_URL))
                .protocol(protocolConfig)
                .service(service)
                .start()
                .await();

        System.out.println("dubbo service started");
        System.in.read();
    }
}
