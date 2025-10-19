package calculator.model;

public class Calculator {

    private boolean hasDelimiter;
    private char delimiter;

    public Calculator(){
        hasDelimiter = false;
    }

    public char getDelimiter(){
        return  delimiter;
    }

    public void setDelimiter(char delimiter){
        this.delimiter = delimiter;
        hasDelimiter = true;
    }

    public boolean hasDelimiter(){
        return hasDelimiter;
    }


}
