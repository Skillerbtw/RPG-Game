package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.character.Player;
import model.quest.Quest;
import model.quest.QuestState;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuestStorage {

    private static final String FILE_PATH = "quests.json";
    private static final Gson gson = new Gson();

    public static void saveQuests(Player player) {
        List<QuestData> dataList = new ArrayList<>();
        for (Quest q : player.getQuestManager().getAllQuests()) {
            dataList.add(new QuestData(q.getTitle(), q.getDescription(), q.getState().name()));
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(dataList, writer);
            System.out.println("💾 Quests gespeichert.");
        } catch (IOException e) {
            System.out.println("⚠️ Fehler beim Speichern der Quests: " + e.getMessage());
        }
    }

    public static void loadQuests(Player player) {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<QuestData>>() {}.getType();
            List<QuestData> loaded = gson.fromJson(reader, listType);

            for (QuestData data : loaded) {
                Quest quest = new Quest(data.title, data.description, null); // 🔸 Belohnung bleibt leer
                if ("ACTIVE".equals(data.state)) quest.start();
                else if ("COMPLETED".equals(data.state)) quest.complete(player);
                player.getQuestManager().addQuest(quest);
            }
            System.out.println("📂 Quests erfolgreich geladen.");
        } catch (IOException e) {
            System.out.println("⚠️ Keine gespeicherten Quests gefunden.");
        }
    }
}
