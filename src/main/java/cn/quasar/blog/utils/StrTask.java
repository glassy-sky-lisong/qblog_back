package cn.quasar.blog.utils;

import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.type.utils.Empty;
import org.springframework.stereotype.Component;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StrTask {

    private Map<String, Object> map;
    private Mode mode;

    public StrTask() {
        mode = Mode.NO;
    }

    private String checkTaskAndGet() throws RuntimeException {
        if (map.containsKey("source")) {
            return (String) map.get("source");
        } else {
            throw new RuntimeException("请启动任务");
        }
    }

    public StrTask start(String source) {
        if (map == null) {
            map = new HashMap<String, Object>();
            map.put("source", source);
        } else {
            throw new RuntimeException("情使用end方法结束任务");
        }

        return this;
    }

    private void ModeHandler(boolean condition) {
        switch (mode) {
            case NOT:
                map.put("boolean", !condition);
                break;
            case AND:
                map.put("boolean", (boolean) map.get("boolean") && condition);
                break;
            case OR:
                map.put("boolean", (boolean) map.get("boolean") || condition);
                break;
            default:
                map.put("boolean", condition);
        }
    }

    public void end() {
        if (map != null) {
            map = null;
        }
    }

    public StrTask and() {
        mode = Mode.AND;
        return this;
    }

    public StrTask or() {
        mode = Mode.OR;
        return this;
    }

    public StrTask not() {
        mode = Mode.NOT;
        return this;
    }

    public StrTask no() {
        mode = Mode.NO;
        return this;
    }

    public static List<String> splitStr(String target, String separator) {
        ArrayList<String> list = new ArrayList<>();

        if (!"".equals(target)) {

            while (target.contains(separator) || "".equals(target)) {
                int next = target.indexOf(separator);
                list.add(target.substring(0, next));
                target = target.substring(next + 1, target.length());
            }
            list.add(target);

            return list;
        } else {
            return new ArrayList<String>();
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

    public  StrTask isEmpty() {
        String target = checkTaskAndGet();
        ModeHandler(target == null);

        return this;
    }

    public StrTask equals(String target) {
        String source = checkTaskAndGet();

        ModeHandler(source.equals(target));

        return this;
    }


    public Object getData(String key, boolean isEnd) {
       if (map != null) {
           Object res = map.containsKey(key) ? map.get(key) : this;

           if(isEnd) end();

           return res;
       } else {
           throw new RuntimeException("任务销毁后不可执行");
       }
    }

    public Object getData(String key, Empty empty) {
        if (map != null) {
            Object res = map.containsKey(key) ? map.get(key) : this;

            empty.call(false);
            return res;
        } else {
            throw new RuntimeException("任务销毁后不可执行");
        }
    }
}
