package pl.msulima.junit;

import static pl.msulima.junit.FailingTest.TIMEOUT;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

public class Parallel01Test {
    @TempDir(cleanup = CleanupMode.ON_SUCCESS)
    Path tempDir;

    @Test
    void test() throws InterruptedException {
        System.out.println(getClass().getName() + " start => " + Thread.currentThread().getName() + " " + tempDir);
        Thread.sleep(TIMEOUT * 2);
        System.out.println(getClass().getName() + " end => " + Thread.currentThread().getName());
    }
}
