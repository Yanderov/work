package dev.client.yanderov.utils.client.managers.api.command.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Registry {
   private final Deque _entries = new LinkedList();
   private final Set registered = new HashSet();
   public final Collection entries;

   public Registry() {
      this.entries = Collections.unmodifiableCollection(this._entries);
   }

   public boolean registered(Object entry) {
      return this.registered.contains(entry);
   }

   public void register(Object entry) {
      if (!this.registered(entry)) {
         this._entries.addFirst(entry);
         this.registered.add(entry);
      }

   }

   public void unregister(Object entry) {
      if (this.registered(entry)) {
         this._entries.remove(entry);
         this.registered.remove(entry);
      }
   }

   public Iterator iterator() {
      return this._entries.iterator();
   }

   public Iterator descendingIterator() {
      return this._entries.descendingIterator();
   }

   public Stream stream() {
      return this._entries.stream();
   }

   public Stream descendingStream() {
      Spliterator<V> spliterator = Spliterators.spliterator(this.descendingIterator(), (long)this._entries.size(), 16448);
      return StreamSupport.stream(spliterator, false);
   }
}

