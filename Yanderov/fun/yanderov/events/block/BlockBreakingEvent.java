package fun.Yanderov.events.block;

import fun.Yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public record BlockBreakingEvent(class_2338 blockPos, class_2350 direction) implements Event {
}

