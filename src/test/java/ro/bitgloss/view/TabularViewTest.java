package ro.bitgloss.view;

import org.junit.jupiter.api.Test;
import ro.bitgloss.data.DataSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabularViewTest {

  @Test
  public void display() {
    var ds = new DataSource() {
      @Override
      public List<String> entryDetails() {
        return Collections.singletonList("text");
      }

      @Override
      public Stream<List<String>> stream() {
        return Stream.of(Collections.singletonList("data"));
      }
    };
    var expected =
            """
                    --------------------------
                    |          text          |
                    --------------------------
                              data         \s
                    --------------------------
                    """;
    
    var actual = TabularView.format.apply(ds);
    
    assertEquals(expected, actual);
  }
}