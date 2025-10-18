package calculator.controller;

import calculator.model.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.ArrayList;

public class CalculatorController {

    private Calculator calculator;

    public void powerOnCalculator(){
        // 입력
        String input = InputView.getInput();

        // 계산
        int value = calculate(input);

        // 결과값 출력
        OutputView.printOutput(value);
    }

    public int calculate(String input){
        // 입력값 검증
        calculator = new Calculator();
        validate(input);

        // 문자열 파싱
        ArrayList<Integer> numbers = parseString(input);

        // 결과값 계산
        return addNum(numbers);
    }

    public void validate(String input){
        // 빈 문자열인 경우
        if(input.isBlank()){
            throw new IllegalArgumentException("빈 문자열입니다");
        }
        // 커스텀 구분자의 선언이 틀린 경우
        // 첫글자가 숫자도, 기본 구분자도 아니면 -> 커스텀 구분자를 선언한거야.
        char c = input.charAt(0);
        if(!(('0' <= c && c <= '9') || (c == ',') || (c == ':'))){
            if(!input.startsWith("//")) throw new IllegalArgumentException("커스텀 구분자의 선언이 틀렸습니다");
            if(!input.substring(3,5).equals("\\n")) throw new IllegalArgumentException("커스텀 구분자의 선언이 틀렸습니다");
            char custom = input.charAt(2);
            if('0' <= custom && custom <= '9') throw new IllegalArgumentException("커스컴 구분자의 선언이 틀렸습니다");
            calculator.setDelimiter(custom);
        }
        // 커스텀, 기본 구분자 이외의 특수기호가 존재하는 경우
        for(int i=5; i<input.length();i++){
            c = input.charAt(i);
            if('0' <= c && c <= '9') continue;
            else if(c == ':' || c == ',') continue;
            else if(calculator.isDelimiter() && c == calculator.getDelimiter()) continue;
            else throw new IllegalArgumentException("커스텀, 기본 구분자 이외의 특수기호가 존재합니다");
        }

        // 음수인 경우
        if(input.contains("-")){
            throw new IllegalArgumentException("음수가 존재합니다");
        }
    }

    public ArrayList<Integer> parseString(String input){
        // 커스텀 구분자가 있는지 확인
        boolean flag = calculator.isDelimiter();
        if(flag){
            input = input.substring(5);
        }
        // 커스텀, 기본 구분자 기준으로 파싱하기
        ArrayList<Integer> numbers = new ArrayList<>();
        String str = "";
        for(int i=0; i<input.length(); i++){
            // 구분자면 패스 -> string reset
            char c = input.charAt(i);
            if(c == ':' || c == ',' || (flag && c == calculator.getDelimiter())){
                numbers.add(Integer.parseInt(str));
                str = "";
            }
            // 구분자가 아니면 string +=
            else{
                str += String.valueOf(c);
            }
        }
        if(str != "") numbers.add(Integer.parseInt(str));
        return numbers;
    }

    public int addNum(ArrayList<Integer> numbers){
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
