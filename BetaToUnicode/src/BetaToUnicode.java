import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import edu.unc.epidoc.transcoder.TransCoder;

/**
 * 
 */

/**
 * @author williamstewart
 *
 */
public class BetaToUnicode{
	static ArrayList<String> filePathList = new ArrayList<String>();
	static ArrayList<String> fileNameList = new ArrayList<String>();
	
	public static void convertBetaToUnicode(String folderLocation){
		try{
			getAllFiles(folderLocation);
		}
		catch(Exception e){
			System.out.println("Couldn't retrieve files");
		}
		
		try{
			String result = "";
			TransCoder tc = new TransCoder("BetaCode","UnicodeC");
			for(int i = 0; i<filePathList.size()-1;i++){
				result = tc.getString(new File(filePathList.get(i)));
				
				File output = new File("/Users/williamstewart/Desktop/UnicodeClassics/" + fileNameList.get(i));
				FileWriter writer = new FileWriter(output);
				writer.write(result);
				writer.flush();
				writer.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void getAllFiles(String folderLocation){
		try{
		Files.walk(Paths.get(folderLocation)).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		        filePathList.add(filePath.toString());
		        fileNameList.add(convertToTxt(filePath.getFileName().toString()));
		    }
		});
		}
		catch(Exception e){
			System.out.println("Invalid file path");
		}
	}
	
	public static String convertToTxt(String fileName){
		return fileName.replaceAll(".xml",".txt");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try{
				convertBetaToUnicode("/Users/williamstewart/Desktop/BetaClassics");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
