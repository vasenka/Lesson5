package task2;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Task2 {
    public static void main(String[] args) {

        try {
            File file = new File(Task2.class.getResource("task2.properties").toURI());
            Universal un = new Universal();
            try {
              //  System.out.println("По данному ключу лежит: "+un.method(file, "str5"));
                un.method2(file, "str5");
            } catch (FileNotFoundException e2) {

            }
        } catch (URISyntaxException e) {

        }


    }
}
class Universal{
    String method(File file, String key) throws FileNotFoundException {
        String res = "";
        if (!file.getName().contains(".properties")){
            System.out.println("It is not a properties file!!!");
        } else
            if (!file.exists()){
            throw new FileNotFoundException();
        } else {
                Pattern p = Pattern.compile("^[^\\.]+");
                Matcher m = p.matcher(file.getName());
                String baseName = "";
                while (m.find()) {
                     baseName = m.group();
                }
                ResourceBundle rbFile = ResourceBundle.getBundle(file.getParentFile().getName()+"."+baseName);

                if (!rbFile.containsKey(key)) {
                    throw new NullPointerException("No such key!");
                } else {
                    try {
                        String value = new String(rbFile.getString(key).getBytes("ISO-8859-1"), "UTF-8");
                        res = value;
                    } catch (UnsupportedEncodingException e) {
                        System.out.println("Change encoding");
                    }
                }

        }
        return res;
    }
    String method2(File file, String key) throws FileNotFoundException {
        String res = "";
        if (!file.getName().contains(".properties")){
            System.out.println("It is not a properties file!!!");
        } else
        if (!file.exists()){
            throw new FileNotFoundException();
        } else {
            try (BufferedReader bf = new BufferedReader(new FileReader(file));) {
                String s;
                String sValue = null;
              m:  while ((s = bf.readLine()) != null) {
                    Pattern p = Pattern.compile("^[^=\\s]+");
                    Matcher m = p.matcher(s);
                    String sKey = null;
                    while (m.find()) {
                        sKey = m.group();
                    }

                    if (sKey.equals(key)) {
                        Pattern p2 = Pattern.compile("[^\\s=]+$");
                        Matcher m2 = p2.matcher(s);
                        while (m2.find()) {
                            sValue = m2.group();
                        }
                        break m;
                    }
                }

                if (sValue == null) {
                    throw new NullPointerException("No such key!");
                } else {
                    System.out.println("По данному ключу лежит: " + sValue);
                }

            } catch (IOException e) {

            }

        }
        return res;
    }
}
