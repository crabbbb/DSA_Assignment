package utility;

import java.io.*;
import adt.*;

public class FileUtility {

    public void append(String fileName, String str) throws IOException {
        try {
            FileWriter fw = new FileWriter(fileName, true); //true to append
            fw.append(str);
            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error Appending...");
        }
    }

    public ArrayList<String[]> read(String fileName) throws IOException {
        ArrayList<String[]> str = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                str.add(values);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found!");
        }
        return str;
    }

    public void clear(String fileName) throws IOException {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error Appending...");
        }
    }
    
    public boolean exist(String str){
        File file = new File(str);
        if(file.exists())
            return true;
        else
            return false;
    }

}
