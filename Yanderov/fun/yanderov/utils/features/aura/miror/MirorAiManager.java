package fun.Yanderov.utils.features.aura.miror;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1621;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_638;

public class MirorAiManager {
   private static MirorAiManager instance;
   private static final String MODELS_FOLDER = "Yanderov/mirorai_models";
   private final Map models = new LinkedHashMap();
   private boolean training = false;
   private final List currentSamples = new ArrayList();
   private long lastClickTime = 0L;
   private final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
   private class_1621 currentTrainer;
   private final Random random = new Random();

   public static MirorAiManager getInstance() {
      if (instance == null) {
         instance = new MirorAiManager();
      }

      return instance;
   }

   public class_1621 getCurrentTrainer() {
      return this.currentTrainer;
   }

   private MirorAiManager() {
      this.loadAllModels();
   }

   private File getBaseDir() {
      class_310 mc = class_310.method_1551();
      File dir = new File(mc.field_1697, "Yanderov/mirorai_models");
      if (!dir.exists()) {
         dir.mkdirs();
      }

      return dir;
   }

   private File getModelFile(String name) {
      return new File(this.getBaseDir(), name + ".json");
   }

   private void loadAllModels() {
      this.models.clear();
      File dir = this.getBaseDir();
      File[] files = dir.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".json"));
      if (files != null) {
         for(File f : files) {
            try {
               Reader r = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);

               try {
                  MirorModel m = (MirorModel)this.gson.fromJson(r, MirorModel.class);
                  if (m != null && m.name != null) {
                     this.models.put(m.name, m);
                  }
               } catch (Throwable var11) {
                  try {
                     r.close();
                  } catch (Throwable var10) {
                     var11.addSuppressed(var10);
                  }

                  throw var11;
               }

               r.close();
            } catch (Exception var12) {
            }
         }

      }
   }

   public Set getModelNames() {
      return new LinkedHashSet(this.models.keySet());
   }

   public MirorModel getModel(String name) {
      return (MirorModel)this.models.get(name);
   }

   public void startTraining() {
      this.training = true;
      this.currentSamples.clear();
      this.lastClickTime = 0L;
   }

   public void stopTraining() {
      this.training = false;
   }

   public boolean isTraining() {
      return this.training;
   }

   public int getCurrentSamplesCount() {
      return this.currentSamples.size();
   }

   public void recordSample(class_1309 target, class_243 aimPoint, class_243 idealPoint) {
      if (this.training && target != null && aimPoint != null && idealPoint != null) {
         long now = System.currentTimeMillis();
         long dt = this.lastClickTime == 0L ? 0L : now - this.lastClickTime;
         this.lastClickTime = now;
         class_243 diff = aimPoint.method_1020(idealPoint);
         Sample s = new Sample();
         s.dx = diff.field_1352;
         s.dy = diff.field_1351;
         s.dz = diff.field_1350;
         s.timeSincePrevMs = dt;
         s.distance = aimPoint.method_1022(idealPoint);
         this.currentSamples.add(s);
      }
   }

   public boolean saveCurrentAs(String name) {
      if (this.training && !this.currentSamples.isEmpty()) {
         MirorModel model = new MirorModel();
         model.name = name;
         model.createdAt = System.currentTimeMillis();
         model.samples = new ArrayList(this.currentSamples);
         File file = this.getModelFile(name);

         try {
            Writer w = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

            try {
               this.gson.toJson(model, w);
            } catch (Throwable var8) {
               try {
                  w.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            w.close();
         } catch (Exception var9) {
            return false;
         }

         this.models.put(name, model);
         this.training = false;
         return true;
      } else {
         return false;
      }
   }

   public List listModels() {
      return new ArrayList(this.models.keySet());
   }

   public boolean deleteModel(String name) {
      this.models.remove(name);
      File file = this.getModelFile(name);
      return file.exists() ? file.delete() : true;
   }

   public Sample getSampleForIndex(String modelName, int index) {
      MirorModel m = (MirorModel)this.models.get(modelName);
      if (m != null && m.samples != null && !m.samples.isEmpty()) {
         if (index < 0) {
            index = 0;
         }

         return (Sample)m.samples.get(index % m.samples.size());
      } else {
         return null;
      }
   }

   public void startTrainingWithSlimes() {
      this.startTraining();
      this.spawnTrainerSlime();
   }

   public void stopTrainingWithSlimes() {
      this.stopTraining();
      this.despawnTrainerSlime();
   }

   public void onPlayerAttackEntity(int entityId) {
      if (this.training) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1687 != null && mc.field_1724 != null) {
            class_1297 e = mc.field_1687.method_8469(entityId);
            if (e instanceof class_1621) {
               class_1621 slime = (class_1621)e;
               if (slime == this.currentTrainer) {
                  double maxDistance = (double)7.0F;
                  class_243 eye = mc.field_1724.method_33571();
                  class_243 center = slime.method_5829().method_1005();
                  double dist = eye.method_1022(center);
                  if (!(dist > maxDistance)) {
                     this.recordSample(slime, eye, center);
                     this.despawnTrainerSlime();
                     this.spawnTrainerSlime();
                     mc.field_1724.method_7353(class_2561.method_43470("MirorAi: Ð¿Ð¾Ð¿Ð°Ð´Ð°Ð½Ð¸Ðµ Ð¿Ð¾ Ñ‚Ñ€ÐµÐ½ÐµÑ€Ñƒ, ÑÐ¸Ð¼Ð¿Ð»Ð¾Ð² ÑÐµÐ¹Ñ‡Ð°Ñ: " + this.getCurrentSamplesCount()), false);
                  }
               }
            }
         }
      }
   }

   private void spawnTrainerSlime() {
      class_310 mc = class_310.method_1551();
      class_638 world = mc.field_1687;
      if (mc.field_1724 != null && world != null) {
         this.despawnTrainerSlime();
         class_243 eye = mc.field_1724.method_33571();
         class_243 look = mc.field_1724.method_5828(1.0F);
         class_1621 slime = null;

         for(int attempt = 0; attempt < 20; ++attempt) {
            double r = (double)1.5F + this.random.nextDouble() * (double)1.5F;
            double sideOffset = (this.random.nextDouble() - (double)0.5F) * 0.8;
            double upOffset = (this.random.nextDouble() - (double)0.5F) * 0.6;
            class_243 right = look.method_1036(new class_243((double)0.0F, (double)1.0F, (double)0.0F)).method_1029();
            if (Double.isNaN(right.field_1352)) {
               right = new class_243((double)1.0F, (double)0.0F, (double)0.0F);
            }

            class_243 spawnPos = eye.method_1019(look.method_1021(r)).method_1019(right.method_1021(sideOffset)).method_1031((double)0.0F, upOffset, (double)0.0F);
            double x = spawnPos.field_1352;
            double y = spawnPos.field_1351;
            double z = spawnPos.field_1350;
            class_2338 bp = class_2338.method_49637(x, y, z);
            if (world.method_8320(bp).method_26220(world, bp).method_1110()) {
               class_2338 bpUp = bp.method_10084();
               if (world.method_8320(bpUp).method_26220(world, bpUp).method_1110()) {
                  slime = new class_1621(class_1299.field_6069, world);
                  slime.method_5808(x + (double)0.5F, y, z + (double)0.5F, 0.0F, 0.0F);
                  break;
               }
            }
         }

         if (slime != null) {
            slime.method_7161(1, true);
            slime.method_5875(false);
            slime.method_5684(true);
            slime.method_5971();
            slime.method_5665(class_2561.method_43470("MirorAi Trainer"));
            world.method_53875(slime);
            this.currentTrainer = slime;
         }
      }
   }

   private void despawnTrainerSlime() {
      if (this.currentTrainer != null) {
         this.currentTrainer.method_31472();
         this.currentTrainer = null;
      }

   }

   public static class MirorModel {
      public String name;
      public long createdAt;
      public List samples;
   }

   public static class Sample {
      public double dx;
      public double dy;
      public double dz;
      public long timeSincePrevMs;
      public double distance;
   }
}

