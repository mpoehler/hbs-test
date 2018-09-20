import com.example.appengine.java8.HelloAppEngineHbs;
import org.junit.Test;

public class TestWithoutDevServer {

    @Test
    public void test() throws Exception {
        // This works!!!
        HelloAppEngineHbs.createWithHandlebar();
    }
}
