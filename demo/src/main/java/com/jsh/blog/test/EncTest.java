package com.jsh.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

  @Test
  public void hashEnc() {
    String encPassword = new BCryptPasswordEncoder().encode("1234");
    System.out.println("1234 해쉬 = " + encPassword);
  }

}
