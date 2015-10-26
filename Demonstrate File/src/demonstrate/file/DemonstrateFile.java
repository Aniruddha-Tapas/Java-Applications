package demonstrate.file;

import java.io.File;

class DemonstrateFile {

    static void p(String s) {
        System.out.println(s);
    }

    public static void main(String args[]) {
        File f1 = new File("C://Users//ANIRUDDHA//Desktop//ToGet.txt");
        p("FILE NAME:" + f1.getName());
        p("PATH NAME:" + f1.getPath());
        p("ABSOLUTE PATH:" + f1.getAbsolutePath());
        p(f1.exists() ? "exists" : "does not exist");
        p(f1.canWrite() ? "is writable" : "is not writeable");
        p(f1.canRead() ? "is readable" : "is not readable");
        p("is" + (f1.isDirectory() ? "" : "not" + "a directory"));
        p(f1.isFile() ? "is normal file" :"might be a named pipe ");
                p(f1.isAbsolute() ? "is absolute" : "is not absloute");
        p("file last modified" + f1.lastModified());
        p("file size:" + f1.length() + "bytes");
    }
}

