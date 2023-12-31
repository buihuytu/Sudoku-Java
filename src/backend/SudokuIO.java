package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SudokuIO {
	public static int[][] readFile(String fileName){
		int[][] input = new int[9][9];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/problems/"+fileName+"/"));
			int i = 0;
			while(br.ready() && i < 9) {
				String[] line = br.readLine().split(" ");
				for(int j = 0; j < 9; j++) {
					input[i][j] = Integer.parseInt(line[j]);
				}
				i++;
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return input;
	}
	
	public static int[][] getRandomGrid(){
		Path dir = Paths.get("src/problems/");
		int i = 0;
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
			for(Path p : stream) {
				i++;
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(i);
		String fileName = "problem " + (int)(Math.random() * i + 1);
		return readFile(fileName);
	}
}
