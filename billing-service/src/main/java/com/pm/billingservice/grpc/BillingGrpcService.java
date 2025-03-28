package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest request,
                                     io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBillingAccount request received: {}", request.toString());
        //Business logic - e.g to save database , perform some operation

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("123")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response); //to send multiple response we are putting this in different lines
        responseObserver.onCompleted();
    }

}
