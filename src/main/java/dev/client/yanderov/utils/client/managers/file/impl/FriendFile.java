package dev.client.yanderov.utils.client.managers.file.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dev.client.yanderov.common.repository.friend.Friend;
import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.utils.client.managers.file.ClientFile;
import dev.client.yanderov.utils.client.managers.file.exception.FileLoadException;
import dev.client.yanderov.utils.client.managers.file.exception.FileSaveException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FriendFile extends ClientFile {
   public FriendFile() {
      super("Friends");
   }

   public void saveToFile(File path) throws FileSaveException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      File file = new File(path, this.getName() + ".json");

      try {
         FileWriter writer = new FileWriter(file);

         try {
            gson.toJson(FriendUtils.getFriends(), writer);
         } catch (Throwable var8) {
            try {
               writer.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         writer.close();
      } catch (IOException | JsonIOException e) {
         throw new FileSaveException(String.format("Failed to save %s to file", this.getName()), e);
      }
   }

   public void loadFromFile(File path) throws FileLoadException {
      Gson gson = new Gson();
      File file = new File(path, this.getName() + ".json");

      try {
         FileReader reader = new FileReader(file);

         try {
            Friend[] friends = (Friend[])gson.fromJson(reader, Friend[].class);
            FriendUtils.clear();
            FriendUtils.getFriends().addAll(Arrays.asList(friends));
         } catch (Throwable var8) {
            try {
               reader.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         reader.close();
      } catch (IOException e) {
         throw new FileLoadException(String.format("Failed to load %s from file", this.getName()), e);
      } catch (JsonSyntaxException e) {
         throw new FileLoadException(String.format("JSON syntax error, %s config cannot be loaded", this.getName()), e);
      } catch (JsonIOException e) {
         throw new FileLoadException(String.format("JSON IO error, %s config cannot be loaded", this.getName()), e);
      }
   }
}

