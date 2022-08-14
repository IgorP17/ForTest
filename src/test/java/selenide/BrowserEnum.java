package selenide;

public enum BrowserEnum {
    CHROME("chrome"), FIREFOX("firefox");

    private final String name;

    BrowserEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BrowserEnum{" +
                "name='" + name + '\'' +
                '}';
    }
}
