package faker;

import com.github.javafaker.Faker;

public class TestData {
    public static String fakeNumber (){
        return new Faker().number().digits(8);
    }
    public static String fakeWord (){
        return new Faker().regexify("[a-zA-Z]{5}");
    }

    public static String emailGenerator(){
        return "test"+System.currentTimeMillis()+"@mailinator.com";
    }
    public static String phoneNumberGenerator(){
        return "00" + fakeNumber();
    }
}