package copy.a.file;

/* Copy.java
 copies one file to another.
 if the destination file exists the user is given the options
 to overwrite or append to the the existing file or enter a
 new file name or exit.
 */
import java.io.*;

class Copy {

    private static FileInputStream fin;
    private static FileOutputStream fout;
    private static boolean flag;

// creates a new file outputstream instance
    static FileOutputStream create(String s) throws IOException {
        flag = true;
        fout = new FileOutputStream(s);
        flag = false;
        return fout;
    }

// creates a file outputstream instance in append mode
    static FileOutputStream append(String s) throws IOException {
        return fout = new FileOutputStream(s, true);
    }

// displays message if program cannot copy a file
    static void nocopy() {
        System.out.println("No File Copied");
        System.out.println("Thank you for using this program....");
    }

    public static void main(String args[]) throws IOException {
        fin = null;
        fout = null;
        File f = null;
        int i = 0;
        char ch;
        boolean fl = false;
        try {
            fin = new FileInputStream(args[0]);
            f = new File(args[1]);
            if (f.isDirectory()) {
                args[1] += args[0];
                f = new File(args[1]);
            }
            if (f.exists())                                         //outer 
                {
                    if (args[0].equals(args[1])) {
                        //throws new MyException("File cannot be copied onto  itself ! ! ");
                    }
         }
         System.out.println("'" + args[1] + "' already exists!!");
                System.out.println();
                if (!f.canWrite()) {
                    System.out.println("'" + args[1] +"' is read-only. Access denied !!");
           System.out.println();
                } else {
                    System.out.println("Overwrite----------------> o");
                    System.out.println("Append to existing file--> a");
                    fl = true;
                }
                System.out.println("New destination file-----> n");
                System.out.println("Exit---------------------> any other key");
         ch = ReadVal.readChar();
                switch (ch) {                                          //switch

           case 'o':
                    case 'O':
                        if (fl) {
                            create(args[1]);
                        } else {
                            //throw new MyException("");
                        }
                        break;
                    case 'a':
                    case 'A':
                        if (fl) {
                            append(args[1]);
                        } else {
                            //throw new MyException("");
                        }
                        break;
                    case 'n':
                    case 'N':
                        String s;
                        System.out.print("enter new file name  :  ");
                        s = ReadVal.readString();
                        File f1 = new File(s);
                        if (f1.exists()) {
                            if (args[0].equals(s)) {
                                //throw new MyException("File cannot be copied onto itself !!");
               }
               if (!f1.canWrite()) {
                                //throw new MyException("'" + s + }"' is read-only. Access denied !!");
                            System.out.println("File exists!!");
                            System.out.print("Overwrite / Append / Exit ? ");
                            char c;
                            c = ReadVal.readChar();
                            if (c == 'o' || c == 'O') {
                                create(s);
                            } else if (c == 'a' || c == 'A') {
                                append(s);
                            } else {
                                nocopy();
                                System.exit(1);
                            }
                        }
                        if (fout == null) {
                            create(s);
                        }
                        break;
                        }
                    default:
                        nocopy();
                        return;
                 //switch
                
                 

                }
                if (fout == null) {
                    create(args[1]);
                }
            }
       
         //try 
       
     catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("Usage : java Copy <source file> <destination file >");
                    System.out.println("Required parameter(s) missing.");
                    return;
                }
        catch(FileNotFoundException fe){
       if(flag)
         System.out.println("Cannot copy to disk. Access denied!!");
       else
         System.out.println("Source file does not exist");
       nocopy();
       return;
     }

// read from source file & write to destination file
        try {
            while ((fin.read()) != -1) {
                fout.write((char) i);
//         i=fin.read();
            }
            fin.close();
            fout.close();
        } catch (IOException ie) {
        }
        System.out.println("File Copied Successfully");
        System.out.println("Thank you for using this program....");
    }
}

//**********************************************************************
//ReadVal.java
//import java.io.*;
class ReadVal {

    static int readInt() {
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            i = Integer.parseInt(br.readLine());
        } catch (IOException ie) {
            System.out.println(ie);
            i = 0;
        } catch (NumberFormatException ne) {
            System.out.println("idiot u were asked to enter a number !!!!");
            i = 0;
        }
        return i;
    }

    static String readString() {
        String s = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s = br.readLine();
        } catch (IOException ie) {
            System.out.println(ie);
            s = "";
        }
        return s;
    }

    static double readDouble() {
        double d = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            d = Double.parseDouble(br.readLine());
        } catch (IOException ie) {
            System.out.println(ie);
            d = 0;
        } catch (NumberFormatException ne) {
            System.out.println("idiot u were asked to enter a number !!!!");
            d = 0;
        }
        return d;
    }

    static float readFloat() {
        float f = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            f = Float.parseFloat(br.readLine());
        } catch (IOException ie) {
            System.out.println(ie);
            f = 0;
        } catch (NumberFormatException ne) {
            System.out.println("idiot u were asked to enter a number!!!!");
            f = 0;
        }
        return f;
    }

    static char readChar() {
        char c;
        try {
            c = readString().charAt(0);
        } catch (StringIndexOutOfBoundsException se) {
            c = 0;
        }
        return c;
    }
}

/*
 Title	copying a file
 Author	Shameek Ray
 Author Email	shameek.ray [at] rediffmail.com
 Description	this code lets one to copy one file into another with options like appending to a file or overwriting a file or creating a new file
 */
