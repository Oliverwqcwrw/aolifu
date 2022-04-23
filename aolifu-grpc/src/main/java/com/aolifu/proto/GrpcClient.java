package com.aolifu.proto;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

public class GrpcClient {

    private final ProductInfoGrpc.ProductInfoBlockingStub productInfoBlockingStub;

    private final ProductInfoGrpc.ProductInfoStub asyncProductInfoStub;

    public GrpcClient(Channel channel){
        productInfoBlockingStub = ProductInfoGrpc.newBlockingStub(channel);
        asyncProductInfoStub = ProductInfoGrpc.newStub(channel);
    }

    // 一元RPC
    public void addProduct(){
        ProductInfoOuterClass.Product product = ProductInfoOuterClass.Product.newBuilder().setId("111").setName("name1").setDescription("desc1").build();
        final ProductInfoOuterClass.ProductId productId = productInfoBlockingStub.addProduct(product);
        System.out.println("请求服务端返回结果：" + productId);
    }

    // 服务端流RPC
    public void searchProduct(){
        final ProductInfoOuterClass.ProductId productId = ProductInfoOuterClass.ProductId.newBuilder().setValue("1").build();
        final Iterator<ProductInfoOuterClass.Product> productIterator = productInfoBlockingStub.searchProduct(productId);
        while (productIterator.hasNext()) {
            System.out.println("productId: " + productIterator.next().getId());
        }
    }

    // 客户端流RPC
    public void updateProduct(){
        StreamObserver<ProductInfoOuterClass.ProductId> streamObserver = new StreamObserver<ProductInfoOuterClass.ProductId>() {
            @Override
            public void onNext(ProductInfoOuterClass.ProductId productId) {
                System.out.println(productId);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted is executing");
            }
        };
        final StreamObserver<ProductInfoOuterClass.Product> productStreamObserver = asyncProductInfoStub.updateProduct(streamObserver);
        productStreamObserver.onNext(ProductInfoOuterClass.Product.newBuilder().setId("1").build());
        productStreamObserver.onNext(ProductInfoOuterClass.Product.newBuilder().setId("2").build());
        productStreamObserver.onNext(ProductInfoOuterClass.Product.newBuilder().setId("3").build());
        productStreamObserver.onCompleted();
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 双向流RPC
    public void processProductInfo(){
        StreamObserver<ProductInfoOuterClass.ProductId> streamObserver = new StreamObserver<ProductInfoOuterClass.ProductId>() {
            @Override
            public void onNext(ProductInfoOuterClass.ProductId productId) {
                System.out.println(productId.getValue());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted is execting");
            }
        };
        final StreamObserver<ProductInfoOuterClass.Product> productStreamObserver = asyncProductInfoStub.processProduct(streamObserver);
        productStreamObserver.onNext(ProductInfoOuterClass.Product.newBuilder().setId("4").build());
        productStreamObserver.onNext(ProductInfoOuterClass.Product.newBuilder().setId("5").build());
        productStreamObserver.onNext(ProductInfoOuterClass.Product.newBuilder().setId("6").build());
        productStreamObserver.onCompleted();
    }

    public static void main(String[] args) {
        String target = "localhost:9000";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        GrpcClient client = new GrpcClient(channel);
        //client.addProduct();
        //client.searchProduct();
        //client.updateProduct();
        client.processProductInfo();
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
