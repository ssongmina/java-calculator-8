package calculator.model;

public class Calculator {

    private boolean hasDelimiter;
    private String delimiter;

    public Calculator(){
        hasDelimiter = false;
    }

    public String getDelimiter(){
        return  delimiter;
    }

    public void setDelimiter(String delimiter){
        this.delimiter = delimiter;
        hasDelimiter = true;
    }

    public boolean hasDelimiter(){
        return hasDelimiter;
    }

    public int getDelimiterSize(){
        return  delimiter.length();
    }
}
