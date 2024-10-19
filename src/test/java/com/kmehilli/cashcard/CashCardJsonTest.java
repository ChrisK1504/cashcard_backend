package com.kmehilli.cashcard;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class CashCardJsonTest {
  
  @Autowired
  private JacksonTester<CashCard> json;

  @Test
  void cashCardSerializationTest() {
  }
  
}
