package log.window;

public class Main {
	
	LogControl logControl = new LogControl();

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		logControl.LogWrite("Main", "test");
	}

}
