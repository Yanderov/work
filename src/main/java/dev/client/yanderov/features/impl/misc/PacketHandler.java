package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.class_1937;
import net.minecraft.class_2535;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_310;
import net.minecraft.class_634;

public class PacketHandler extends Module implements QuickImports {
   private final MultiSelectSetting allow = (new MultiSelectSetting("Allow", "Ð Ð°Ð·Ñ€ÐµÑˆÑ‘Ð½Ð½Ñ‹Ðµ Ð¸ÑÑ…Ð¾Ð´ÑÑ‰Ð¸Ðµ Ð¿Ð°ÐºÐµÑ‚Ñ‹ Ð¿Ð¾ Ð¿Ñ€Ð¾ÑÑ‚Ð¾Ð¼Ñƒ Ð¸Ð¼ÐµÐ½Ð¸ ÐºÐ»Ð°ÑÑÐ°")).value("PlayerMoveC2SPacket", "PlayerActionC2SPacket", "PlayerInteractBlockC2SPacket", "PlayerInteractItemC2SPacket", "HandSwingC2SPacket", "UpdateSelectedSlotC2SPacket", "ClientCommandC2SPacket", "ChatMessageC2SPacket", "CommandExecutionC2SPacket", "ClickSlotC2SPacket", "CreativeInventoryActionC2SPacket", "CloseHandledScreenC2SPacket", "ConfirmScreenActionC2SPacket", "ButtonClickC2SPacket", "RenameItemC2SPacket", "UpdateSignC2SPacket", "BookUpdateC2SPacket", "PickFromInventoryC2SPacket", "JigsawGeneratingC2SPacket", "VehicleMoveC2SPacket", "BoatPaddleStateC2SPacket", "TeleportConfirmC2SPacket", "KeepAliveC2SPacket", "ResourcePackStatusC2SPacket", "AdvancementTabC2SPacket", "CustomPayloadC2SPacket", "RequestCommandCompletionsC2SPacket", "UpdateCommandBlockC2SPacket", "UpdateCommandBlockMinecartC2SPacket", "PlayerInputC2SPacket", "InteractEntityC2SPacket", "QueryBlockNbtC2SPacket", "QueryEntityNbtC2SPacket", "RecipeBookDataC2SPacket", "UpdateDifficultyLockC2SPacket", "UpdateDifficultyC2SPacket", "ProgramCommandBlockC2SPacket", "UpdateStructureBlockC2SPacket", "PlayerSessionC2SPacket").selected("PlayerMoveC2SPacket", "PlayerActionC2SPacket", "PlayerInteractBlockC2SPacket", "PlayerInteractItemC2SPacket", "HandSwingC2SPacket", "UpdateSelectedSlotC2SPacket", "ClientCommandC2SPacket", "ChatMessageC2SPacket", "CommandExecutionC2SPacket", "ClickSlotC2SPacket", "CreativeInventoryActionC2SPacket", "CloseHandledScreenC2SPacket", "ConfirmScreenActionC2SPacket", "ButtonClickC2SPacket", "RenameItemC2SPacket", "UpdateSignC2SPacket", "BookUpdateC2SPacket", "PickFromInventoryC2SPacket", "JigsawGeneratingC2SPacket", "VehicleMoveC2SPacket", "BoatPaddleStateC2SPacket", "TeleportConfirmC2SPacket", "KeepAliveC2SPacket", "ResourcePackStatusC2SPacket", "AdvancementTabC2SPacket", "CustomPayloadC2SPacket", "RequestCommandCompletionsC2SPacket", "UpdateCommandBlockC2SPacket", "UpdateCommandBlockMinecartC2SPacket", "PlayerInputC2SPacket", "InteractEntityC2SPacket", "QueryBlockNbtC2SPacket", "QueryEntityNbtC2SPacket", "RecipeBookDataC2SPacket", "UpdateDifficultyLockC2SPacket", "UpdateDifficultyC2SPacket", "ProgramCommandBlockC2SPacket", "UpdateStructureBlockC2SPacket", "PlayerSessionC2SPacket");
   private final MultiSelectSetting forbid = (new MultiSelectSetting("Forbid", "Ð—Ð°Ð¿Ñ€ÐµÑ‰Ñ‘Ð½Ð½Ñ‹Ðµ Ð¸ÑÑ…Ð¾Ð´ÑÑ‰Ð¸Ðµ Ð¿Ð°ÐºÐµÑ‚Ñ‹ (Ð¿Ð¾Ð²ÐµÑ€Ñ… allow)")).value("PlayerMoveC2SPacket", "PlayerActionC2SPacket", "PlayerInteractBlockC2SPacket", "PlayerInteractItemC2SPacket", "HandSwingC2SPacket", "UpdateSelectedSlotC2SPacket", "ClientCommandC2SPacket", "ChatMessageC2SPacket", "CommandExecutionC2SPacket", "ClickSlotC2SPacket", "CreativeInventoryActionC2SPacket", "CloseHandledScreenC2SPacket", "ConfirmScreenActionC2SPacket", "ButtonClickC2SPacket", "RenameItemC2SPacket", "UpdateSignC2SPacket", "BookUpdateC2SPacket", "PickFromInventoryC2SPacket", "JigsawGeneratingC2SPacket", "VehicleMoveC2SPacket", "BoatPaddleStateC2SPacket", "TeleportConfirmC2SPacket", "KeepAliveC2SPacket", "ResourcePackStatusC2SPacket", "AdvancementTabC2SPacket", "CustomPayloadC2SPacket", "RequestCommandCompletionsC2SPacket", "UpdateCommandBlockC2SPacket", "UpdateCommandBlockMinecartC2SPacket", "PlayerInputC2SPacket", "InteractEntityC2SPacket", "QueryBlockNbtC2SPacket", "QueryEntityNbtC2SPacket", "RecipeBookDataC2SPacket", "UpdateDifficultyLockC2SPacket", "UpdateDifficultyC2SPacket", "ProgramCommandBlockC2SPacket", "UpdateStructureBlockC2SPacket", "PlayerSessionC2SPacket");
   private final MultiSelectSetting bufferPackets = (new MultiSelectSetting("Buffer", "ÐŸÐ°ÐºÐµÑ‚Ñ‹, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð½ÑƒÐ¶Ð½Ð¾ Ð±ÑƒÑ„ÐµÑ€Ð¸Ð·Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ (DEFER)")).value("PlayerMoveC2SPacket", "PlayerActionC2SPacket", "PlayerInteractBlockC2SPacket", "PlayerInteractItemC2SPacket");
   private final MultiSelectSetting duplicatePackets = (new MultiSelectSetting("Duplicate", "ÐŸÐ°ÐºÐµÑ‚Ñ‹, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð½ÑƒÐ¶Ð½Ð¾ Ð¿Ñ€Ð¾Ð´ÑƒÐ±Ð»Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ (FORK)")).value("PlayerMoveC2SPacket", "KeepAliveC2SPacket", "TeleportConfirmC2SPacket");
   private final MultiSelectSetting acceleratePackets = (new MultiSelectSetting("Accelerate", "ÐŸÐ°ÐºÐµÑ‚Ñ‹, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Â«ÑƒÑÐºÐ¾Ñ€ÑÐµÐ¼Â» (ACCELERATE â€“ Ð¸Ð·Ð¼ÐµÐ½ÑÐµÐ¼ ÑÐ¾Ð´ÐµÑ€Ð¶Ð¸Ð¼Ð¾Ðµ)")).value("PlayerMoveC2SPacket", "VehicleMoveC2SPacket");
   private final MultiSelectSetting customPackets = (new MultiSelectSetting("Custom", "ÐŸÐ°ÐºÐµÑ‚Ñ‹, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð¾Ð±Ñ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°ÑŽÑ‚ÑÑ ÐºÐ°ÑÑ‚Ð¾Ð¼Ð½Ð¾ (MovementModifierHandler Ð¸ Ð´Ñ€.)")).value("PlayerMoveC2SPacket");
   private final SelectSetting defaultMode = (new SelectSetting("Default Mode", "ÐŸÐ¾Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ Ð¿Ð¾ ÑƒÐ¼Ð¾Ð»Ñ‡Ð°Ð½Ð¸ÑŽ Ð´Ð»Ñ Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² Ð±ÐµÐ· ÑÐ²Ð½Ð¾Ð³Ð¾ Ñ€ÐµÐ¶Ð¸Ð¼Ð°")).value("PASS", "BLOCK", "BUFFER", "DUPLICATE").selected("PASS");
   private final AdvancedRegistry registry = new AdvancedRegistry();
   private final AdvancedDispatcher dispatcher = new AdvancedDispatcher();

   public PacketHandler() {
      super("PacketHandler", ModuleCategory.MISC);
      this.setup(new Setting[]{this.allow, this.forbid, this.bufferPackets, this.duplicatePackets, this.acceleratePackets, this.customPackets, this.defaultMode});
      this.bootstrapHandlers();
   }

   private void bootstrapHandlers() {
      this.registry.registerGlobalSend(new LoggingHandler(false, true, true));
      this.registry.registerGlobalReceive(new LoggingHandler(false, true, true));
      this.registry.registerGlobalSend(new AllowForbidAndModeHandler());
      this.registry.registerSend(class_2828.class, new MovementModifierHandler());
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc != null && mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
         Object raw = e.getPacket();
         if (raw instanceof class_2596) {
            class_2596<?> pkt = (class_2596)raw;
            AdvancedPacketContext.Direction dir = e.getType() == PacketEvent.Type.SEND ? PacketHandler.AdvancedPacketContext.Direction.SEND : PacketHandler.AdvancedPacketContext.Direction.RECEIVE;
            AdvancedPacketContext ctx = new AdvancedPacketContext(mc, mc.field_1687, mc.method_1562(), mc.method_1562().method_48296(), dir);
            AdvancedDispatcher.DispatchResult<class_2596<?>> result = this.dispatcher.dispatch(ctx, pkt);
            if (ctx.isCancelled()) {
               e.cancel();
            } else {
               class_2596<?> processed = result.packet();
               if (processed != pkt) {
                  try {
                     mc.method_1562().method_48296().method_10743(processed);
                  } catch (Throwable var9) {
                  }

                  e.cancel();
               }

            }
         }
      }
   }

   public interface IPacketHandler {
      default int getPriority() {
         return 2;
      }

      default boolean shouldHandle(AdvancedPacketContext ctx, class_2596 packet) {
         return true;
      }

      PacketAction handle(AdvancedPacketContext var1, class_2596 var2);

      default void preReceive(AdvancedPacketContext ctx, class_2596 packet) {
      }

      default void postReceive(AdvancedPacketContext ctx, class_2596 packet) {
      }

      default void preSend(AdvancedPacketContext ctx, class_2596 packet) {
      }

      default void postSend(AdvancedPacketContext ctx, class_2596 packet) {
      }
   }

   public static enum PacketAction {
      PASS,
      MODIFY,
      BLOCK,
      REPLACE,
      DEFER,
      FORK;

      // $FF: synthetic method
      private static PacketAction[] $values() {
         return new PacketAction[]{PASS, MODIFY, BLOCK, REPLACE, DEFER, FORK};
      }
   }

   public static class AdvancedPacketContext {
      private final class_310 client;
      private final class_1937 world;
      private final class_634 networkHandler;
      private final class_2535 connection;
      private final Direction direction;
      private long timestamp;
      private boolean cancelled;
      private PacketAction action;
      private class_2596 replacement;
      private boolean asyncSafe;

      public AdvancedPacketContext(class_310 client, class_1937 world, class_634 networkHandler, class_2535 connection, Direction direction) {
         this.action = PacketHandler.PacketAction.PASS;
         this.client = client;
         this.world = world;
         this.networkHandler = networkHandler;
         this.connection = connection;
         this.direction = direction;
         this.timestamp = System.currentTimeMillis();
      }

      public void cancel() {
         this.cancelled = true;
         this.action = PacketHandler.PacketAction.BLOCK;
      }

      public class_310 getClient() {
         return this.client;
      }

      public class_1937 getWorld() {
         return this.world;
      }

      public class_634 getNetworkHandler() {
         return this.networkHandler;
      }

      public class_2535 getConnection() {
         return this.connection;
      }

      public Direction getDirection() {
         return this.direction;
      }

      public long getTimestamp() {
         return this.timestamp;
      }

      public boolean isCancelled() {
         return this.cancelled;
      }

      public PacketAction getAction() {
         return this.action;
      }

      public class_2596 getReplacement() {
         return this.replacement;
      }

      public boolean isAsyncSafe() {
         return this.asyncSafe;
      }

      public void setTimestamp(long timestamp) {
         this.timestamp = timestamp;
      }

      public void setCancelled(boolean cancelled) {
         this.cancelled = cancelled;
      }

      public void setAction(PacketAction action) {
         this.action = action;
      }

      public void setReplacement(class_2596 replacement) {
         this.replacement = replacement;
      }

      public void setAsyncSafe(boolean asyncSafe) {
         this.asyncSafe = asyncSafe;
      }

      public static enum Direction {
         SEND,
         RECEIVE;

         // $FF: synthetic method
         private static Direction[] $values() {
            return new Direction[]{SEND, RECEIVE};
         }
      }
   }

   public static class AdvancedRegistry {
      private final Map sendHandlers = new ConcurrentHashMap();
      private final Map recvHandlers = new ConcurrentHashMap();
      private final List globalSendHandlers = new ArrayList();
      private final List globalRecvHandlers = new ArrayList();

      public void registerSend(Class type, IPacketHandler handler) {
         this.registerInternal(this.sendHandlers, type, handler);
      }

      public void registerReceive(Class type, IPacketHandler handler) {
         this.registerInternal(this.recvHandlers, type, handler);
      }

      public void registerGlobalSend(IPacketHandler handler) {
         this.globalSendHandlers.add(handler);
         this.globalSendHandlers.sort(Comparator.comparingInt(IPacketHandler::getPriority));
      }

      public void registerGlobalReceive(IPacketHandler handler) {
         this.globalRecvHandlers.add(handler);
         this.globalRecvHandlers.sort(Comparator.comparingInt(IPacketHandler::getPriority));
      }

      public List getHandlers(AdvancedPacketContext.Direction dir, Class clazz) {
         Map<Class<? extends class_2596<?>>, List<IPacketHandler<?>>> map = dir == PacketHandler.AdvancedPacketContext.Direction.SEND ? this.sendHandlers : this.recvHandlers;
         List<IPacketHandler<?>> specific = (List)map.getOrDefault(clazz, Collections.emptyList());
         List<IPacketHandler<T>> out = new ArrayList(specific.size());

         for(IPacketHandler h : specific) {
            out.add(h);
         }

         for(IPacketHandler g : dir == PacketHandler.AdvancedPacketContext.Direction.SEND ? this.globalSendHandlers : this.globalRecvHandlers) {
            out.add(g);
         }

         out.sort(Comparator.comparingInt(IPacketHandler::getPriority));
         return out;
      }

      private void registerInternal(Map map, Class type, IPacketHandler handler) {
         map.compute(type, (k, v) -> {
            List<IPacketHandler<?>> list = v == null ? new ArrayList() : new ArrayList(v);
            list.add(handler);
            list.sort(Comparator.comparingInt(IPacketHandler::getPriority));
            return Collections.unmodifiableList(list);
         });
      }
   }

   public static class AdvancedPipeline {
      public static class_2596 process(AdvancedPacketContext ctx, class_2596 packet, List handlers) {
         if (handlers.isEmpty()) {
            return packet;
         } else {
            for(IPacketHandler handler : handlers) {
               if (handler.shouldHandle(ctx, packet)) {
                  if (ctx.getDirection() == PacketHandler.AdvancedPacketContext.Direction.RECEIVE) {
                     handler.preReceive(ctx, packet);
                  } else {
                     handler.preSend(ctx, packet);
                  }

                  PacketAction action = handler.handle(ctx, packet);
                  ctx.setAction(action);
                  if (action == PacketHandler.PacketAction.BLOCK) {
                     ctx.cancel();
                     return packet;
                  }

                  if (action == PacketHandler.PacketAction.REPLACE && ctx.getReplacement() != null) {
                     packet = (T)ctx.getReplacement();
                  } else {
                     if (action == PacketHandler.PacketAction.DEFER) {
                        return packet;
                     }

                     if (action == PacketHandler.PacketAction.FORK) {
                     }
                  }

                  if (ctx.getDirection() == PacketHandler.AdvancedPacketContext.Direction.RECEIVE) {
                     handler.postReceive(ctx, packet);
                  } else {
                     handler.postSend(ctx, packet);
                  }

                  if (ctx.isCancelled()) {
                     return packet;
                  }
               }
            }

            return packet;
         }
      }
   }

   public class AdvancedDispatcher {
      private final LinkedBlockingQueue deferred = new LinkedBlockingQueue(10000);
      private final int maxPerTick = 1000;

      public DispatchResult dispatch(AdvancedPacketContext ctx, class_2596 packet) {
         AdvancedRegistry reg = PacketHandler.this.registry;
         List<IPacketHandler<T>> handlers = reg.getHandlers(ctx.getDirection(), packet.getClass());
         T processed = (T)PacketHandler.AdvancedPipeline.process(ctx, packet, handlers);
         return new DispatchResult(processed, ctx);
      }

      public void tick() {
         for(int processed = 0; processed < 1000 && !this.deferred.isEmpty(); ++processed) {
            Runnable r = (Runnable)this.deferred.poll();
            if (r == null) {
               break;
            }

            try {
               r.run();
            } catch (Throwable var4) {
            }
         }

      }

      public void submitDeferred(Runnable r) {
         this.deferred.offer(r);
      }

      public static record DispatchResult(class_2596 packet, AdvancedPacketContext context) {
      }
   }

   public static class LoggingHandler implements IPacketHandler {
      private final boolean logAll;
      private final boolean logModified;
      private final boolean logDropped;

      public LoggingHandler(boolean logAll, boolean logModified, boolean logDropped) {
         this.logAll = logAll;
         this.logModified = logModified;
         this.logDropped = logDropped;
      }

      public int getPriority() {
         return 5;
      }

      public PacketAction handle(AdvancedPacketContext ctx, class_2596 packet) {
         String dir = ctx.getDirection().name();
         String name = packet.getClass().getSimpleName();
         switch (ctx.getAction().ordinal()) {
            case 1:
            case 3:
               if (this.logModified || this.logAll) {
                  System.out.println("[PKT][" + dir + "][MODIFY] " + name);
               }
               break;
            case 2:
               if (this.logDropped) {
                  System.out.println("[PKT][" + dir + "][BLOCK] " + name);
               }
               break;
            default:
               if (this.logAll) {
                  System.out.println("[PKT][" + dir + "][PASS] " + name);
               }
         }

         return PacketHandler.PacketAction.PASS;
      }
   }

   public class AllowForbidAndModeHandler implements IPacketHandler {
      public int getPriority() {
         return 0;
      }

      public PacketAction handle(AdvancedPacketContext ctx, class_2596 packet) {
         if (ctx.getDirection() != PacketHandler.AdvancedPacketContext.Direction.SEND) {
            return PacketHandler.PacketAction.PASS;
         } else {
            String simple = packet.getClass().getSimpleName();
            if (PacketHandler.this.forbid.getList().contains(simple) && PacketHandler.this.forbid.isSelected(simple)) {
               ctx.cancel();
               return PacketHandler.PacketAction.BLOCK;
            } else if (PacketHandler.this.allow.getList().contains(simple) && !PacketHandler.this.allow.isSelected(simple)) {
               ctx.cancel();
               return PacketHandler.PacketAction.BLOCK;
            } else if (PacketHandler.this.bufferPackets.isSelected(simple)) {
               PacketHandler.this.dispatcher.submitDeferred(() -> {
                  try {
                     PacketHandler var10000 = PacketHandler.this;
                     PacketHandler.mc.method_1562().method_48296().method_10743(packet);
                  } catch (Throwable var3) {
                  }

               });
               return PacketHandler.PacketAction.DEFER;
            } else if (PacketHandler.this.duplicatePackets.isSelected(simple)) {
               try {
                  QuickImports.mc.method_1562().method_48296().method_10743(packet);
               } catch (Throwable var8) {
               }

               return PacketHandler.PacketAction.FORK;
            } else if (PacketHandler.this.acceleratePackets.isSelected(simple)) {
               return PacketHandler.PacketAction.MODIFY;
            } else if (PacketHandler.this.customPackets.isSelected(simple)) {
               return PacketHandler.PacketAction.MODIFY;
            } else {
               PacketAction var10000;
               switch (PacketHandler.this.defaultMode.getSelected()) {
                  case "BLOCK":
                     ctx.cancel();
                     PacketAction var11 = PacketHandler.PacketAction.BLOCK;
                     var10000 = var11;
                     break;
                  case "BUFFER":
                     PacketHandler.this.dispatcher.submitDeferred(() -> {
                        try {
                           PacketHandler var10000 = PacketHandler.this;
                           PacketHandler.mc.method_1562().method_48296().method_10743(packet);
                        } catch (Throwable var3) {
                        }

                     });
                     PacketAction var10 = PacketHandler.PacketAction.DEFER;
                     var10000 = var10;
                     break;
                  case "DUPLICATE":
                     try {
                        QuickImports.mc.method_1562().method_48296().method_10743(packet);
                     } catch (Throwable var9) {
                     }

                     PacketAction var6 = PacketHandler.PacketAction.FORK;
                     var10000 = var6;
                     break;
                  default:
                     PacketAction var12 = PacketHandler.PacketAction.PASS;
                     var10000 = var12;
               }

               return var10000;
            }
         }
      }
   }

   public static class MovementModifierHandler implements IPacketHandler {
      public int getPriority() {
         return 1;
      }

      public PacketAction handle(AdvancedPacketContext ctx, class_2828 packet) {
         return PacketHandler.PacketAction.PASS;
      }
   }
}

