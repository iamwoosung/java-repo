package http.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class HttpHandlerControl {
	
	enum HttpMethod {
		GET, POST, UNKNOWN;
	}
	
	public static void fnHandleRequestGet(HttpExchange exc) throws IOException {
		if (getHttpMethod(exc.getRequestMethod()) != HttpMethod.GET) {
			// 405 처리해도 됨
            return;
		}
		fnPrintRequestInfo(exc);
		String content = "Hello World!";
		byte[] bytes = content.getBytes();
		exc.sendResponseHeaders(200, bytes.length);

		OutputStream outputStream = exc.getResponseBody();
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}
	
	public static void fnHandleRequestPost(HttpExchange exc) throws IOException {
		if (getHttpMethod(exc.getRequestMethod()) != HttpMethod.POST) {
			// 405 처리해도 됨
			return;
		}
		fnPrintRequestInfo(exc);
		
		String content = "Hello World!";
		byte[] bytes = content.getBytes();
		exc.sendResponseHeaders(200, bytes.length);

		OutputStream outputStream = exc.getResponseBody();
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}
	
	private static void fnPrintRequestInfo(HttpExchange exc) throws IOException {
    	String method = exc.getRequestMethod();
        String path = exc.getRequestURI().getPath();
        String query = exc.getRequestURI().getQuery();

        Headers reqHeaders = exc.getRequestHeaders();
        StringBuilder headersString = new StringBuilder();
        for (String key : reqHeaders.keySet()) {
            List<String> values = reqHeaders.get(key);
            headersString.append(key).append(": ").append(values).append("\n");
        }
        String headers = headersString.toString();

        InputStream inputStream = exc.getRequestBody();
        String body = new String(inputStream.readAllBytes());
        
        DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    	
        System.out.println(String.format(
        		"Datetime: %s\n"
        		+ "Method: %s\n"
        		+ "Path: %s\n"
        		+ "Query: %s\n"
        		+ "%s"
        		+ "body: %s\n"
        		, LocalDateTime.now().format(logFormatter), method, path, query, headers, body));
    }
	
	private static HttpMethod getHttpMethod(String method) {
        switch (method.toUpperCase()) {
            case "GET":
                return HttpMethod.GET;
            case "POST":
                return HttpMethod.POST;
            default:
                return HttpMethod.UNKNOWN;
        }
    }

}
