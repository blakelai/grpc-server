package com.github.blakelai.grpc;

import com.github.blakelai.grpc.proto.GreeterGrpc;
import com.github.blakelai.grpc.proto.HelloReply;
import com.github.blakelai.grpc.proto.HelloRequest;
import io.vertx.core.Promise;

public class GreeterService extends GreeterGrpc.GreeterVertxImplBase {

  @Override
  public void sayHello(HelloRequest request, Promise<HelloReply> future) {
    future.complete(HelloReply.newBuilder().setMessage(request.getName()).build());
  }
}
