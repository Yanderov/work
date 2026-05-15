package kotlin.io.encoding;

import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0006\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"},
   d2 = {"Ljava/io/InputStream;", "Lkotlin/io/encoding/Base64;", "base64", "decodingWith", "(Ljava/io/InputStream;Lkotlin/io/encoding/Base64;)Ljava/io/InputStream;", "Ljava/io/OutputStream;", "encodingWith", "(Ljava/io/OutputStream;Lkotlin/io/encoding/Base64;)Ljava/io/OutputStream;", "kotlin-stdlib"},
   xs = "kotlin/io/encoding/StreamEncodingKt"
)
class StreamEncodingKt__Base64IOStreamKt {
   @SinceKotlin(
      version = "1.8"
   )
   @ExperimentalEncodingApi
   @NotNull
   public static final InputStream decodingWith(@NotNull InputStream $this$decodingWith, @NotNull Base64 base64) {
      Intrinsics.checkNotNullParameter($this$decodingWith, "<this>");
      Intrinsics.checkNotNullParameter(base64, "base64");
      return new DecodeInputStream($this$decodingWith, base64);
   }

   @SinceKotlin(
      version = "1.8"
   )
   @ExperimentalEncodingApi
   @NotNull
   public static final OutputStream encodingWith(@NotNull OutputStream $this$encodingWith, @NotNull Base64 base64) {
      Intrinsics.checkNotNullParameter($this$encodingWith, "<this>");
      Intrinsics.checkNotNullParameter(base64, "base64");
      return new EncodeOutputStream($this$encodingWith, base64);
   }

   public StreamEncodingKt__Base64IOStreamKt() {
   }
}
