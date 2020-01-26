# dbutils-pr7test
Example project using `ColumnListHandler<Integer>` of Apache Commons DbUtils to get generated keys from a
batch insert. Inserts are made to an in-memory H2 database.

## Running

The default goal for the projet is `clean test`, so running this only requires involing `mvn`.
```java
$ mvn

-- snip --
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.apache.commons.dbutils.PR7Test
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.717 s - in org.apache.commons.dbutils.PR7Test
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
-- snip --
```
