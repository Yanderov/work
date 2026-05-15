package dev.client.yanderov.events.block;

import dev.client.yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;

public record BreakBlockEvent(class_2338 blockPos) implements Event {
}

