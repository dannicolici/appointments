package ro.bitgloss.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private static BufferedReader SYS_IN_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void printLine(Object o) {
        print(o + "\n");
    }

    public static char choice(String menu) {
        printLine(menu);
        try {
            return SYS_IN_READER.readLine().charAt(0);
        } catch (IOException e) {
            e.printStackTrace();
            return 'x';
        }
    }
}
