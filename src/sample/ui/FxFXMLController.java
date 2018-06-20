package sample.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.db.DBConnection;
import sample.common.Result;


public class FxFXMLController {

    private static final int NUM_LENGTH = 15; // Пример: INC000000083335
    private static final String user = "opener";

    @FXML
    private TextField incNum;

    @FXML
    private TextField reqNum;

    @FXML
    private void initialize() {
    }

    @FXML
    private void enableInc() {
        incNum.setDisable(false);
        reqNum.setDisable(true);
    }

    @FXML
    private void enableReq() {
        incNum.setDisable(true);
        reqNum.setDisable(false);
    }

    @FXML
    private void update() {

        String password = Dialogs.getPass();
        if (!checkPass(password))
            return;

        Result result = new Result();
        DBConnection dbConn = new DBConnection();
        String num;
        if (incNum.isDisabled()) {
            num = reqNum.getText();
            if (checkNum(num)) {
                result = dbConn.executeQuery(DBConnection.Type.REQ, num, user, password);
            } else {
                Dialogs.showErrLength();
                return;
            }
        }
        if (reqNum.isDisabled()) {
            num = incNum.getText();
            if (checkNum(num)) {
                result = dbConn.executeQuery(DBConnection.Type.INC, num, user, password);
            } else {
                Dialogs.showErrLength();
                return;
            }
        }
        if (result.getCode() > Result.ERR) Dialogs.showOk(result);
        else Dialogs.showErr(result.getMes());
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    private Boolean checkNum(String num) {
        if (num == null) return false;
        return num.length() == NUM_LENGTH;
    }

    private Boolean checkPass(String pass) {
        if (pass.equals(""))
            Dialogs.showPassErr();
        return (!pass.equals("") && !pass.equals(Dialogs.CANCEL));
    }

}
