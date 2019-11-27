package jp.tokuo.sand.data.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.tokuo.sand.data.model.SampleData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DataController {

  private final ObjectMapper objectMapper;
  private final Counter myCounter; // Counter, Gauge, Histogram, Summary

  public DataController(ObjectMapper objectMapper, MeterRegistry meterRegistry){
    this.objectMapper = objectMapper;
    this.myCounter = meterRegistry.counter("jp.tokuo.sand", "myTagName1", "myTagValue1", "myTagName2", "myTagValue2");
  }


  @GetMapping(path = "/test/{anyWord}")
  @Timed
  public ResponseEntity<String> test(@PathVariable String anyWord) throws JsonProcessingException {
    SampleData res = new SampleData();
    res.setCandidate(anyWord);
    try {
      log.info("ログ出力アンチパターン - 無駄出力");
    } catch (Exception e) {
      log.warn("人が介入しないのでログレベルはwarn");
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("セキュリティ的に問題なければ、5W1Hで記述");
    } finally {
      log.debug("デバッグ時のみ出力");
      myCounter.increment();
    }
    return ResponseEntity.ok().body(objectMapper.writeValueAsString(res));
  }
}
