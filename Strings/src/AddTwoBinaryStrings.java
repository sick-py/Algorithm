import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddTwoBinaryStrings {
    String add(String str1, String str2) {
        List<String> res = new ArrayList<>();
        int carry = 0;
        int p1 = str1.length() - 1, p2 = str2.length() - 1;

        while (p1 >= 0 || p2 >= 0) {
            int x1 = 0, x2 = 0;
            if (p1 >= 0) {
                x1 = Character.codePointAt(str1, p1) - Character.codePointAt("0", 0);
            }

            if (p2 >= 0) {
                x2 = Character.codePointAt(str2, p2) - Character.codePointAt("0", 0);
            }

            int val = (x1 + x2 + carry) % 2;
            carry = (x1 + x2 + carry) / 2;

            res.add(String.valueOf(val));
            p1--;
            p2--;
        }

        if (carry != 0) {
            res.add(String.valueOf(carry));
        }

        Collections.reverse(res);
        return String.join("", res);
    }
}
