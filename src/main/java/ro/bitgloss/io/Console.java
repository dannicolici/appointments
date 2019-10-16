package ro.bitgloss.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Console implements TypedIO {
    private static BufferedReader SYS_IN_READER = new BufferedReader(new InputStreamReader(System.in));

    private static final Console INSTANCE = new Console();

    @Override
    public void print(Object o) {
        System.out.print(o);
    }

    @Override
    public void printLine(Object o) {
        print(o + "\n");
    }

    public static Console getInstance() {
        return INSTANCE;
    }

    public char choice(String menu) {
        printLine(menu);
        try {
            return SYS_IN_READER.readLine().charAt(0);
        } catch (Exception e) {
            printLine("Cannot read line. Try again.");
            return choice(menu);
        }
    }

    @Override
    public Optional<String> readString(String prompt, String errMessage) {
        print(prompt);
        try {
            return Optional.ofNullable(SYS_IN_READER.readLine());
        } catch (IOException e) {
            printLine(errMessage);
            return Optional.empty();
        }
    }
}
