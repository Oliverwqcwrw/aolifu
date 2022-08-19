package com.aolifu.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: ProductInfo.proto")
public final class ProductInfoGrpc {

  private ProductInfoGrpc() {}

  public static final String SERVICE_NAME = "ProductInfo";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aolifu.proto.Product,
      com.aolifu.proto.ProductId> METHOD_ADD_PRODUCT =
      io.grpc.MethodDescriptor.<com.aolifu.proto.Product, com.aolifu.proto.ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "addProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.Product.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aolifu.proto.ProductId,
      com.aolifu.proto.Product> METHOD_GET_PRODUCT =
      io.grpc.MethodDescriptor.<com.aolifu.proto.ProductId, com.aolifu.proto.Product>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "getProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.Product.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aolifu.proto.ProductId,
      com.aolifu.proto.Product> METHOD_SEARCH_PRODUCT =
      io.grpc.MethodDescriptor.<com.aolifu.proto.ProductId, com.aolifu.proto.Product>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "searchProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.Product.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aolifu.proto.Product,
      com.aolifu.proto.ProductId> METHOD_UPDATE_PRODUCT =
      io.grpc.MethodDescriptor.<com.aolifu.proto.Product, com.aolifu.proto.ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "updateProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.Product.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aolifu.proto.Product,
      com.aolifu.proto.ProductId> METHOD_PROCESS_PRODUCT =
      io.grpc.MethodDescriptor.<com.aolifu.proto.Product, com.aolifu.proto.ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "processProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.Product.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aolifu.proto.ProductId,
      com.aolifu.proto.ProductId> METHOD_REMOVE_PRODUCT =
      io.grpc.MethodDescriptor.<com.aolifu.proto.ProductId, com.aolifu.proto.ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "removeProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aolifu.proto.ProductId.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductInfoStub newStub(io.grpc.Channel channel) {
    return new ProductInfoStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductInfoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProductInfoBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductInfoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProductInfoFutureStub(channel);
  }

  /**
   */
  public static abstract class ProductInfoImplBase implements io.grpc.BindableService {

    /**
     */
    public void addProduct(com.aolifu.proto.Product request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_PRODUCT, responseObserver);
    }

    /**
     */
    public void getProduct(com.aolifu.proto.ProductId request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.Product> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PRODUCT, responseObserver);
    }

    /**
     */
    public void searchProduct(com.aolifu.proto.ProductId request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.Product> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEARCH_PRODUCT, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.aolifu.proto.Product> updateProduct(
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_UPDATE_PRODUCT, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.aolifu.proto.Product> processProduct(
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_PROCESS_PRODUCT, responseObserver);
    }

    /**
     */
    public void removeProduct(com.aolifu.proto.ProductId request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REMOVE_PRODUCT, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                com.aolifu.proto.Product,
                com.aolifu.proto.ProductId>(
                  this, METHODID_ADD_PRODUCT)))
          .addMethod(
            METHOD_GET_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                com.aolifu.proto.ProductId,
                com.aolifu.proto.Product>(
                  this, METHODID_GET_PRODUCT)))
          .addMethod(
            METHOD_SEARCH_PRODUCT,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.aolifu.proto.ProductId,
                com.aolifu.proto.Product>(
                  this, METHODID_SEARCH_PRODUCT)))
          .addMethod(
            METHOD_UPDATE_PRODUCT,
            asyncClientStreamingCall(
              new MethodHandlers<
                com.aolifu.proto.Product,
                com.aolifu.proto.ProductId>(
                  this, METHODID_UPDATE_PRODUCT)))
          .addMethod(
            METHOD_PROCESS_PRODUCT,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.aolifu.proto.Product,
                com.aolifu.proto.ProductId>(
                  this, METHODID_PROCESS_PRODUCT)))
          .addMethod(
            METHOD_REMOVE_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                com.aolifu.proto.ProductId,
                com.aolifu.proto.ProductId>(
                  this, METHODID_REMOVE_PRODUCT)))
          .build();
    }
  }

  /**
   */
  public static final class ProductInfoStub extends io.grpc.stub.AbstractStub<ProductInfoStub> {
    private ProductInfoStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProductInfoStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductInfoStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProductInfoStub(channel, callOptions);
    }

    /**
     */
    public void addProduct(com.aolifu.proto.Product request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProduct(com.aolifu.proto.ProductId request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.Product> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchProduct(com.aolifu.proto.ProductId request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.Product> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_SEARCH_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.aolifu.proto.Product> updateProduct(
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_UPDATE_PRODUCT, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.aolifu.proto.Product> processProduct(
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_PROCESS_PRODUCT, getCallOptions()), responseObserver);
    }

    /**
     */
    public void removeProduct(com.aolifu.proto.ProductId request,
        io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REMOVE_PRODUCT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProductInfoBlockingStub extends io.grpc.stub.AbstractStub<ProductInfoBlockingStub> {
    private ProductInfoBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProductInfoBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductInfoBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProductInfoBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.aolifu.proto.ProductId addProduct(com.aolifu.proto.Product request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public com.aolifu.proto.Product getProduct(com.aolifu.proto.ProductId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.aolifu.proto.Product> searchProduct(
        com.aolifu.proto.ProductId request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_SEARCH_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public com.aolifu.proto.ProductId removeProduct(com.aolifu.proto.ProductId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REMOVE_PRODUCT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProductInfoFutureStub extends io.grpc.stub.AbstractStub<ProductInfoFutureStub> {
    private ProductInfoFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProductInfoFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductInfoFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProductInfoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aolifu.proto.ProductId> addProduct(
        com.aolifu.proto.Product request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aolifu.proto.Product> getProduct(
        com.aolifu.proto.ProductId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aolifu.proto.ProductId> removeProduct(
        com.aolifu.proto.ProductId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REMOVE_PRODUCT, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_PRODUCT = 0;
  private static final int METHODID_GET_PRODUCT = 1;
  private static final int METHODID_SEARCH_PRODUCT = 2;
  private static final int METHODID_REMOVE_PRODUCT = 3;
  private static final int METHODID_UPDATE_PRODUCT = 4;
  private static final int METHODID_PROCESS_PRODUCT = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProductInfoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProductInfoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_PRODUCT:
          serviceImpl.addProduct((com.aolifu.proto.Product) request,
              (io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId>) responseObserver);
          break;
        case METHODID_GET_PRODUCT:
          serviceImpl.getProduct((com.aolifu.proto.ProductId) request,
              (io.grpc.stub.StreamObserver<com.aolifu.proto.Product>) responseObserver);
          break;
        case METHODID_SEARCH_PRODUCT:
          serviceImpl.searchProduct((com.aolifu.proto.ProductId) request,
              (io.grpc.stub.StreamObserver<com.aolifu.proto.Product>) responseObserver);
          break;
        case METHODID_REMOVE_PRODUCT:
          serviceImpl.removeProduct((com.aolifu.proto.ProductId) request,
              (io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_PRODUCT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.updateProduct(
              (io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId>) responseObserver);
        case METHODID_PROCESS_PRODUCT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.processProduct(
              (io.grpc.stub.StreamObserver<com.aolifu.proto.ProductId>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ProductInfoDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aolifu.proto.ProductInfoOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProductInfoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductInfoDescriptorSupplier())
              .addMethod(METHOD_ADD_PRODUCT)
              .addMethod(METHOD_GET_PRODUCT)
              .addMethod(METHOD_SEARCH_PRODUCT)
              .addMethod(METHOD_UPDATE_PRODUCT)
              .addMethod(METHOD_PROCESS_PRODUCT)
              .addMethod(METHOD_REMOVE_PRODUCT)
              .build();
        }
      }
    }
    return result;
  }
}
