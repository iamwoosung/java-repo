package http.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class HttpServerControl {
	
	private static HttpServer server;
	
	public static void init(int port, int pool) {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.setExecutor(Executors.newFixedThreadPool(pool));
			server.createContext("/", exc -> handleRequest(exc));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fnStartHttpServer() {
		server.start();
		System.out.println("listen server: 8080");
	}
	
	public static void fnStopHttpServer() {
		server.stop(0);
		System.out.println("stop server: 8080");
	}
	
	// HTTP 요청을 처리하는 메소드
    private static void handleRequest(HttpExchange exc) throws IOException {
    	System.out.println("asdasdasd");
        String response = "안녕하세요, HTTP 서버에 오신 것을 환영합니다!";
        exc.sendResponseHeaders(200, response.length());
        
        OutputStream os = exc.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
