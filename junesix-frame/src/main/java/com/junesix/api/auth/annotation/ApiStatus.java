package com.junesix.api.auth.annotation;

/**
 * @author x-spirit
 * @author jolestar
 */
public enum ApiStatus {
    DRAFT {
        public int id() {
            return 1;
        }

        public String desc() {
            return "草稿";
        }
    },
    DEV {
        public int id() {
            return 2;
        }

        public String desc() {
            return "开发中";
        }
    },
    ALPHA {
        public int id() {
            return 3;
        }

        public String desc() {
            return "Alpha内测";
        }
    },
    BETA {
        public int id() {
            return 4;
        }

        public String desc() {
            return "Beta内测";
        }
    },
    INTERNAL {
        public int id() {
            return 5;
        }

        public String desc() {
            return "已上线的内部接口";
        }
    },
    COOPERATE {
        public int id() {
            return 6;
        }

        public String desc() {
            return "已上线的合作方接口";
        }
    },
    PUBLIC {
        public int id() {
            return 7;
        }

        public String desc() {
            return "已上线的公开接口";
        }
    },
    DEPRECATED {
        public int id() {
            return 8;
        }

        public String desc() {
            return "已经废弃的接口";
        }
    };

    public abstract int id();

    public abstract String desc();
}
