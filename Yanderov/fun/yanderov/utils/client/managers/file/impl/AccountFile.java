package fun.Yanderov.utils.client.managers.file.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import fun.Yanderov.display.screens.mainmenu.altscreen.impl.AccountData;
import fun.Yanderov.display.screens.mainmenu.altscreen.impl.AccountRepository;
import fun.Yanderov.utils.client.managers.file.ClientFile;
import fun.Yanderov.utils.client.managers.file.exception.FileLoadException;
import fun.Yanderov.utils.client.managers.file.exception.FileSaveException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccountFile extends ClientFile {
   private final AccountRepository accountRepository;

   public AccountFile(AccountRepository accountRepository) {
      super("Accounts");
      this.accountRepository = accountRepository;
   }

   public void saveToFile(File path) throws FileSaveException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      File file = new File(path, this.getName() + ".json");

      try {
         FileWriter writer = new FileWriter(file);

         try {
            AccountData data = new AccountData();
            data.accounts = this.accountRepository.accountList;
            data.currentAccount = this.accountRepository.currentAccount;
            gson.toJson(data, writer);
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
         throw new FileSaveException("Failed to save accounts to file", e);
      }
   }

   public void loadFromFile(File path) throws FileLoadException {
      Gson gson = new Gson();
      File file = new File(path, this.getName() + ".json");

      try {
         FileReader reader = new FileReader(file);

         try {
            AccountData data = (AccountData)gson.fromJson(reader, AccountData.class);
            this.accountRepository.accountList.clear();
            if (data.accounts != null) {
               this.accountRepository.accountList.addAll(data.accounts);
            }

            if (data.currentAccount != null) {
               this.accountRepository.currentAccount = data.currentAccount;
            }
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
         throw new FileLoadException("Failed to load accounts from file", e);
      } catch (JsonSyntaxException e) {
         throw new FileLoadException("JSON syntax error, accounts config cannot be loaded", e);
      } catch (JsonIOException e) {
         throw new FileLoadException("JSON IO error, accounts config cannot be loaded", e);
      }
   }
}

