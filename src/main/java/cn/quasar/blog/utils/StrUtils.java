package cn.quasar.blog.utils;

import cn.quasar.blog.type.utils.Empty;

import java.util.ArrayList;
import java.util.List;

public class StrUtils {

    public static List<String> splitStr(String target, String separator) {
        List<String> list = new ArrayList<>();
        if (!"".equals(target)) {

            while (target.indexOf(separator) != -1 || "".equals(target)) {
                int next = target.indexOf(separator);
                list.add(target.substring(0, next));
                target = target.substring(next + 1);
            }
            list.add(target);

            return list;
        } else {
            list.add("");
            return list;
        }
    }

    public static String mergeList(List<String> target, String separator) {
        if (target.size() != 0) {
            String builder = "";
            for (int i = 0; i < target.size(); i++) {
                if (i < target.size() - 1) {
                    builder = builder + target.get(i)+separator;
                } else {
                    builder = builder + target.get(i);
                }
            }
            return builder;
        } else {
            return "";
        }
    }

    public static boolean isEmptyCallBack(String target, Empty empty) {
        return empty.call(target == null);
    }
}
