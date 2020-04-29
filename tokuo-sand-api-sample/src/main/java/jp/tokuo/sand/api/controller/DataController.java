package jp.tokuo.sand.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.tokuo.sand.api.model.SampleData;
import jp.tokuo.sand.api.service.DateTimeService;
import jp.tokuo.sand.api.service.ParallelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@Api(tags = "DataControlle")
public class DataController {

  private final ParallelService parallelService;
  private final DateTimeService dateTimeService;
  private final ObjectMapper objectMapper;
  private final Counter myCounter; // Counter, Gauge, Histogram, Summary

  public DataController(
      ParallelService parallelService,
      DateTimeService dateTimeService,
      ObjectMapper objectMapper,
      MeterRegistry meterRegistry
  ){
    this.parallelService = parallelService;
    this.dateTimeService = dateTimeService;
    this.objectMapper = objectMapper;
    this.myCounter = meterRegistry.counter("jp.tokuo.sand", "myTagName1", "myTagValue1", "myTagName2", "myTagValue2");
  }


  @GetMapping(value = "/get/{anyWord}")
  @Timed
  @ApiOperation(
      value = "Get the metrics",
      notes = "get test"
  )
  @ApiResponses(value = {
      @ApiResponse(code = 401, message = "Token Required"),
      @ApiResponse(code = 403, message = "Permission Denied."),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<String> getTest(@PathVariable String anyWord) throws JsonProcessingException {
    SampleData res = new SampleData();
    res.setCandidate(anyWord);
    try {
      parallelService.exceptionService();
    } catch (Exception e) {
      log.warn("人が介入しないのでログレベルはwarn");
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("セキュリティ的に問題なければ、5W1Hで記述");
    } finally {
      myCounter.increment();
    }
    return ResponseEntity.ok().body(objectMapper.writeValueAsString(res));
  }

  @PostMapping(value = "/post", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> postTest(
      @RequestParam(name = "param01", required = false) String param01,
      @RequestParam(name = "param02", required = false) String param02
  ) {
    dateTimeService.showDateTime();
    return ResponseEntity.ok().body(String.format("{\"param\": \"param\"}"));
  }
}
