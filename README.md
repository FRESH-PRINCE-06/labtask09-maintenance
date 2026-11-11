# Lab Task 08 – Software Maintenance (Fall 2026)

**University of Engineering and Technology, Abbottabad Campus**
**Course:** Software Construction & Development (SE-206) – 5th Semester Software Engineering
**Instructor:** Engr. Rizwan Shah
**Student:** M. Mubashir Hassan (24ABSWE0009)

## Objective
Practice identifying code smells, refactoring legacy logic into maintainable patterns, and
implementing diagnostic observability features.

## Project layout (Maven, Java 17, JUnit 5)

```
LabTask08/
├── pom.xml
├── README.md
└── src
    ├── main/java/labtask08
    │   ├── legacy/   ReportManager (original smelly code), EmailClient
    │   ├── task1/    ReportFormatter, PdfReportFormatter, CsvReportFormatter,
    │   │             Notifier, EmailNotifier, ReportManager, RefactoredReportDemo
    │   ├── task2/    BankTransfer (record), BankTransferDemo
    │   ├── task3/    PaymentProcessor, PaymentProcessorDemo
    │   └── task4/    VipDiscount, PercentageVipDiscount, DatabaseVipDiscount, OrderService
    └── test/java/labtask08
        ├── task1/ ReportManagerTest
        ├── task2/ BankTransferTest
        ├── task3/ PaymentProcessorTest
        └── task4/ OrderServiceTest
```

## Task descriptions

### Lab Task 1 – Refactoring Legacy Code (Smell Removal)
The legacy `ReportManager` had four maintainability anti-patterns. All four are fixed in
`labtask08.task1.ReportManager`:

| # | Smell | Fix |
|---|-------|-----|
| 1 | String concatenation inside a loop | `String.join(",", data)` |
| 2 | `NullPointerException` risk on `type.equals(...)` | Explicit null guard → `IllegalArgumentException` with a clear message |
| 3 | Hard-coded PDF/CSV `if / else if` chain | `ReportFormatter` interface with `PdfReportFormatter` and `CsvReportFormatter` |
| 4 | Static, untestable `EmailClient` | `Notifier` interface injected through the constructor |

### Lab Task 2 – Fail-Fast and Immutable Boundaries
`BankTransfer` is a Java `record` (`fromAccount`, `toAccount`, `amount` as `BigDecimal`).
The compact constructor uses `Objects.requireNonNull` for every field and throws
`IllegalArgumentException` unless `amount > 0`. `BankTransferDemo` shows a valid transfer, then a
negative amount and a null account, printing each stack trace.

### Lab Task 3 – Structured Tracing (SLF4J MDC simulation)
`PaymentProcessor.charge(String txId, double amount)` binds the transaction id to a
`ThreadLocal` (simulating `MDC.put`), prints `[TraceID: <txId>] Processing payment...`, and
always clears the context in a `finally` block (printing `Context Cleared`) – even when the
payment fails – so the trace id cannot leak to another request on the same thread.

### Lab Task 4 (second "Task 3" in the lab sheet) – The Automated Quality Gate (JUnit 5)
`OrderService` receives a `VipDiscount` through its constructor. The test
`calculateTotal_appliesVipDiscount_withoutTouchingDatabase()` follows Arrange-Act-Assert and uses an
in-memory `PercentageVipDiscount`, so no database is involved.

## How to run

**Requirements:** JDK 17 or newer, Maven (bundled with Apache NetBeans).

### In Apache NetBeans
1. `File → Open Project…` and select the `LabTask08` folder.
2. Run a demo: right-click the file → **Run File** (or `Shift+F6`):
   - `labtask08/task1/RefactoredReportDemo.java`
   - `labtask08/task2/BankTransferDemo.java`
   - `labtask08/task3/PaymentProcessorDemo.java`
3. Run all tests: right-click the project → **Test** (or `Alt+F6`). Run a single test class with
   right-click → **Test File**.

### From the command line
```bash
mvn clean test                                                      # run all JUnit tests
mvn compile exec:java -Dexec.mainClass=labtask08.task1.RefactoredReportDemo
mvn compile exec:java -Dexec.mainClass=labtask08.task2.BankTransferDemo
mvn compile exec:java -Dexec.mainClass=labtask08.task3.PaymentProcessorDemo
```
# labtask09-maintenance
