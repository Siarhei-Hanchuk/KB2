package by.siarhei.kb2.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Response;

public class DebugLogger {
    public static void logRequest(Request req) {
        Gson gson = new GsonBuilder().create();
        System.out.println("---------------------------");
        System.out.print("REQ: ");
        System.out.println(gson.toJson(req));
    }

    public static void logView(Response view) {
        Gson gson = new GsonBuilder().create();
        System.out.print("RESP: ");
        System.out.println(gson.toJson(view));
    }
}
