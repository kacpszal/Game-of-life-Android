package pl.edu.agh.gameoflife.app.util;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

/**
 * Created by Lenovo on 2018-04-07.
 */

public class CellRuleWatcher implements TextWatcher {
    private final Pattern regex = Pattern.compile("[0-9]\\[0-9]");

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        int length = text.length();

        if (!regex.matcher(text).matches() && length > 0) {
            s.delete(length - 1, length);
        }
    }

}
