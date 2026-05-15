package dev.client.yanderov.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FontGenerator {
   public static final String CHARSET = "\"\\\" Â¡â€°Â·â‚´â‰ Â¿Ã—Ã˜Ã¸ÐÐ‘Ð’Ð“Ð”Ð•ÐÐ–Ð—Ð˜Ð™ÐšÐ›ÐœÐÐžÐŸÐ Ð¡Ð¢Ð£Ð¤Ð¥Ð¦Ð§Ð¨Ð©ÐªÐ«Ð¬Ð­Ð®Ð¯Ð°Ð±Ð²Ð³Ð´ÐµÑ‘Ð¶Ð·Ð¸Ð¹ÐºÐ»Ð¼Ð½Ð¾Ð¿Ñ€ÑÑ‚ÑƒÑ„Ñ…Ñ†Ñ‡ÑˆÑ‰ÑŠÑ‹ÑŒÑÑŽÑÑ”â€“â€”â€˜â€™â€œâ€â€žâ€¦â†â†‘â†’â†“Ê»ËŒÍ¾â°Â¹Â³â´âµâ¶â·â¸â¹âºâ»â¼â½â¾â±â„¢Ê”Ê•Â¤Â¥Â©Â®ÂµÂ¶Â¼Â½Â¾Î‡â€â€šâ€ â€¡â€¢â€²â€³â€´â€¹â€ºâ€½â‚â„—âˆ’âˆžÐ„â™ â™£â™¥â™¦â™­â™®â™¯âš€âšâš‚âšƒâš„âš…Ê¬â„ââ»â¼â½â­˜â–²â–¶â–¼â—€â—â—¦ï¿½Â¦á´€Ê™á´„á´…á´‡êœ°É¢Êœá´Šá´‹ÊŸá´É´á´á´˜êž¯Ê€êœ±á´›á´œá´ á´¡Êá´¢Â§Ê¡Ê¢Ê˜Ç€ÇƒÇ‚Çâ˜‚â™¤â™§â™¡â™¢â†”âˆ‘â–¡â–³â–·â–½â—â—‹â˜†â˜…â‚€â‚â‚‚â‚ƒâ‚„â‚…â‚†â‚‡â‚ˆâ‚‰â‚Šâ‚‹â‚Œâ‚â‚Žâˆ«âŒ€âŒ˜âš â“ªâ‘ â‘¡â‘¢â‘£â‘¤â‘¥â‘¦â‘§â‘¨â‘©â‘ªâ‘«â‘¬â‘­â‘®â‘¯â‘°â‘±â‘²â‘³â’¶â’·â’¸â’¹â’ºâ’»â’¼â’½â’¾â’¿â“€â“â“‚â“ƒâ“„â“…â“†â“‡â“ˆâ“‰â“Šâ“‹â“Œâ“â“Žâ“â“â“‘â“’â““â“”â“•â“–â“—â“˜â“™â“šâ“›â“œâ“â“žâ“Ÿâ“ â“¡â“¢â“£â“¤â“¥â“¦â“§â“¨â“©â˜‘â˜’!#$%&'()*+,-./0123456789:;<=>[\\\\]^_`?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz{|}~Â£Æ’ÂªÂºÂ¬Â«Â»â‰¡Â±â‰¥â‰¤âŒ âŒ¡Ã·â‰ˆÂ°âˆ™âˆšâ¿Â²â– \"";

   public static void main(String[] args) {
      String fontFolderPath = "mtsdf-font/";
      Path outputPath = initFile(fontFolderPath);
      if (outputPath == null) {
      }

      generate(fontFolderPath, outputPath, "iconmain");
   }

   private static void generate(String fontFolderPath, Path outputPath, String fontPath) {
      File fontFolder = new File(fontFolderPath + fontPath);
      File[] fontFiles = fontFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".ttf"));
      if (fontFiles != null && fontFiles.length > 0) {
         ExecutorService executor = Executors.newFixedThreadPool(fontFiles.length);
         List<Process> processes = new ArrayList();

         for(File fontFile : fontFiles) {
            executor.execute(() -> {
               try {
                  String fontFileName = fontFile.getName().replaceFirst("[.][^.]+$", "");
                  String command = String.format("%s/atlas-gen.exe -font %s -charset %s/charset.txt -type mtsdf -format png -imageout %s.png -json %s.json -size 64 -square4 -pxrange 12", fontFolderPath, fontFile.getAbsolutePath(), fontFolderPath, outputPath.resolve(fontFileName.toLowerCase().replaceAll("-", "_")), outputPath.resolve(fontFileName.toLowerCase().replaceAll("-", "_")));
                  Process process = Runtime.getRuntime().exec(command);
                  processes.add(process);
                  int exitCode = process.waitFor();
                  if (exitCode == 0) {
                     System.out.println("ÐÑ‚Ð»Ð°Ñ Ð´Ð»Ñ ÑˆÑ€Ð¸Ñ„Ñ‚Ð° " + fontFileName + " ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ ÑÐ¾Ð·Ð´Ð°Ð½.");
                  } else {
                     System.out.println("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ ÑÐ¾Ð·Ð´Ð°Ð½Ð¸Ð¸ Ð°Ñ‚Ð»Ð°ÑÐ° Ð´Ð»Ñ ÑˆÑ€Ð¸Ñ„Ñ‚Ð° " + fontFileName);
                  }
               } catch (InterruptedException | IOException e) {
                  PrintStream var10000 = System.err;
                  String var10001 = fontFile.getName();
                  var10000.println("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ Ð²Ñ‹Ð¿Ð¾Ð»Ð½ÐµÐ½Ð¸Ð¸ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹ Ð´Ð»Ñ ÑˆÑ€Ð¸Ñ„Ñ‚Ð° " + var10001 + ": " + ((Exception)e).getMessage());
               }

            });
         }

         executor.shutdown();

         try {
            if (executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
               System.out.println("ÐŸÑ€Ð¾Ñ†ÐµÑÑ Ð·Ð°Ð²ÐµÑ€ÑˆÑ‘Ð½.");
            }
         } catch (InterruptedException e) {
            System.err.println("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ Ð¾Ð¶Ð¸Ð´Ð°Ð½Ð¸Ð¸ Ð·Ð°Ð²ÐµÑ€ÑˆÐµÐ½Ð¸Ñ Ð¿Ð¾Ñ‚Ð¾ÐºÐ¾Ð²: " + e.getMessage());
         }

         for(Process process : processes) {
            process.destroy();
         }
      } else {
         System.out.println("ÐÐµ Ð½Ð°Ð¹Ð´ÐµÐ½Ñ‹ Ñ„Ð°Ð¹Ð»Ñ‹ ÑˆÑ€Ð¸Ñ„Ñ‚Ð¾Ð² Ð² ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ð¹ Ð¿Ð°Ð¿ÐºÐµ.");
      }

   }

   private static Path initFile(String fontFolderPath) {
      Path outputPath = Path.of(fontFolderPath + "out");
      if (Files.notExists(outputPath, new LinkOption[0])) {
         try {
            Files.createDirectories(outputPath);
         } catch (IOException e) {
            System.err.println("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ ÑÐ¾Ð·Ð´Ð°Ð½Ð¸Ð¸ Ð¿Ð°Ð¿ÐºÐ¸: " + e.getMessage());
            return null;
         }
      }

      Path charsetPath = Path.of(fontFolderPath + "charset.txt");
      if (Files.notExists(charsetPath, new LinkOption[0])) {
         try {
            Files.createFile(charsetPath);
            Files.write(charsetPath, "\"\\\" Â¡â€°Â·â‚´â‰ Â¿Ã—Ã˜Ã¸ÐÐ‘Ð’Ð“Ð”Ð•ÐÐ–Ð—Ð˜Ð™ÐšÐ›ÐœÐÐžÐŸÐ Ð¡Ð¢Ð£Ð¤Ð¥Ð¦Ð§Ð¨Ð©ÐªÐ«Ð¬Ð­Ð®Ð¯Ð°Ð±Ð²Ð³Ð´ÐµÑ‘Ð¶Ð·Ð¸Ð¹ÐºÐ»Ð¼Ð½Ð¾Ð¿Ñ€ÑÑ‚ÑƒÑ„Ñ…Ñ†Ñ‡ÑˆÑ‰ÑŠÑ‹ÑŒÑÑŽÑÑ”â€“â€”â€˜â€™â€œâ€â€žâ€¦â†â†‘â†’â†“Ê»ËŒÍ¾â°Â¹Â³â´âµâ¶â·â¸â¹âºâ»â¼â½â¾â±â„¢Ê”Ê•Â¤Â¥Â©Â®ÂµÂ¶Â¼Â½Â¾Î‡â€â€šâ€ â€¡â€¢â€²â€³â€´â€¹â€ºâ€½â‚â„—âˆ’âˆžÐ„â™ â™£â™¥â™¦â™­â™®â™¯âš€âšâš‚âšƒâš„âš…Ê¬â„ââ»â¼â½â­˜â–²â–¶â–¼â—€â—â—¦ï¿½Â¦á´€Ê™á´„á´…á´‡êœ°É¢Êœá´Šá´‹ÊŸá´É´á´á´˜êž¯Ê€êœ±á´›á´œá´ á´¡Êá´¢Â§Ê¡Ê¢Ê˜Ç€ÇƒÇ‚Çâ˜‚â™¤â™§â™¡â™¢â†”âˆ‘â–¡â–³â–·â–½â—â—‹â˜†â˜…â‚€â‚â‚‚â‚ƒâ‚„â‚…â‚†â‚‡â‚ˆâ‚‰â‚Šâ‚‹â‚Œâ‚â‚Žâˆ«âŒ€âŒ˜âš â“ªâ‘ â‘¡â‘¢â‘£â‘¤â‘¥â‘¦â‘§â‘¨â‘©â‘ªâ‘«â‘¬â‘­â‘®â‘¯â‘°â‘±â‘²â‘³â’¶â’·â’¸â’¹â’ºâ’»â’¼â’½â’¾â’¿â“€â“â“‚â“ƒâ“„â“…â“†â“‡â“ˆâ“‰â“Šâ“‹â“Œâ“â“Žâ“â“â“‘â“’â““â“”â“•â“–â“—â“˜â“™â“šâ“›â“œâ“â“žâ“Ÿâ“ â“¡â“¢â“£â“¤â“¥â“¦â“§â“¨â“©â˜‘â˜’!#$%&'()*+,-./0123456789:;<=>[\\\\]^_`?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz{|}~Â£Æ’ÂªÂºÂ¬Â«Â»â‰¡Â±â‰¥â‰¤âŒ âŒ¡Ã·â‰ˆÂ°âˆ™âˆšâ¿Â²â– \"".getBytes(), new OpenOption[0]);
         } catch (IOException e) {
            System.err.println("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ ÑÐ¾Ð·Ð´Ð°Ð½Ð¸Ð¸ charset.txt: " + e.getMessage());
            return null;
         }
      }

      return outputPath;
   }
}

