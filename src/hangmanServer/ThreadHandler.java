/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanServer;

/**
 *
 * @author Ahad
 */
import java.io.*;
import java.net.*;


public class ThreadHandler extends Thread {

    Socket sc;
    PrintWriter pr;

    public ThreadHandler(Socket sc) {
        this.sc = sc;
    }

    public void run() {
        try {
            InputStream in = sc.getInputStream();
            OutputStream out = sc.getOutputStream();

            int point = 0;

            Word w = new Word();
            while (true) {

                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String input = br.readLine();
                if (input.equals("end")) {
                    System.out.println("Game Stopped");
                    break;
                }
                if (input.equals("start")) {
                    System.out.println("Game Started");
                    pr = new PrintWriter(out, true);
                }
                String selectedWord, hints = "";
                int life = 10;

                //Picking word from dictionary

                selectedWord = w.wordSelect();
                selectedWord = selectedWord.toLowerCase();
                System.out.println(selectedWord);
                //Word Picked

                //Sending hints First time
                int length = selectedWord.length();
                while (length > 0) {
                    length--;
                    hints = hints.trim() + "-";
                }
                pr.println(hints);
                pr.println(life);

                //End Sending....

                //get letter from client and check

                char[] hintsarray = hints.toCharArray();
                char[] wordArray = selectedWord.toCharArray();
                length = selectedWord.length();
                while (true) {

                    String l = br.readLine();
                    l = l.toLowerCase();

                    try {
                        Thread.sleep(2500);
                    }
                    catch (InterruptedException e) {
                       System.out.print(e);
                    }

                    //System.out.print(l);

                    if (selectedWord.equals(l)) {
                        point++;
                        pr.println("Correct");
                        pr.println(selectedWord);
                        pr.println(point);
                        break;
                    }
                    if (l.length() > 1) 
                    {
                        life--;
                        pr.println("Incorrect");
                        pr.println(life);
                        if (life == 0) {
                            point--;
                            pr.println(point);
                            pr.println(selectedWord);
                            break;
                        }

                    } else if (l.length() == 1) {
                        char[] ca = l.toCharArray();
                        char c = ca[0];
                        //System.out.print(c);

                        int i;
                        boolean flag = false, finish = true;
                        for (i = 0; i < length; i++) {
                            if (wordArray[i] == c) {
                                hintsarray[i] = c;
                                flag = true;
                            }
                            if (hintsarray[i] == '-') {
                                finish = false;
                            }
                        }
                        hints = new String(hintsarray);
                        //System.out.println(hints);

                        // System.out.print(c);

                        if (finish == true) {
                            pr.println("Done");
                            pr.println(hints);
                            point++;
                            pr.println(point);

                            break;
                        }


                        if (flag == false) {
                            life--;
                            pr.println("NO");
                            pr.println(life);
                            if (life == 0) {
                                point--;
                                pr.println(point);
                                pr.println(life);
                                pr.println(selectedWord);
                                break;
                            }

                        }   else if (flag == true) {
                            pr.println("YES");
                            pr.println(hints);
                        }
                    }
                }

                //end
            }
            sc.close();
            in.close();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
