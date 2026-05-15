package kotlin.io.path;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000Ò\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001a,\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00002\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0006\"\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\b\u001aA\u0010\r\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00002\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a8\u0010\r\u001a\u00020\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00002\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0087\b¢\u0006\u0004\b\r\u0010\u000f\u001aM\u0010\u0011\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00002\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001aD\u0010\u0011\u001a\u00020\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00002\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0087\b¢\u0006\u0004\b\u0011\u0010\u0013\u001a#\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0001\u001a\u00020\u00022\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a;\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\u0002\b\u001cH\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u001f\u0010 \u001a\u0014\u0010!\u001a\u00020\u0002*\u00020\u0002H\u0087\b¢\u0006\u0004\b!\u0010\"\u001a\u0014\u0010#\u001a\u00020\u0000*\u00020\u0002H\u0087\b¢\u0006\u0004\b#\u0010$\u001a0\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0\u0006\"\u00020&H\u0087\b¢\u0006\u0004\b(\u0010)\u001a&\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\b\b\u0002\u0010+\u001a\u00020*H\u0087\b¢\u0006\u0004\b(\u0010,\u001a0\u0010-\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0087\b¢\u0006\u0004\b-\u0010.\u001a0\u0010/\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0087\b¢\u0006\u0004\b/\u0010.\u001a0\u00100\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0087\b¢\u0006\u0004\b0\u0010.\u001a\u001c\u00101\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b1\u00102\u001a/\u00103\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0007¢\u0006\u0004\b3\u0010.\u001a8\u00104\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0006\"\u0006\u0012\u0002\b\u00030\u000bH\u0087\b¢\u0006\u0004\b4\u00105\u001a\u0014\u00106\u001a\u00020\u001b*\u00020\u0002H\u0087\b¢\u0006\u0004\b6\u00107\u001a\u0014\u00108\u001a\u00020**\u00020\u0002H\u0087\b¢\u0006\u0004\b8\u00109\u001a\u001c\u0010;\u001a\u00020\u0002*\u00020\u00022\u0006\u0010:\u001a\u00020\u0002H\u0087\n¢\u0006\u0004\b;\u00102\u001a\u001c\u0010;\u001a\u00020\u0002*\u00020\u00022\u0006\u0010:\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b;\u0010<\u001a(\u0010>\u001a\u00020**\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\b>\u0010?\u001a4\u0010B\u001a\u00028\u0000\"\n\b\u0000\u0010A\u0018\u0001*\u00020@*\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bB\u0010C\u001a6\u0010D\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010A\u0018\u0001*\u00020@*\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bD\u0010C\u001a\u0014\u0010F\u001a\u00020E*\u00020\u0002H\u0087\b¢\u0006\u0004\bF\u0010G\u001a\u0014\u0010I\u001a\u00020H*\u00020\u0002H\u0087\b¢\u0006\u0004\bI\u0010J\u001a5\u0010M\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010K\u001a\u00020\u00002\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u001b0\u0019H\u0087\bø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a2\u0010Q\u001a\u0004\u0018\u00010P*\u00020\u00022\u0006\u0010O\u001a\u00020\u00002\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bQ\u0010R\u001a(\u0010T\u001a\u00020S*\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bT\u0010U\u001a*\u0010W\u001a\u0004\u0018\u00010V*\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bW\u0010X\u001a.\u0010[\u001a\b\u0012\u0004\u0012\u00020Z0Y*\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\b[\u0010\\\u001a(\u0010]\u001a\u00020**\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\b]\u0010?\u001a\u0014\u0010^\u001a\u00020**\u00020\u0002H\u0087\b¢\u0006\u0004\b^\u00109\u001a\u0014\u0010_\u001a\u00020**\u00020\u0002H\u0087\b¢\u0006\u0004\b_\u00109\u001a\u0014\u0010`\u001a\u00020**\u00020\u0002H\u0087\b¢\u0006\u0004\b`\u00109\u001a(\u0010a\u001a\u00020**\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\ba\u0010?\u001a\u001c\u0010b\u001a\u00020**\u00020\u00022\u0006\u0010:\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\bb\u0010c\u001a\u0014\u0010d\u001a\u00020**\u00020\u0002H\u0087\b¢\u0006\u0004\bd\u00109\u001a\u0014\u0010e\u001a\u00020**\u00020\u0002H\u0087\b¢\u0006\u0004\be\u00109\u001a#\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00020f*\u00020\u00022\b\b\u0002\u0010K\u001a\u00020\u0000H\u0007¢\u0006\u0004\bg\u0010h\u001a0\u0010i\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0\u0006\"\u00020&H\u0087\b¢\u0006\u0004\bi\u0010)\u001a&\u0010i\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\b\b\u0002\u0010+\u001a\u00020*H\u0087\b¢\u0006\u0004\bi\u0010,\u001a(\u0010j\u001a\u00020**\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bj\u0010?\u001a4\u0010m\u001a\u00028\u0000\"\n\b\u0000\u0010l\u0018\u0001*\u00020k*\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bm\u0010n\u001a>\u0010m\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010P0o*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00002\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bm\u0010p\u001a\u0014\u0010q\u001a\u00020\u0002*\u00020\u0002H\u0087\b¢\u0006\u0004\bq\u0010\"\u001a\u001b\u0010r\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\br\u00102\u001a\u001d\u0010s\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\bs\u00102\u001a\u001b\u0010t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\bt\u00102\u001a:\u0010v\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u00002\b\u0010u\u001a\u0004\u0018\u00010P2\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0\u0006\"\u00020=H\u0087\b¢\u0006\u0004\bv\u0010w\u001a\u001c\u0010x\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020SH\u0087\b¢\u0006\u0004\bx\u0010y\u001a\u001c\u0010z\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020VH\u0087\b¢\u0006\u0004\bz\u0010{\u001a\"\u0010|\u001a\u00020\u0002*\u00020\u00022\f\u0010u\u001a\b\u0012\u0004\u0012\u00020Z0YH\u0087\b¢\u0006\u0004\b|\u0010}\u001a\u0015\u0010\u007f\u001a\u00020\u0002*\u00020~H\u0087\b¢\u0006\u0005\b\u007f\u0010\u0080\u0001\u001aG\u0010\u0084\u0001\u001a\u00028\u0000\"\u0005\b\u0000\u0010\u0081\u0001*\u00020\u00022\b\b\u0002\u0010K\u001a\u00020\u00002\u001a\u0010\u0083\u0001\u001a\u0015\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00020\u0082\u0001\u0012\u0004\u0012\u00028\u00000\u0019H\u0087\bø\u0001\u0000¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001\u001a<\u0010\u008a\u0001\u001a\u00020\u001b*\u00020\u00022\r\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\n\b\u0002\u0010\u0088\u0001\u001a\u00030\u0087\u00012\t\b\u0002\u0010\u0089\u0001\u001a\u00020*H\u0007¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001\u001aS\u0010\u008a\u0001\u001a\u00020\u001b*\u00020\u00022\n\b\u0002\u0010\u0088\u0001\u001a\u00030\u0087\u00012\t\b\u0002\u0010\u0089\u0001\u001a\u00020*2\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\u0002\b\u001cH\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0006\b\u008a\u0001\u0010\u008c\u0001\u001a3\u0010\u008e\u0001\u001a\t\u0012\u0004\u0012\u00020\u00020\u0082\u0001*\u00020\u00022\u0014\u0010'\u001a\u000b\u0012\u0007\b\u0001\u0012\u00030\u008d\u00010\u0006\"\u00030\u008d\u0001H\u0007¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001\"!\u0010\u0092\u0001\u001a\u00020\u0000*\u00020\u00028FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0091\u0001\u00107\u001a\u0005\b\u0090\u0001\u0010$\"\"\u0010\u0095\u0001\u001a\u00020\u0000*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0094\u0001\u00107\u001a\u0005\b\u0093\u0001\u0010$\"!\u0010\u0098\u0001\u001a\u00020\u0000*\u00020\u00028FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0097\u0001\u00107\u001a\u0005\b\u0096\u0001\u0010$\"!\u0010\u009b\u0001\u001a\u00020\u0000*\u00020\u00028FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u009a\u0001\u00107\u001a\u0005\b\u0099\u0001\u0010$\"!\u0010\u009e\u0001\u001a\u00020\u0000*\u00020\u00028FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u009d\u0001\u00107\u001a\u0005\b\u009c\u0001\u0010$\"\"\u0010¡\u0001\u001a\u00020\u0000*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\u000e\u0012\u0005\b \u0001\u00107\u001a\u0005\b\u009f\u0001\u0010$\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¢\u0001"},
   d2 = {"", "path", "Ljava/nio/file/Path;", "Path", "(Ljava/lang/String;)Ljava/nio/file/Path;", "base", "", "subpaths", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", "directory", "prefix", "Ljava/nio/file/attribute/FileAttribute;", "attributes", "createTempDirectory", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "suffix", "createTempFile", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "Ljava/lang/Class;", "attributeViewClass", "", "fileAttributeViewNotAvailable", "(Ljava/nio/file/Path;Ljava/lang/Class;)Ljava/lang/Void;", "Lkotlin/Function1;", "Lkotlin/io/path/FileVisitorBuilder;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "Ljava/nio/file/FileVisitor;", "fileVisitor", "(Lkotlin/jvm/functions/Function1;)Ljava/nio/file/FileVisitor;", "absolute", "(Ljava/nio/file/Path;)Ljava/nio/file/Path;", "absolutePathString", "(Ljava/nio/file/Path;)Ljava/lang/String;", "target", "Ljava/nio/file/CopyOption;", "options", "copyTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/CopyOption;)Ljava/nio/file/Path;", "", "overwrite", "(Ljava/nio/file/Path;Ljava/nio/file/Path;Z)Ljava/nio/file/Path;", "createDirectories", "(Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createDirectory", "createFile", "createLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;)Ljava/nio/file/Path;", "createParentDirectories", "createSymbolicLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "deleteExisting", "(Ljava/nio/file/Path;)V", "deleteIfExists", "(Ljava/nio/file/Path;)Z", "other", "div", "(Ljava/nio/file/Path;Ljava/lang/String;)Ljava/nio/file/Path;", "Ljava/nio/file/LinkOption;", "exists", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "Ljava/nio/file/attribute/FileAttributeView;", "V", "fileAttributesView", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileAttributeView;", "fileAttributesViewOrNull", "", "fileSize", "(Ljava/nio/file/Path;)J", "Ljava/nio/file/FileStore;", "fileStore", "(Ljava/nio/file/Path;)Ljava/nio/file/FileStore;", "glob", "action", "forEachDirectoryEntry", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "attribute", "", "getAttribute", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "Ljava/nio/file/attribute/FileTime;", "getLastModifiedTime", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileTime;", "Ljava/nio/file/attribute/UserPrincipal;", "getOwner", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/UserPrincipal;", "", "Ljava/nio/file/attribute/PosixFilePermission;", "getPosixFilePermissions", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;", "isDirectory", "isExecutable", "isHidden", "isReadable", "isRegularFile", "isSameFileAs", "(Ljava/nio/file/Path;Ljava/nio/file/Path;)Z", "isSymbolicLink", "isWritable", "", "listDirectoryEntries", "(Ljava/nio/file/Path;Ljava/lang/String;)Ljava/util/List;", "moveTo", "notExists", "Ljava/nio/file/attribute/BasicFileAttributes;", "A", "readAttributes", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/BasicFileAttributes;", "", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/util/Map;", "readSymbolicLink", "relativeTo", "relativeToOrNull", "relativeToOrSelf", "value", "setAttribute", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;", "setLastModifiedTime", "(Ljava/nio/file/Path;Ljava/nio/file/attribute/FileTime;)Ljava/nio/file/Path;", "setOwner", "(Ljava/nio/file/Path;Ljava/nio/file/attribute/UserPrincipal;)Ljava/nio/file/Path;", "setPosixFilePermissions", "(Ljava/nio/file/Path;Ljava/util/Set;)Ljava/nio/file/Path;", "Ljava/net/URI;", "toPath", "(Ljava/net/URI;)Ljava/nio/file/Path;", "T", "Lkotlin/sequences/Sequence;", "block", "useDirectoryEntries", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "visitor", "", "maxDepth", "followLinks", "visitFileTree", "(Ljava/nio/file/Path;Ljava/nio/file/FileVisitor;IZ)V", "(Ljava/nio/file/Path;IZLkotlin/jvm/functions/Function1;)V", "Lkotlin/io/path/PathWalkOption;", "walk", "(Ljava/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)Lkotlin/sequences/Sequence;", "getExtension", "getExtension$annotations", "extension", "getInvariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "invariantSeparatorsPath", "getInvariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "invariantSeparatorsPathString", "getName", "getName$annotations", "name", "getNameWithoutExtension", "getNameWithoutExtension$annotations", "nameWithoutExtension", "getPathString", "getPathString$annotations", "pathString", "kotlin-stdlib-jdk7"},
   xs = "kotlin/io/path/PathsKt"
)
@SourceDebugExtension({"SMAP\nPathUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1181:1\n1#2:1182\n1863#3,2:1183\n*S KotlinDebug\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n*L\n440#1:1183,2\n*E\n"})
class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
   @NotNull
   public static final String getName(@NotNull Path $this$name) {
      Intrinsics.checkNotNullParameter($this$name, "<this>");
      Path var10000 = $this$name.getFileName();
      String var1 = var10000 != null ? var10000.toString() : null;
      if (var1 == null) {
         var1 = "";
      }

      return var1;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   public static void getName$annotations(Path <this>) {
   }

   @NotNull
   public static final String getNameWithoutExtension(@NotNull Path $this$nameWithoutExtension) {
      Intrinsics.checkNotNullParameter($this$nameWithoutExtension, "<this>");
      Path var10000 = $this$nameWithoutExtension.getFileName();
      String var2;
      if (var10000 != null) {
         var2 = var10000.toString();
         if (var2 != null) {
            var2 = StringsKt.substringBeforeLast$default(var2, ".", (String)null, 2, (Object)null);
            if (var2 != null) {
               return var2;
            }
         }
      }

      var2 = "";
      return var2;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   public static void getNameWithoutExtension$annotations(Path <this>) {
   }

   @NotNull
   public static final String getExtension(@NotNull Path $this$extension) {
      Intrinsics.checkNotNullParameter($this$extension, "<this>");
      Path var10000 = $this$extension.getFileName();
      String var2;
      if (var10000 != null) {
         var2 = var10000.toString();
         if (var2 != null) {
            var2 = StringsKt.substringAfterLast(var2, '.', "");
            if (var2 != null) {
               return var2;
            }
         }
      }

      var2 = "";
      return var2;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   public static void getExtension$annotations(Path <this>) {
   }

   private static final String getPathString(Path $this$pathString) {
      Intrinsics.checkNotNullParameter($this$pathString, "<this>");
      return $this$pathString.toString();
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   public static void getPathString$annotations(Path <this>) {
   }

   @NotNull
   public static final String getInvariantSeparatorsPathString(@NotNull Path $this$invariantSeparatorsPathString) {
      Intrinsics.checkNotNullParameter($this$invariantSeparatorsPathString, "<this>");
      String separator = $this$invariantSeparatorsPathString.getFileSystem().getSeparator();
      String var2;
      if (!Intrinsics.areEqual((Object)separator, (Object)"/")) {
         var2 = $this$invariantSeparatorsPathString.toString();
         Intrinsics.checkNotNull(separator);
         var2 = StringsKt.replace$default(var2, separator, "/", false, 4, (Object)null);
      } else {
         var2 = $this$invariantSeparatorsPathString.toString();
      }

      return var2;
   }

   /** @deprecated */
   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   public static void getInvariantSeparatorsPathString$annotations(Path <this>) {
   }

   /** @deprecated */
   private static final String getInvariantSeparatorsPath(Path $this$invariantSeparatorsPath) {
      Intrinsics.checkNotNullParameter($this$invariantSeparatorsPath, "<this>");
      return PathsKt.getInvariantSeparatorsPathString($this$invariantSeparatorsPath);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Use invariantSeparatorsPathString property instead.",
      replaceWith = @ReplaceWith(
   expression = "invariantSeparatorsPathString",
   imports = {}
),
      level = DeprecationLevel.ERROR
   )
   @SinceKotlin(
      version = "1.4"
   )
   @ExperimentalPathApi
   @InlineOnly
   public static void getInvariantSeparatorsPath$annotations(Path <this>) {
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path absolute(Path $this$absolute) {
      Intrinsics.checkNotNullParameter($this$absolute, "<this>");
      Path var10000 = $this$absolute.toAbsolutePath();
      Intrinsics.checkNotNullExpressionValue(var10000, "toAbsolutePath(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final String absolutePathString(Path $this$absolutePathString) {
      Intrinsics.checkNotNullParameter($this$absolutePathString, "<this>");
      return $this$absolutePathString.toAbsolutePath().toString();
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @NotNull
   public static final Path relativeTo(@NotNull Path $this$relativeTo, @NotNull Path base) {
      Intrinsics.checkNotNullParameter($this$relativeTo, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");

      try {
         Path var2 = PathRelativizer.INSTANCE.tryRelativeTo($this$relativeTo, base);
         return var2;
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException(e.getMessage() + "\nthis path: " + $this$relativeTo + "\nbase path: " + base, (Throwable)e);
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @NotNull
   public static final Path relativeToOrSelf(@NotNull Path $this$relativeToOrSelf, @NotNull Path base) {
      Intrinsics.checkNotNullParameter($this$relativeToOrSelf, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");
      Path var10000 = PathsKt.relativeToOrNull($this$relativeToOrSelf, base);
      if (var10000 == null) {
         var10000 = $this$relativeToOrSelf;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @Nullable
   public static final Path relativeToOrNull(@NotNull Path $this$relativeToOrNull, @NotNull Path base) {
      Intrinsics.checkNotNullParameter($this$relativeToOrNull, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");

      Path var2;
      try {
         var2 = PathRelativizer.INSTANCE.tryRelativeTo($this$relativeToOrNull, base);
      } catch (IllegalArgumentException var4) {
         var2 = null;
      }

      return var2;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path copyTo(Path $this$copyTo, Path target, boolean overwrite) throws IOException {
      Intrinsics.checkNotNullParameter($this$copyTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      CopyOption[] var10000;
      if (overwrite) {
         CopyOption[] var4 = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
         var10000 = var4;
      } else {
         var10000 = new CopyOption[0];
      }

      CopyOption[] options = var10000;
      Path var5 = Files.copy($this$copyTo, target, (CopyOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var5, "copy(...)");
      return var5;
   }

   // $FF: synthetic method
   static Path copyTo$default(Path $this$copyTo_u24default, Path target, boolean overwrite, int options, Object var4) throws IOException {
      if ((options & 2) != 0) {
         overwrite = false;
      }

      Intrinsics.checkNotNullParameter($this$copyTo_u24default, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      CopyOption[] var10000;
      if (overwrite) {
         CopyOption[] var6 = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
         var10000 = var6;
      } else {
         var10000 = new CopyOption[0];
      }

      CopyOption[] options = var10000;
      Path var7 = Files.copy($this$copyTo_u24default, target, (CopyOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var7, "copy(...)");
      return var7;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path copyTo(Path $this$copyTo, Path target, CopyOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$copyTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.copy($this$copyTo, target, (CopyOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "copy(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean exists(Path $this$exists, LinkOption... options) {
      Intrinsics.checkNotNullParameter($this$exists, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      return Files.exists($this$exists, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean notExists(Path $this$notExists, LinkOption... options) {
      Intrinsics.checkNotNullParameter($this$notExists, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      return Files.notExists($this$notExists, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isRegularFile(Path $this$isRegularFile, LinkOption... options) {
      Intrinsics.checkNotNullParameter($this$isRegularFile, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      return Files.isRegularFile($this$isRegularFile, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isDirectory(Path $this$isDirectory, LinkOption... options) {
      Intrinsics.checkNotNullParameter($this$isDirectory, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      return Files.isDirectory($this$isDirectory, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isSymbolicLink(Path $this$isSymbolicLink) {
      Intrinsics.checkNotNullParameter($this$isSymbolicLink, "<this>");
      return Files.isSymbolicLink($this$isSymbolicLink);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isExecutable(Path $this$isExecutable) {
      Intrinsics.checkNotNullParameter($this$isExecutable, "<this>");
      return Files.isExecutable($this$isExecutable);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isHidden(Path $this$isHidden) throws IOException {
      Intrinsics.checkNotNullParameter($this$isHidden, "<this>");
      return Files.isHidden($this$isHidden);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isReadable(Path $this$isReadable) {
      Intrinsics.checkNotNullParameter($this$isReadable, "<this>");
      return Files.isReadable($this$isReadable);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isWritable(Path $this$isWritable) {
      Intrinsics.checkNotNullParameter($this$isWritable, "<this>");
      return Files.isWritable($this$isWritable);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean isSameFileAs(Path $this$isSameFileAs, Path other) throws IOException {
      Intrinsics.checkNotNullParameter($this$isSameFileAs, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      return Files.isSameFile($this$isSameFileAs, other);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @NotNull
   public static final List listDirectoryEntries(@NotNull Path $this$listDirectoryEntries, @NotNull String glob) throws IOException {
      Intrinsics.checkNotNullParameter($this$listDirectoryEntries, "<this>");
      Intrinsics.checkNotNullParameter(glob, "glob");
      Closeable var2 = (Closeable)Files.newDirectoryStream($this$listDirectoryEntries, glob);
      Throwable var3 = null;

      List var10;
      try {
         DirectoryStream it = (DirectoryStream)var2;
         int var5 = 0;
         Intrinsics.checkNotNull(it);
         var10 = CollectionsKt.toList((Iterable)it);
      } catch (Throwable var8) {
         var3 = var8;
         throw var8;
      } finally {
         CloseableKt.closeFinally(var2, var3);
      }

      return var10;
   }

   // $FF: synthetic method
   public static List listDirectoryEntries$default(Path var0, String var1, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         var1 = "*";
      }

      return PathsKt.listDirectoryEntries(var0, var1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Object useDirectoryEntries(Path $this$useDirectoryEntries, String glob, Function1 block) throws IOException {
      Intrinsics.checkNotNullParameter($this$useDirectoryEntries, "<this>");
      Intrinsics.checkNotNullParameter(glob, "glob");
      Intrinsics.checkNotNullParameter(block, "block");
      Closeable var3 = (Closeable)Files.newDirectoryStream($this$useDirectoryEntries, glob);
      Throwable var4 = null;
      boolean var10 = false;

      Object var14;
      try {
         var10 = true;
         DirectoryStream it = (DirectoryStream)var3;
         int var6 = false;
         Intrinsics.checkNotNull(it);
         var14 = block.invoke(CollectionsKt.asSequence((Iterable)it));
         var10 = false;
      } catch (Throwable var12) {
         var4 = var12;
         throw var12;
      } finally {
         if (var10) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var3, var4);
            } else if (var3 != null) {
               if (var4 == null) {
                  var3.close();
               } else {
                  try {
                     var3.close();
                  } catch (Throwable var11) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var3, var4);
      } else if (var3 != null) {
         var3.close();
      }

      InlineMarker.finallyEnd(1);
      return var14;
   }

   // $FF: synthetic method
   static Object useDirectoryEntries$default(Path $this$useDirectoryEntries_u24default, String glob, Function1 block, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         glob = "*";
      }

      Intrinsics.checkNotNullParameter($this$useDirectoryEntries_u24default, "<this>");
      Intrinsics.checkNotNullParameter(glob, "glob");
      Intrinsics.checkNotNullParameter(block, "block");
      Closeable var14 = (Closeable)Files.newDirectoryStream($this$useDirectoryEntries_u24default, glob);
      Throwable var15 = null;
      boolean var10 = false;

      Object var16;
      try {
         var10 = true;
         DirectoryStream it = (DirectoryStream)var14;
         int var6 = false;
         Intrinsics.checkNotNull(it);
         var16 = block.invoke(CollectionsKt.asSequence((Iterable)it));
         var10 = false;
      } catch (Throwable var12) {
         var15 = var12;
         throw var12;
      } finally {
         if (var10) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var14, var15);
            } else if (var14 != null) {
               if (var15 == null) {
                  var14.close();
               } else {
                  try {
                     var14.close();
                  } catch (Throwable var11) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var14, var15);
      } else if (var14 != null) {
         var14.close();
      }

      InlineMarker.finallyEnd(1);
      return var16;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final void forEachDirectoryEntry(Path $this$forEachDirectoryEntry, String glob, Function1 action) throws IOException {
      Intrinsics.checkNotNullParameter($this$forEachDirectoryEntry, "<this>");
      Intrinsics.checkNotNullParameter(glob, "glob");
      Intrinsics.checkNotNullParameter(action, "action");
      Closeable var3 = (Closeable)Files.newDirectoryStream($this$forEachDirectoryEntry, glob);
      Throwable var4 = null;
      boolean var14 = false;

      try {
         var14 = true;
         DirectoryStream it = (DirectoryStream)var3;
         int var6 = false;
         Intrinsics.checkNotNull(it);
         Iterable $this$forEach$iv = (Iterable)it;
         int $i$f$forEach = false;

         for(Object element$iv : $this$forEach$iv) {
            action.invoke(element$iv);
         }

         Unit var18 = Unit.INSTANCE;
         var14 = false;
      } catch (Throwable var16) {
         var4 = var16;
         throw var16;
      } finally {
         if (var14) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var3, var4);
            } else if (var3 != null) {
               if (var4 == null) {
                  var3.close();
               } else {
                  try {
                     var3.close();
                  } catch (Throwable var15) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var3, var4);
      } else if (var3 != null) {
         var3.close();
      }

      InlineMarker.finallyEnd(1);
   }

   // $FF: synthetic method
   static void forEachDirectoryEntry$default(Path $this$forEachDirectoryEntry_u24default, String glob, Function1 action, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         glob = "*";
      }

      Intrinsics.checkNotNullParameter($this$forEachDirectoryEntry_u24default, "<this>");
      Intrinsics.checkNotNullParameter(glob, "glob");
      Intrinsics.checkNotNullParameter(action, "action");
      Closeable var18 = (Closeable)Files.newDirectoryStream($this$forEachDirectoryEntry_u24default, glob);
      Throwable var19 = null;
      boolean var14 = false;

      try {
         var14 = true;
         DirectoryStream it = (DirectoryStream)var18;
         int var6 = false;
         Intrinsics.checkNotNull(it);
         Iterable $this$forEach$iv = (Iterable)it;
         int $i$f$forEach = false;

         for(Object element$iv : $this$forEach$iv) {
            action.invoke(element$iv);
         }

         Unit var20 = Unit.INSTANCE;
         var14 = false;
      } catch (Throwable var16) {
         var19 = var16;
         throw var16;
      } finally {
         if (var14) {
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               CloseableKt.closeFinally(var18, var19);
            } else if (var18 != null) {
               if (var19 == null) {
                  var18.close();
               } else {
                  try {
                     var18.close();
                  } catch (Throwable var15) {
                  }
               }
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         CloseableKt.closeFinally(var18, var19);
      } else if (var18 != null) {
         var18.close();
      }

      InlineMarker.finallyEnd(1);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final long fileSize(Path $this$fileSize) throws IOException {
      Intrinsics.checkNotNullParameter($this$fileSize, "<this>");
      return Files.size($this$fileSize);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final void deleteExisting(Path $this$deleteExisting) throws IOException {
      Intrinsics.checkNotNullParameter($this$deleteExisting, "<this>");
      Files.delete($this$deleteExisting);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final boolean deleteIfExists(Path $this$deleteIfExists) throws IOException {
      Intrinsics.checkNotNullParameter($this$deleteIfExists, "<this>");
      return Files.deleteIfExists($this$deleteIfExists);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createDirectory(Path $this$createDirectory, FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter($this$createDirectory, "<this>");
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createDirectory($this$createDirectory, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createDirectory(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createDirectories(Path $this$createDirectories, FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter($this$createDirectories, "<this>");
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createDirectories($this$createDirectories, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createDirectories(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.9"
   )
   @NotNull
   public static final Path createParentDirectories(@NotNull Path $this$createParentDirectories, @NotNull FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter($this$createParentDirectories, "<this>");
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      int var4 = 0;
      Path parent = $this$createParentDirectories.getParent();
      if (parent != null) {
         LinkOption[] var10001 = new LinkOption[0];
         if (!Files.isDirectory(parent, (LinkOption[])Arrays.copyOf(var10001, var10001.length))) {
            try {
               FileAttribute[] var9 = (FileAttribute[])Arrays.copyOf(attributes, attributes.length);
               Intrinsics.checkNotNullExpressionValue(Files.createDirectories(parent, (FileAttribute[])Arrays.copyOf(var9, var9.length)), "createDirectories(...)");
            } catch (FileAlreadyExistsException e) {
               var10001 = new LinkOption[0];
               if (!Files.isDirectory(parent, (LinkOption[])Arrays.copyOf(var10001, var10001.length))) {
                  throw e;
               }
            }
         }
      }

      return $this$createParentDirectories;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path moveTo(Path $this$moveTo, Path target, CopyOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$moveTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.move($this$moveTo, target, (CopyOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "move(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path moveTo(Path $this$moveTo, Path target, boolean overwrite) throws IOException {
      Intrinsics.checkNotNullParameter($this$moveTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      CopyOption[] var10000;
      if (overwrite) {
         CopyOption[] var4 = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
         var10000 = var4;
      } else {
         var10000 = new CopyOption[0];
      }

      CopyOption[] options = var10000;
      Path var5 = Files.move($this$moveTo, target, (CopyOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var5, "move(...)");
      return var5;
   }

   // $FF: synthetic method
   static Path moveTo$default(Path $this$moveTo_u24default, Path target, boolean overwrite, int options, Object var4) throws IOException {
      if ((options & 2) != 0) {
         overwrite = false;
      }

      Intrinsics.checkNotNullParameter($this$moveTo_u24default, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      CopyOption[] var10000;
      if (overwrite) {
         CopyOption[] var6 = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
         var10000 = var6;
      } else {
         var10000 = new CopyOption[0];
      }

      CopyOption[] options = var10000;
      Path var7 = Files.move($this$moveTo_u24default, target, (CopyOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var7, "move(...)");
      return var7;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final FileStore fileStore(Path $this$fileStore) throws IOException {
      Intrinsics.checkNotNullParameter($this$fileStore, "<this>");
      FileStore var10000 = Files.getFileStore($this$fileStore);
      Intrinsics.checkNotNullExpressionValue(var10000, "getFileStore(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Object getAttribute(Path $this$getAttribute, String attribute, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$getAttribute, "<this>");
      Intrinsics.checkNotNullParameter(attribute, "attribute");
      Intrinsics.checkNotNullParameter(options, "options");
      return Files.getAttribute($this$getAttribute, attribute, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path setAttribute(Path $this$setAttribute, String attribute, Object value, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$setAttribute, "<this>");
      Intrinsics.checkNotNullParameter(attribute, "attribute");
      Intrinsics.checkNotNullParameter(options, "options");
      Path var10000 = Files.setAttribute($this$setAttribute, attribute, value, (LinkOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "setAttribute(...)");
      return var10000;
   }

   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final FileAttributeView fileAttributesViewOrNull(Path $this$fileAttributesViewOrNull, LinkOption... options) {
      Intrinsics.checkNotNullParameter($this$fileAttributesViewOrNull, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      Intrinsics.reifiedOperationMarker(4, "V");
      return Files.getFileAttributeView($this$fileAttributesViewOrNull, FileAttributeView.class, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final FileAttributeView fileAttributesView(Path $this$fileAttributesView, LinkOption... options) {
      Intrinsics.checkNotNullParameter($this$fileAttributesView, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      Intrinsics.reifiedOperationMarker(4, "V");
      FileAttributeView var10000 = Files.getFileAttributeView($this$fileAttributesView, FileAttributeView.class, (LinkOption[])Arrays.copyOf(options, options.length));
      if (var10000 == null) {
         Intrinsics.reifiedOperationMarker(4, "V");
         PathsKt.fileAttributeViewNotAvailable($this$fileAttributesView, FileAttributeView.class);
         throw new KotlinNothingValueException();
      } else {
         return var10000;
      }
   }

   @PublishedApi
   @NotNull
   public static final Void fileAttributeViewNotAvailable(@NotNull Path path, @NotNull Class attributeViewClass) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(attributeViewClass, "attributeViewClass");
      throw new UnsupportedOperationException("The desired attribute view type " + attributeViewClass + " is not available for the file " + path + '.');
   }

   // $FF: synthetic method
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final BasicFileAttributes readAttributes(Path $this$readAttributes, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$readAttributes, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      Intrinsics.reifiedOperationMarker(4, "A");
      BasicFileAttributes var10000 = Files.readAttributes($this$readAttributes, BasicFileAttributes.class, (LinkOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "readAttributes(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Map readAttributes(Path $this$readAttributes, String attributes, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$readAttributes, "<this>");
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Intrinsics.checkNotNullParameter(options, "options");
      Map var10000 = Files.readAttributes($this$readAttributes, attributes, (LinkOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "readAttributes(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final FileTime getLastModifiedTime(Path $this$getLastModifiedTime, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$getLastModifiedTime, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      FileTime var10000 = Files.getLastModifiedTime($this$getLastModifiedTime, (LinkOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "getLastModifiedTime(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path setLastModifiedTime(Path $this$setLastModifiedTime, FileTime value) throws IOException {
      Intrinsics.checkNotNullParameter($this$setLastModifiedTime, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      Path var10000 = Files.setLastModifiedTime($this$setLastModifiedTime, value);
      Intrinsics.checkNotNullExpressionValue(var10000, "setLastModifiedTime(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final UserPrincipal getOwner(Path $this$getOwner, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$getOwner, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      return Files.getOwner($this$getOwner, (LinkOption[])Arrays.copyOf(options, options.length));
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path setOwner(Path $this$setOwner, UserPrincipal value) throws IOException {
      Intrinsics.checkNotNullParameter($this$setOwner, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      Path var10000 = Files.setOwner($this$setOwner, value);
      Intrinsics.checkNotNullExpressionValue(var10000, "setOwner(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Set getPosixFilePermissions(Path $this$getPosixFilePermissions, LinkOption... options) throws IOException {
      Intrinsics.checkNotNullParameter($this$getPosixFilePermissions, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      Set var10000 = Files.getPosixFilePermissions($this$getPosixFilePermissions, (LinkOption[])Arrays.copyOf(options, options.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "getPosixFilePermissions(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path setPosixFilePermissions(Path $this$setPosixFilePermissions, Set value) throws IOException {
      Intrinsics.checkNotNullParameter($this$setPosixFilePermissions, "<this>");
      Intrinsics.checkNotNullParameter(value, "value");
      Path var10000 = Files.setPosixFilePermissions($this$setPosixFilePermissions, value);
      Intrinsics.checkNotNullExpressionValue(var10000, "setPosixFilePermissions(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createLinkPointingTo(Path $this$createLinkPointingTo, Path target) throws IOException {
      Intrinsics.checkNotNullParameter($this$createLinkPointingTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Path var10000 = Files.createLink($this$createLinkPointingTo, target);
      Intrinsics.checkNotNullExpressionValue(var10000, "createLink(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createSymbolicLinkPointingTo(Path $this$createSymbolicLinkPointingTo, Path target, FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter($this$createSymbolicLinkPointingTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createSymbolicLink($this$createSymbolicLinkPointingTo, target, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createSymbolicLink(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path readSymbolicLink(Path $this$readSymbolicLink) throws IOException {
      Intrinsics.checkNotNullParameter($this$readSymbolicLink, "<this>");
      Path var10000 = Files.readSymbolicLink($this$readSymbolicLink);
      Intrinsics.checkNotNullExpressionValue(var10000, "readSymbolicLink(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createFile(Path $this$createFile, FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter($this$createFile, "<this>");
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createFile($this$createFile, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createFile(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createTempFile(String prefix, String suffix, FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createTempFile(prefix, suffix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createTempFile(...)");
      return var10000;
   }

   // $FF: synthetic method
   static Path createTempFile$default(String prefix, String suffix, FileAttribute[] attributes, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         prefix = null;
      }

      if ((var3 & 2) != 0) {
         suffix = null;
      }

      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createTempFile(prefix, suffix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createTempFile(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @NotNull
   public static final Path createTempFile(@Nullable Path directory, @Nullable String prefix, @Nullable String suffix, @NotNull FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000;
      if (directory != null) {
         var10000 = Files.createTempFile(directory, prefix, suffix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
         Intrinsics.checkNotNullExpressionValue(var10000, "createTempFile(...)");
      } else {
         var10000 = Files.createTempFile(prefix, suffix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
         Intrinsics.checkNotNullExpressionValue(var10000, "createTempFile(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static Path createTempFile$default(Path var0, String var1, String var2, FileAttribute[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         var1 = null;
      }

      if ((var4 & 4) != 0) {
         var2 = null;
      }

      return PathsKt.createTempFile(var0, var1, var2, var3);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path createTempDirectory(String prefix, FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createTempDirectory(prefix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createTempDirectory(...)");
      return var10000;
   }

   // $FF: synthetic method
   static Path createTempDirectory$default(String prefix, FileAttribute[] attributes, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         prefix = null;
      }

      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000 = Files.createTempDirectory(prefix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "createTempDirectory(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @NotNull
   public static final Path createTempDirectory(@Nullable Path directory, @Nullable String prefix, @NotNull FileAttribute... attributes) throws IOException {
      Intrinsics.checkNotNullParameter(attributes, "attributes");
      Path var10000;
      if (directory != null) {
         var10000 = Files.createTempDirectory(directory, prefix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
         Intrinsics.checkNotNullExpressionValue(var10000, "createTempDirectory(...)");
      } else {
         var10000 = Files.createTempDirectory(prefix, (FileAttribute[])Arrays.copyOf(attributes, attributes.length));
         Intrinsics.checkNotNullExpressionValue(var10000, "createTempDirectory(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static Path createTempDirectory$default(Path var0, String var1, FileAttribute[] var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var1 = null;
      }

      return PathsKt.createTempDirectory(var0, var1, var2);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path div(Path $this$div, Path other) {
      Intrinsics.checkNotNullParameter($this$div, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      Path var10000 = $this$div.resolve(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "resolve(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path div(Path $this$div, String other) {
      Intrinsics.checkNotNullParameter($this$div, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      Path var10000 = $this$div.resolve(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "resolve(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path Path(String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      Path var10000 = Paths.get(path);
      Intrinsics.checkNotNullExpressionValue(var10000, "get(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path Path(String base, String... subpaths) {
      Intrinsics.checkNotNullParameter(base, "base");
      Intrinsics.checkNotNullParameter(subpaths, "subpaths");
      Path var10000 = Paths.get(base, (String[])Arrays.copyOf(subpaths, subpaths.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "get(...)");
      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalPathApi.class}
   )
   @InlineOnly
   private static final Path toPath(URI $this$toPath) {
      Intrinsics.checkNotNullParameter($this$toPath, "<this>");
      Path var10000 = Paths.get($this$toPath);
      Intrinsics.checkNotNullExpressionValue(var10000, "get(...)");
      return var10000;
   }

   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.7"
   )
   @NotNull
   public static final Sequence walk(@NotNull Path $this$walk, @NotNull PathWalkOption... options) {
      Intrinsics.checkNotNullParameter($this$walk, "<this>");
      Intrinsics.checkNotNullParameter(options, "options");
      return new PathTreeWalk($this$walk, options);
   }

   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.7"
   )
   public static final void visitFileTree(@NotNull Path $this$visitFileTree, @NotNull FileVisitor visitor, int maxDepth, boolean followLinks) {
      Intrinsics.checkNotNullParameter($this$visitFileTree, "<this>");
      Intrinsics.checkNotNullParameter(visitor, "visitor");
      Set options = followLinks ? SetsKt.setOf(FileVisitOption.FOLLOW_LINKS) : SetsKt.emptySet();
      Files.walkFileTree($this$visitFileTree, options, maxDepth, visitor);
   }

   // $FF: synthetic method
   public static void visitFileTree$default(Path var0, FileVisitor var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = Integer.MAX_VALUE;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      PathsKt.visitFileTree(var0, var1, var2, var3);
   }

   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.7"
   )
   public static final void visitFileTree(@NotNull Path $this$visitFileTree, int maxDepth, boolean followLinks, @NotNull Function1 builderAction) {
      Intrinsics.checkNotNullParameter($this$visitFileTree, "<this>");
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      PathsKt.visitFileTree($this$visitFileTree, PathsKt.fileVisitor(builderAction), maxDepth, followLinks);
   }

   // $FF: synthetic method
   public static void visitFileTree$default(Path var0, int var1, boolean var2, Function1 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = Integer.MAX_VALUE;
      }

      if ((var4 & 2) != 0) {
         var2 = false;
      }

      PathsKt.visitFileTree(var0, var1, var2, var3);
   }

   @ExperimentalPathApi
   @SinceKotlin(
      version = "1.7"
   )
   @NotNull
   public static final FileVisitor fileVisitor(@NotNull Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      FileVisitorBuilderImpl var1 = new FileVisitorBuilderImpl();
      builderAction.invoke(var1);
      return var1.build();
   }

   public PathsKt__PathUtilsKt() {
   }
}
