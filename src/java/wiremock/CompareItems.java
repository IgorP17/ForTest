package wiremock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CompareItems {

    static Logger logger = LoggerFactory.getLogger(CompareItems.class);

    public static boolean compareItems(List<CompareItem> compareItems) {
        boolean result = true;
        for (CompareItem item : compareItems) {
            logger.info("{}: compare method = {}, expected = {}, actual = {}",
                    item.getMessage(), item.getCompareEnum(), item.getExpectedResult(), item.getActualResult());
            switch (item.getCompareEnum()) {
                case EQUALS:
                    if (item.getExpectedResult().equalsIgnoreCase(item.getActualResult())) {
                        logger.info("result = TRUE");
                    } else {
                        logger.info("result = FALSE");
                        result = false;
                    }
                    break;
                case CONTAINS:
                    if (item.getActualResult().contains(item.getExpectedResult())) {
                        logger.info("result = TRUE");
                    } else {
                        logger.info("result = FALSE");
                        result = false;
                    }
                    break;
            }
        }
        return result;
    }
}
