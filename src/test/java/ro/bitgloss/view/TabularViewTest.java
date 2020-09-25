package ro.bitgloss.view;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabularViewTest {

  @Test
  public void display() {
    var expected =
            """
                    --------------------------
                    |          text          |
                    --------------------------
                              data         \s
                    --------------------------
                    """;
    
    var actual = TabularView.tabularFormat.apply(
            Collections.singletonList("text"),
            () -> Stream.of(Collections.singletonList("data")));
    
    assertEquals(expected, actual);
  }
}