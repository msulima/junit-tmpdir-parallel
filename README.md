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

### Expected behavior

There should be only one directory:

```
$ ls target | grep junit

junit1999562538851304897
```

and one log message with "Skipping cleanup of temp dir".

It works fine after changing JUnit version to `5.10.0` in `pom.xml`:
