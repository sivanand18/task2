package encoding;

import java.util.ArrayList;
import java.util.List;

public class Encrypted {
    public static void main(String[] args) {
        List<Integer> test = encryptString("administrator");

    }

    public static List<Integer> getConvertedString(String encryptedStr) {
        List<Integer> result = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < encryptedStr.length(); i++) {
            int charCode = Character.codePointAt(encryptedStr, i);
            if (charCode < 0x80) {
                result.add(charCode);
                k++;
            } else if (charCode < 0x800) {
                result.add(0xc0 | charCode >> 6);
                k++;
                result.add(0x80 | (charCode & 0x3f));
                k++;
            } else if (charCode < 0xd800 || charCode >= 0xe000) {
                result.add(0xe0 | (charCode >> 12));
                k++;
                result.add(0x80 | ((charCode >> 6) & 0x3f));
                k++;
                result.add(0x80 | (charCode & 0x3f));
                k++;
            } else {
                i++;
                charCode = 0x10000 + (((charCode & 0x3ff) << 10) | (Character.codePointAt(encryptedStr, i) & 0x3ff));
                result.add(0xf0 | (charCode >> 18));
                k++;
                result.add(0x80 | ((charCode >> 12) & 0x3f));
                k++;
                result.add(0x80 | ((charCode >> 6) & 0x3f));
                k++;
                result.add(0x80 | (charCode & 0x3f));
                k++;
            }
        }
        return result;
    }

    public static List<Integer> encryptString(String stringValue) {
        if(stringValue != null && !stringValue.equals("")) {
            List<Integer> utfArray  = getConvertedString(stringValue);
            List<Integer> finalArray = new ArrayList<>();
            finalArray.add(utfArray.size());
            for(int i = 0 ; i < utfArray.size() ; i++)
            {
                finalArray.add(utfArray.get(i));
                for(int j = 1 ; j <= i ; j++ )
                {
                    finalArray.add(( i * j * 162) % 255);
                }
            }
            return finalArray;
        }
        return null;
    }
}
