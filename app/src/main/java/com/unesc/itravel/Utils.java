package com.unesc.itravel;

import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

public final class Utils {

    public static boolean validarCamposVazios(List<EditText> campos) {
        boolean allFieldsFilled = true;

        for (EditText campo : campos) {
            String input = campo.getText().toString().trim();

            if (TextUtils.isEmpty(input)) {
                campo.setError("Campo obrigatório");
                allFieldsFilled = false;
            } else {
                campo.setError(null);
            }
        }

        return allFieldsFilled;
    }

    public static boolean validarCheckBoxesVazios(List<CheckBox> checkBoxes) {
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                return true; // Pelo menos um CheckBox foi marcado
            }
        }

        return false; // Nenhum CheckBox foi marcado
    }
}
