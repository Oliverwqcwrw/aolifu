package com.aolifu.proto;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private final ProductInfoGrpc.ProductInfoBlockingStub productInfoBlockingStub;

    public GrpcClient(Channel channel){
        productInfoBlockingStub = ProductInfoGrpc.newBlockingStub(channel);
    }

    public void addProduct(){
        ProductInfoOuterClass.Product product = ProductInfoOuterClass.Product.newBuilder().setId("111").setName("name1").setDescription("desc1").build();
        final ProductInfoOuterClass.ProductId productId = productInfoBlockingStub.addProduct(product);
        System.out.println("请求服务端返回结果：" + productId);
    }

    public static void main(String[] args) {
        String target = "localhost:9000";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        GrpcClient client = new GrpcClient(channel);
        client.addProduct();

    }


}
