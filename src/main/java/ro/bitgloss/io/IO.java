package ro.bitgloss.io;

import java.util.Optional;

public interface IO {

    void print(Object o);
    void printLine(Object o);
    Optional<String> readString(String prompt, String errorMessage);
}
