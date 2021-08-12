// polynomial hashing algorithm for checking if 2 strings are cyclic rotations
import java.util.*;
import java.io.*;

public class polyhash{
	
	
	static boolean cycRot(String pat, String txt)
	{
        if (pat.length()!=txt.length()){
            return false;
        } 
        pat=pat.toLowerCase();
        txt=txt.toLowerCase();
        int c=pat.length();
        int []a=new int[c];
        int []b=new int[c];
        for (int i=0;i<c;i++){
            a[i]=pat.charAt(i)-97;
            b[i]=txt.charAt(i)-97;
            
        }

		int i=0, j=0;
        
        boolean flag=false;
        for(;j<c;j++){
            if(a[i]==b[j]){
                int p=i+1;
                int q=j+1;
                flag=true;
                for(;p<c;p++,q++){
                    if(q==c){
                        q=0;
                    }
                    if(a[p]!=b[q]){
                        flag=false;
                        break;
                    }
                }
                
            }
        }
        return flag;

		
	}
	
	public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		
		System.out.println(cycRot(a, b)?a+" is a cyclic rotation of "+b: "not a cyclic rotation");
	}
}

