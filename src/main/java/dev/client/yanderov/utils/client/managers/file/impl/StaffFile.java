package dev.client.yanderov.utils.client.managers.file.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.client.yanderov.common.repository.staff.StaffRepository;
import dev.client.yanderov.utils.client.managers.file.ClientFile;
import dev.client.yanderov.utils.client.managers.file.exception.FileLoadException;
import dev.client.yanderov.utils.client.managers.file.exception.FileSaveException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class StaffFile extends ClientFile {
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

   public StaffFile() {
      super("Staff");
   }

   public void loadFromFile(File directory) throws FileLoadException {
      File file = new File(directory, this.getName() + ".json");
      if (file.exists()) {
         try {
            String content = Files.readString(file.toPath());
            if (!content.isEmpty()) {
               List<StaffRepository.Staff> staff = (List)GSON.fromJson(content, (new TypeToken() {
               }).getType());
               StaffRepository.getStaff().clear();
               StaffRepository.getStaff().addAll(staff);
            }
         } catch (IOException e) {
            throw new FileLoadException("ÐÐµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ Ð¿Ñ€Ð¾Ñ‡Ð¸Ñ‚Ð°Ñ‚ÑŒ Ñ„Ð°Ð¹Ð» Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°", e);
         }
      }
   }

   public void saveToFile(File directory) throws FileSaveException {
      File file = new File(directory, this.getName() + ".json");

      try {
         Files.writeString(file.toPath(), GSON.toJson(StaffRepository.getStaff()), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
      } catch (IOException e) {
         throw new FileSaveException("ÐÐµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ ÑÐ¾Ñ…Ñ€Ð°Ð½Ð¸Ñ‚ÑŒ Ñ„Ð°Ð¹Ð» Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°", e);
      }
   }
}

