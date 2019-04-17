package ro.bitgloss.data;

import java.util.List;
import java.util.stream.Stream;

public interface DataSource {
  List<String> entryDetails();
  Stream<List<String>> stream();
}
