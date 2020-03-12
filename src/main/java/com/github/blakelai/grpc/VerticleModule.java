package com.github.blakelai.grpc;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class VerticleModule {

  @Provides @Singleton
  static GreeterService provideGreeterService() {
    return new GreeterService();
  }

  @Provides @Singleton
  static MyInterceptor provideMyInterceptor() {
    return new MyInterceptor();
  }
}
