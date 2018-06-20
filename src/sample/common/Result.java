package sample.common;

public class Result {
    public static final String OK_MSG = "Обработано %d строк";
    public static final String CONN_ERR = "Не удалось подключиться к базе";
    public static final int ERR = -1;


    private String mes;
    private int code;

    public Result() {
    }

    public Result(String mes, int code) {
        this.mes = mes;
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
