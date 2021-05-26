package lab2;

import java.util.function.BiPredicate;

public class StringUtils {

    static String betterString(String str1, String str2, BiPredicate<String, String> f) {

        return f.test(str1, str2) ? str1 : str2;

    }
    
    static boolean isLetter(String str1) {
        
        return str1.chars().allMatch((a) -> Character.isLetter(a));
        
    }

}
