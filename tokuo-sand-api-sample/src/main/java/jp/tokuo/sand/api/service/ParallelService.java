package jp.tokuo.sand.api.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ParallelService {

  /**
   * parallel streamで発生したエラーは最初に発生したエラーしか外側ではcatchすることができない。
   *
   * @throws Exception
   */
  public void exceptionService() throws Exception {
    List<String> myStrList = Arrays.asList("A", "B", "C", "D", "E", "A", "B", "C", "D", "E", "A", "B", "C", "D", "E", "A", "B", "C", "D", "E", "A", "B", "C", "D", "E");
    myStrList.stream().parallel().forEach(
        mystr -> {
          if(mystr.equals("A") || mystr.equals("C")){
            throw new RuntimeException();
          }
          log.info(mystr);
        }
    );
  }

  /**
   * ListやMapなどのオブジェクトの中にあるフィールドに作用するメソッドの呼び出し（List#addやMap#putなど）は利用できる。
   */
  public void referVariable(){
    List<String> myStrList = new ArrayList<>(){{
        add("aaa");
        add("bbb");
        add("ccc");
    }};

    List<String> insertList = new ArrayList<>(){{
        add("first");
    }};

    myStrList.stream().parallel().forEach(
        mystr -> {
          if(mystr.contains("aaa") || mystr.contains("ccc")){
            insertList.add(mystr);
          }
          log.info(mystr);
        }
    );
    insertList.stream().sequential().forEach(log::info);
  }
}
