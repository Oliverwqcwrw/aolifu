package com.aolifu.grpc.grpc;

import com.aolifu.proto.Product;
import com.aolifu.proto.ProductId;
import com.aolifu.proto.ProductInfoGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Random;

public class GrpcServer {

    private Server server;

    private static class ProductInfoService extends ProductInfoGrpc.ProductInfoImplBase {

        @Override
        public void addProduct(Product request,
                               io.grpc.stub.StreamObserver<ProductId> responseObserver){
            System.out.println(request.getDescription());
            System.out.println(request.getName());
            final ProductId build =ProductId.newBuilder().setValue(new Random().nextInt() + "").build();
            responseObserver.onNext(build);
            responseObserver.onCompleted();
        }

        @Override
        public void searchProduct(ProductId request,
                                  io.grpc.stub.StreamObserver<Product> responseObserver){

            for (int  i = 1;i < 10;i++) {
                final Product build = Product.newBuilder().setId(i + "").build();
                responseObserver.onNext(build);
            }
            responseObserver.onCompleted();
        }

        @Override
        public io.grpc.stub.StreamObserver<Product> updateProduct(
                io.grpc.stub.StreamObserver<ProductId> responseObserver){

            return new StreamObserver<Product>() {
                int i = 1;
                @Override
                public void onNext(Product product) {
                    System.out.println(product.getId());
                    i++;
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(ProductId.newBuilder().setValue(i + "").build());
                    responseObserver.onCompleted();
                }
            };
        }

        @Override
        public io.grpc.stub.StreamObserver<Product> processProduct(
                io.grpc.stub.StreamObserver<ProductId> responseObserver) {
            return new StreamObserver<Product>() {
                @Override
                public void onNext(Product product) {
                    System.out.println(product.getId());
                    responseObserver.onNext(ProductId.newBuilder().setValue(product.getId()).build());
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
