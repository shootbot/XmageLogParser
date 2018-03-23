import com.timporin.xmageparser.MillCounter;
import com.timporin.xmageparser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MillCountTest {

    @Test
    public void allTests() throws Exception {
        test10();
    }

    @Test
    public void test10() throws Exception {
        Parser p = new Parser("in (10).html");
        MillCounter mc = p.getMilled();

        assertEquals(15, mc.getHedronCrab());
        assertEquals(0, mc.getMesmericOrb());
        assertEquals(0, mc.getManicScribe());
        assertEquals(10, mc.getGlimpse());
        assertEquals(27, mc.getMindFuneral());
        assertEquals(0, mc.getAshiok());
        assertEquals(0, mc.getSanity());
        assertEquals(0, mc.getArchiveTrap());
        assertEquals(0, mc.getShelldock());
    }
}
