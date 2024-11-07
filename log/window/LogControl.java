package log.window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogControl {

	private static String processName = "LogTestProgram";
	private static DateTimeFormatter dirFormatter = DateTimeFormatter.ofPattern("yyyyMM/dd");
	private static DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");

	public void LogWrite(String fnName, String data) {

		String currentDir = System.getProperty("user.dir");
		String datePath = LocalDate.now().format(dirFormatter).replace("/", "\\");

		File dir = new File(String.format("%s\\Log\\%s", currentDir, datePath));
		File file = new File(String.format("%s\\%s.log", dir.getPath(), processName));

		// 디렉토리, 파일 없으면 생성
		try {
			if (!dir.exists()) dir.mkdirs();
			if (!file.exists()) file.createNewFile();

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
				bw.write(String.format("[%s] %s: %s", LocalDateTime.now().format(logFormatter), fnName, data));
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
