import java.io.*;
import java.util.ArrayList;


public class textscan {
	
	public static void main(String[] args) throws Exception {
	
		FileReader file = new FileReader("/Users/anantsinghbisht/Desktop/The Raven.txt");
		BufferedReader read = new BufferedReader(file);    
		ArrayList<String> word = new ArrayList<String>();
		
		String str;
		while ((str=read.readLine())!=null) {
			str = str.replaceAll("\\p{IsPunctuation}","");
			String[] string = str.toLowerCase().split(" " ,100);
			for (String a : string) {
				word.add(a);
			}
		}
		
		read.close();
		
		ArrayList<Integer> count = new ArrayList<Integer>();
		for (int i=0; i<word.size(); i++) {
			int counts=1;
			for(int j=i+1; j<word.size(); j++){    
				if(word.get(i).equals(word.get(j))){    
                    counts++;    
                }
			}
			count.add(counts);
		}
		
		ArrayList<Integer> ordered_count = new ArrayList<Integer>();
		ordered_count = count;
		
		
		
		int temp;
		for (int i = 0; i < count.size(); i++) {     
            for (int j = i+1; j < count.size(); j++) {     
               if(ordered_count.get(i) > ordered_count.get(j)) {    
                   temp = ordered_count.get(i);    
                   ordered_count.set(i, ordered_count.get(j)); 
                   ordered_count.set(j, temp);    
               }     
            }     
        }    
//output
		int q=20;
		int y;
		int b=ordered_count.size();
		while (b>0) {
			y = count.indexOf(ordered_count.get(b-1));
			System.out.println(word.get(y) + " : " + count.get(y));
			q--;
			b--;
			if (q==0) {
				break;
			}
		}
//output ends		
		

	}
}
