package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.SinceKotlin;
import kotlin.reflect.KFunction;

@SinceKotlin(
   version = "1.7"
)
public class FunInterfaceConstructorReference extends FunctionReference implements Serializable {
   private final Class funInterface;

   public FunInterfaceConstructorReference(Class funInterface) {
      super(1);
      this.funInterface = funInterface;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof FunInterfaceConstructorReference)) {
         return false;
      } else {
         FunInterfaceConstructorReference other = (FunInterfaceConstructorReference)o;
         return this.funInterface.equals(other.funInterface);
      }
   }

   public int hashCode() {
      return this.funInterface.hashCode();
   }

   public String toString() {
      return "fun interface " + this.funInterface.getName();
   }

   protected KFunction getReflected() {
      throw new UnsupportedOperationException("Functional interface constructor does not support reflection");
   }
}
