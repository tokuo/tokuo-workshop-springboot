package jp.tokuo.sand.data.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.tokuo.sand.data.model.SampleData;
import jp.tokuo.sand.data.service.DateTimeService;
import jp.tokuo.sand.data.service.ParallelService;
import jp.tokuo.sand.data.unitl.MyCounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;

import io.micrometer.core.instrument.MeterRegistry;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class DataControllerSTests {

  @Test
  @DisplayName("単体テスト")
  void getTest_01() throws Exception {
    // given
    String expect = "<200 OK OK,hoge,[]>";
    ParallelService parallelServiceMock= mock(ParallelService.class);
    DateTimeService dateTimeServiceMock = mock(DateTimeService.class);
    ObjectMapper objectMapperMock = mock(ObjectMapper.class);
    MeterRegistry meterRegistryMock = mock(MeterRegistry.class);
    // mockは完全に空のオブジェクトを生成するのに対し、spyは一部をモック化する
    DataController target = new DataController(parallelServiceMock, dateTimeServiceMock, objectMapperMock, meterRegistryMock);

    doNothing().when(parallelServiceMock).exceptionService();
    // 実際には myCounter = meterRegistry.counter(...)　で値を代入できていないため、myCounterはnull となる
//    doReturn(myCounter).when(meterRegistryMock).counter("jp.tokuo.sand", "myTagValue1", "myTagName2", "myTagValue2");
    // テスト用に本番のコードにsetterを追加した方が分かりやすく良いが、ここではリフレクションを利用する。
    // private final フィールドのリフレクション
    Class targetClass = target.getClass();
    Field targetField = targetClass.getDeclaredField("myCounter"); // privateは.getDeclaredField("FIELD")、publicは.getField("FIELD")
    targetField.setAccessible(true); // finalを無効化
    targetField.set(target, new MyCounter()); // 無効化できたためsetする
    // ここまでリフレクション
    SampleData sampleData = new SampleData();
    sampleData.setCandidate("hoge");
    doReturn("hoge").when(objectMapperMock).writeValueAsString(sampleData);

    // when
    ResponseEntity<String> res = target.getTest("hoge");

    // then
    Assertions.assertEquals(expect, res.toString());
  }
}
