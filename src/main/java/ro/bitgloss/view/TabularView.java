package ro.bitgloss.view;

import ro.bitgloss.data.DataSource;

import java.util.List;
import java.util.function.Function;

public class TabularView {

  private static final String PAD = "          ";

  public static Function<DataSource, String> format = ds -> {
    StringBuilder sb = new StringBuilder();
    String rowSeparator = rowSeparator(ds.entryDetails());
    sb.append(rowSeparator);
    sb.append(headers(ds));
    sb.append(rowSeparator);
    sb.append(data(ds));
    sb.append(rowSeparator);

    return sb.toString();
  };

  private static StringBuilder data(DataSource ds) {
    StringBuilder sb = new StringBuilder();
    ds.stream().forEach(row -> {
      row.forEach(d -> sb.append(cell(d)));
      sb.append("\n");
    });
    return sb;
  }

  private static StringBuilder headers(DataSource ds) {
    StringBuilder sb = new StringBuilder();
    ds.entryDetails().forEach(d -> sb.append("|").append(cell(d)));
    sb.append("|\n");
    
    return sb;
  }

  private static String cell(String data) {
    return PAD +data+ PAD;
  }

  private static String rowSeparator(List<String> headers) {
    int headersCharCount = headers.stream().mapToInt(String::length).sum();
    int rowSize = headersCharCount + PAD.length() * 2 * headers.size() + headers.size() + 1;
    return "-".repeat(Math.max(0, rowSize)) + "\n";
  }
}
