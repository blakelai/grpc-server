package com.github.blakelai.grpc;

import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;

import javax.inject.Inject;

public class MainVerticle extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  @Inject
  GreeterService service;

  @Inject
  MyInterceptor interceptor;

  public MainVerticle() {
    DaggerVerticleComponent.create().inject(this);
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    ServerServiceDefinition serviceDefinition = ServerInterceptors.intercept(service, interceptor);

    VertxServer rpcServer = VertxServerBuilder
      .forAddress(vertx, "0.0.0.0", 8080)
      .addService(serviceDefinition)
      .build();

    // Start is asynchronous
    rpcServer.start((result) -> {
      if (result.succeeded()) {
        logger.info("Grpc server established");
        startPromise.complete();
      } else {
        startPromise.fail(result.cause());
      }
    });

  }
}
