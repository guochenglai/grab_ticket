package net.gcl.ticket.util;

/**
 * Created by guochenglai on 1/8/17.
 */
public class Base32 {
    private static int delta = 0x9E3779B8;
    public static String encrypt(String str, String key) {
        if (str == "") {
            return "";
        }
        int[] v = stringToLongArray(str, true);
        int[] k = stringToLongArray(key, false);
        if (k.length < 4) {
            int[] newArr = new int[4];
            System.arraycopy(k, 0, newArr, 0, k.length);
            k = newArr;
        }
        int n = v.length - 1;
        int z = v[n], y = v[0];
        int mx, e, p, sum = 0;
        int q = (int)Math.floor( 6 + 52 / (n + 1) );
        while (0 < q--) {
            sum = sum + delta & 0xffffffff;
            e = sum >>> 2 & 3;
            for (p = 0; p < n; p++) {
                y = v[p + 1];
                mx = (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
                z = v[p] = v[p] + mx & 0xffffffff;
            }
            y = v[0];
            mx = (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            z = v[n] = v[n] + mx & 0xffffffff;
        }
        return longArrayToString(v, false);
    };

    public static String longArrayToString(int[] data, boolean includeLength) {
        int length = data.length;
        int n = (length - 1) << 2;
        if (includeLength) {
            int m = data[length - 1];
            if ((m < n - 3) || (m > n))
                return null;
            n = m;
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            int i0 = data[i] & 0xff;
            int i8 = data[i] >>> 8 & 0xff;
            int i16 = data[i] >>> 16 & 0xff;
            int i24 = data[i] >>> 24 & 0xff;
            if (i0 != 0)
                sb.append((char)i0);
            if (i8 != 0)
                sb.append((char)i8);
            if (i16 != 0)
                sb.append((char)i16);
            if (i24 != 0)
                sb.append((char)i24);
        }

        String result;
        if (includeLength) {
            result = sb.substring(0, n);
        }
        else
            result = sb.toString();
        return result;
    }

    public static int[] stringToLongArray(String str, boolean includeLength) {
        int length = str.length();
        int arrsize = length % 4 == 0 ? length / 4 : length / 4 + 1;
        int[] result = new int[arrsize];
        for (int i = 0; i < length; i += 4) {
            if (i + 4 > length) {
                int char8 = i + 1 >= length ? 0 : str.charAt(i + 1) << 8;
                int char16 = i + 2 >= length ? 0 : str.charAt(i + 1) << 16;
                int char24 = i + 3 >= length ? 0 : str.charAt(i + 1) << 24;
                result[i >> 2] = str.charAt(i) | char8 | char16 | char24;
            }
            else
                result[i >> 2] =
                        str.charAt(i) | str.charAt(i + 1) << 8 | str.charAt(i + 2) << 16 | str.charAt(i + 3) << 24;
        }
        if (includeLength) {
            int[] newArr = new int[arrsize + 1];
            System.arraycopy(result, 0, newArr, 0, arrsize);
            newArr[arrsize] = length;
            result = newArr;
        }
        return result;
    }
}
