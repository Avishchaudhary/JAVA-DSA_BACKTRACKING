import java.util.ArrayList;

public class PhoneNumberLetterCombinationProblem {
    // make akey for the phones
    static String keys[] = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    // make a function lettercombination
    static ArrayList<String> lettercombination(String digits) {
        // termination case
        if (digits.length() == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add("");
            return list;
        }
        // small problem
        char singlechar = digits.charAt(0);
        int digit = singlechar - '0'; // minus karo uski ascii value se
        String strkeys = keys[digit];
        String remstring = digits.substring(1);

        ArrayList<String> finallist = new ArrayList<>();
        for (int i = 0; i < strkeys.length(); i++) {
            ArrayList<String> result = lettercombination(remstring);
            for (String s : result) {
                finallist.add(strkeys.charAt(i) + s);
            }
        }
        return finallist;
    }

    public static void main(String[] args) {
        ArrayList<String> finalresult = lettercombination("23");
        System.out.println(finalresult);
    }
}