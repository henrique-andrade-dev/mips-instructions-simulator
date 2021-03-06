public class Constants {
    public static final String PATH_INPUT_FILE = "./assets/input.txt";

    public static final String PATH_INSTRUCTIONS_FILE = "./assets/instructions.txt";

    public static final String PATH_REGISTER_FILE = "./assets/register.txt";

    public static final String PATH_MEMORY_FILE = "./assets/memory.txt";

    public static final String[] REGISTER_KEYS = { "v0", "v1", "a0", "a1", "a2", "a3", "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9", "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "gp", "fp", "sp", "ra", "at", "k1", "k2" };

    public static final String[] COMMENT_LINE_SYMBOLS = { "#", "//" };

    public static final int LINES_OF_MEMORY_LENGTH = 4096;

    public static final int MINIMUM_MEMORY_LINE_ADDRESS = 0;

    public static final int MAXIMUM_MEMORY_LINE_ADDRESS = 4095;

    public static final int MEMORY_ADDRESS_LENGTH = 3;

    public static final int MEMORY_LENGTH = 8;

    public static final int REGISTER_LENGTH = 8;

    public static final int BINARY_LENGTH = 32;
}
