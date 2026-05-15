package fun.Yanderov.events.block;

import fun.Yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;

public record BreakBlockEvent(class_2338 blockPos) implements Event {
}

