package com.aolifu.rocketmq.proto;

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
  public static final io.grpc.MethodDescriptor<Product, ProductId> METHOD_ADD_PRODUCT =
      io.grpc.MethodDescriptor.<Product, ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "addProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Product.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ProductId, Product> METHOD_GET_PRODUCT =
      io.grpc.MethodDescriptor.<ProductId, Product>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "getProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Product.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ProductId, Product> METHOD_SEARCH_PRODUCT =
      io.grpc.MethodDescriptor.<ProductId, Product>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "searchProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Product.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Product, ProductId> METHOD_UPDATE_PRODUCT =
      io.grpc.MethodDescriptor.<Product, ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "updateProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Product.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Product, ProductId> METHOD_PROCESS_PRODUCT =
      io.grpc.MethodDescriptor.<Product, ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "processProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Product.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ProductId, ProductId> METHOD_REMOVE_PRODUCT =
      io.grpc.MethodDescriptor.<ProductId, ProductId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ProductInfo", "removeProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ProductId.getDefaultInstance()))
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
    public void addProduct(Product request,
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_PRODUCT, responseObserver);
    }

    /**
     */
    public void getProduct(ProductId request,
        io.grpc.stub.StreamObserver<Product> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PRODUCT, responseObserver);
    }

    /**
     */
    public void searchProduct(ProductId request,
        io.grpc.stub.StreamObserver<Product> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEARCH_PRODUCT, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Product> updateProduct(
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_UPDATE_PRODUCT, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Product> processProduct(
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_PROCESS_PRODUCT, responseObserver);
    }

    /**
     */
    public void removeProduct(ProductId request,
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REMOVE_PRODUCT, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<Product, ProductId>(
                  this, METHODID_ADD_PRODUCT)))
          .addMethod(
            METHOD_GET_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<ProductId, Product>(
                  this, METHODID_GET_PRODUCT)))
          .addMethod(
            METHOD_SEARCH_PRODUCT,
            asyncServerStreamingCall(
              new MethodHandlers<ProductId, Product>(
                  this, METHODID_SEARCH_PRODUCT)))
          .addMethod(
            METHOD_UPDATE_PRODUCT,
            asyncClientStreamingCall(
              new MethodHandlers<Product, ProductId>(
                  this, METHODID_UPDATE_PRODUCT)))
          .addMethod(
            METHOD_PROCESS_PRODUCT,
            asyncBidiStreamingCall(
              new MethodHandlers<Product, ProductId>(
                  this, METHODID_PROCESS_PRODUCT)))
          .addMethod(
            METHOD_REMOVE_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<ProductId, ProductId>(
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
    public void addProduct(Product request,
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProduct(ProductId request,
        io.grpc.stub.StreamObserver<Product> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchProduct(ProductId request,
        io.grpc.stub.StreamObserver<Product> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_SEARCH_PRODUCT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Product> updateProduct(
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_UPDATE_PRODUCT, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<Product> processProduct(
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_PROCESS_PRODUCT, getCallOptions()), responseObserver);
    }

    /**
     */
    public void removeProduct(ProductId request,
        io.grpc.stub.StreamObserver<ProductId> responseObserver) {
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
    public ProductId addProduct(Product request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public Product getProduct(ProductId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Product> searchProduct(
        ProductId request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_SEARCH_PRODUCT, getCallOptions(), request);
    }

    /**
     */
    public ProductId removeProduct(ProductId request) {
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
    public com.google.common.util.concurrent.ListenableFuture<ProductId> addProduct(
        Product request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Product> getProduct(
        ProductId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PRODUCT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ProductId> removeProduct(
        ProductId request) {
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
          serviceImpl.addProduct((Product) request,
              (io.grpc.stub.StreamObserver<ProductId>) responseObserver);
          break;
        case METHODID_GET_PRODUCT:
          serviceImpl.getProduct((ProductId) request,
              (io.grpc.stub.StreamObserver<Product>) responseObserver);
          break;
        case METHODID_SEARCH_PRODUCT:
          serviceImpl.searchProduct((ProductId) request,
              (io.grpc.stub.StreamObserver<Product>) responseObserver);
          break;
        case METHODID_REMOVE_PRODUCT:
          serviceImpl.removeProduct((ProductId) request,
              (io.grpc.stub.StreamObserver<ProductId>) responseObserver);
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
              (io.grpc.stub.StreamObserver<ProductId>) responseObserver);
        case METHODID_PROCESS_PRODUCT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.processProduct(
              (io.grpc.stub.StreamObserver<ProductId>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ProductInfoDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ProductInfoOuterClass.getDescriptor();
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
