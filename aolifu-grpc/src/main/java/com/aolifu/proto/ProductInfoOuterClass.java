// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProductInfo.proto

package com.aolifu.proto;

public final class ProductInfoOuterClass {
  private ProductInfoOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Product_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Product_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ProductId_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ProductId_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ProductName_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ProductName_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021ProductInfo.proto\"8\n\007Product\022\n\n\002id\030\001 \001" +
      "(\t\022\014\n\004name\030\002 \001(\t\022\023\n\013description\030\003 \001(\t\"\032\n" +
      "\tProductId\022\r\n\005value\030\001 \001(\t\"\034\n\013ProductName" +
      "\022\r\n\005value\030\001 \001(\t2\374\001\n\013ProductInfo\022\"\n\naddPr" +
      "oduct\022\010.Product\032\n.ProductId\022\"\n\ngetProduc" +
      "t\022\n.ProductId\032\010.Product\022\'\n\rsearchProduct" +
      "\022\n.ProductId\032\010.Product0\001\022\'\n\rupdateProduc" +
      "t\022\010.Product\032\n.ProductId(\001\022*\n\016processProd" +
      "uct\022\010.Product\032\n.ProductId(\0010\001\022\'\n\rremoveP" +
      "roduct\022\n.ProductId\032\n.ProductIdB\024\n\020com.ao" +
      "lifu.protoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Product_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Product_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Product_descriptor,
        new java.lang.String[] { "Id", "Name", "Description", });
    internal_static_ProductId_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ProductId_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ProductId_descriptor,
        new java.lang.String[] { "Value", });
    internal_static_ProductName_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ProductName_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ProductName_descriptor,
        new java.lang.String[] { "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
