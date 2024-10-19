package com.kmehilli.cashcard;

import java.io.IOException;

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
  void cashCardSerializationTest() throws IOException {
    CashCard cashCard = new CashCard(99L, 123.45);

    // Assert that the cashCard object is equal to the json after serialization
    assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");

    // Extracting and comparing values
    assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
    assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);

    assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
    assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
  }

  @Test
  void cashCardDeserializationTest() throws IOException {
    String expected = """
        {
          "id": 99,
          "amount": 123.45
        }
        """;
    assertThat(json.parse(expected)).isEqualTo(new CashCard(99, 123.45));
    assertThat(json.parseObject(expected).id()).isEqualTo(99);
    assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
  }
  
}
