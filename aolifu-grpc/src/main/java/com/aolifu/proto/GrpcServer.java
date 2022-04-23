package com.aolifu.proto;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

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

        @Override
        public void searchProduct(com.aolifu.proto.ProductInfoOuterClass.ProductId request,
                                  io.grpc.stub.StreamObserver<com.aolifu.proto.ProductInfoOuterClass.Product> responseObserver){

            for (int  i = 1;i < 10;i++) {
                final ProductInfoOuterClass.Product build = ProductInfoOuterClass.Product.newBuilder().setId(i + "").build();
                responseObserver.onNext(build);
            }
            responseObserver.onCompleted();
        }

        @Override
        public io.grpc.stub.StreamObserver<com.aolifu.proto.ProductInfoOuterClass.Product> updateProduct(
                io.grpc.stub.StreamObserver<com.aolifu.proto.ProductInfoOuterClass.ProductId> responseObserver){

            return new StreamObserver<ProductInfoOuterClass.Product>() {
                int i = 1;
                @Override
                public void onNext(ProductInfoOuterClass.Product product) {
                    System.out.println(product.getId());
                    i++;
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(ProductInfoOuterClass.ProductId.newBuilder().setValue(i + "").build());
                    responseObserver.onCompleted();
                }
            };
        }

        @Override
        public io.grpc.stub.StreamObserver<com.aolifu.proto.ProductInfoOuterClass.Product> processProduct(
                io.grpc.stub.StreamObserver<com.aolifu.proto.ProductInfoOuterClass.ProductId> responseObserver) {
            return new StreamObserver<ProductInfoOuterClass.Product>() {
                @Override
                public void onNext(ProductInfoOuterClass.Product product) {
                    System.out.println(product.getId());
                    responseObserver.onNext(ProductInfoOuterClass.ProductId.newBuilder().setValue(product.getId()).build());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
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
