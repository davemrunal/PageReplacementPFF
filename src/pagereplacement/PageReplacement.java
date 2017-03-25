/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagereplacement;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mrunal
 */
public class PageReplacement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        System.out.flush();
        String filename = scanner.nextLine();
        String path = "D:\\JAVA Projects\\PageReplacement\\src\\pagereplacement\\";
        path = path + filename + ".txt";
        int pages = 0; //Number of pages occupied by the process in memory
        ArrayList<Integer> pageReferences = new ArrayList<Integer>(); //Stores Page references
        HashMap<Integer, Integer> residentSet = new HashMap<Integer, Integer>(); //Stores resident set. key is page number and value denotes used bit.
        int time = 0;
        int F = 2;
        /*If F is small, principle of locality would not be much beneficial hence greater number of page faults. 
        If F is large, locality benefits kick in and fewer page faults. 
        */
        
        residentSet.put(1,0);
        residentSet.put(4,0);
        residentSet.put(5,0);    
        int fault1 = 0;
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                pageReferences.add(Integer.parseInt(line));
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        pages = pageReferences.get(0);
        pageReferences.remove(0);
        
        //PFF Implementation
        for (int i = 0; i < pageReferences.size(); i++) {
            int x = pageReferences.get(i);
            boolean bln = residentSet.containsKey(x);
            if (bln == false) {
                fault1++;
                if (time < F) {
                    residentSet.put(x, 1);
                } else {
                    residentSet.values().removeAll(Collections.singleton(0));
                    Set<Integer> keys = residentSet.keySet();
                    for (Integer key : keys) {
                        residentSet.put(key, 0);
                    }
                    residentSet.put(x, 1);
                }
                time=0;
            } else {
                time++;
            }
        }
        System.out.println(fault1);
        
    }
}
