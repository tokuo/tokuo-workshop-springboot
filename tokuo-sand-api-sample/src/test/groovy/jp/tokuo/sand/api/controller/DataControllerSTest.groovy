package jp.tokuo.sand.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import jp.tokuo.sand.api.model.SampleData
import jp.tokuo.sand.api.service.DateTimeService
import jp.tokuo.sand.api.service.ParallelService
import spock.lang.Specification

import java.lang.reflect.Field

class DataControllerSTest extends Specification {

  def "単体テスト" (){
    given:
    def expect = "<200 OK OK,hoge,[]>"
    def parallelServiceMock = Mock(ParallelService)
    def dateTimeServiceMock = Mock(DateTimeService)
    def objectMapperMock = Mock(ObjectMapper)
    def meterRegistryMock = Mock(MeterRegistry)
    def sampleData = new SampleData()
    sampleData.setCandidate("hoge")
    objectMapperMock.writeValueAsString(sampleData) >> endPoint

    def target = new DataController(parallelServiceMock, dateTimeServiceMock, objectMapperMock, meterRegistryMock)
    Class targetClass = target.getClass()
    Field targetField = targetClass.getDeclaredField("myCounter")
    targetField.setAccessible(true)
    targetField.set(target, Mock(Counter))

    when:
    def res = target.getTest(endPoint)

    then:
    expect == res.toString()

    where:
    endPoint  | _
    "hoge"    | _
  }
}
