package http.server;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		HttpServerControl.init(8888, 10);
		HttpServerControl.fnStartHttpServer();
		// HttpServerControl.fnStopHttpServer();
    }

}
