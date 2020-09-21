package ro.bitgloss.view;

import ro.bitgloss.data.DataSource;

import java.util.function.Function;

public class ListView implements Function<DataSource, String> {

  @Override
  public String apply(DataSource ds) {
    return header(ds).append(data(ds)).toString();
  }

  private static StringBuilder data(DataSource ds) {
    StringBuilder sb = new StringBuilder();
    ds.stream().forEach(row -> {
      sb.append("- ");
      row.forEach(item -> sb.append(item).append(", "));
      sb.delete(sb.length() - 2, sb.length()).append(";\n");
    });

    return sb;
  }

  private static StringBuilder header(DataSource ds) {
    StringBuilder sb = new StringBuilder();    
    sb.append("Details (");
    ds.entryDetails().forEach(d -> sb.append(d).append(", "));
    sb.delete(sb.length() - 2, sb.length()).append("):\n");

    return sb;
  }
}
