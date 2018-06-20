package sample.ui;

import com.sun.istack.internal.NotNull;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import sample.common.Result;

import java.util.Optional;

class Dialogs {
    private static final String ERROR = "Ошибка!";
    private static final String ERR_LENGTH = "Введите корректый номер!";
    private static final String OK_MSG = "Операция завершена!";
    private static final String ENTER_PASSWORD = "Введите пароль";
    private static final String ERR_PASS = "Введен некорректный пароль";
    static final String CANCEL = "-1";

    static void showErr(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText(ERROR);
        alert.setContentText(err);
        alert.showAndWait();
    }

    static void showOk(Result result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(OK_MSG);
        alert.setHeaderText(OK_MSG);
        alert.setContentText(result.getMes());
        alert.showAndWait();
    }

    @NotNull
    static String getPass() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(ENTER_PASSWORD);
        dialog.setHeaderText(ENTER_PASSWORD);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(CANCEL);
    }

    static void showErrLength() {
        showErr(ERR_LENGTH);
    }

    static void showPassErr() {
        showErr(ERR_PASS);
    }
}
