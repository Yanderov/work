package dev.client.yanderov.utils.client.managers.file;

import dev.client.yanderov.utils.client.logs.Logger;
import dev.client.yanderov.utils.client.managers.file.exception.FileLoadException;
import dev.client.yanderov.utils.client.managers.file.exception.FileSaveException;
import dev.client.yanderov.utils.client.managers.file.impl.ModuleFile;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FileController {
   private final List clientFiles;
   private final File directory;
   private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

   public FileController(List clientFiles, File directory) {
      this.clientFiles = clientFiles;
      this.directory = directory;
      this.startAutoSave();
   }

   public void startAutoSave() {
      Logger.info("Auto-save system started!");
      this.scheduler.scheduleAtFixedRate(() -> {
         try {
            Logger.info("Saving with auto-save.");
            this.saveFiles();
         } catch (FileSaveException e) {
            Logger.error("Failed to auto-save files: " + e.getMessage());
         }

      }, 1L, 1L, TimeUnit.MINUTES);
   }

   public void stopAutoSave() {
      Logger.info("Auto-save shutdown!");
      this.scheduler.shutdown();

      try {
         if (!this.scheduler.awaitTermination(1L, TimeUnit.MINUTES)) {
            this.scheduler.shutdownNow();
         }
      } catch (InterruptedException var2) {
         this.scheduler.shutdownNow();
      }

   }

   public void saveFiles() throws FileSaveException {
      if (this.clientFiles.isEmpty()) {
         Logger.warn("No files to save from directory: " + this.directory.getPath());
      } else {
         for(ClientFile clientFile : this.clientFiles) {
            try {
               clientFile.saveToFile(this.directory);
               String var10000 = clientFile.getName();
               Logger.info("Successfully saved file: " + var10000 + " to " + this.directory.getPath());
            } catch (FileSaveException e) {
               throw new FileSaveException("Failed to save file: " + clientFile.getName(), e);
            }
         }

      }
   }

   public void loadFiles() throws FileLoadException {
      if (this.clientFiles.isEmpty()) {
         Logger.warn("No files to load from directory: " + this.directory.getPath());
      } else {
         for(ClientFile clientFile : this.clientFiles) {
            try {
               clientFile.loadFromFile(this.directory);
               String var10000 = clientFile.getName();
               Logger.info("Successfully loaded file: " + var10000 + " from " + this.directory.getPath());
            } catch (FileLoadException e) {
               throw new FileLoadException("Failed to load file: " + clientFile.getName(), e);
            }
         }

      }
   }

   public void saveFile(String fileName) throws FileSaveException {
      for(ClientFile clientFile : this.clientFiles) {
         if (clientFile instanceof ModuleFile) {
            try {
               clientFile.saveToFile(this.directory, fileName);
               Logger.info("Successfully saved file: " + fileName + " to " + this.directory.getPath());
            } catch (FileSaveException e) {
               throw new FileSaveException("Failed to save file: " + fileName, e);
            }
         }
      }

   }

   public void loadFile(String fileName) throws FileLoadException {
      for(ClientFile clientFile : this.clientFiles) {
         if (clientFile instanceof ModuleFile) {
            try {
               clientFile.loadFromFile(this.directory, fileName);
               Logger.info("Successfully loaded file: " + fileName + " from " + this.directory.getPath());
            } catch (FileLoadException e) {
               throw new FileLoadException("Failed to load file: " + fileName, e);
            }
         }
      }

   }

   public void saveFile(Class fileClass) {
      Stream var10000 = this.clientFiles.stream();
      Objects.requireNonNull(fileClass);
      var10000.filter(fileClass::isInstance).findFirst().ifPresent((file) -> {
         try {
            file.saveToFile(this.directory);
            Logger.info("Successfully saved file on-demand: " + file.getName());
         } catch (FileSaveException e) {
            Logger.error("Failed to save file on-demand: " + file.getName(), (Throwable)e);
         }

      });
   }
}

