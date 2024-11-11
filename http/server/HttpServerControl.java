package http.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class HttpServerControl {

	private static HttpServer server;

	public static void init(int port, int pool) {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.setExecutor(Executors.newFixedThreadPool(pool));
			
			server.createContext("/testget", exc -> HttpHandlerControl.fnHandleRequestGet(exc));
			server.createContext("/testpost", exc -> HttpHandlerControl.fnHandleRequestPost(exc));
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

}
