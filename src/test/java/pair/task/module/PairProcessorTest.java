package pair.task.module;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairProcessorTest {

    @Test
    public void testSimpleCase() throws URISyntaxException {
        File file = new File(PairProcessorTest.class.getResource("/test.csv").toURI());
        PairProcessor processor = new PairProcessor();
        processor.processPairs(file);
        Pair result = processor.getTopPair();
        assertEquals("218", result.getFirst());
        assertEquals("143", result.getSecond());
        assertEquals(2, result.getTotalDuration());
        assertEquals(1, result.getDurationsPerProject().size());
        assertEquals(2, result.getDurationsPerProject().get("10"));
    }

    @Test
    public void testInverseOrder() throws URISyntaxException {
        File file = new File(PairProcessorTest.class.getResource("/testInverseOrder.csv").toURI());
        PairProcessor processor = new PairProcessor();
        processor.processPairs(file);
        Pair result = processor.getTopPair();
        assertEquals("143", result.getFirst());
        assertEquals("218", result.getSecond());
        assertEquals(2, result.getTotalDuration());
        assertEquals(1, result.getDurationsPerProject().size());
        assertEquals(2, result.getDurationsPerProject().get("10"));
    }

    @Test
    public void testTwoProjects() throws URISyntaxException {
        File file = new File(PairProcessorTest.class.getResource("/testTwoProjects.csv").toURI());
        PairProcessor processor = new PairProcessor();
        processor.processPairs(file);
        Pair result = processor.getTopPair();
        assertEquals("143", result.getFirst());
        assertEquals("218", result.getSecond());
        assertEquals(5, result.getTotalDuration());
        assertEquals(2, result.getDurationsPerProject().size());
        assertEquals(2, result.getDurationsPerProject().get("10"));
        assertEquals(3, result.getDurationsPerProject().get("15"));
    }

    @Test
    public void testTwoPairs() throws URISyntaxException {
        File file = new File(PairProcessorTest.class.getResource("/testTwoPairs.csv").toURI());
        PairProcessor processor = new PairProcessor();
        processor.processPairs(file);
        Pair result = processor.getTopPair();
        assertEquals("143", result.getFirst());
        assertEquals("111", result.getSecond());
        assertEquals(3, result.getTotalDuration());
        assertEquals(1, result.getDurationsPerProject().size());
        assertEquals(3, result.getDurationsPerProject().get("15"));
    }

    @Test
    public void testHeader() throws URISyntaxException {
        File file = new File(PairProcessorTest.class.getResource("/testHeader.csv").toURI());
        PairProcessor processor = new PairProcessor();
        processor.processPairs(file);
        Pair result = processor.getTopPair();
        assertEquals("218", result.getFirst());
        assertEquals("143", result.getSecond());
        assertEquals(2, result.getTotalDuration());
        assertEquals(1, result.getDurationsPerProject().size());
        assertEquals(2, result.getDurationsPerProject().get("10"));
    }

    @Test
    public void testDateFormat() throws URISyntaxException {
        File file = new File(PairProcessorTest.class.getResource("/testDateFormat.csv").toURI());
        PairProcessor processor = new PairProcessor();
        processor.processPairs(file);
        Pair result = processor.getTopPair();
        assertEquals("218", result.getFirst());
        assertEquals("143", result.getSecond());
        assertEquals(2, result.getTotalDuration());
        assertEquals(1, result.getDurationsPerProject().size());
        assertEquals(2, result.getDurationsPerProject().get("10"));
    }

}
