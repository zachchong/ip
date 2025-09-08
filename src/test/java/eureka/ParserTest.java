package eureka;

import org.junit.jupiter.api.Test; // external imports first

import eureka.command.EventCommand; // then project imports
import eureka.command.MarkCommand;

import static org.junit.jupiter.api.Assertions.assertEquals; // finally, static imports (all grouped together)
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {

    @Test
    public void markCommandTest() {
        var cmd = Parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, cmd);
        assertEquals(2, ((MarkCommand) cmd).getIndex() + 1);
    }

    @Test
    public void eventCommandTest() {
        var cmd = Parser.parse("event Test /from 2025-09-01 18:00 /to 2025-10-01 18:00");
        assertInstanceOf(EventCommand.class, cmd);
        assertEquals(DateTimeConverter.dateTimeConverter("2025-09-01 18:00"), ((EventCommand) cmd).getFrom());
    }

}
