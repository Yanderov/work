package fun.Yanderov.features.module.exception;

public final class ModuleException extends RuntimeException {
   private final String message;
   private final String moduleName;

   public ModuleException(String message, String moduleName) {
      this.message = message;
      this.moduleName = moduleName;
   }

   public String getMessage() {
      return this.message;
   }

   public String getModuleName() {
      return this.moduleName;
   }

   public String toString() {
      String var10000 = this.getMessage();
      return "ModuleException(message=" + var10000 + ", moduleName=" + this.getModuleName() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ModuleException)) {
         return false;
      } else {
         ModuleException other = (ModuleException)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            Object this$moduleName = this.getModuleName();
            Object other$moduleName = other.getModuleName();
            if (this$moduleName == null) {
               if (other$moduleName != null) {
                  return false;
               }
            } else if (!this$moduleName.equals(other$moduleName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ModuleException;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = super.hashCode();
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $moduleName = this.getModuleName();
      result = result * 59 + ($moduleName == null ? 43 : $moduleName.hashCode());
      return result;
   }
}

