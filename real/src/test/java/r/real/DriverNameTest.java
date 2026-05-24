package r.real;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import r.real.model.valueObjects.DriverName;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DriverNameTest {

    @TestFactory
    Stream<DynamicTest> shouldBeAccepted() {
        return Stream.of(
                "John Smith",
                "Ana Petrova",
                "O'Connor",
                "Jean-Luc Picard",
                "Dr. Brown",
                "Mary Jane"
        ).map(name ->
                dynamicTest("Driver name accepted: " + name,
                        () -> assertDoesNotThrow(() -> new DriverName(name)))
        );
    }

    @TestFactory
    Stream<DynamicTest> shouldBeAcceptedBoundaryCases() {
        return Stream.of(
                "Al",
                "Jo",
                "A B",
                "J.K",
                "O'N",
                "Anne-Marie Johnson-Smith"
        ).map(name ->
                dynamicTest("Boundary valid driver name: " + name,
                        () -> assertDoesNotThrow(() -> new DriverName(name)))
        );
    }

    @TestFactory
    Stream<DynamicTest> shouldNotBeAcceptedBoundaryCases() {
        return Stream.of(
                "",
                "A",
                " ",
                "J",
                "A".repeat(101),
                "John123"
        ).map(name ->
                dynamicTest("Boundary invalid driver name: " + name,
                        () -> assertThrows(RuntimeException.class, () -> new DriverName(name)))
        );
    }

    @TestFactory
    Stream<DynamicTest> shouldNotBeAcceptedInvalidInput() {
        return Stream.of(
                null,
                "\t",
                "\n",
                "John@Doe",
                "Jane_Doe",
                "Robert!",
                "<script>",
                "123",
                "0!."
        ).map(name ->
                dynamicTest("Invalid driver name rejected: " + String.valueOf(name),
                        () -> assertThrows(RuntimeException.class, () -> new DriverName(name)))
        );
    }

    @TestFactory
    Stream<DynamicTest> shouldRejectSqlLikeInput() {
        return Stream.of(
                "admin--",
                "or 1=1",
                "John or Mary",
                "select",
                "drop table",
                "union",
                "and",
                "insert",
                "delete"
        ).map(name ->
                dynamicTest("SQL-like driver name rejected: " + name,
                        () -> assertThrows(RuntimeException.class, () -> new DriverName(name)))
        );
    }

    @TestFactory
    Stream<DynamicTest> shouldRejectExtremeInput() {
        return Stream.<Supplier<String>>of(
                () -> "A".repeat(1000),
                () -> "B".repeat(10000),
                () -> "C".repeat(100000),
                () -> "D".repeat(1000000)
        ).map(nameSupplier ->
                dynamicTest("Extreme driver name rejected",
                        () -> assertThrows(RuntimeException.class,
                                () -> new DriverName(nameSupplier.get())))
        );
    }
}