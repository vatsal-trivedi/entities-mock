package com.tradeix.entityapimock

import com.tradeix.entityapimock.GrpcConfig
import io.grpc.Server
import io.grpc.ServerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import mu.KLogging

@Service
class GrpcServer(@Autowired
                       private val config: GrpcConfig,
                 private val entitiesGrpcImpl: EntitiesGrpcImpl) {

    companion object: KLogging()

    private var server: Server? = null

    @PostConstruct
    fun start() {
        logger.info("Starting entities grpc server")

        server = ServerBuilder.forPort(config.serverPort)
                .addService(entitiesGrpcImpl)
                .build()
                .start()

        logger.info("Started")
    }

    @PreDestroy
    fun stop() {
        logger.info("Stopping entities grpc server")
    }
}