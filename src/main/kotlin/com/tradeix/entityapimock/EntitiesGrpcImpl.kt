package com.tradeix.entityapimock

import com.tradeix.core.grpc.entities.EntitiesServiceGrpc
import com.tradeix.core.grpc.entities.SearchEntitiesRequest
import com.tradeix.core.grpc.entities.SearchEntitiesResponse
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

@Service
class EntitiesGrpcImpl : EntitiesServiceGrpc.EntitiesServiceImplBase() {

    companion object observer : StreamObserver<SearchEntitiesRequest>{

        override fun onNext(value: SearchEntitiesRequest?) {
            // add whatever logic is needed here

            SearchEntitiesResponse.newBuilder()
                    .setId("SomeId")  // change to the test value
                    .setX500("X500") // change to the test value
                    .setRegisteredName(value?.registeredName)
                    .build()
        }

        override fun onError(t: Throwable?) {
           if (t!=null) throw t
        }

        override fun onCompleted() {}

    }

    override fun search(responseObserver: StreamObserver<SearchEntitiesResponse>?): StreamObserver<SearchEntitiesRequest> =
            observer
}