package ro.bitgloss.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import ro.bitgloss.data.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabularViewTest {

  @Test
  public void display() {
    DataSource ds = new DataSource() {
      @Override
      public List<String> entryDetails() {
        return Arrays.asList("text");
      }

      @Override
      public Stream<List<String>> stream() {
        return Stream.of(Collections.singletonList("data"));
      }
    };
    String expected = 
        "--------------------------\n" +
        "|          text          |\n" +
        "--------------------------\n" +
        "          data          \n" +
        "--------------------------\n";
    
    String actual = new TabularView().display(ds);
    
    assertEquals(expected, actual);
  }
}