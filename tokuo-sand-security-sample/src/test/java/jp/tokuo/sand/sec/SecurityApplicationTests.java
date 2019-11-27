package jp.tokuo.sand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("JUnit5 TEST in springboot");
	}

}
