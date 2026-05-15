package kotlin.io.encoding;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001c\u0010\u0018J\u000f\u0010\u001d\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001d\u0010\tJ\u000f\u0010\u001e\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001e\u0010\tR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001fR\u0014\u0010 \u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0018R\u0016\u0010&\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010#R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010,\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010!R\u0014\u0010-\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010!¨\u0006."},
   d2 = {"Lkotlin/io/encoding/DecodeInputStream;", "Ljava/io/InputStream;", "input", "Lkotlin/io/encoding/Base64;", "base64", "<init>", "(Ljava/io/InputStream;Lkotlin/io/encoding/Base64;)V", "", "close", "()V", "", "dst", "", "dstOffset", "length", "copyByteBufferInto", "([BII)V", "dstEndIndex", "symbolBufferLength", "decodeSymbolBufferInto", "([BIII)I", "handlePaddingSymbol", "(I)I", "read", "()I", "destination", "offset", "([BII)I", "readNextSymbol", "resetByteBufferIfEmpty", "shiftByteBufferToStartIfNeeded", "Lkotlin/io/encoding/Base64;", "byteBuffer", "[B", "byteBufferEndIndex", "I", "getByteBufferLength", "byteBufferLength", "byteBufferStartIndex", "Ljava/io/InputStream;", "", "isClosed", "Z", "isEOF", "singleByteBuffer", "symbolBuffer", "kotlin-stdlib"}
)
@ExperimentalEncodingApi
final class DecodeInputStream extends InputStream {
   @NotNull
   private final InputStream input;
   @NotNull
   private final Base64 base64;
   private boolean isClosed;
   private boolean isEOF;
   @NotNull
   private final byte[] singleByteBuffer;
   @NotNull
   private final byte[] symbolBuffer;
   @NotNull
   private final byte[] byteBuffer;
   private int byteBufferStartIndex;
   private int byteBufferEndIndex;

   public DecodeInputStream(@NotNull InputStream input, @NotNull Base64 base64) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(base64, "base64");
      super();
      this.input = input;
      this.base64 = base64;
      this.singleByteBuffer = new byte[1];
      this.symbolBuffer = new byte[1024];
      this.byteBuffer = new byte[1024];
   }

   private final int getByteBufferLength() {
      return this.byteBufferEndIndex - this.byteBufferStartIndex;
   }

   public int read() {
      if (this.byteBufferStartIndex < this.byteBufferEndIndex) {
         int var1 = this.byteBuffer[this.byteBufferStartIndex] & 255;
         ++this.byteBufferStartIndex;
         this.resetByteBufferIfEmpty();
         return var1;
      } else {
         int var10000;
         switch (this.read(this.singleByteBuffer, 0, 1)) {
            case -1:
               var10000 = -1;
               break;
            case 0:
            default:
               throw new IllegalStateException("Unreachable".toString());
            case 1:
               var10000 = this.singleByteBuffer[0] & 255;
         }

         return var10000;
      }
   }

   public int read(@NotNull byte[] destination, int offset, int length) {
      Intrinsics.checkNotNullParameter(destination, "destination");
      if (offset >= 0 && length >= 0 && offset + length <= destination.length) {
         if (this.isClosed) {
            throw new IOException("The input stream is closed.");
         } else if (this.isEOF) {
            return -1;
         } else if (length == 0) {
            return 0;
         } else if (this.getByteBufferLength() >= length) {
            this.copyByteBufferInto(destination, offset, length);
            return length;
         } else {
            int bytesNeeded = length - this.getByteBufferLength();
            int groupsNeeded = (bytesNeeded + 3 - 1) / 3;
            int symbolsNeeded = groupsNeeded * 4;

            int dstOffset;
            int symbolBufferLength;
            for(dstOffset = offset; !this.isEOF && symbolsNeeded > 0; dstOffset += this.decodeSymbolBufferInto(destination, dstOffset, length + offset, symbolBufferLength)) {
               symbolBufferLength = 0;
               int symbolsToRead = Math.min(this.symbolBuffer.length, symbolsNeeded);

               while(!this.isEOF && symbolBufferLength < symbolsToRead) {
                  int symbol = this.readNextSymbol();
                  switch (symbol) {
                     case -1:
                        this.isEOF = true;
                        break;
                     case 61:
                        symbolBufferLength = this.handlePaddingSymbol(symbolBufferLength);
                        this.isEOF = true;
                        break;
                     default:
                        this.symbolBuffer[symbolBufferLength] = (byte)symbol;
                        ++symbolBufferLength;
                  }
               }

               boolean var12 = this.isEOF || symbolBufferLength == symbolsToRead;
               if (!var12) {
                  String var11 = "Check failed.";
                  throw new IllegalStateException(var11.toString());
               }

               symbolsNeeded -= symbolBufferLength;
            }

            return dstOffset == offset && this.isEOF ? -1 : dstOffset - offset;
         }
      } else {
         throw new IndexOutOfBoundsException("offset: " + offset + ", length: " + length + ", buffer size: " + destination.length);
      }
   }

   public void close() {
      if (!this.isClosed) {
         this.isClosed = true;
         this.input.close();
      }

   }

   private final int decodeSymbolBufferInto(byte[] dst, int dstOffset, int dstEndIndex, int symbolBufferLength) {
      this.byteBufferEndIndex += this.base64.decodeIntoByteArray((byte[])this.symbolBuffer, this.byteBuffer, this.byteBufferEndIndex, 0, symbolBufferLength);
      int bytesToCopy = Math.min(this.getByteBufferLength(), dstEndIndex - dstOffset);
      this.copyByteBufferInto(dst, dstOffset, bytesToCopy);
      this.shiftByteBufferToStartIfNeeded();
      return bytesToCopy;
   }

   private final void copyByteBufferInto(byte[] dst, int dstOffset, int length) {
      ArraysKt.copyInto(this.byteBuffer, dst, dstOffset, this.byteBufferStartIndex, this.byteBufferStartIndex + length);
      this.byteBufferStartIndex += length;
      this.resetByteBufferIfEmpty();
   }

   private final void resetByteBufferIfEmpty() {
      if (this.byteBufferStartIndex == this.byteBufferEndIndex) {
         this.byteBufferStartIndex = 0;
         this.byteBufferEndIndex = 0;
      }

   }

   private final void shiftByteBufferToStartIfNeeded() {
      int byteBufferCapacity = this.byteBuffer.length - this.byteBufferEndIndex;
      int symbolBufferCapacity = this.symbolBuffer.length / 4 * 3;
      if (symbolBufferCapacity > byteBufferCapacity) {
         ArraysKt.copyInto(this.byteBuffer, this.byteBuffer, 0, this.byteBufferStartIndex, this.byteBufferEndIndex);
         this.byteBufferEndIndex -= this.byteBufferStartIndex;
         this.byteBufferStartIndex = 0;
      }

   }

   private final int handlePaddingSymbol(int symbolBufferLength) {
      this.symbolBuffer[symbolBufferLength] = 61;
      int var10000;
      if ((symbolBufferLength & 3) == 2) {
         int secondPad = this.readNextSymbol();
         if (secondPad >= 0) {
            this.symbolBuffer[symbolBufferLength + 1] = (byte)secondPad;
         }

         var10000 = symbolBufferLength + 2;
      } else {
         var10000 = symbolBufferLength + 1;
      }

      return var10000;
   }

   private final int readNextSymbol() {
      if (!this.base64.isMimeScheme$kotlin_stdlib()) {
         return this.input.read();
      } else {
         int read = 0;

         do {
            read = this.input.read();
         } while(read != -1 && !Base64Kt.isInMimeAlphabet(read));

         return read;
      }
   }
}
