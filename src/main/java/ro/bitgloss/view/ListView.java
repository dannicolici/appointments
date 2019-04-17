package ro.bitgloss.view;

import ro.bitgloss.data.DataSource;

public class ListView implements View {

  @Override
  public String display(DataSource ds) {
    return header(ds).append(data(ds)).toString();
  }

  private StringBuilder data(DataSource ds) {
    StringBuilder sb = new StringBuilder();
    ds.stream().forEach(row -> {
      sb.append("- ");
      row.forEach(item -> {
        sb.append(item).append(", ");
      });
      sb.delete(sb.length() - 2, sb.length()).append(";\n");
    });

    return sb;
  }

  private StringBuilder header(DataSource ds) {
    StringBuilder sb = new StringBuilder();    
    sb.append("Details (");
    ds.entryDetails().forEach(d -> sb.append(d).append(", "));
    sb.delete(sb.length() - 2, sb.length()).append("):\n");

    return sb;
  }
}
