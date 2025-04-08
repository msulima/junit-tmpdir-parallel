package pl.msulima.junit;

import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

public class FailingTest {
    static final int TIMEOUT = 250;

    @TempDir(cleanup = CleanupMode.ON_SUCCESS)
    Path tempDir;

    @Test
    void test() throws InterruptedException {
        System.out.println(getClass().getName() + " start => " + Thread.currentThread().getName() + " " + tempDir);
        Thread.sleep(TIMEOUT);
        System.out.println(getClass().getName() + " end => " + Thread.currentThread().getName());
        Assertions.fail("Expected failure");
    }
}
