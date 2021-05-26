package lab2; 

public class Lab2 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String s1 = "Morty1";
        String s2 = "Rick";
        
        String result = StringUtils.betterString(s1, s2, (st1,st2) -> st1.length() > st2.length());
        
        String result2 = StringUtils.betterString(s1, s2, (st1,st2) -> (int)st1.charAt(0) > (int)st2.charAt(0));
        
        System.out.println(result);
        System.out.println(result2);

        
        System.out.println(StringUtils.isLetter(s1));
        System.out.println(StringUtils.isLetter(s2));
        
    }

}
