package com.example.ld.keyboarddemo;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class KeyUtils {

    private KeyboardView keyboardView;
    private Keyboard k1;// 字母键盘
    private Keyboard k2;// 数字键盘
    private Keyboard k3;// 符号键盘
    private boolean isNum = false;// 是否数据键盘
    private boolean isUpper = false;// 是否大写
    private boolean isSymbol = false;// 是否符号

    private static final int SYMBOL_CODE = -7;//符号键盘
    private static final int ELLIPSES_CODE = -8;//省略号
    private static final int CHINESE_HORIZONTAL_LINE_CODE = -9;//中文横线
    private static final int SMILING_FACE_CODE = -10;//笑脸
    private static final int LEFT_CODE = -11;//中文横线
    private static final int RIGHT_CODE = -12;//中文横线
    private static final int HEE_CODE = -13;//哈哈
    private static final int AWKWARD_CODE = -14;//尴尬

    private ViewGroup rootView;
    private KeyService mService;

    public KeyUtils(KeyService service, KeyboardView mKeyboardView) {
        k1 = new Keyboard(service, R.xml.test_letter);
        k2 = new Keyboard(service, R.xml.test_number);
        k3 = new Keyboard(service, R.xml.test_symbol);

        mService = service;
        keyboardView = mKeyboardView;
        keyboardView.setKeyboard(k1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(onKeyboardActionListener);

//        rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    KeyboardView.OnKeyboardActionListener onKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
                mService.hideInputMethod();
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                mService.deleteText();
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {// 大小写切换
                isUpper = !isUpper;
                k1.setShifted(isUpper);
                keyboardView.invalidateAllKeys();
            } else if (primaryCode == SYMBOL_CODE) {// 符号键盘
                if (isSymbol) {
                    isSymbol = false;
                    keyboardView.setKeyboard(k2);
                } else {
                    isSymbol = true;
                    keyboardView.setKeyboard(k3);
                }
            } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {// 数字键盘切换
                if (isNum) {
                    isNum = false;
                    keyboardView.setKeyboard(k1);
                } else {
                    isNum = true;
                    keyboardView.setKeyboard(k2);
                }
            } else if (primaryCode == ELLIPSES_CODE) { //省略号
                mService.commitText("...");
            } else if (primaryCode == CHINESE_HORIZONTAL_LINE_CODE) {
                mService.commitText("——");
            } else if (primaryCode == SMILING_FACE_CODE) {
                mService.commitText("^_^");
            } else if (primaryCode == HEE_CODE) {
                mService.commitText("^o^");
            } else if (primaryCode == AWKWARD_CODE) {
                mService.commitText(">_<");
            } else {
                String str = Character.toString((char) primaryCode);
                if (isWord(str)) {
                    if (isUpper) {
                        str = str.toUpperCase();
                    } else {
                        str = str.toLowerCase();
                    }
                }
                mService.commitText(str);
            }
        }
    };

    private boolean isShow = false;

    private boolean isWord(String str) {
        return str.matches("[a-zA-Z]");
    }

}
