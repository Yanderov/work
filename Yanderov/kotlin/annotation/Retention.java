package kotlin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Target(
   allowedTargets = {AnnotationTarget.ANNOTATION_CLASS}
)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.ANNOTATION_TYPE})
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/annotation/Retention;", "", "Lkotlin/annotation/AnnotationRetention;", "value", "<init>", "(Lkotlin/annotation/AnnotationRetention;)V", "()Lkotlin/annotation/AnnotationRetention;", "kotlin-stdlib"}
)
public @interface Retention {
   AnnotationRetention value() default AnnotationRetention.RUNTIME;
}
