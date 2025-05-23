package ai;

import com.google.gson.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoryBot {

    private static final String API_URL = "https://api.ai.rh-koeln.de/v1/chat/completions";
    private static final String API_KEY = "20996269-a9ff-4653-b7ae-c0f5cbdc25cd";

    public static String ask(String playerInput) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // GPT-kompatibles Prompting für NPC-Dialog
            String jsonInput = """
            {
              "model": "meta-llama-3-8b-instruct",
              "messages": [
                {"role": "system", "content": "Du bist ein weiser, mysteriöser NPC in einem mittelalterlichen Rollenspiel. Antworte kurz, poetisch und geheimnisvoll, aber dennoch verständlich und Zielgerichtet."},
                {"role": "user", "content": "%s"}
              ]
            }
            """.formatted(playerInput);

            // Anfrage senden
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            int status = conn.getResponseCode();
            InputStream stream = status >= 400 ? conn.getErrorStream() : conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            // Fehler anzeigen
            if (status >= 400) {
                return "❌ Fehler vom KI-Server (HTTP " + status + "):\n" + responseBuilder;
            }

            // JSON-Antwort verarbeiten mit Gson
            JsonObject json = JsonParser.parseString(responseBuilder.toString()).getAsJsonObject();
            JsonArray choices = json.getAsJsonArray("choices");
            JsonObject message = choices.get(0).getAsJsonObject().getAsJsonObject("message");
            String content = message.get("content").getAsString();

            return content.trim();

        } catch (IOException e) {
            return "❌ Netzwerkfehler: " + e.getMessage();
        } catch (Exception e) {
            return "⚠️ Die Verbindung zur KI ist fehlgeschlagen.";
        }
    }
}

