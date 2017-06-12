package task1;

import java.util.*;
import java.io.*;


public class Task1 {
    public static void main(String[] args) {


       // File folder = new File("/Users/Vasilisa/Documents/Files_to_give");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the path to your directory");
        File folder = new File(in.next());
        System.out.println("Files in the current directory: ");
        System.out.println();
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.showFolder(folder);
        } catch (FileNotFoundException e) {

        }

    }
}
class FileSystem{
    void showFolder(File folder)  throws FileNotFoundException{
            if (!folder.exists()) {
                throw new FileNotFoundException("No such folder!");
            }
            File[] files = folder.listFiles();
            for (File file : files) {
                System.out.println(file.getName());
            }
        System.out.println();
            System.out.println("Enter the action: 1. View the folder in this directory 2. Exit 3. Add to file 4. Go up ===>");
            Scanner in = new Scanner(System.in);
            String answer = in.next();
            if (answer.equals("1")) {
                System.out.println("Enter the name of the folder ===>");
                String folderName = folder.getPath() + "/" + in.next();
                File fileName = new File(folderName);
                System.out.println("============================");
                showFolder(fileName);
            } else if (answer.equals("2")) {
                System.out.println("By-by...");
            } else if (answer.equals("3")) {
                System.out.println("Enter file name ===>");
                File fileName = new File(folder.getPath()+"/"+in.next());
                System.out.println(fileName);
                System.out.println("Enter string to file ===>");
                String toFile = in.next();
                writeToFile(fileName, toFile);
            }else if (answer.equals("4")){
                showFolder(folder.getParentFile());
            }else {
                System.out.println("No such option!");
            }
    }
    void writeToFile(File file, String toFile){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
            if (!file.exists()) {
                file.createNewFile();
            }
            bw.write(toFile+"\n");

            } catch (IOException e) {
            }

    }

}
