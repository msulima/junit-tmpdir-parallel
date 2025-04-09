To trigger tests:

```shell
mvn clean test
```

Checking results:

```shell
ls target | grep junit
```

### Actual behavior

Since only one test has failed, there should be just one directory saved, but there are more:

```
$ ls target | grep junit

junit-14003857681827952389
junit-15463632749978588079
junit-5569688180692638841
junit-8538748452148301642
```

There are also 4 log messages like:

```
2025-04-09 11:57:16,092 [ForkJoinPool-1-worker-4]  INFO o.j.j.e.e.TempDirectory$CloseablePath - Skipping cleanup of temp dir [...]/target/junit-14003857681827952389 due to cleanup mode configuration.
```

#### Serial execution

It also fails when parallel execution is disabled (in `junit-platform.properties`):

```
[INFO] Running pl.msulima.junit.Parallel02Test
pl.msulima.junit.Parallel02Test start => main /Users/mateusz/code/junit-tmpdir-parallel/target/junit-15318095156646094268
pl.msulima.junit.Parallel02Test end => main
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.546 s -- in pl.msulima.junit.Parallel02Test
[INFO] Running pl.msulima.junit.FailingTest
pl.msulima.junit.FailingTest start => main /Users/mateusz/code/junit-tmpdir-parallel/target/junit-6292596683032299518
pl.msulima.junit.FailingTest end => main
2025-04-09 12:12:49,144 [main]  INFO o.j.j.e.e.TempDirectory$CloseablePath - Skipping cleanup of temp dir /Users/mateusz/code/junit-tmpdir-parallel/target/junit-6292596683032299518 due to cleanup mode configuration.
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.281 s <<< FAILURE! -- in pl.msulima.junit.FailingTest
[ERROR] pl.msulima.junit.FailingTest.test -- Time elapsed: 0.279 s <<< FAILURE!
org.opentest4j.AssertionFailedError: Expected failure
	at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:38)
	at org.junit.jupiter.api.Assertions.fail(Assertions.java:138)
	at pl.msulima.junit.FailingTest.test(FailingTest.java:20)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

[INFO] Running pl.msulima.junit.Parallel03Test
pl.msulima.junit.Parallel03Test start => main /Users/mateusz/code/junit-tmpdir-parallel/target/junit-17054851304921472221
pl.msulima.junit.Parallel03Test end => main
2025-04-09 12:12:49,660 [main]  INFO o.j.j.e.e.TempDirectory$CloseablePath - Skipping cleanup of temp dir /Users/mateusz/code/junit-tmpdir-parallel/target/junit-17054851304921472221 due to cleanup mode configuration.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.509 s -- in pl.msulima.junit.Parallel03Test
[INFO] Running pl.msulima.junit.Parallel01Test
pl.msulima.junit.Parallel01Test start => main /Users/mateusz/code/junit-tmpdir-parallel/target/junit-9603462226393908919
pl.msulima.junit.Parallel01Test end => main
2025-04-09 12:12:50,169 [main]  INFO o.j.j.e.e.TempDirectory$CloseablePath - Skipping cleanup of temp dir /Users/mateusz/code/junit-tmpdir-parallel/target/junit-9603462226393908919 due to cleanup mode configuration.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.506 s -- in pl.msulima.junit.Parallel01Test
[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Failures: 
[ERROR]   FailingTest.test:20 Expected failure
[INFO] 
[ERROR] Tests run: 4, Failures: 1, Errors: 0, Skipped: 0
```

But I don't know how to force test order, so I've left this option enabled to make it consistently reproducible.

### Expected behavior

There should be only one directory:

```
$ ls target | grep junit

junit1999562538851304897
```

and one log message with "Skipping cleanup of temp dir".

It works fine after changing JUnit version to `5.10.0` in `pom.xml`:
