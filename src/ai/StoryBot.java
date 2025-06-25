package ai;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class StoryBot {

    private static final String API_URL = "https://api.ai.rh-koeln.de/v1/chat/completions";
    private static final String API_KEY = "20996269-a9ff-4653-b7ae-c0f5cbdc25cd";

    public static String ask(String playerInput, String systemPrompt) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // JSON sauber bauen:
            JsonObject root = new JsonObject();
            root.addProperty("model", "meta-llama-3-8b-instruct");

            JsonArray messages = new JsonArray();

            JsonObject sysMsg = new JsonObject();
            sysMsg.addProperty("role", "system");
            sysMsg.addProperty("content", systemPrompt);
            messages.add(sysMsg);

            JsonObject userMsg = new JsonObject();
            userMsg.addProperty("role", "user");
            userMsg.addProperty("content", playerInput);
            messages.add(userMsg);

            root.add("messages", messages);

            String jsonInput = root.toString();

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes());
            os.flush();
            os.close();

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            String full = response.toString();
            int start = full.indexOf("\"content\":\"") + 11;
            int end = full.indexOf("\"", start);
            return full.substring(start, end).replace("\\n", "\n");

        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ Die Verbindung zur KI ist fehlgeschlagen.";
        }
    }
}

