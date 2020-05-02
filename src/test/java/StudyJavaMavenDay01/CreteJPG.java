package StudyJavaMavenDay01;

import java.io.*;

public class CreteJPG {
    public static void main(String[] args) throws IOException {
        String frompath="C:\\Users\\ASUS\\Pictures\\Camera Roll\\电影.jpg";
        String topath="C:\\Users\\ASUS\\Pictures\\Saved Pictures\\电影.jpg";
        FileInputStream fileInputStream=new FileInputStream(new File(frompath));
        FileOutputStream fileOutputStream=new FileOutputStream(new File(topath));
        int a= 0;
        while((a=fileInputStream.read())!=-1){
            fileOutputStream.write(a);
        }
        if (fileInputStream!=null){
            fileInputStream.close();
        }
        if (fileOutputStream!=null){
            fileOutputStream.close();
        }
        System.out.println("复制文件成功");
    }

}
