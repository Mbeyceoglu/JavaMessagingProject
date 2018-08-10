package com.mustafa;

import java.util.List;

public class NameChecker {
    public NameChecker() {
    }

    static boolean nameChecker(List list, String name) {
        if (list.isEmpty()) {
            return true;
        } else if (list.contains(name)) {
            return false;
        } else {
            return !list.contains(name);
        }
    }
}
