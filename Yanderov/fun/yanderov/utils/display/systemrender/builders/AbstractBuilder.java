package fun.Yanderov.utils.display.systemrender.builders;

public abstract class AbstractBuilder {
   public AbstractBuilder() {
      this.reset();
   }

   public final Object build() {
      T instance = (T)this._build();
      this.reset();
      return instance;
   }

   protected abstract void reset();

   protected abstract Object _build();
}

