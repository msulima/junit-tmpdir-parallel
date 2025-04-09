To trigger tests:

```shell
mvn clean test
```

Checking results:

```shell
ls target | grep junit
```

Order of tests is set in `junit-platform.properties`, so it's always:

* success
* failure
* success
* success

### Actual behavior

Since only one test has failed, there should be just one directory saved, but there are more:

```
$ ls target | grep junit
junit-14418919801202898796
junit-3558652412717603429
junit-8238501641488877135
```

There are also 3 log messages like:

```
2025-04-09 13:50:46,318 [main]  INFO o.j.j.e.e.TempDirectory$CloseablePath - Skipping cleanup of temp dir /Users/mateusz/code/junit-tmpdir-parallel/target/junit-8238501641488877135 due to cleanup mode configuration.
```

### Expected behavior

There should be only one directory:

```
$ ls target | grep junit

junit1999562538851304897
```

and one log message with "Skipping cleanup of temp dir".

It works fine after changing JUnit version to `5.10.0` in `pom.xml`:
