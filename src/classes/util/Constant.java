package classes.util;

public class Constant {
	public class dataPath {
		public static final String data_BaseDir = defaultDirectory() + "/QMS/";
		public static final String accounts_File = data_BaseDir + "/Accounts";
		public static final String SubjectList_File = data_BaseDir + "/SubjectList";
		public static final String Exams_Dir = data_BaseDir + "/Exams/";
		public static final String ExamRecords_Dir = data_BaseDir + "/ExamRecords/";
		public static final String QuestionBanks_Dir = data_BaseDir + "/QuestionBanks/";

		private static String defaultDirectory() {
			String OS = System.getProperty("os.name").toUpperCase();
			if (OS.contains("WIN"))
				return System.getenv("APPDATA");
			else if (OS.contains("MAC"))
				return System.getProperty("user.home") + "/Library/Application " + "Support";
			else if (OS.contains("NUX"))
				return System.getProperty("user.home");
			return System.getProperty("user.dir");
		}
	}
}
