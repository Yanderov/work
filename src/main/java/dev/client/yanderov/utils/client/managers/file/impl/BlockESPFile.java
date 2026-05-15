package dev.client.yanderov.utils.client.managers.file.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dev.client.yanderov.common.repository.box.BoxESPRepository;
import dev.client.yanderov.utils.client.managers.file.ClientFile;
import dev.client.yanderov.utils.client.managers.file.exception.FileLoadException;
import dev.client.yanderov.utils.client.managers.file.exception.FileSaveException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_7923;

public class BlockESPFile extends ClientFile {
   private final BoxESPRepository repository;

   public BlockESPFile(BoxESPRepository repository) {
      super("BlockESP");
      this.repository = repository;
   }

   public void saveToFile(File path) throws FileSaveException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      File file = new File(path, this.getName() + ".json");

      try {
         FileWriter writer = new FileWriter(file);

         try {
            List<BlockESP> blocksList = new ArrayList();
            this.repository.blocks.forEach((block, color) -> blocksList.add(new BlockESP(block.method_63499(), color)));
            gson.toJson(blocksList, writer);
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
            BlockESP[] blockESP = (BlockESP[])gson.fromJson(reader, BlockESP[].class);
            this.repository.blocks.clear();
            Arrays.asList(blockESP).forEach((esp) -> class_7923.field_41175.method_10220().filter((type) -> type.method_63499().equals(esp.block)).findFirst().ifPresent((type) -> this.repository.blocks.put(type, esp.color)));
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

   private static record BlockESP(String block, int color) {
   }
}

