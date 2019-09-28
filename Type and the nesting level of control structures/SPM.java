/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Rathnayake
 */
public class SPM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int total_complexity = 0;
        int line_complexity = 0;
        InputStream is;
        int line_count = 0;
        int line_no = 0;

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:/Users/Rathnayake/Documents/NetBeansProjects/SPM/test/Test.java"));
            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.isEmpty()) {
                    line_no++;
                    System.out.println(line_no);

                    if (((line.contains("if (") || line.contains("if(")) && (line.contains(") { ") || line.contains("){ "))) || line.contains("if(") || line.contains("if (") || ((line.contains("for (") || line.contains("for(")) && (line.contains(") { ") || line.contains("){ "))) || line.contains("for (") || line.contains("for(") || ((line.contains("while (") || line.contains("while(")) && (line.contains(") { ") || line.contains("){ "))) || line.contains("while(") || line.contains("while (")) {
                        total_complexity++;
                        line_complexity = line_complexity + 1;
                        System.out.println("line cnc:" + line_complexity);
                        System.out.println(line);
                        line_complexity = 0;

                        while (((line = reader.readLine()) != null)) {
                            if (!line.isEmpty()) {
                                line_no++;
                                System.out.println(line_no);

                                if (line.contains("} ")) {
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    break;
                                } else if ((line.contains("if (") || line.contains("if("))) {
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    line_complexity = 0;

                                } else if (line.contains("return ")) {
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    line_complexity = 0;
                                } else if ((line.contains("for(") && line.contains("{")) || line.contains("for (")) {

                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    line_complexity = 0;

                                } else if (line.contains("System.out.")) {
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    line_complexity = 0;
                                } else if ((line.contains("while(") && line.contains("{")) || line.contains("while (")) {
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    line_complexity = 0;
                                } else {
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                }

                            }
                        }

                    } else if (line.contains("try {") || line.contains("try{")) {
                        System.out.println("line cnc:" + line_complexity);
                        System.out.println(line);

                        while (((line = reader.readLine()) != null)) {
                            if (!line.isEmpty()) {
                                line_no++;
                                System.out.println(line_no);
                                if ((line.contains("if (") || line.contains("if("))) {
                                    total_complexity++;
                                    line_complexity = line_complexity + 1;
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    line_complexity = 0;
                                   

                                } else if (line.contains("catch(")) {
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    break;

                                } else {
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                }
                             
                            } else {
                                System.out.println("line cnc:" + line_complexity);
                                System.out.println(line);
                            }
                        }
                    } else if (line.contains("// ") || line.contains("/* ") || line.contains("//") || line.contains("/**")) {
                        System.out.println("line cnc:" + line_complexity);
                        System.out.println(line);
                        while (((line = reader.readLine()) != null)) {
                            if (!line.isEmpty()) {
                                line_no++;
                                System.out.println(line_no);

                                if (line.contains("if(") || line.contains("if (") || line.contains("for(") || line.contains("while(")) {
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    break;
                                } else if ((line.contains("*/") || line.contains(" */"))) {
                                    System.out.println("line cnc:" + line_complexity);
                                    System.out.println(line);
                                    break;
                                }
                                System.out.println("line cnc:" + line_complexity);
                                System.out.println(line);
                                break;
                            }
                        }

                    } else {
                        System.out.println("line cnc:" + line_complexity);
                        System.out.println(line);
                    }

                }

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total CNC Result: " + total_complexity);
        System.out.println("Total CNC line_count: " + line_no);

    }
}
