package com.empathy.cache;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/7/5 17:18
 * @desc
 */
public class OsInfo {

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static OsInfo _instance = new OsInfo();

    private OsType platform;

    private OsInfo() {
    }

    public static boolean isLinux() {
        return OS.indexOf("linux") >= 0;
    }

    public static boolean isMacOS() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;
    }

    public static boolean isMacOSX() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
    }

    public static boolean isWindows() {
        return OS.indexOf("windows") >= 0;
    }

    public static boolean isOS2() {
        return OS.indexOf("os/2") >= 0;
    }

    public static boolean isSolaris() {
        return OS.indexOf("solaris") >= 0;
    }

    public static boolean isSunOS() {
        return OS.indexOf("sunos") >= 0;
    }

    public static boolean isMPEiX() {
        return OS.indexOf("mpe/ix") >= 0;
    }

    public static boolean isHPUX() {
        return OS.indexOf("hp-ux") >= 0;
    }

    public static boolean isAix() {
        return OS.indexOf("aix") >= 0;
    }

    public static boolean isOS390() {
        return OS.indexOf("os/390") >= 0;
    }

    public static boolean isFreeBSD() {
        return OS.indexOf("freebsd") >= 0;
    }

    public static boolean isIrix() {
        return OS.indexOf("irix") >= 0;
    }

    public static boolean isDigitalUnix() {
        return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;
    }

    public static boolean isNetWare() {
        return OS.indexOf("netware") >= 0;
    }

    public static boolean isOSF1() {
        return OS.indexOf("osf1") >= 0;
    }

    public static boolean isOpenVMS() {
        return OS.indexOf("openvms") >= 0;
    }

    /**
     * 获取操作系统名字
     *
     * @return 操作系统名
     */
    public static OsType getOSname() {
        if (isAix()) {
            _instance.platform = OsType.AIX;
        } else if (isDigitalUnix()) {
            _instance.platform = OsType.Digital_Unix;
        } else if (isFreeBSD()) {
            _instance.platform = OsType.FreeBSD;
        } else if (isHPUX()) {
            _instance.platform = OsType.HP_UX;
        } else if (isIrix()) {
            _instance.platform = OsType.Irix;
        } else if (isLinux()) {
            _instance.platform = OsType.Linux;
        } else if (isMacOS()) {
            _instance.platform = OsType.Mac_OS;
        } else if (isMacOSX()) {
            _instance.platform = OsType.Mac_OS_X;
        } else if (isMPEiX()) {
            _instance.platform = OsType.MPEiX;
        } else if (isNetWare()) {
            _instance.platform = OsType.NetWare_411;
        } else if (isOpenVMS()) {
            _instance.platform = OsType.OpenVMS;
        } else if (isOS2()) {
            _instance.platform = OsType.OS2;
        } else if (isOS390()) {
            _instance.platform = OsType.OS390;
        } else if (isOSF1()) {
            _instance.platform = OsType.OSF1;
        } else if (isSolaris()) {
            _instance.platform = OsType.Solaris;
        } else if (isSunOS()) {
            _instance.platform = OsType.SunOS;
        } else if (isWindows()) {
            _instance.platform = OsType.Windows;
        } else {
            _instance.platform = OsType.Others;
        }
        return _instance.platform;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(OsInfo.getOSname());
    }
}
