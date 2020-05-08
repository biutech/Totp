package test;

import com.google.authenticator.otp.TotpUtils;
import org.junit.Test;

public class TotpTest {

  @Test
  public void test() {

    try {
      String secret = "DFASFDASFASFASDEB";
      System.out.println(TotpUtils.getCode(secret));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
