package jp.tokuo.sand.api.service;

import org.springframework.stereotype.Component;

// java8からjava.utilではなく、java.timeにあるapi群を利用する方が良い
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateTimeService {

  public void showDateTime(){
    // LocalDateTime, LocalDate, LocalTime
    LocalDateTime nowDateTime = LocalDateTime.now(Clock.systemDefaultZone());
    LocalDate nowDate = nowDateTime.toLocalDate(); // = LocalDate.now() or LocalDate.now(Clock.systemDefaultZone())
    LocalTime nowTime = nowDateTime.toLocalTime(); // = LocalTime.now() or LocalTime.now(Clock.systemDefaultZone())
    log.info(String.format("{LocalDateTime: %s, LocalDate: %s, LocalTime %s}",
        nowDateTime, nowDate, nowTime));
    nowDateTime = LocalDateTime.of(nowDate, LocalTime.of(nowDateTime.getHour(), 0, 0, 0));
    log.info(String.format("nowDateTime: %s", nowDateTime));

    // java.sql.XXX
    Timestamp timestamp = Timestamp.valueOf(nowDateTime);
    log.info(String.format("nowDateTime for Sql: %s", timestamp));

    // format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime formattedNowDateTime = LocalDateTime.parse("2020/01/01 12:00:00", formatter);
    log.info(String.format("formatted LocalDateTime: %s", formattedNowDateTime));

    // compare
    TemporalAmount temporalAmount = Period.ofDays(-3);
    LocalDateTime minusDateTimeFromNow = nowDateTime.plus(temporalAmount); // = nowDateTime.plusDays(-3)
    boolean flag = nowDateTime.isAfter(minusDateTimeFromNow); // flag == true  nowDateTime after timeBeforNowDateTime

    // difference
    long diffDay = ChronoUnit.DAYS.between(nowDateTime, minusDateTimeFromNow);
    TemporalAmount duration = Duration.between(nowDateTime, minusDateTimeFromNow);
    TemporalAmount period = Period.between(nowDateTime.toLocalDate(), minusDateTimeFromNow.toLocalDate());
    log.info(String.format("{Difference in long: %s, Difference in duration: %s, Difference in period: %s}",
        diffDay, duration, period));

    // ISO 8601 準拠
    OffsetDateTime offsetDateTime = OffsetDateTime.of(nowDateTime, ZoneOffset.UTC);
    OffsetDateTime changedOffsetDataTime = offsetDateTime.plusDays(-3); // LocalDateTime型のplusと異なり、immutableなのでplus()しても元オブジェクトに変化なし
    log.info(String.format("{OffsetDateTime based on ISO 8601: %s, changed OffsetDataTime: %s",
        offsetDateTime, changedOffsetDataTime));
  }
}
