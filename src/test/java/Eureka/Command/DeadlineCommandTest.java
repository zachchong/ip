package Eureka.Command;

import Eureka.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineCommandTest {

    @Test
    public void testExit() {
        assertFalse(Parser.parse("deadline Test /by 2025-09-01 18:00").isExit());
    }
}
