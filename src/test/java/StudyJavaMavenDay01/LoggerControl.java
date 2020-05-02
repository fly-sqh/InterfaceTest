package StudyJavaMavenDay01;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class LoggerControl {
    public static Logger log= Logger.getLogger(LoggerControl.class);

    public static void main(String[] args) throws Exception {
             test();

    }
    public static void test() throws Exception {

        log.info("错误111");
    }
}
