package be.fpluquet.labo4.db;

public abstract class CrimeDbSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
