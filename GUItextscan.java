import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class GUItextscan extends Application implements EventHandler<ActionEvent> {
	
	Button button;
	Button button2;
	
	public static void main(String[] args) throws Exception {
		
		launch(args);
		
	}
	@Override
	public void start (Stage primaryStage) throws Exception{
		primaryStage.setTitle("Word Counter Application");
		button = new Button("Print top 20 most used words");
		button2 = new Button("Print the count for random words");
		GridPane layout = new GridPane();

		
		
		button.setOnAction(this);
		button2.setOnAction(this);
		
		Scene scene = new Scene(layout, 500, 250);
		layout.addRow(0, button , button2);
		primaryStage.setScene(scene); 
		primaryStage.show();
		
	}
	@Override
	public void handle (ActionEvent event) {
		FileReader file = null;
		try {
			file = new FileReader("/Users/anantsinghbisht/Desktop/The Raven.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader read = new BufferedReader(file);    
		ArrayList<String> word = new ArrayList<String>();
		
		String str;
		try {
			while ((str=read.readLine())!=null) {
				str = str.replaceAll("\\p{IsPunctuation}","");
				String[] string = str.toLowerCase().split(" " ,100);
				for (String a : string) {
					word.add(a);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		
		if (event.getSource()==button) {
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
			
		}
		if (event.getSource()==button2) {
			String y;
			int x;
			for (int i=0; i<20; i++) {
				Random rand = new Random();
				y=word.get(rand.nextInt(word.size()));
				x=word.indexOf(y);
				System.out.println(y+ " : " + count.get(x));
			}
			
			
		}
		
	}
	
	
	
}
