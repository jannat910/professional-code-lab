
import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			System.out.println("Please provide a command: a, r, ?, +, or c");
			return;
		}

		Constants obj = new Constants();

		String fileContents = LoadData(Constants.StudentList);

		// Check arguments
		if (args[0].equals(obj.ShowAll)) {

			System.out.println("Loading data ...");

			try {
				String words[] = fileContents.split(obj.StudentEntryDelimiter);
				for (String word : words) {
					System.out.println(word);
				}
			} catch (Exception e) {

			}

			System.out.println("Data Loaded.");

		}

		else if (args[0].equals(obj.ShowRandom)) {

			System.out.println("Loading data ...");

			try {
				String words[] = fileContents.split(obj.StudentEntryDelimiter);
				Random random = new Random();
				int randomIndex = random.nextInt(0, words.length);
				System.out.println(words[randomIndex]);
			} catch (Exception e) {

			}
			System.out.println("Data Loaded.");

		}

		else if (args[0].contains(obj.AddEntry)) {

			System.out.println("Loading data ...");

			try {
				BufferedWriter filestream = new BufferedWriter(
						new FileWriter("students.txt", true));
				String argValue = args[0].substring(1);
				Date date = new Date();
				String dateFormateObj = "dd/mm/yyyy-hh:mm:ss a";
				DateFormat dateFormat = new SimpleDateFormat(dateFormateObj);
				String formateDate = dateFormat.format(date);
				filestream.write(", " + argValue + "\nList last updated on " + formateDate);
				filestream.close();
			} catch (Exception e) {

			}

			System.out.println("Data Loaded.");

		}

		else if (args[0].contains(obj.FindEntry)) {

			System.out.println("Loading data ...");

			String[] words = fileContents.split(obj.StudentEntryDelimiter);
			String argValue = args[0].substring(1);
			int indexLocation = -1;

			for (int idx = 0; idx < words.length; idx++) {
				if (words[idx].trim().equals(argValue)) {
					indexLocation = idx;
					break;
				}
			}

			if (indexLocation >= 0) {
				System.out.println(String.format("We found it", argValue));
			}

			else {

				System.out.println(String.format("Not Found", argValue));
			}

			System.out.println("Data Loaded.");

		}

		else if (args[0].contains(obj.ShowCount)) {

			System.out.println("Loading data ...");

			char[] words = fileContents.toCharArray();
			System.out.println(String.format("%d words found", words.length));

			System.out.println("Data Loaded.");

		}
	}

	public static String LoadData(String fileName) {
		BufferedReader fileStream = null;
		try {
			fileStream = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("students.txt")));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		String reader = null;
		try {
			reader = fileStream.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return reader;
	}

}