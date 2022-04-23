package com.aolifu.proto;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Random;

public class GrpcServer {

    private Server server;

    private static class ProductInfoService extends ProductInfoGrpc.ProductInfoImplBase{

        @Override
        public void addProduct(ProductInfoOuterClass.Product request,
                               io.grpc.stub.StreamObserver<ProductInfoOuterClass.ProductId> responseObserver){
            System.out.println(request.getDescription());
            System.out.println(request.getName());
            final ProductInfoOuterClass.ProductId build =ProductInfoOuterClass.ProductId.newBuilder().setValue(new Random().nextInt() + "").build();
            responseObserver.onNext(build);
            responseObserver.onCompleted();
        }
    }

    public void start() throws IOException {
        int port = 9000;
        server = ServerBuilder.forPort(port).addService(new ProductInfoService()).build();
        System.out.println("Server started, listening on " + port);
        server.start();
    }

    public void blockWaitShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.blockWaitShutdown();
    }


}
