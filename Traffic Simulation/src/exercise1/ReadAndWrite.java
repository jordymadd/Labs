package exercise1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadAndWrite {

	private int simTime;
	private int carGenInput;
	private int driveStyleBlue;
	private int driveStylePurple;
	private int driveStyleRed;
	private int driveStyleYellow;


	public int getCarGenInput() {
		return carGenInput;
	}


	public void setCarGenInput(int carGenInput) {
		this.carGenInput= carGenInput*60; 
	}


	public int getDriveStyleBlue() {
		return driveStyleBlue;
	}


	public void setDriveStyleBlue(int driveStyleBlue) {
		this.driveStyleBlue = driveStyleBlue;
	}


	public int getDriveStylePurple() {
		return driveStylePurple;
	}


	public void setDriveStylePurple(int driveStylePurple) {
		this.driveStylePurple = driveStylePurple;
	}


	public int getDriveStyleRed() {
		return driveStyleRed;
	}


	public void setDriveStyleRed(int driveStyleRed) {
		this.driveStyleRed = driveStyleRed;
	}


	public int getDriveStyleYellow() {
		return driveStyleYellow;
	}


	public void setDriveStyleYellow(int driveStyleYellow) {
		this.driveStyleYellow = driveStyleYellow;
	}
	public int getSimTime() {
		return simTime;
	}


	public void setSimTime(int simTime) {
		this.simTime = simTime*60;
		
	}


	static Scanner scan = new Scanner(System.in); 
	public void write(String[] text) {
		try{
			FileWriter writer = new FileWriter("src/runSettings.txt", false); 
			BufferedWriter bWriter = new BufferedWriter(writer); 
			//			for(int i = 0; i < text.length; i++){ 
			//				System.out.println("Type a line. ");
			//				String input = scan.nextLine();
			//				if(input.equals("exit")) break; 
			//				bWriter.write(text[i]);
			//				bWriter.newLine();
			//				bWriter.flush();
			//			}
			writeInt(bWriter, simTime);
			writeInt(bWriter,carGenInput);
			writeInt(bWriter,driveStyleBlue);
			writeInt(bWriter,driveStylePurple);
			writeInt(bWriter,driveStyleRed);
			writeInt(bWriter,driveStyleYellow);
			bWriter.close();
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();

		}

	}

	private void writeInt(BufferedWriter bWriter, int n) throws IOException {
		bWriter.write(""+n);
		bWriter.newLine();
		bWriter.flush();		
	}

	private int readInt(BufferedReader bReader) throws IOException {
		String s = bReader.readLine();
		int n = Integer.parseInt(s);
		return n;
	}

	public void read() {
		try{ 
			FileReader fReader = new FileReader("src/runSettings.txt"); 
			//runSettings.properties is a relative path, you could also give it an absolute path. 
			BufferedReader bReader = new BufferedReader(fReader); 
			//			while(bReader.ready()){
			//				String s = bReader.readLine(); 
			//				System.out.println(s);
			//			}
			simTime = readInt(bReader);
			carGenInput = readInt(bReader);
			driveStyleBlue = readInt(bReader);
			driveStylePurple = readInt(bReader);
			driveStyleRed = readInt(bReader);
			driveStyleYellow = readInt(bReader);
			bReader.close(); 
			fReader.close(); 
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
