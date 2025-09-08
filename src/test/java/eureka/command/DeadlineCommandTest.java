package eureka.command;

import org.junit.jupiter.api.Test;

import eureka.Parser;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineCommandTest {

    @Test
    public void testExit() {
        assertFalse(Parser.parse("deadline Test /by 2025-09-01 18:00").isExit());
    }
}
