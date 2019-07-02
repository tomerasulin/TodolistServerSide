import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class test {
    static Logger log = Logger.getLogger(test.class);
    public static void main(String[] args){
        String str = "  CREATE TABLE LOGS\n" +
                "   (USER_ID VARCHAR(30)    NOT NULL,\n" +
                "    DATED   DATE           NOT NULL,\n" +
                "    LOGGER  VARCHAR(45)    NOT NULL,\n" +
                "    LEVEL   VARCHAR(12)    NOT NULL,\n" +
                "    MESSAGE VARCHAR(1000)  NOT NULL\n" +
                "   );";
        PropertyConfigurator.configure("src/log4j.properties");
        log.debug("Test debug");
        log.info("Test info");
    }
}
