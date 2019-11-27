package jp.tokuo.sand.base.appender;

import java.util.Objects;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class TokuoSandLogAppender<E> extends ConsoleAppender<E> {

  private String arg1;
  private String arg2;

  public String getArg1(){
    return arg1;
  }
  public String getArg2(){
    return arg2;
  }

  public void setArg1(String arg1){
    this.arg1 = arg1;
  }
  public void setArg2(String arg2){
    this.arg2 = arg2;
  }

  @Override
  public void start() {
    super.start();
    Objects.requireNonNull(arg1, "arg1 is null");
    Objects.requireNonNull(arg2, "arg2 is null");
  }

  @Override
  protected void subAppend(E event) {
    E eventNew = event;

    if (event instanceof LoggingEvent) {
      // ログ出力時に任意の処理を追加
    }

    super.subAppend(eventNew);
  }

  @Override
  public void stop() {
    super.stop();
  }
}
