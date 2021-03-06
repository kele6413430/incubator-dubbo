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
package org.apache.dubbo.config.spring.extension;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.spring.api.DemoService;
import org.apache.dubbo.config.spring.impl.DemoServiceImpl;

import org.apache.dubbo.rpc.GraceFulShutDown;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanForContext2 {
    @Bean("bean1")
    public DemoService context2Bean() {
        return new DemoServiceImpl();
    }

    @Bean("graceFulShutdown")
    public GraceFulShutDown graceFulShutDown(){
        return new GraceFulShutDown() {
            private final Logger logger = LoggerFactory.getLogger(SpringExtensionFactory.class);

            private boolean flag = false;
            private boolean flag1 = false;
            @Override
            public void afterRegistriesDestroyed() {
                flag = true;
                if (logger.isInfoEnabled()){
                    logger.info("i do afterRegistriesDestroyed");
                }
            }

            @Override
            public void afterProtocolDestroyed() {
                flag1 = true;
                if (logger.isInfoEnabled()){
                    logger.info("i do afterProtocolDestroyed");
                }
            }
        };
    }
}
