//Author Vikas Mangal
package lab10;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Lab10 {
    static String addspace(String s,int n){
        int l=s.length();
        for(int i=l;i<=n;i++){
            s+=" ";
        }
        return s;
    }
    public static void main(String[] args) throws IOException {
        FileReader f1 = new FileReader("input.c");
        BufferedWriter f2 = new BufferedWriter( new FileWriter("output.txt"));
        String text="";
        int temp;
        char cc;
        boolean flag=false;
        HashMap<String,Integer> hm=new HashMap<String,Integer>();
        while((temp = f1.read())!=-1){
            if(!flag && (char)temp=='{'){
                flag=true;
            }else if(flag){
                cc=(char)temp;
                text=text+cc;
            }
        }
        text=text.substring(0,text.length()-2);
        text=text.replaceAll("\\s+"," ");
        String pattern= "(int|float|double|for|if|while|else|char|return)";
        Pattern p = Pattern.compile(pattern);
        Matcher m=p.matcher(text);
        while(m.find()){
            int tt;
            if(hm.get(m.group(1))!=null){
                tt=hm.get(m.group(1));
            }else{
                tt=0;
            }
            hm.put(m.group(1),tt+1);
        }
        String ft="";
        int l=text.length();
        for(int i=0;i<l;i++){
            int pp=(int)text.charAt(i);
            if(!(((pp>=48 && pp<=57) ||( pp>=65 && pp<=80) || (pp>=97 && pp<=122)) || pp==32)){
                ft="";
                char rr,re=(char)pp;
                if(re=='+' || re=='-' || re=='>' || re=='<' || re=='='){
                    rr=text.charAt(i+1);
                    if(re==rr){
                        ft=ft+rr;
                        i++;
                    }
                }
                if(re=='['){
                    ft=ft+re;
                    pp=']';
                }
                if(re==']'){
                    continue;
                }
                ft=ft+(char)pp;
                int tt ;
                if( hm.get(ft)!=null){
                    tt=hm.get(ft);
                }else{
                    tt=0;
                }
                hm.put(ft,tt+1);
            }
        }
        int count=0,sum=0;
        f2.write("S.No            Operater        Count\n\n");f2.newLine();f2.newLine();
        for(Map.Entry mm:hm.entrySet()){
            count++;   
            sum+=(int)mm.getValue();
            String ss = addspace(mm.getKey().toString(),5);
            String formatted = String.format("%02d", count);
            f2.write(formatted+"                   "+ss+"            "+mm.getValue());
            f2.newLine();
            f2.newLine();
        }
        f2.write("Total         Uniqe\n\n");
        f2.newLine();f2.newLine();
        f2.write(sum+"              "+count+"\n");f2.newLine();f2.newLine();
        StringTokenizer st=new StringTokenizer(text,"(,;)+-{*/}[]=<> ");
        String ret="";
        String rte="";
         HashMap<String,Integer> ht=new HashMap<String,Integer>();
        while(st.hasMoreTokens()){
            ret=st.nextToken();
            boolean tlag=false;
            for(Map.Entry mm:hm.entrySet()){
                if(ret.equals(mm.getKey())){
                    tlag=true;
                    break;
                }
            }
            if(!tlag){
           		rte+=ret;
                if(ht.get(ret)!=null){
                    ht.put(ret,ht.get(ret)+1);
                }else{
                    ht.put(ret,1);
                }
            }
        }
        count=0;sum=0;
        f2.write("S.No              Operands           Count\n\n");f2.newLine();f2.newLine();
        for(Map.Entry mm:ht.entrySet()){
            count++;   
            sum+=(int)mm.getValue();
            String ss=addspace(mm.getKey().toString(),5);
            String formatted = String.format("%02d", count);
            f2.write(formatted+"                   "+ss+"            "+mm.getValue());
            f2.newLine();
            f2.newLine();
        }
        f2.write("Total         Uniqe\n\n");f2.newLine();f2.newLine();
        f2.write(sum+"              "+count+"\n");
        f2.close();
    }
    
}
