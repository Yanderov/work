package kotlin;

import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Target(
   allowedTargets = {}
)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({})
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\b\u0087\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0004\"\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\u0006\u001a\u0004\b\u0003\u0010\bR\u0019\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00048\u0006¢\u0006\u0006\u001a\u0004\b\u0005\u0010\t¨\u0006\n"},
   d2 = {"Lkotlin/ReplaceWith;", "", "", "expression", "", "imports", "<init>", "(Ljava/lang/String;Lkotlin/Array;)V", "()Ljava/lang/String;", "()[Ljava/lang/String;", "kotlin-stdlib"}
)
public @interface ReplaceWith {
   String expression();

   String[] imports();
}
