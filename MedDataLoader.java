import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MedDataLoader {

	public static List<MedDataInterface> loadFile(String filePath) {
		List<MedDataInterface> list = new LinkedList<MedDataInterface>();
		File file = new File(filePath);
		Scanner fileScan = null;
		String[] data;
		if (!file.isFile()) {
			return null;
		}
		try {
			fileScan = new Scanner(file);
			while (fileScan.hasNext()) {
				data = fileScan.nextLine().split(",");
				list.add(new MedData(Integer.parseInt(data[1]), data[0], Double.parseDouble(data[2])));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} finally {
			fileScan.close();
		}
		return list;
	}

	public static List<MedDataInterface> loadAllFilesInDirectory(String directoryPath) {
		List<MedDataInterface> list = new LinkedList<MedDataInterface>();
		File dir = new File(directoryPath);
		if (!dir.isDirectory()) {
			return null;
		}
		File[] fileList = dir.listFiles();
		for (File file : fileList) {
			if (file.getName().substring(file.getName().length() - 4, file.getName().length()).equals(".csv")) {
				list.addAll(loadFile(directoryPath + file.getName()));
			}
		}

		return list;
	}

	/*
	public static boolean isWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isLetter(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	*/
	
	@Test
	public static void dataWrangler_TestMedDataFunctionality() {
		MedData data = new MedData(25, "Dan", 150.45);
		assertEquals(25, data.getAge());
		assertEquals("Dan", data.getName());
		assertEquals(150.45, data.getWeight());
		assertEquals("Dan, 25, 150.45", data.toString());
		assertTrue(data.equals(new MedData(25, "Dan", 150.45)));
	}
	
	@Test
	public static void dataWrangler_TestLoadFile() {
		List<MedDataInterface> file = loadFile("FILES/namelist_SHORT.csv");
		List<MedDataInterface> fakeFile = loadFile("thisfileaintreal");
		List<MedDataInterface> directory = loadFile("FILES/");
		assertEquals(50, file.size());
		assertEquals(null, fakeFile);
		assertEquals(null, directory);
	}
	
	@Test
	public static void dataWrangler_TestLoadAllFilesInDirectory() {
		List<MedDataInterface> fileList = loadAllFilesInDirectory("FILES/");
		List<MedDataInterface> file = loadAllFilesInDirectory("names.csv");
		assertEquals(686088, fileList.size());
		assertEquals(null, file);
	}
	
	public static void main(String[] args) {
		/*
		Random rand = new Random();
		try {
			Scanner fileScan = new Scanner(new File());
			PrintWriter output = new PrintWriter(new File());
			fileScan.useDelimiter(",|\\n");
			while (fileScan.hasNext()) {
				String temp = fileScan.next();
				if (isWord(temp)) {
					output.println(temp + "," + (rand.nextInt(90) + 5) + "," + (rand.nextDouble() * 198 + 22));
				}
			}
			output.close();
		} catch (FileNotFoundException e) {

		}
		*/
		dataWrangler_TestMedDataFunctionality();
		dataWrangler_TestLoadFile();
		dataWrangler_TestLoadAllFilesInDirectory();

	}
}
