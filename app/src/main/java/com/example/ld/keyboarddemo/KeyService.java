package com.example.ld.keyboarddemo;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class KeyService extends InputMethodService {
    @Override
    public View onCreateInputView() {
        View mkeyView = LayoutInflater.from(this).inflate(
                R.layout.key_layout, null);
        new KeyUtils(this, (KeyboardView) mkeyView.findViewById(R.id.keyboardView));
//        new KeyBoradUtil(this, (KeyboardView) mkeyView.findViewById(R.id.keyboardView));
        return mkeyView;
    }

    @Override
    public View onCreateCandidatesView() {
        return null;
    }

    public void commitText(String data) {
        getCurrentInputConnection().commitText(data, 0); // 往输入框输出内容
        setCandidatesViewShown(false); // 隐藏 CandidatesView
    }

    public void deleteText() {
        getCurrentInputConnection().deleteSurroundingText(1, 0);
    }

    public void hideInputMethod() {
        hideWindow();
    }

    public void moveLeft() {
    }
}
