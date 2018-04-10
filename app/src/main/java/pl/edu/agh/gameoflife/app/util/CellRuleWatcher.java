package pl.edu.agh.gameoflife.app.util;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;


public class CellRuleWatcher implements TextWatcher {

    private EditText field;
    public Boolean fieldValid = false;
    private final String regex = "[0-9]{1,9}/[0-9]{1,9}";


    public CellRuleWatcher(EditText cellRule) {
        this.field = cellRule;
        field.setFilters(new InputFilter[]{
                new PartialRegexInputFilter(regex)
        });
    }

    @Override
    public void afterTextChanged(Editable s) {
        String value  = s.toString();
        if(value.matches(regex)){
            field.setTextColor(Color.BLACK);
            fieldValid = true;
        }
        else{
            field.setTextColor(Color.RED);
            fieldValid = false;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start,
                                  int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start,
                              int before, int count) {}

    public Boolean getFieldValid() {
        return fieldValid;
    }
}
