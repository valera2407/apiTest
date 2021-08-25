import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest {
    DataKeep dataKeep = new DataKeep();

    @BeforeSuite
    public void setUri() throws IOException {
        dataKeep.setUri("http://dummy.restapiexample.com/");
        dataKeep.setFile(Resources.getResource("test.json"));
        dataKeep.setMyJson(Resources.toString(dataKeep.getFile(), Charsets.UTF_8));
    }

}
