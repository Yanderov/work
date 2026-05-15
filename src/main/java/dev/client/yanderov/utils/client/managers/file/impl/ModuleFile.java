package dev.client.yanderov.utils.client.managers.file.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleRepository;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.GroupSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.features.module.setting.implement.TextSetting;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.client.managers.api.draggable.DraggableRepository;
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

public class ModuleFile extends ClientFile {
   private final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   private final ModuleRepository moduleRepository;
   private final DraggableRepository draggableRepository;

   public ModuleFile(ModuleRepository moduleRepository, DraggableRepository draggableRepository) {
      super("AutoCfg");
      this.moduleRepository = moduleRepository;
      this.draggableRepository = draggableRepository;
   }

   public void saveToFile(File path) throws FileSaveException {
      this.saveToFile(path, this.getName() + ".json");
   }

   public void loadFromFile(File path) throws FileLoadException {
      this.loadFromFile(path, this.getName() + ".json");
   }

   public void saveToFile(File path, String fileName) throws FileSaveException {
      JsonObject functionObject = this.createJsonObjectFromModules();
      File file = new File(path, fileName);
      this.writeJsonToFile(functionObject, file);
      super.saveToFile(path, fileName);
   }

   public void loadFromFile(File path, String fileName) throws FileLoadException {
      File file = new File(path, fileName);
      JsonObject functionObject = this.readJsonFromFile(file);
      if (functionObject != null) {
         this.updateModulesFromJsonObject(functionObject);
      }

      super.loadFromFile(path, fileName);
   }

   private JsonObject createJsonObjectFromModules() {
      JsonObject functionObject = new JsonObject();

      for(Module module : this.moduleRepository.modules()) {
         JsonObject moduleObject = new JsonObject();
         moduleObject.addProperty("bind", module.getKey());
         moduleObject.addProperty("state", module.isState());
         module.settings().forEach((setting) -> this.addSettingToJsonObject(moduleObject, setting));
         functionObject.add(module.getName().toLowerCase(), moduleObject);
      }

      for(AbstractDraggable draggable : this.draggableRepository.draggable()) {
         JsonObject draggableObject = new JsonObject();
         draggableObject.addProperty("posX", draggable.getX());
         draggableObject.addProperty("posY", draggable.getY());
         functionObject.add(draggable.getName().toLowerCase(), draggableObject);
      }

      return functionObject;
   }

   private void addSettingToJsonObject(JsonObject moduleObject, Setting setting) {
      if (setting instanceof BooleanSetting booleanSetting) {
         moduleObject.addProperty(setting.getName(), booleanSetting.isValue());
      }

      if (setting instanceof SliderSettings valueSetting) {
         moduleObject.addProperty(setting.getName(), valueSetting.getValue());
      }

      if (setting instanceof ColorSetting colorSetting) {
         moduleObject.addProperty(setting.getName(), colorSetting.getColor());
      }

      if (setting instanceof BindSetting bindSetting) {
         moduleObject.addProperty(setting.getName(), bindSetting.getKey());
      }

      if (setting instanceof TextSetting textSetting) {
         moduleObject.addProperty(setting.getName(), textSetting.getText());
      }

      if (setting instanceof SelectSetting selectSetting) {
         moduleObject.addProperty(setting.getName(), selectSetting.getSelected());
      }

      if (setting instanceof MultiSelectSetting multiSelectSetting) {
         List<String> selected = multiSelectSetting.getSelected();
         String selectedAsString = String.join(",", selected);
         moduleObject.addProperty(setting.getName(), selectedAsString);
      }

      if (setting instanceof GroupSetting groupSetting) {
         JsonObject groupObject = new JsonObject();
         groupObject.addProperty("state", groupSetting.isValue());

         for(Setting subSetting : groupSetting.getSubSettings()) {
            this.addSettingToJsonObject(groupObject, subSetting);
         }

         moduleObject.add(setting.getName(), groupObject);
      }

   }

   private void writeJsonToFile(JsonObject functionObject, File file) throws FileSaveException {
      try {
         FileWriter writer = new FileWriter(file);

         try {
            this.GSON.toJson(functionObject, writer);
         } catch (Throwable var7) {
            try {
               writer.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         writer.close();
      } catch (IOException e) {
         throw new FileSaveException("Failed to save module to file", e);
      }
   }

   private JsonObject readJsonFromFile(File file) throws FileLoadException {
      try {
         FileReader reader = new FileReader(file);

         JsonObject var3;
         try {
            var3 = JsonParser.parseReader(reader).getAsJsonObject();
         } catch (Throwable var6) {
            try {
               reader.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         reader.close();
         return var3;
      } catch (IOException e) {
         throw new FileLoadException("Failed to load module from file", e);
      } catch (JsonIOException | JsonSyntaxException e) {
         throw new FileLoadException("Failed to parse JSON from file", e);
      }
   }

   private void updateModulesFromJsonObject(JsonObject functionObject) {
      for(Module module : this.moduleRepository.modules()) {
         JsonObject moduleObject = functionObject.getAsJsonObject(module.getName().toLowerCase());
         if (moduleObject != null) {
            if (moduleObject.has("bind") && moduleObject.has("state")) {
               module.setKey(moduleObject.get("bind").getAsInt());
               module.setState(moduleObject.get("state").getAsBoolean());
            }

            module.settings().forEach((setting) -> this.updateSettingFromJsonObject(moduleObject, setting));
         }
      }

      for(AbstractDraggable draggable : this.draggableRepository.draggable()) {
         JsonObject draggableObject = functionObject.getAsJsonObject(draggable.getName().toLowerCase());
         if (draggableObject != null && draggableObject.has("posX") && draggableObject.has("posY")) {
            draggable.setX(draggableObject.get("posX").getAsInt());
            draggable.setY(draggableObject.get("posY").getAsInt());
         }
      }

   }

   private void updateSettingFromJsonObject(JsonObject moduleObject, Setting setting) {
      JsonElement settingElement = moduleObject.get(setting.getName());
      if (settingElement != null && !settingElement.isJsonNull()) {
         if (setting instanceof BooleanSetting) {
            BooleanSetting booleanSetting = (BooleanSetting)setting;
            booleanSetting.setValue(settingElement.getAsBoolean());
         }

         if (setting instanceof SliderSettings) {
            SliderSettings valueSetting = (SliderSettings)setting;
            if (settingElement.isJsonPrimitive()) {
               JsonPrimitive primitive = settingElement.getAsJsonPrimitive();
               if (primitive.isNumber()) {
                  valueSetting.setValue(primitive.getAsFloat());
               } else if (primitive.isBoolean()) {
                  valueSetting.setValue(primitive.getAsBoolean() ? 1.0F : 0.0F);
               }
            }
         }

         if (setting instanceof ColorSetting) {
            ColorSetting colorSetting = (ColorSetting)setting;
            colorSetting.setColor(settingElement.getAsInt());
         }

         if (setting instanceof BindSetting) {
            BindSetting bindSetting = (BindSetting)setting;
            bindSetting.setKey(settingElement.getAsInt());
         }

         if (setting instanceof TextSetting) {
            TextSetting textSetting = (TextSetting)setting;
            textSetting.setText(settingElement.getAsString());
         }

         if (setting instanceof SelectSetting) {
            SelectSetting selectSetting = (SelectSetting)setting;
            selectSetting.setSelected(settingElement.getAsString());
         }

         if (setting instanceof MultiSelectSetting) {
            MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
            String asString = settingElement.getAsString();
            List<String> selectedList = new ArrayList(Arrays.asList(asString.split(",")));
            selectedList.removeIf((s) -> !multiSelectSetting.getList().contains(s));
            multiSelectSetting.setSelected(selectedList);
         }

         if (setting instanceof GroupSetting) {
            GroupSetting groupSetting = (GroupSetting)setting;
            JsonObject groupObject = settingElement.getAsJsonObject();
            if (groupObject.has("state")) {
               groupSetting.setValue(groupObject.get("state").getAsBoolean());
            }

            for(Setting subSetting : groupSetting.getSubSettings()) {
               this.updateSettingFromJsonObject(groupObject, subSetting);
            }
         }

      }
   }
}

