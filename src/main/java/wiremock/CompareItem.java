package wiremock;

public class CompareItem {

    private final String actualResult;
    private final String expectedResult;
    private final CompareEnum compareEnum;
    private final String message;

    public CompareItem(String actualResult, String expectedResult, CompareEnum compareEnum, String message) {
        this.actualResult = actualResult;
        this.expectedResult = expectedResult;
        this.compareEnum = compareEnum;
        this.message = message;
    }

    public String getActualResult() {
        return actualResult;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public CompareEnum getCompareEnum() {
        return compareEnum;
    }

    public String getMessage() {
        return message;
    }
}
