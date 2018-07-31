package pracs.prac1;

import java.io.*;

public class Task3 {
    public static void main(String[] args) {
        String fileName1 = args[0];
        String fileName2 = args[1];
        char character = args[2].charAt(0);

        File file1 = new File(fileName1);
        File file2 = new File(fileName2);
        try{
            BufferedReader in = new BufferedReader(new FileReader(file1));
            BufferedWriter out = new BufferedWriter(new FileWriter(file2, false));

            while(in.ready()){
                String Line = in.readLine();
                char[] myChars = Line.toCharArray();

                for(char myChar : myChars){
                    if(myChar != character){
                        out.write(myChar);
                    }
                }

                out.newLine();
            }

            in.close();
            out.close();

        } catch(Exception e){
            System.out.println("Exception");
            System.out.println(e.getMessage());
        }
    }
}
