package test;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class CodeFomat {
	public static void main(String[] args) {
		Random random = new Random();
		String sRand="";
		String ctmp = "";
		for(int i = 0;i<4;i++){
			String[] rBase = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
			int r1 = random.nextInt(3)+11;
			String str_r1 = rBase[r1];
			int r2;
			if(r1 == 13){
				r2 = random.nextInt(7);
			}else{
				r2 = random.nextInt(16);
			}
			String str_r2 = rBase[r2];
			int r3 = random.nextInt(6)+10;
			String str_r3 = rBase[r3];
			int r4;
			if(r3 == 10){
				r4 = random.nextInt(15);
			}else if(r3 == 15){
				r4 = random.nextInt(15);
			}else{
				r4 = random.nextInt(16);
			}
			String str_r4 = rBase[r4];
			byte[] bytes = new byte[2];
			String str_r12 = str_r1+str_r2;
			int tempLow = Integer.parseInt(str_r12,16);
			bytes[0] = (byte) tempLow;
			String str_r34 = str_r3+str_r4;
			int tempHigh = Integer.parseInt(str_r34,16);
			bytes[1] = (byte) tempHigh;
			ctmp = new String(bytes);
			sRand += ctmp;
		}
		System.out.println(sRand);
		
		 char[] words = new char[4];  
	      
	        for (int i = 0; i<words.length; i++) {  
	            words[i] = getRandomChar();  
	        }  
	          
	        System.out.println(words); 
	}
	 public static char getRandomChar() {  
	        String str = "";  
	        int hightPos;  
	        int lowPos;  
	  
	        Random random = new Random();  
	  
	        hightPos = (176 + Math.abs(random.nextInt(39)));  
	        lowPos = (161 + Math.abs(random.nextInt(93)));  
	  
	        byte[] b = new byte[2];  
	        b[0] = (Integer.valueOf(hightPos)).byteValue();  
	        b[1] = (Integer.valueOf(lowPos)).byteValue();  
	  
	        try {  
	            str = new String(b, "GB2312");  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	  
	        return str.charAt(0);  
	    }  
}
