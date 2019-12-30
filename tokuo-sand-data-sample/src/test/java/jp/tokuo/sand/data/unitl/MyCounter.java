package jp.tokuo.sand.data.unitl;

import io.micrometer.core.instrument.Counter;

public class MyCounter implements Counter {

  public MyCounter() {}


  public void increment(){
  }

  @Override
  public void increment(double amount) {}

  @Override
  public double count() {
    return 0;
  }

  @Override
  public Id getId() {
    return null;
  }
}
