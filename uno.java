import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class uno {
    static Scanner s = new Scanner(System.in);
    static Random r = new Random();
    static int dc = 0, roc = 1;
    static String[] p1 = new String[4];
    static String[] p2 = new String[4];
    static String[] p3 = new String[4];
    static String[] p4 = new String[4];
    static String playcard;

    public static void main(String[] args) {
        int ai = 0, gc1 = 0;
        coloursel(ai, gc1);
    }

    public static void coloursel(int ai, int gc1) {
        int rc = 1, bc = 1, gc = 1, yc = 1;
        if (gc1 == 178) {
            rc = 0;
            bc = 0;
            gc = 0;
            yc = 0;
        }

        if (gc1 == 178) {
            dc = 15;
        }
        while (dc < 16) {
            //System.out.println("dc is " + dc);
            int ra = r.nextInt(4);
            //System.out.println("random selection is  " + ra);
            String col = "";
            if (ra == 0 && rc <= 4) {                   //red           //y4=   g4=   b4=   r4=
                col = "r";
                rc++;
            } else if (ra == 1 && bc <= 4) {            //blue          //rt 16
                col = "b";
                bc++;
            } else if (ra == 2 && yc <= 4) {            //yellow
                col = "y";
                yc++;
            } else if (ra == 3 && gc <= 4) {            //green
                col = "g";
                gc++;
            }
            //System.out.println("the colour is " + col);
            if (col.equals("r") || col.equals("g") || col.equals("b") || col.equals("y")) {
                if (dc == 4 || dc == 8 || dc == 12 || dc == 16 || dc == 0) {
                    ai = 0;
                } else {
                    ai++;
                }
                ctype(col, ai, p1, p2, p3, p4, gc1);
            }
        }
    }

    public static void ctype(String col, int ai, String[] p1, String[] p2, String[] p3, String[] p4, int gc1) {
        int oc = 1, tc = 1, thc = 1, fc = 1, ot = 0;
        if (gc1 == 178) {
            oc = 0;
            tc = 0;
            thc = 0;
            fc = 0;
            ot = 1;
        }
        int ran = r.nextInt(3);
        if (ran == 0 && oc <= 4) {                      //one oc
            col = col + "1";
            oc++;
            // System.out.println("the colour and no is " + col);
        } else if (ran == 1 && tc <= 4) {                      //two   tc
            col = col + "2";
            // System.out.println("the colour and no is " + col);
            tc++;
        } else if (ran == 2 && thc <= 4) {                      //three    thc
            col = col + "3";
            // System.out.println("the colour and no is " + col);
            thc++;
        } else if (ran == 3 && fc <= 4) {                      //four     fc
            col = col + "4";
            // System.out.println("the colour and no is " + col);
            fc++;
        }
        //System.out.println("dc is "+ dc);
        //System.out.println("ai is"+ai);
        if (dc < 4 && gc1 != 178) {
            p1[ai] = col;
            dc++;
            //System.out.println("stage 1 works");
        } else if (dc > 3 && dc < 8 && gc1 != 178) {
            p2[ai] = col;
            dc++;
            //System.out.println("stage 2 works");
        } else if (dc > 7 && dc < 12 && gc1 != 178) {
            p3[ai] = col;
            dc++;
            //System.out.println("stage 3 works");
        } else if (dc >= 12 && dc <= 16 && gc1 != 178) {
            p4[ai] = col;
            dc++;
            //System.out.println("stage 4 works");
        } else if (gc1 == 178) {
            System.out.println("play card is " + col);
            dc++;
        }
        if (dc >= 16 && gc1 != 178) {
            System.out.println("here is the deck of player 1" + Arrays.toString(p1));
            System.out.println();
            System.out.println("here is the deck of player 2" + Arrays.toString(p2));
            System.out.println();
            System.out.println("here is the deck of player 3" + Arrays.toString(p3));
            System.out.println();
            System.out.println("here is the deck of player 4" + Arrays.toString(p4));
            System.out.println();
        }
        //System.out.println("ot is "+ot);
        if (dc >= 16 && ot != 1) {
            gstart(ai);
        } else if (gc1 == 178) {
            pstart(p1, p2, p3, p4, col);
        }
    }

    public static void gstart(int ai) {
        //System.out.println("gstart works");
        int gc1 = 178;
        coloursel(ai, gc1);
    }

    public static void pstart(String[] p1, String[] p2, String[] p3, String[] p4, String col) {
        boolean po = true;
        int chanceno = 0,turnstate=1;;
        String inin="  ";
        while (po == true) {
            if (turnstate > 4) {
                turnstate = 1;
            }
            System.out.println("turnstate "+turnstate);
            System.out.println("chance of player " + turnstate);
            System.out.println("enter row no- ");
            int in = s.nextInt();
            if (turnstate == 1) {
                inin = p1[in];
                System.out.println("chance of p1");
            } else if (turnstate == 2) {
                inin = p2[in];
            } else if (turnstate == 3) {
                inin = p3[in];
            } else if (turnstate == 4) {
                inin = p4[in];
            }
            System.out.println(inin);
            char atcolpc = col.charAt(0);
            char atnopc = col.charAt(1);
            char atcolgc =inin.charAt(0);
            char atnogc = inin.charAt(1);
            if (chanceno == 0) {
                if (atcolpc == atcolgc || atnopc == atnogc) {
                    System.out.println("turnstate@if " + turnstate);
                    playcard = inin;
                    roc++;
                    turnstate++;
                    chanceno++;
                }
                else{
                    turnstate++;
                    System.out.println("u gave wrong");
                }
                }
                else{
                    char atnoplc = playcard.charAt(1);
                    char atcolplc = playcard.charAt(0);
                    if (atcolplc == atcolgc || atnoplc == atnogc) {
                        playcard = inin;
                        roc++;
                        turnstate++;
                        chanceno++;
                    }
                    else{
                        turnstate++;
                        System.out.println("u gave wrong");
                    }
                }
            }
        }
    }
