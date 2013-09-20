package fr.epf.computer.utils;


public class EResult {

    public static final int OK = 1;
    public static final int FAIL = 2;
    public static final int INVALID_COMPUTER_NAME = 4;
    public static final int INVALID_COMPUTER_INTRODUCED_DATE = 8;
    public static final int INVALID_COMPUTER_DISCONTINUED_DATE = 16;
    public static final int INVALID_COMPANY = 32;

    public static final int INVALID_COMPANY_NAME = 64;

    public static final int MAX = 128;

    private EResult() {

    }

}
