package pl.msulima.junit;

import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

public class A02FailureTest {
    @TempDir(cleanup = CleanupMode.ON_SUCCESS)
    Path tempDir;

    @Test
    void test() {
        System.out.println(getClass().getName() + " => " + Thread.currentThread().getName() + " " + tempDir);
        Assertions.fail("Expected failure");
    }
}
