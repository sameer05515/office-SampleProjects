import java.util.ArrayList;
import java.util.Collections;

public class MyClass{
    static Integer carry = 0;
    public static void main(String args[]){
        String s1 = "121";
        String s2 = "11";
        add(s1, s2);
    }

    public static void add(String a, String b){
        ArrayList<String> res = new ArrayList<String>();
        StringBuilder myString = null;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while(true){
            int i1 = Integer.parseInt(Character.toString(a.charAt(i)));
            int i2 = Integer.parseInt(Character.toString(b.charAt(j)));
            Integer i3  = i1 + i2 + carry;
            if(i3 > 9){
                carry = 1;
                i3 = i3 - 10;
            }else carry = 0;
            res.add(i3.toString());
            i--;j--;
            if(i < 0){
                res.add(carry.toString());
                break;
            }
        }
        Collections.reverse(res);
        for(String r : res){
            System.out.print(r);
        }
    }
} 