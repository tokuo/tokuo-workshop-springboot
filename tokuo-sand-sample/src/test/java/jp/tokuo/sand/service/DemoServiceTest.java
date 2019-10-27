package jp.tokuo.sand.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.EnabledOnOs;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;

@DisplayName("JUnit5のお試しテスト")
public class DemoServiceTest {

  @Test
  @Tag("DEMO")
  @EnabledOnOs({ LINUX, MAC })
  @Disabled("DemoTest") //実行されない
//  @DisabledIfSystemProperty(named = "local", matches = "true") // namedで指定したJVMシステムプロパティの値に応じてテストのOnOffを切り替え
  void demoTest(TestInfo testInfo){
    System.out.println("DEMO");
    assertEquals("DemoTest", testInfo.getDisplayName());
    // アサーションをグループ化
    assertAll("グループ名",
        () -> assertEquals(0, 0),
        () -> assertEquals(1, 1)
    );
  }

  @Test
  void serviceTest() {
    assertEquals(0, 0);
 }
}



//package jp.co.yahoo.anemos.abs.sre.db.service;
//
//    import jp.co.yahoo.anemos.abs.sre.db.dao.SsKeywordDao;
//
//    import org.junit.jupiter.api.BeforeEach;
//    import org.junit.jupiter.api.DisplayName;
//    import org.junit.jupiter.api.Test;
//    import org.mockito.InjectMocks;
//    import org.mockito.Mock;
//    import org.mockito.MockitoAnnotations;
//
//    import java.time.LocalDate;
//
//    import static org.mockito.Mockito.times;
//    import static org.mockito.Mockito.verify;
//
//public class DeletionServiceSTest {
//
//  @Mock
//  public SsKeywordDao ssKeywordDao;
//
//  @InjectMocks
//  public DeletionServiceImpl deletionService;
//
//  @BeforeEach
//  void setup() {
//    // 各テストの実行前にモックオブジェクトを初期化する
//    MockitoAnnotations.initMocks(this);
//  }
//
//  @Test
//  @DisplayName("正常系")
//  void deleteDataTest() {
//    // expect
//    LocalDate now = LocalDate.now();
//    deletionService.deleteData(1, now);
//
//    // then
//    verify(ssKeywordDao, times(1)).deleteAggregatedData(1, now);
//  }
//}

