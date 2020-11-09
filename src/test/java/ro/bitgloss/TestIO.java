package ro.bitgloss;

import io.TypedIO;

import java.util.*;

class TestIO implements TypedIO {

    List<String> printBuffer = new ArrayList<>();
    Queue<String> readBuffer = new LinkedList<>();

    @Override
    public void print(Object o) {
        printBuffer.add(o.toString());
    }

    @Override
    public void printLine(Object o) {
        printBuffer.add(o + "\n");
    }

    @Override
    public Optional<String> readString(String prompt, String errorMessage) {
        return Optional.of(readBuffer.remove());
    }
}
