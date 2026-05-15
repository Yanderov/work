package kotlin.text;

import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000n\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\n\n\u0002\b\n\n\u0002\u0010\u0001\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0016\n\u0002\b\u0006\u001a'\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\b\u0010\t\u001a/\u0010\u000e\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001aG\u0010\u000e\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000e\u0010\u0013\u001aG\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0015\u0010\u0013\u001a'\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a<\u0010\u001f\u001a\u00020\u0002*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0018H\u0082\b¢\u0006\u0004\b\u001f\u0010 \u001a+\u0010$\u001a\u00020#*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0002¢\u0006\u0004\b$\u0010%\u001a#\u0010&\u001a\u00020\u0002*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0002¢\u0006\u0004\b&\u0010'\u001aC\u0010*\u001a\u00020#*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010+\u001a\u001c\u0010,\u001a\u00020\u0002*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0082\b¢\u0006\u0004\b,\u0010-\u001a3\u00104\u001a\u00020\u0002*\u00020.2\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u0002H\u0002¢\u0006\u0004\b4\u00105\u001aC\u00104\u001a\u00020\u0002*\u00020.2\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u00106\u001a\u00020\u00182\u0006\u00107\u001a\u00020\u00182\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u0002H\u0002¢\u0006\u0004\b4\u00108\u001a1\u0010<\u001a\u00020;*\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0004\b<\u0010=\u001a\u001d\u0010<\u001a\u00020;*\u00020\u00182\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\b<\u0010>\u001a1\u0010?\u001a\u00020.*\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0004\b?\u0010@\u001a\u001d\u0010?\u001a\u00020.*\u00020\u00182\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\b?\u0010A\u001a-\u0010D\u001a\u0004\u0018\u00010.*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020BH\u0003¢\u0006\u0004\bD\u0010E\u001a-\u0010F\u001a\u0004\u0018\u00010.*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020BH\u0003¢\u0006\u0004\bF\u0010E\u001a-\u0010G\u001a\u0004\u0018\u00010.*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020BH\u0003¢\u0006\u0004\bG\u0010E\u001a+\u0010H\u001a\u00020.*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020BH\u0003¢\u0006\u0004\bH\u0010E\u001a1\u0010I\u001a\u00020\u0002*\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0004\bI\u0010J\u001a\u001d\u0010I\u001a\u00020\u0002*\u00020\u00182\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bI\u0010K\u001a3\u0010L\u001a\u00020\u0002*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010:\u001a\u0002092\u0006\u0010\"\u001a\u00020\u0002H\u0003¢\u0006\u0004\bL\u0010M\u001a1\u0010N\u001a\u00020\u0000*\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0004\bN\u0010O\u001a\u001d\u0010N\u001a\u00020\u0000*\u00020\u00182\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bN\u0010P\u001a3\u0010Q\u001a\u00020\u0000*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010:\u001a\u0002092\u0006\u0010\"\u001a\u00020\u0002H\u0003¢\u0006\u0004\bQ\u0010R\u001a1\u0010T\u001a\u00020S*\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0004\bT\u0010U\u001a\u001d\u0010T\u001a\u00020S*\u00020\u00182\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bT\u0010V\u001a\u001c\u0010W\u001a\u00020\u0000*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0082\b¢\u0006\u0004\bW\u0010X\u001a\u001b\u0010Y\u001a\u00020;*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0002¢\u0006\u0004\bY\u0010Z\u001a#\u0010[\u001a\u00020\u0002*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0002¢\u0006\u0004\b[\u0010'\u001a#\u0010\\\u001a\u00020\u0000*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\\\u0010]\u001a\u001b\u0010_\u001a\u00020^*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0002¢\u0006\u0004\b_\u0010`\u001a3\u0010b\u001a\u00020#*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010a\u001a\u00020\u001cH\u0002¢\u0006\u0004\bb\u0010c\u001a3\u0010d\u001a\u00020#*\u00020\u00182\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0002¢\u0006\u0004\bd\u0010e\u001a3\u0010f\u001a\u00020#*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u0018H\u0002¢\u0006\u0004\bf\u0010e\u001a#\u0010g\u001a\u00020\u0002*\u00020\u00182\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u0002H\u0002¢\u0006\u0004\bg\u0010h\u001a\u001d\u0010i\u001a\u00020\u0018*\u00020;2\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bi\u0010j\u001a1\u0010i\u001a\u00020\u0018*\u00020.2\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bi\u0010k\u001a\u001d\u0010i\u001a\u00020\u0018*\u00020.2\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bi\u0010l\u001a\u001d\u0010i\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bi\u0010m\u001a\u001d\u0010i\u001a\u00020\u0018*\u00020\u00002\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bi\u0010n\u001a\u001d\u0010i\u001a\u00020\u0018*\u00020S2\b\b\u0002\u0010:\u001a\u000209H\u0007¢\u0006\u0004\bi\u0010o\u001a+\u0010t\u001a\u00020\u0018*\u00020\u00002\u0006\u0010q\u001a\u00020p2\u0006\u0010r\u001a\u00020\u00182\u0006\u0010s\u001a\u00020\u0002H\u0003¢\u0006\u0004\bt\u0010u\u001a3\u0010v\u001a\u00020\u0018*\u00020.2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020B2\u0006\u00100\u001a\u00020/H\u0003¢\u0006\u0004\bv\u0010w\u001a3\u0010x\u001a\u00020\u0018*\u00020.2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020B2\u0006\u00100\u001a\u00020/H\u0003¢\u0006\u0004\bx\u0010w\u001a3\u0010y\u001a\u00020\u0018*\u00020.2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020B2\u0006\u00100\u001a\u00020/H\u0003¢\u0006\u0004\by\u0010w\u001a3\u0010z\u001a\u00020\u0018*\u00020.2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010C\u001a\u00020B2\u0006\u00100\u001a\u00020/H\u0003¢\u0006\u0004\bz\u0010w\"\u0014\u0010{\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|\"\u0014\u0010}\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b}\u0010|\"\u0014\u0010~\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010|\"\u0017\u0010\u0080\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001\"\u0017\u0010\u0082\u0001\u001a\u00020\u00188\u0002X\u0082T¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0017\u0010\u0084\u0001\u001a\u00020\u00188\u0002X\u0082T¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001¨\u0006\u0085\u0001"},
   d2 = {"", "charsPerElement", "", "elementsPerSet", "elementSeparatorLength", "charsPerSet", "(JII)J", "formatLength", "checkFormatLength", "(J)I", "numberOfBytes", "byteSeparatorLength", "bytePrefixLength", "byteSuffixLength", "formattedStringLength", "(IIII)I", "bytesPerLine", "bytesPerGroup", "groupSeparatorLength", "(IIIIIII)I", "stringLength", "parsedByteArrayMaxSize", "wholeElementsPerSet", "(JJI)J", "", "index", "endIndex", "part", "", "ignoreCase", "partName", "checkContainsAt", "(Ljava/lang/String;IILjava/lang/String;ZLjava/lang/String;)I", "startIndex", "maxDigits", "", "checkMaxDigits", "(Ljava/lang/String;III)V", "checkNewLineAt", "(Ljava/lang/String;II)I", "prefix", "suffix", "checkPrefixSuffixMaxDigits", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;ZI)V", "decimalFromHexDigitAt", "(Ljava/lang/String;I)I", "", "", "byteToDigits", "", "destination", "destinationOffset", "formatByteAt", "([BI[I[CI)I", "bytePrefix", "byteSuffix", "([BILjava/lang/String;Ljava/lang/String;[I[CI)I", "Lkotlin/text/HexFormat;", "format", "", "hexToByte", "(Ljava/lang/String;IILkotlin/text/HexFormat;)B", "(Ljava/lang/String;Lkotlin/text/HexFormat;)B", "hexToByteArray", "(Ljava/lang/String;IILkotlin/text/HexFormat;)[B", "(Ljava/lang/String;Lkotlin/text/HexFormat;)[B", "Lkotlin/text/HexFormat$BytesHexFormat;", "bytesFormat", "hexToByteArrayNoLineAndGroupSeparator", "(Ljava/lang/String;IILkotlin/text/HexFormat$BytesHexFormat;)[B", "hexToByteArrayNoLineAndGroupSeparatorSlowPath", "hexToByteArrayShortByteSeparatorNoPrefixAndSuffix", "hexToByteArraySlowPath", "hexToInt", "(Ljava/lang/String;IILkotlin/text/HexFormat;)I", "(Ljava/lang/String;Lkotlin/text/HexFormat;)I", "hexToIntImpl", "(Ljava/lang/String;IILkotlin/text/HexFormat;I)I", "hexToLong", "(Ljava/lang/String;IILkotlin/text/HexFormat;)J", "(Ljava/lang/String;Lkotlin/text/HexFormat;)J", "hexToLongImpl", "(Ljava/lang/String;IILkotlin/text/HexFormat;I)J", "", "hexToShort", "(Ljava/lang/String;IILkotlin/text/HexFormat;)S", "(Ljava/lang/String;Lkotlin/text/HexFormat;)S", "longDecimalFromHexDigitAt", "(Ljava/lang/String;I)J", "parseByteAt", "(Ljava/lang/String;I)B", "parseInt", "parseLong", "(Ljava/lang/String;II)J", "", "throwInvalidDigitAt", "(Ljava/lang/String;I)Ljava/lang/Void;", "requireMaxLength", "throwInvalidNumberOfDigits", "(Ljava/lang/String;IIIZ)V", "throwInvalidPrefixSuffix", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;)V", "throwNotContainedAt", "toCharArrayIfNotEmpty", "(Ljava/lang/String;[CI)I", "toHexString", "(BLkotlin/text/HexFormat;)Ljava/lang/String;", "([BIILkotlin/text/HexFormat;)Ljava/lang/String;", "([BLkotlin/text/HexFormat;)Ljava/lang/String;", "(ILkotlin/text/HexFormat;)Ljava/lang/String;", "(JLkotlin/text/HexFormat;)Ljava/lang/String;", "(SLkotlin/text/HexFormat;)Ljava/lang/String;", "Lkotlin/text/HexFormat$NumberHexFormat;", "numberFormat", "digits", "bits", "toHexStringImpl", "(JLkotlin/text/HexFormat$NumberHexFormat;Ljava/lang/String;I)Ljava/lang/String;", "toHexStringNoLineAndGroupSeparator", "([BIILkotlin/text/HexFormat$BytesHexFormat;[I)Ljava/lang/String;", "toHexStringNoLineAndGroupSeparatorSlowPath", "toHexStringShortByteSeparatorNoPrefixAndSuffix", "toHexStringSlowPath", "BYTE_TO_LOWER_CASE_HEX_DIGITS", "[I", "BYTE_TO_UPPER_CASE_HEX_DIGITS", "HEX_DIGITS_TO_DECIMAL", "", "HEX_DIGITS_TO_LONG_DECIMAL", "[J", "LOWER_CASE_HEX_DIGITS", "Ljava/lang/String;", "UPPER_CASE_HEX_DIGITS", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nHexExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1050:1\n998#1,7:1052\n998#1,7:1059\n998#1,7:1066\n998#1,7:1073\n998#1,7:1080\n998#1,7:1087\n998#1,7:1094\n998#1,7:1101\n1009#1,5:1108\n1009#1,5:1113\n998#1,7:1118\n998#1,7:1125\n1009#1,5:1132\n1018#1,5:1137\n1#2:1051\n1188#3,3:1142\n1188#3,3:1145\n1188#3,3:1148\n1188#3,3:1151\n*S KotlinDebug\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n*L\n418#1:1052,7\n451#1:1059,7\n455#1:1066,7\n458#1:1073,7\n499#1:1080,7\n502#1:1087,7\n507#1:1094,7\n512#1:1101,7\n519#1:1108,5\n520#1:1113,5\n967#1:1118,7\n969#1:1125,7\n983#1:1132,5\n991#1:1137,5\n42#1:1142,3\n43#1:1145,3\n54#1:1148,3\n55#1:1151,3\n*E\n"})
public final class HexExtensionsKt {
   @NotNull
   private static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";
   @NotNull
   private static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";
   @NotNull
   private static final int[] BYTE_TO_LOWER_CASE_HEX_DIGITS;
   @NotNull
   private static final int[] BYTE_TO_UPPER_CASE_HEX_DIGITS;
   @NotNull
   private static final int[] HEX_DIGITS_TO_DECIMAL;
   @NotNull
   private static final long[] HEX_DIGITS_TO_LONG_DECIMAL;

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final String toHexString(@NotNull byte[] $this$toHexString, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$toHexString, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return toHexString($this$toHexString, 0, $this$toHexString.length, format);
   }

   // $FF: synthetic method
   public static String toHexString$default(byte[] var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return toHexString(var0, var1);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final String toHexString(@NotNull byte[] $this$toHexString, int startIndex, int endIndex, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$toHexString, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$toHexString.length);
      if (startIndex == endIndex) {
         return "";
      } else {
         int[] byteToDigits = format.getUpperCase() ? BYTE_TO_UPPER_CASE_HEX_DIGITS : BYTE_TO_LOWER_CASE_HEX_DIGITS;
         HexFormat.BytesHexFormat bytesFormat = format.getBytes();
         return bytesFormat.getNoLineAndGroupSeparator$kotlin_stdlib() ? toHexStringNoLineAndGroupSeparator($this$toHexString, startIndex, endIndex, bytesFormat, byteToDigits) : toHexStringSlowPath($this$toHexString, startIndex, endIndex, bytesFormat, byteToDigits);
      }
   }

   // $FF: synthetic method
   public static String toHexString$default(byte[] var0, int var1, int var2, HexFormat var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length;
      }

      if ((var4 & 4) != 0) {
         var3 = HexFormat.Companion.getDefault();
      }

      return toHexString(var0, var1, var2, var3);
   }

   @ExperimentalStdlibApi
   private static final String toHexStringNoLineAndGroupSeparator(byte[] $this$toHexStringNoLineAndGroupSeparator, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat, int[] byteToDigits) {
      return bytesFormat.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() ? toHexStringShortByteSeparatorNoPrefixAndSuffix($this$toHexStringNoLineAndGroupSeparator, startIndex, endIndex, bytesFormat, byteToDigits) : toHexStringNoLineAndGroupSeparatorSlowPath($this$toHexStringNoLineAndGroupSeparator, startIndex, endIndex, bytesFormat, byteToDigits);
   }

   @ExperimentalStdlibApi
   private static final String toHexStringShortByteSeparatorNoPrefixAndSuffix(byte[] $this$toHexStringShortByteSeparatorNoPrefixAndSuffix, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat, int[] byteToDigits) {
      int byteSeparatorLength = bytesFormat.getByteSeparator().length();
      int numberOfBytes = byteSeparatorLength <= 1;
      if (!numberOfBytes) {
         String var14 = "Failed requirement.";
         throw new IllegalArgumentException(var14.toString());
      } else {
         numberOfBytes = endIndex - startIndex;
         int charIndex = 0;
         if (byteSeparatorLength == 0) {
            char[] charArray = new char[checkFormatLength(2L * (long)numberOfBytes)];

            for(int byteIndex = startIndex; byteIndex < endIndex; ++byteIndex) {
               charIndex = formatByteAt($this$toHexStringShortByteSeparatorNoPrefixAndSuffix, byteIndex, byteToDigits, charArray, charIndex);
            }

            return StringsKt.concatToString(charArray);
         } else {
            char[] charArray = new char[checkFormatLength(3L * (long)numberOfBytes - 1L)];
            char byteSeparatorChar = bytesFormat.getByteSeparator().charAt(0);
            charIndex = formatByteAt($this$toHexStringShortByteSeparatorNoPrefixAndSuffix, startIndex, byteToDigits, charArray, charIndex);

            for(int byteIndex = startIndex + 1; byteIndex < endIndex; ++byteIndex) {
               charArray[charIndex++] = byteSeparatorChar;
               charIndex = formatByteAt($this$toHexStringShortByteSeparatorNoPrefixAndSuffix, byteIndex, byteToDigits, charArray, charIndex);
            }

            return StringsKt.concatToString(charArray);
         }
      }
   }

   @ExperimentalStdlibApi
   private static final String toHexStringNoLineAndGroupSeparatorSlowPath(byte[] $this$toHexStringNoLineAndGroupSeparatorSlowPath, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat, int[] byteToDigits) {
      String bytePrefix = bytesFormat.getBytePrefix();
      String byteSuffix = bytesFormat.getByteSuffix();
      String byteSeparator = bytesFormat.getByteSeparator();
      int formatLength = formattedStringLength(endIndex - startIndex, byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
      char[] charArray = new char[formatLength];
      int charIndex = 0;
      charIndex = formatByteAt($this$toHexStringNoLineAndGroupSeparatorSlowPath, startIndex, bytePrefix, byteSuffix, byteToDigits, charArray, charIndex);

      for(int byteIndex = startIndex + 1; byteIndex < endIndex; ++byteIndex) {
         charIndex = toCharArrayIfNotEmpty(byteSeparator, charArray, charIndex);
         charIndex = formatByteAt($this$toHexStringNoLineAndGroupSeparatorSlowPath, byteIndex, bytePrefix, byteSuffix, byteToDigits, charArray, charIndex);
      }

      return StringsKt.concatToString(charArray);
   }

   @ExperimentalStdlibApi
   private static final String toHexStringSlowPath(byte[] $this$toHexStringSlowPath, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat, int[] byteToDigits) {
      int bytesPerLine = bytesFormat.getBytesPerLine();
      int bytesPerGroup = bytesFormat.getBytesPerGroup();
      String bytePrefix = bytesFormat.getBytePrefix();
      String byteSuffix = bytesFormat.getByteSuffix();
      String byteSeparator = bytesFormat.getByteSeparator();
      String groupSeparator = bytesFormat.getGroupSeparator();
      int formatLength = formattedStringLength(endIndex - startIndex, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
      char[] charArray = new char[formatLength];
      int charIndex = 0;
      int indexInLine = 0;
      int indexInGroup = 0;

      for(int byteIndex = startIndex; byteIndex < endIndex; ++byteIndex) {
         if (indexInLine == bytesPerLine) {
            charArray[charIndex++] = '\n';
            indexInLine = 0;
            indexInGroup = 0;
         } else if (indexInGroup == bytesPerGroup) {
            charIndex = toCharArrayIfNotEmpty(groupSeparator, charArray, charIndex);
            indexInGroup = 0;
         }

         if (indexInGroup != 0) {
            charIndex = toCharArrayIfNotEmpty(byteSeparator, charArray, charIndex);
         }

         charIndex = formatByteAt($this$toHexStringSlowPath, byteIndex, bytePrefix, byteSuffix, byteToDigits, charArray, charIndex);
         ++indexInGroup;
         ++indexInLine;
      }

      boolean var18 = charIndex == formatLength;
      if (!var18) {
         String var17 = "Check failed.";
         throw new IllegalStateException(var17.toString());
      } else {
         return StringsKt.concatToString(charArray);
      }
   }

   private static final int formatByteAt(byte[] $this$formatByteAt, int index, String bytePrefix, String byteSuffix, int[] byteToDigits, char[] destination, int destinationOffset) {
      int offset = toCharArrayIfNotEmpty(bytePrefix, destination, destinationOffset);
      offset = formatByteAt($this$formatByteAt, index, byteToDigits, destination, offset);
      return toCharArrayIfNotEmpty(byteSuffix, destination, offset);
   }

   private static final int formatByteAt(byte[] $this$formatByteAt, int index, int[] byteToDigits, char[] destination, int destinationOffset) {
      int var5 = $this$formatByteAt[index] & 255;
      int byteDigits = byteToDigits[var5];
      destination[destinationOffset] = (char)(byteDigits >> 8);
      destination[destinationOffset + 1] = (char)(byteDigits & 255);
      return destinationOffset + 2;
   }

   private static final int formattedStringLength(int numberOfBytes, int byteSeparatorLength, int bytePrefixLength, int byteSuffixLength) {
      boolean var4 = numberOfBytes > 0;
      if (!var4) {
         String var5 = "Failed requirement.";
         throw new IllegalArgumentException(var5.toString());
      } else {
         long charsPerByte = 2L + (long)bytePrefixLength + (long)byteSuffixLength + (long)byteSeparatorLength;
         long formatLength = (long)numberOfBytes * charsPerByte - (long)byteSeparatorLength;
         return checkFormatLength(formatLength);
      }
   }

   public static final int formattedStringLength(int numberOfBytes, int bytesPerLine, int bytesPerGroup, int groupSeparatorLength, int byteSeparatorLength, int bytePrefixLength, int byteSuffixLength) {
      int lineSeparators = numberOfBytes > 0;
      if (!lineSeparators) {
         String var16 = "Failed requirement.";
         throw new IllegalArgumentException(var16.toString());
      } else {
         lineSeparators = (numberOfBytes - 1) / bytesPerLine;
         int var9 = 0;
         int groupSeparatorsPerLine = (bytesPerLine - 1) / bytesPerGroup;
         int groupSeparatorsInLastLine = numberOfBytes % bytesPerLine;
         int var13 = 0;
         int bytesInLastLine = groupSeparatorsInLastLine == 0 ? bytesPerLine : groupSeparatorsInLastLine;
         groupSeparatorsInLastLine = (bytesInLastLine - 1) / bytesPerGroup;
         int groupSeparators = lineSeparators * groupSeparatorsPerLine + groupSeparatorsInLastLine;
         var9 = numberOfBytes - 1 - lineSeparators - groupSeparators;
         long formatLength = (long)lineSeparators + (long)groupSeparators * (long)groupSeparatorLength + (long)var9 * (long)byteSeparatorLength + (long)numberOfBytes * ((long)bytePrefixLength + 2L + (long)byteSuffixLength);
         return checkFormatLength(formatLength);
      }
   }

   private static final int checkFormatLength(long formatLength) {
      if (!(0L <= formatLength ? formatLength <= 2147483647L : false)) {
         throw new IllegalArgumentException("The resulting string length is too big: " + ULong.toString-impl(ULong.constructor-impl(formatLength)));
      } else {
         return (int)formatLength;
      }
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final byte[] hexToByteArray(@NotNull String $this$hexToByteArray, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToByteArray, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return hexToByteArray($this$hexToByteArray, 0, $this$hexToByteArray.length(), format);
   }

   // $FF: synthetic method
   public static byte[] hexToByteArray$default(String var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return hexToByteArray(var0, var1);
   }

   @ExperimentalStdlibApi
   private static final byte[] hexToByteArray(String $this$hexToByteArray, int startIndex, int endIndex, HexFormat format) {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$hexToByteArray.length());
      if (startIndex == endIndex) {
         return new byte[0];
      } else {
         HexFormat.BytesHexFormat bytesFormat = format.getBytes();
         if (bytesFormat.getNoLineAndGroupSeparator$kotlin_stdlib()) {
            byte[] it = hexToByteArrayNoLineAndGroupSeparator($this$hexToByteArray, startIndex, endIndex, bytesFormat);
            if (it != null) {
               int var8 = 0;
               return it;
            }
         }

         return hexToByteArraySlowPath($this$hexToByteArray, startIndex, endIndex, bytesFormat);
      }
   }

   // $FF: synthetic method
   static byte[] hexToByteArray$default(String var0, int var1, int var2, HexFormat var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length();
      }

      if ((var4 & 4) != 0) {
         var3 = HexFormat.Companion.getDefault();
      }

      return hexToByteArray(var0, var1, var2, var3);
   }

   @ExperimentalStdlibApi
   private static final byte[] hexToByteArrayNoLineAndGroupSeparator(String $this$hexToByteArrayNoLineAndGroupSeparator, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat) {
      return bytesFormat.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() ? hexToByteArrayShortByteSeparatorNoPrefixAndSuffix($this$hexToByteArrayNoLineAndGroupSeparator, startIndex, endIndex, bytesFormat) : hexToByteArrayNoLineAndGroupSeparatorSlowPath($this$hexToByteArrayNoLineAndGroupSeparator, startIndex, endIndex, bytesFormat);
   }

   @ExperimentalStdlibApi
   private static final byte[] hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(String $this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat) {
      int byteSeparatorLength = bytesFormat.getByteSeparator().length();
      int numberOfChars = byteSeparatorLength <= 1;
      if (!numberOfChars) {
         String var20 = "Failed requirement.";
         throw new IllegalArgumentException(var20.toString());
      } else {
         numberOfChars = endIndex - startIndex;
         int charIndex = 0;
         if (byteSeparatorLength == 0) {
            if ((numberOfChars & 1) != 0) {
               return null;
            } else {
               int numberOfBytes = numberOfChars >> 1;
               byte[] byteArray = new byte[numberOfBytes];

               for(int byteIndex = 0; byteIndex < numberOfBytes; ++byteIndex) {
                  byteArray[byteIndex] = parseByteAt($this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix, charIndex);
                  charIndex += 2;
               }

               return byteArray;
            }
         } else if (numberOfChars % 3 != 2) {
            return null;
         } else {
            int numberOfBytes = numberOfChars / 3 + 1;
            byte[] byteArray = new byte[numberOfBytes];
            char byteSeparatorChar = bytesFormat.getByteSeparator().charAt(0);
            byteArray[0] = parseByteAt($this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix, charIndex);
            charIndex += 2;

            for(int byteIndex = 1; byteIndex < numberOfBytes; ++byteIndex) {
               if ($this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix.charAt(charIndex) != byteSeparatorChar) {
                  String $this$checkContainsAt$iv = $this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix;
                  String part$iv = bytesFormat.getByteSeparator();
                  boolean ignoreCase$iv = bytesFormat.getIgnoreCase$kotlin_stdlib();
                  String partName$iv = "byte separator";
                  int $i$f$checkContainsAt = 0;
                  if (((CharSequence)part$iv).length() != 0) {
                     int i$iv = 0;

                     for(int var17 = ((CharSequence)part$iv).length(); i$iv < var17; ++i$iv) {
                        if (!CharsKt.equals(part$iv.charAt(i$iv), $this$checkContainsAt$iv.charAt(charIndex + i$iv), ignoreCase$iv)) {
                           throwNotContainedAt($this$checkContainsAt$iv, charIndex, endIndex, part$iv, partName$iv);
                        }
                     }

                     int var10000 = charIndex + part$iv.length();
                  }
               }

               byteArray[byteIndex] = parseByteAt($this$hexToByteArrayShortByteSeparatorNoPrefixAndSuffix, charIndex + 1);
               charIndex += 3;
            }

            return byteArray;
         }
      }
   }

   @ExperimentalStdlibApi
   private static final byte[] hexToByteArrayNoLineAndGroupSeparatorSlowPath(String $this$hexToByteArrayNoLineAndGroupSeparatorSlowPath, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat) {
      String bytePrefix = bytesFormat.getBytePrefix();
      String byteSuffix = bytesFormat.getByteSuffix();
      String byteSeparator = bytesFormat.getByteSeparator();
      int byteSeparatorLength = byteSeparator.length();
      long charsPerByte = 2L + (long)bytePrefix.length() + (long)byteSuffix.length() + (long)byteSeparatorLength;
      long numberOfChars = (long)(endIndex - startIndex);
      int numberOfBytes = (int)((numberOfChars + (long)byteSeparatorLength) / charsPerByte);
      if ((long)numberOfBytes * charsPerByte - (long)byteSeparatorLength != numberOfChars) {
         return null;
      } else {
         boolean ignoreCase = bytesFormat.getIgnoreCase$kotlin_stdlib();
         byte[] byteArray = new byte[numberOfBytes];
         int charIndex = startIndex;
         String between = $this$hexToByteArrayNoLineAndGroupSeparatorSlowPath;
         String partName$iv = "byte prefix";
         int $i$f$checkContainsAt = 0;
         int var10000;
         if (((CharSequence)bytePrefix).length() == 0) {
            var10000 = startIndex;
         } else {
            int i$iv = 0;

            for(int var20 = ((CharSequence)bytePrefix).length(); i$iv < var20; ++i$iv) {
               if (!CharsKt.equals(bytePrefix.charAt(i$iv), between.charAt(charIndex + i$iv), ignoreCase)) {
                  throwNotContainedAt(between, charIndex, endIndex, bytePrefix, partName$iv);
               }
            }

            var10000 = charIndex + bytePrefix.length();
         }

         charIndex = var10000;
         between = byteSuffix + byteSeparator + bytePrefix;
         int byteIndex = 0;

         for(int index$iv = numberOfBytes - 1; byteIndex < index$iv; ++byteIndex) {
            byteArray[byteIndex] = parseByteAt($this$hexToByteArrayNoLineAndGroupSeparatorSlowPath, charIndex);
            String partName$iv = $this$hexToByteArrayNoLineAndGroupSeparatorSlowPath;
            int $i$f$checkContainsAt = charIndex + 2;
            String partName$iv = "byte suffix + byte separator + byte prefix";
            int $i$f$checkContainsAt = 0;
            if (((CharSequence)between).length() == 0) {
               var10000 = $i$f$checkContainsAt;
            } else {
               int i$iv = 0;

               for(int var24 = ((CharSequence)between).length(); i$iv < var24; ++i$iv) {
                  if (!CharsKt.equals(between.charAt(i$iv), partName$iv.charAt($i$f$checkContainsAt + i$iv), ignoreCase)) {
                     throwNotContainedAt(partName$iv, $i$f$checkContainsAt, endIndex, between, partName$iv);
                  }
               }

               var10000 = $i$f$checkContainsAt + between.length();
            }

            charIndex = var10000;
         }

         byteArray[numberOfBytes - 1] = parseByteAt($this$hexToByteArrayNoLineAndGroupSeparatorSlowPath, charIndex);
         String var28 = $this$hexToByteArrayNoLineAndGroupSeparatorSlowPath;
         $i$f$checkContainsAt = charIndex + 2;
         String partName$iv = "byte suffix";
         int $i$f$checkContainsAt = 0;
         if (((CharSequence)byteSuffix).length() != 0) {
            int i$iv = 0;

            for(int var36 = ((CharSequence)byteSuffix).length(); i$iv < var36; ++i$iv) {
               if (!CharsKt.equals(byteSuffix.charAt(i$iv), var28.charAt($i$f$checkContainsAt + i$iv), ignoreCase)) {
                  throwNotContainedAt(var28, $i$f$checkContainsAt, endIndex, byteSuffix, partName$iv);
               }
            }

            var10000 = $i$f$checkContainsAt + byteSuffix.length();
         }

         return byteArray;
      }
   }

   @ExperimentalStdlibApi
   private static final byte[] hexToByteArraySlowPath(String $this$hexToByteArraySlowPath, int startIndex, int endIndex, HexFormat.BytesHexFormat bytesFormat) {
      int bytesPerLine = bytesFormat.getBytesPerLine();
      int bytesPerGroup = bytesFormat.getBytesPerGroup();
      String bytePrefix = bytesFormat.getBytePrefix();
      String byteSuffix = bytesFormat.getByteSuffix();
      String byteSeparator = bytesFormat.getByteSeparator();
      String groupSeparator = bytesFormat.getGroupSeparator();
      boolean ignoreCase = bytesFormat.getIgnoreCase$kotlin_stdlib();
      int parseMaxSize = parsedByteArrayMaxSize(endIndex - startIndex, bytesPerLine, bytesPerGroup, groupSeparator.length(), byteSeparator.length(), bytePrefix.length(), byteSuffix.length());
      byte[] byteArray = new byte[parseMaxSize];
      int charIndex = startIndex;
      int byteIndex = 0;
      int indexInLine = 0;

      int var41;
      for(int indexInGroup = 0; charIndex < endIndex; charIndex = var41) {
         if (indexInLine == bytesPerLine) {
            charIndex = checkNewLineAt($this$hexToByteArraySlowPath, charIndex, endIndex);
            indexInLine = 0;
            indexInGroup = 0;
         } else if (indexInGroup == bytesPerGroup) {
            String $this$checkContainsAt$iv = $this$hexToByteArraySlowPath;
            String partName$iv = "group separator";
            int $i$f$checkContainsAt = 0;
            if (((CharSequence)groupSeparator).length() == 0) {
               var41 = charIndex;
            } else {
               int i$iv = 0;

               for(int var36 = ((CharSequence)groupSeparator).length(); i$iv < var36; ++i$iv) {
                  if (!CharsKt.equals(groupSeparator.charAt(i$iv), $this$checkContainsAt$iv.charAt(charIndex + i$iv), ignoreCase)) {
                     throwNotContainedAt($this$checkContainsAt$iv, charIndex, endIndex, groupSeparator, partName$iv);
                  }
               }

               var41 = charIndex + groupSeparator.length();
            }

            charIndex = var41;
            indexInGroup = 0;
         } else if (indexInGroup != 0) {
            String $this$checkContainsAt$iv = $this$hexToByteArraySlowPath;
            String partName$iv = "byte separator";
            int $i$f$checkContainsAt = 0;
            if (((CharSequence)byteSeparator).length() == 0) {
               var41 = charIndex;
            } else {
               int i$iv = 0;

               for(int var21 = ((CharSequence)byteSeparator).length(); i$iv < var21; ++i$iv) {
                  if (!CharsKt.equals(byteSeparator.charAt(i$iv), $this$checkContainsAt$iv.charAt(charIndex + i$iv), ignoreCase)) {
                     throwNotContainedAt($this$checkContainsAt$iv, charIndex, endIndex, byteSeparator, partName$iv);
                  }
               }

               var41 = charIndex + byteSeparator.length();
            }

            charIndex = var41;
         }

         ++indexInLine;
         ++indexInGroup;
         String $this$checkContainsAt$iv = $this$hexToByteArraySlowPath;
         String partName$iv = "byte prefix";
         int $i$f$checkContainsAt = 0;
         if (((CharSequence)bytePrefix).length() == 0) {
            var41 = charIndex;
         } else {
            int i$iv = 0;

            for(int var37 = ((CharSequence)bytePrefix).length(); i$iv < var37; ++i$iv) {
               if (!CharsKt.equals(bytePrefix.charAt(i$iv), $this$checkContainsAt$iv.charAt(charIndex + i$iv), ignoreCase)) {
                  throwNotContainedAt($this$checkContainsAt$iv, charIndex, endIndex, bytePrefix, partName$iv);
               }
            }

            var41 = charIndex + bytePrefix.length();
         }

         charIndex = var41;
         if (endIndex - 2 < charIndex) {
            throwInvalidNumberOfDigits($this$hexToByteArraySlowPath, charIndex, endIndex, 2, true);
         }

         byteArray[byteIndex++] = parseByteAt($this$hexToByteArraySlowPath, charIndex);
         $this$checkContainsAt$iv = $this$hexToByteArraySlowPath;
         int index$iv = charIndex + 2;
         String partName$iv = "byte suffix";
         int $i$f$checkContainsAt = 0;
         if (((CharSequence)byteSuffix).length() == 0) {
            var41 = index$iv;
         } else {
            int i$iv = 0;

            for(int var22 = ((CharSequence)byteSuffix).length(); i$iv < var22; ++i$iv) {
               if (!CharsKt.equals(byteSuffix.charAt(i$iv), $this$checkContainsAt$iv.charAt(index$iv + i$iv), ignoreCase)) {
                  throwNotContainedAt($this$checkContainsAt$iv, index$iv, endIndex, byteSuffix, partName$iv);
               }
            }

            var41 = index$iv + byteSuffix.length();
         }
      }

      byte[] var42;
      if (byteIndex == byteArray.length) {
         var42 = byteArray;
      } else {
         var42 = Arrays.copyOf(byteArray, byteIndex);
         Intrinsics.checkNotNullExpressionValue(var42, "copyOf(...)");
      }

      return var42;
   }

   private static final byte parseByteAt(String $this$parseByteAt, int index) {
      int $i$f$decimalFromHexDigitAt = 0;
      int code$iv = $this$parseByteAt.charAt(index);
      if (code$iv >>> 8 == 0 && HEX_DIGITS_TO_DECIMAL[code$iv] >= 0) {
         int high = HEX_DIGITS_TO_DECIMAL[code$iv];
         code$iv = index + 1;
         boolean var6 = false;
         int code$iv = $this$parseByteAt.charAt(code$iv);
         if (code$iv >>> 8 == 0 && HEX_DIGITS_TO_DECIMAL[code$iv] >= 0) {
            int $this$decimalFromHexDigitAt$iv = HEX_DIGITS_TO_DECIMAL[code$iv];
            return (byte)(high << 4 | $this$decimalFromHexDigitAt$iv);
         } else {
            throwInvalidDigitAt($this$parseByteAt, code$iv);
            throw new KotlinNothingValueException();
         }
      } else {
         throwInvalidDigitAt($this$parseByteAt, index);
         throw new KotlinNothingValueException();
      }
   }

   public static final int parsedByteArrayMaxSize(int stringLength, int bytesPerLine, int bytesPerGroup, int groupSeparatorLength, int byteSeparatorLength, int bytePrefixLength, int byteSuffixLength) {
      boolean var7 = stringLength > 0;
      if (!var7) {
         String var8 = "Failed requirement.";
         throw new IllegalArgumentException(var8.toString());
      } else {
         long charsPerByte = (long)bytePrefixLength + 2L + (long)byteSuffixLength;
         long charsPerGroup = charsPerSet(charsPerByte, bytesPerGroup, byteSeparatorLength);
         long var10000;
         if (bytesPerLine <= bytesPerGroup) {
            var10000 = charsPerSet(charsPerByte, bytesPerLine, byteSeparatorLength);
         } else {
            int groupsPerLine = bytesPerLine / bytesPerGroup;
            long result = charsPerSet(charsPerGroup, groupsPerLine, groupSeparatorLength);
            int bytesPerLastGroupInLine = bytesPerLine % bytesPerGroup;
            if (bytesPerLastGroupInLine != 0) {
               result += (long)groupSeparatorLength;
               result += charsPerSet(charsPerByte, bytesPerLastGroupInLine, byteSeparatorLength);
            }

            var10000 = result;
         }

         long charsPerLine = var10000;
         long numberOfChars = (long)stringLength;
         long wholeLines = wholeElementsPerSet(numberOfChars, charsPerLine, 1);
         numberOfChars -= wholeLines * (charsPerLine + 1L);
         long wholeGroupsInLastLine = wholeElementsPerSet(numberOfChars, charsPerGroup, groupSeparatorLength);
         numberOfChars -= wholeGroupsInLastLine * (charsPerGroup + (long)groupSeparatorLength);
         long wholeBytesInLastGroup = wholeElementsPerSet(numberOfChars, charsPerByte, byteSeparatorLength);
         numberOfChars -= wholeBytesInLastGroup * (charsPerByte + (long)byteSeparatorLength);
         int spare = numberOfChars > 0L ? 1 : 0;
         return (int)(wholeLines * (long)bytesPerLine + wholeGroupsInLastLine * (long)bytesPerGroup + wholeBytesInLastGroup + (long)spare);
      }
   }

   private static final long charsPerSet(long charsPerElement, int elementsPerSet, int elementSeparatorLength) {
      boolean var4 = elementsPerSet > 0;
      if (!var4) {
         String var5 = "Failed requirement.";
         throw new IllegalArgumentException(var5.toString());
      } else {
         return charsPerElement * (long)elementsPerSet + (long)elementSeparatorLength * ((long)elementsPerSet - 1L);
      }
   }

   private static final long wholeElementsPerSet(long charsPerSet, long charsPerElement, int elementSeparatorLength) {
      return charsPerSet > 0L && charsPerElement > 0L ? (charsPerSet + (long)elementSeparatorLength) / (charsPerElement + (long)elementSeparatorLength) : 0L;
   }

   private static final int checkNewLineAt(String $this$checkNewLineAt, int index, int endIndex) {
      int var10000;
      if ($this$checkNewLineAt.charAt(index) == '\r') {
         var10000 = index + 1 < endIndex && $this$checkNewLineAt.charAt(index + 1) == '\n' ? index + 2 : index + 1;
      } else {
         if ($this$checkNewLineAt.charAt(index) != '\n') {
            throw new NumberFormatException("Expected a new line at index " + index + ", but was " + $this$checkNewLineAt.charAt(index));
         }

         var10000 = index + 1;
      }

      return var10000;
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final String toHexString(byte $this$toHexString, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      String digits = format.getUpperCase() ? "0123456789ABCDEF" : "0123456789abcdef";
      HexFormat.NumberHexFormat numberFormat = format.getNumber();
      if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
         char[] charArray = new char[2];
         charArray[0] = digits.charAt($this$toHexString >> 4 & 15);
         charArray[1] = digits.charAt($this$toHexString & 15);
         return numberFormat.getRemoveLeadingZeros() ? StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros($this$toHexString & 255) - 24 >> 2, 1), 0, 2, (Object)null) : StringsKt.concatToString(charArray);
      } else {
         return toHexStringImpl((long)$this$toHexString, numberFormat, digits, 8);
      }
   }

   // $FF: synthetic method
   public static String toHexString$default(byte var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return toHexString(var0, var1);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   public static final byte hexToByte(@NotNull String $this$hexToByte, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToByte, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return hexToByte($this$hexToByte, 0, $this$hexToByte.length(), format);
   }

   // $FF: synthetic method
   public static byte hexToByte$default(String var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return hexToByte(var0, var1);
   }

   @ExperimentalStdlibApi
   private static final byte hexToByte(String $this$hexToByte, int startIndex, int endIndex, HexFormat format) {
      return (byte)hexToIntImpl($this$hexToByte, startIndex, endIndex, format, 2);
   }

   // $FF: synthetic method
   static byte hexToByte$default(String var0, int var1, int var2, HexFormat var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length();
      }

      if ((var4 & 4) != 0) {
         var3 = HexFormat.Companion.getDefault();
      }

      return hexToByte(var0, var1, var2, var3);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final String toHexString(short $this$toHexString, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      String digits = format.getUpperCase() ? "0123456789ABCDEF" : "0123456789abcdef";
      HexFormat.NumberHexFormat numberFormat = format.getNumber();
      if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
         char[] charArray = new char[4];
         charArray[0] = digits.charAt($this$toHexString >> 12 & 15);
         charArray[1] = digits.charAt($this$toHexString >> 8 & 15);
         charArray[2] = digits.charAt($this$toHexString >> 4 & 15);
         charArray[3] = digits.charAt($this$toHexString & 15);
         return numberFormat.getRemoveLeadingZeros() ? StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros($this$toHexString & '\uffff') - 16 >> 2, 3), 0, 2, (Object)null) : StringsKt.concatToString(charArray);
      } else {
         return toHexStringImpl((long)$this$toHexString, numberFormat, digits, 16);
      }
   }

   // $FF: synthetic method
   public static String toHexString$default(short var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return toHexString(var0, var1);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   public static final short hexToShort(@NotNull String $this$hexToShort, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToShort, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return hexToShort($this$hexToShort, 0, $this$hexToShort.length(), format);
   }

   // $FF: synthetic method
   public static short hexToShort$default(String var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return hexToShort(var0, var1);
   }

   @ExperimentalStdlibApi
   private static final short hexToShort(String $this$hexToShort, int startIndex, int endIndex, HexFormat format) {
      return (short)hexToIntImpl($this$hexToShort, startIndex, endIndex, format, 4);
   }

   // $FF: synthetic method
   static short hexToShort$default(String var0, int var1, int var2, HexFormat var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length();
      }

      if ((var4 & 4) != 0) {
         var3 = HexFormat.Companion.getDefault();
      }

      return hexToShort(var0, var1, var2, var3);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final String toHexString(int $this$toHexString, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      String digits = format.getUpperCase() ? "0123456789ABCDEF" : "0123456789abcdef";
      HexFormat.NumberHexFormat numberFormat = format.getNumber();
      if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
         char[] charArray = new char[8];
         charArray[0] = digits.charAt($this$toHexString >> 28 & 15);
         charArray[1] = digits.charAt($this$toHexString >> 24 & 15);
         charArray[2] = digits.charAt($this$toHexString >> 20 & 15);
         charArray[3] = digits.charAt($this$toHexString >> 16 & 15);
         charArray[4] = digits.charAt($this$toHexString >> 12 & 15);
         charArray[5] = digits.charAt($this$toHexString >> 8 & 15);
         charArray[6] = digits.charAt($this$toHexString >> 4 & 15);
         charArray[7] = digits.charAt($this$toHexString & 15);
         return numberFormat.getRemoveLeadingZeros() ? StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros($this$toHexString) >> 2, 7), 0, 2, (Object)null) : StringsKt.concatToString(charArray);
      } else {
         return toHexStringImpl((long)$this$toHexString, numberFormat, digits, 32);
      }
   }

   // $FF: synthetic method
   public static String toHexString$default(int var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return toHexString(var0, var1);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   public static final int hexToInt(@NotNull String $this$hexToInt, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToInt, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return hexToInt($this$hexToInt, 0, $this$hexToInt.length(), format);
   }

   // $FF: synthetic method
   public static int hexToInt$default(String var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return hexToInt(var0, var1);
   }

   @ExperimentalStdlibApi
   private static final int hexToInt(String $this$hexToInt, int startIndex, int endIndex, HexFormat format) {
      return hexToIntImpl($this$hexToInt, startIndex, endIndex, format, 8);
   }

   // $FF: synthetic method
   static int hexToInt$default(String var0, int var1, int var2, HexFormat var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length();
      }

      if ((var4 & 4) != 0) {
         var3 = HexFormat.Companion.getDefault();
      }

      return hexToInt(var0, var1, var2, var3);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final String toHexString(long $this$toHexString, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter(format, "format");
      String digits = format.getUpperCase() ? "0123456789ABCDEF" : "0123456789abcdef";
      HexFormat.NumberHexFormat numberFormat = format.getNumber();
      if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
         char[] charArray = new char[16];
         charArray[0] = digits.charAt((int)($this$toHexString >> 60 & 15L));
         charArray[1] = digits.charAt((int)($this$toHexString >> 56 & 15L));
         charArray[2] = digits.charAt((int)($this$toHexString >> 52 & 15L));
         charArray[3] = digits.charAt((int)($this$toHexString >> 48 & 15L));
         charArray[4] = digits.charAt((int)($this$toHexString >> 44 & 15L));
         charArray[5] = digits.charAt((int)($this$toHexString >> 40 & 15L));
         charArray[6] = digits.charAt((int)($this$toHexString >> 36 & 15L));
         charArray[7] = digits.charAt((int)($this$toHexString >> 32 & 15L));
         charArray[8] = digits.charAt((int)($this$toHexString >> 28 & 15L));
         charArray[9] = digits.charAt((int)($this$toHexString >> 24 & 15L));
         charArray[10] = digits.charAt((int)($this$toHexString >> 20 & 15L));
         charArray[11] = digits.charAt((int)($this$toHexString >> 16 & 15L));
         charArray[12] = digits.charAt((int)($this$toHexString >> 12 & 15L));
         charArray[13] = digits.charAt((int)($this$toHexString >> 8 & 15L));
         charArray[14] = digits.charAt((int)($this$toHexString >> 4 & 15L));
         charArray[15] = digits.charAt((int)($this$toHexString & 15L));
         return numberFormat.getRemoveLeadingZeros() ? StringsKt.concatToString$default(charArray, RangesKt.coerceAtMost(Long.numberOfLeadingZeros($this$toHexString) >> 2, 15), 0, 2, (Object)null) : StringsKt.concatToString(charArray);
      } else {
         return toHexStringImpl($this$toHexString, numberFormat, digits, 64);
      }
   }

   // $FF: synthetic method
   public static String toHexString$default(long var0, HexFormat var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var2 = HexFormat.Companion.getDefault();
      }

      return toHexString(var0, var2);
   }

   @ExperimentalStdlibApi
   @SinceKotlin(
      version = "1.9"
   )
   public static final long hexToLong(@NotNull String $this$hexToLong, @NotNull HexFormat format) {
      Intrinsics.checkNotNullParameter($this$hexToLong, "<this>");
      Intrinsics.checkNotNullParameter(format, "format");
      return hexToLong($this$hexToLong, 0, $this$hexToLong.length(), format);
   }

   // $FF: synthetic method
   public static long hexToLong$default(String var0, HexFormat var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = HexFormat.Companion.getDefault();
      }

      return hexToLong(var0, var1);
   }

   @ExperimentalStdlibApi
   private static final long hexToLong(String $this$hexToLong, int startIndex, int endIndex, HexFormat format) {
      return hexToLongImpl($this$hexToLong, startIndex, endIndex, format, 16);
   }

   // $FF: synthetic method
   static long hexToLong$default(String var0, int var1, int var2, HexFormat var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length();
      }

      if ((var4 & 4) != 0) {
         var3 = HexFormat.Companion.getDefault();
      }

      return hexToLong(var0, var1, var2, var3);
   }

   @ExperimentalStdlibApi
   private static final String toHexStringImpl(long $this$toHexStringImpl, HexFormat.NumberHexFormat numberFormat, String digits, int bits) {
      boolean var5 = (bits & 3) == 0;
      if (!var5) {
         String var6 = "Failed requirement.";
         throw new IllegalArgumentException(var6.toString());
      } else {
         long value = $this$toHexStringImpl;
         int numberOfHexDigits = bits >> 2;
         String prefix = numberFormat.getPrefix();
         String suffix = numberFormat.getSuffix();
         boolean removeZeros = false;
         removeZeros = numberFormat.getRemoveLeadingZeros();
         long formatLength = (long)prefix.length() + (long)numberOfHexDigits + (long)suffix.length();
         char[] charArray = new char[checkFormatLength(formatLength)];
         int charIndex = 0;
         charIndex = toCharArrayIfNotEmpty(prefix, charArray, 0);
         int shift = 0;
         shift = bits;

         for(int var16 = 0; var16 < numberOfHexDigits; ++var16) {
            int var18 = 0;
            shift -= 4;
            int decimal = (int)(value >> shift & 15L);
            removeZeros = removeZeros && decimal == 0 && shift > 0;
            if (!removeZeros) {
               int var20 = charIndex++;
               charArray[var20] = digits.charAt(decimal);
            }
         }

         charIndex = toCharArrayIfNotEmpty(suffix, charArray, charIndex);
         return charIndex == charArray.length ? StringsKt.concatToString(charArray) : StringsKt.concatToString$default(charArray, 0, charIndex, 1, (Object)null);
      }
   }

   private static final int toCharArrayIfNotEmpty(String $this$toCharArrayIfNotEmpty, char[] destination, int destinationOffset) {
      switch ($this$toCharArrayIfNotEmpty.length()) {
         case 0:
            break;
         case 1:
            destination[destinationOffset] = $this$toCharArrayIfNotEmpty.charAt(0);
            break;
         default:
            byte var4 = 0;
            int var5 = $this$toCharArrayIfNotEmpty.length();
            Intrinsics.checkNotNull($this$toCharArrayIfNotEmpty, "null cannot be cast to non-null type java.lang.String");
            $this$toCharArrayIfNotEmpty.getChars(var4, var5, destination, destinationOffset);
      }

      return destinationOffset + $this$toCharArrayIfNotEmpty.length();
   }

   @ExperimentalStdlibApi
   private static final int hexToIntImpl(String $this$hexToIntImpl, int startIndex, int endIndex, HexFormat format, int maxDigits) {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$hexToIntImpl.length());
      HexFormat.NumberHexFormat numberFormat = format.getNumber();
      if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
         checkMaxDigits($this$hexToIntImpl, startIndex, endIndex, maxDigits);
         return parseInt($this$hexToIntImpl, startIndex, endIndex);
      } else {
         String prefix = numberFormat.getPrefix();
         String suffix = numberFormat.getSuffix();
         checkPrefixSuffixMaxDigits($this$hexToIntImpl, startIndex, endIndex, prefix, suffix, numberFormat.getIgnoreCase$kotlin_stdlib(), maxDigits);
         return parseInt($this$hexToIntImpl, startIndex + prefix.length(), endIndex - suffix.length());
      }
   }

   @ExperimentalStdlibApi
   private static final long hexToLongImpl(String $this$hexToLongImpl, int startIndex, int endIndex, HexFormat format, int maxDigits) {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$hexToLongImpl.length());
      HexFormat.NumberHexFormat numberFormat = format.getNumber();
      if (numberFormat.isDigitsOnly$kotlin_stdlib()) {
         checkMaxDigits($this$hexToLongImpl, startIndex, endIndex, maxDigits);
         return parseLong($this$hexToLongImpl, startIndex, endIndex);
      } else {
         String prefix = numberFormat.getPrefix();
         String suffix = numberFormat.getSuffix();
         checkPrefixSuffixMaxDigits($this$hexToLongImpl, startIndex, endIndex, prefix, suffix, numberFormat.getIgnoreCase$kotlin_stdlib(), maxDigits);
         return parseLong($this$hexToLongImpl, startIndex + prefix.length(), endIndex - suffix.length());
      }
   }

   private static final void checkPrefixSuffixMaxDigits(String $this$checkPrefixSuffixMaxDigits, int startIndex, int endIndex, String prefix, String suffix, boolean ignoreCase, int maxDigits) {
      if (endIndex - startIndex - prefix.length() <= suffix.length()) {
         throwInvalidPrefixSuffix($this$checkPrefixSuffixMaxDigits, startIndex, endIndex, prefix, suffix);
      }

      String $this$checkContainsAt$iv = $this$checkPrefixSuffixMaxDigits;
      String partName$iv = "prefix";
      int $i$f$checkContainsAt = 0;
      int var10000;
      if (((CharSequence)prefix).length() == 0) {
         var10000 = startIndex;
      } else {
         int i$iv = 0;

         for(int var12 = ((CharSequence)prefix).length(); i$iv < var12; ++i$iv) {
            if (!CharsKt.equals(prefix.charAt(i$iv), $this$checkContainsAt$iv.charAt(startIndex + i$iv), ignoreCase)) {
               throwNotContainedAt($this$checkContainsAt$iv, startIndex, endIndex, prefix, partName$iv);
            }
         }

         var10000 = startIndex + prefix.length();
      }

      int digitsStartIndex = var10000;
      int digitsEndIndex = endIndex - suffix.length();
      partName$iv = $this$checkPrefixSuffixMaxDigits;
      String partName$iv = "suffix";
      int $i$f$checkContainsAt = 0;
      if (((CharSequence)suffix).length() != 0) {
         int i$iv = 0;

         for(int var13 = ((CharSequence)suffix).length(); i$iv < var13; ++i$iv) {
            if (!CharsKt.equals(suffix.charAt(i$iv), partName$iv.charAt(digitsEndIndex + i$iv), ignoreCase)) {
               throwNotContainedAt(partName$iv, digitsEndIndex, endIndex, suffix, partName$iv);
            }
         }

         var10000 = digitsEndIndex + suffix.length();
      }

      checkMaxDigits($this$checkPrefixSuffixMaxDigits, digitsStartIndex, digitsEndIndex, maxDigits);
   }

   private static final void checkMaxDigits(String $this$checkMaxDigits, int startIndex, int endIndex, int maxDigits) {
      if (startIndex >= endIndex || endIndex - startIndex > maxDigits) {
         throwInvalidNumberOfDigits($this$checkMaxDigits, startIndex, endIndex, maxDigits, false);
      }

   }

   private static final int parseInt(String $this$parseInt, int startIndex, int endIndex) {
      int result = 0;

      for(int i = startIndex; i < endIndex; ++i) {
         int var10000 = result << 4;
         int $i$f$decimalFromHexDigitAt = 0;
         int code$iv = $this$parseInt.charAt(i);
         if (code$iv >>> 8 != 0 || HEX_DIGITS_TO_DECIMAL[code$iv] < 0) {
            throwInvalidDigitAt($this$parseInt, i);
            throw new KotlinNothingValueException();
         }

         result = var10000 | HEX_DIGITS_TO_DECIMAL[code$iv];
      }

      return result;
   }

   private static final long parseLong(String $this$parseLong, int startIndex, int endIndex) {
      long result = 0L;

      for(int i = startIndex; i < endIndex; ++i) {
         long var10000 = result << 4;
         int $i$f$longDecimalFromHexDigitAt = 0;
         int code$iv = $this$parseLong.charAt(i);
         if (code$iv >>> 8 != 0 || HEX_DIGITS_TO_LONG_DECIMAL[code$iv] < 0L) {
            throwInvalidDigitAt($this$parseLong, i);
            throw new KotlinNothingValueException();
         }

         result = var10000 | HEX_DIGITS_TO_LONG_DECIMAL[code$iv];
      }

      return result;
   }

   private static final int checkContainsAt(String $this$checkContainsAt, int index, int endIndex, String part, boolean ignoreCase, String partName) {
      int $i$f$checkContainsAt = 0;
      if (((CharSequence)part).length() == 0) {
         return index;
      } else {
         int i = 0;

         for(int var8 = ((CharSequence)part).length(); i < var8; ++i) {
            if (!CharsKt.equals(part.charAt(i), $this$checkContainsAt.charAt(index + i), ignoreCase)) {
               throwNotContainedAt($this$checkContainsAt, index, endIndex, part, partName);
            }
         }

         return index + part.length();
      }
   }

   private static final int decimalFromHexDigitAt(String $this$decimalFromHexDigitAt, int index) {
      int $i$f$decimalFromHexDigitAt = 0;
      int code = $this$decimalFromHexDigitAt.charAt(index);
      if (code >>> 8 == 0 && HEX_DIGITS_TO_DECIMAL[code] >= 0) {
         return HEX_DIGITS_TO_DECIMAL[code];
      } else {
         throwInvalidDigitAt($this$decimalFromHexDigitAt, index);
         throw new KotlinNothingValueException();
      }
   }

   private static final long longDecimalFromHexDigitAt(String $this$longDecimalFromHexDigitAt, int index) {
      int $i$f$longDecimalFromHexDigitAt = 0;
      int code = $this$longDecimalFromHexDigitAt.charAt(index);
      if (code >>> 8 == 0 && HEX_DIGITS_TO_LONG_DECIMAL[code] >= 0L) {
         return HEX_DIGITS_TO_LONG_DECIMAL[code];
      } else {
         throwInvalidDigitAt($this$longDecimalFromHexDigitAt, index);
         throw new KotlinNothingValueException();
      }
   }

   private static final void throwInvalidNumberOfDigits(String $this$throwInvalidNumberOfDigits, int startIndex, int endIndex, int maxDigits, boolean requireMaxLength) {
      String specifier = requireMaxLength ? "exactly" : "at most";
      Intrinsics.checkNotNull($this$throwInvalidNumberOfDigits, "null cannot be cast to non-null type java.lang.String");
      String var10000 = $this$throwInvalidNumberOfDigits.substring(startIndex, endIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      String substring = var10000;
      throw new NumberFormatException("Expected " + specifier + ' ' + maxDigits + " hexadecimal digits at index " + startIndex + ", but was " + substring + " of length " + (endIndex - startIndex));
   }

   private static final void throwNotContainedAt(String $this$throwNotContainedAt, int index, int endIndex, String part, String partName) {
      int var7 = RangesKt.coerceAtMost(index + part.length(), endIndex);
      Intrinsics.checkNotNull($this$throwNotContainedAt, "null cannot be cast to non-null type java.lang.String");
      String var10000 = $this$throwNotContainedAt.substring(index, var7);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      String substring = var10000;
      throw new NumberFormatException("Expected " + partName + " \"" + part + "\" at index " + index + ", but was " + substring);
   }

   private static final void throwInvalidPrefixSuffix(String $this$throwInvalidPrefixSuffix, int startIndex, int endIndex, String prefix, String suffix) {
      Intrinsics.checkNotNull($this$throwInvalidPrefixSuffix, "null cannot be cast to non-null type java.lang.String");
      String var10000 = $this$throwInvalidPrefixSuffix.substring(startIndex, endIndex);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      String substring = var10000;
      throw new NumberFormatException("Expected a hexadecimal number with prefix \"" + prefix + "\" and suffix \"" + suffix + "\", but was " + substring);
   }

   private static final Void throwInvalidDigitAt(String $this$throwInvalidDigitAt, int index) {
      throw new NumberFormatException("Expected a hexadecimal digit at index " + index + ", but was " + $this$throwInvalidDigitAt.charAt(index));
   }

   static {
      int var0 = 0;

      int[] $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242;
      for($this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242 = new int[256]; var0 < 256; ++var0) {
         $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[var0] = "0123456789abcdef".charAt(var0 >> 4) << 8 | "0123456789abcdef".charAt(var0 & 15);
      }

      BYTE_TO_LOWER_CASE_HEX_DIGITS = $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242;
      var0 = 0;

      for($this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242 = new int[256]; var0 < 256; ++var0) {
         $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[var0] = "0123456789ABCDEF".charAt(var0 >> 4) << 8 | "0123456789ABCDEF".charAt(var0 & 15);
      }

      BYTE_TO_UPPER_CASE_HEX_DIGITS = $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242;
      var0 = 0;

      for($this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242 = new int[256]; var0 < 256; ++var0) {
         $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[var0] = -1;
      }

      $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242 = $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242;
      int var2 = 0;
      CharSequence $this$forEachIndexed$iv = (CharSequence)"0123456789abcdef";
      int $i$f$forEachIndexed = 0;
      int index$iv = 0;

      for(int var6 = 0; var6 < $this$forEachIndexed$iv.length(); ++var6) {
         char item$iv = $this$forEachIndexed$iv.charAt(var6);
         int index = index$iv++;
         int var10 = 0;
         $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[item$iv] = index;
      }

      $this$forEachIndexed$iv = (CharSequence)"0123456789ABCDEF";
      $i$f$forEachIndexed = 0;
      index$iv = 0;

      for(int var31 = 0; var31 < $this$forEachIndexed$iv.length(); ++var31) {
         char item$iv = $this$forEachIndexed$iv.charAt(var31);
         int index = index$iv++;
         int var40 = 0;
         $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242[item$iv] = index;
      }

      HEX_DIGITS_TO_DECIMAL = $this$HEX_DIGITS_TO_DECIMAL_u24lambda_u242;
      var0 = 0;

      for(var19 = new long[256]; var0 < 256; ++var0) {
         var19[var0] = -1L;
      }

      long[] $this$HEX_DIGITS_TO_LONG_DECIMAL_u24lambda_u245 = var19;
      var2 = 0;
      $this$forEachIndexed$iv = (CharSequence)"0123456789abcdef";
      $i$f$forEachIndexed = 0;
      index$iv = 0;

      for(int var32 = 0; var32 < $this$forEachIndexed$iv.length(); ++var32) {
         char item$iv = $this$forEachIndexed$iv.charAt(var32);
         int index = index$iv++;
         int var41 = 0;
         $this$HEX_DIGITS_TO_LONG_DECIMAL_u24lambda_u245[item$iv] = (long)index;
      }

      $this$forEachIndexed$iv = (CharSequence)"0123456789ABCDEF";
      $i$f$forEachIndexed = 0;
      index$iv = 0;

      for(int var33 = 0; var33 < $this$forEachIndexed$iv.length(); ++var33) {
         char item$iv = $this$forEachIndexed$iv.charAt(var33);
         int index = index$iv++;
         int var42 = 0;
         $this$HEX_DIGITS_TO_LONG_DECIMAL_u24lambda_u245[item$iv] = (long)index;
      }

      HEX_DIGITS_TO_LONG_DECIMAL = var19;
   }
}
