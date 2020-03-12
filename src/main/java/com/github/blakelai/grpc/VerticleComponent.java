package com.github.blakelai.grpc;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = VerticleModule.class)
public interface VerticleComponent {
  void inject(MainVerticle verticle);
}
