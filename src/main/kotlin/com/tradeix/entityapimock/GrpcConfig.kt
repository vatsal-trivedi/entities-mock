package com.tradeix.entityapimock

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("grpc.client")
class GrpcConfig {

    var serverHost = "localhost"

    var serverPort = 8082
}