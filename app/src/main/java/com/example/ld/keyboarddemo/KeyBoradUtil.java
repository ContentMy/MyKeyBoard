package com.example.ld.keyboarddemo;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class KeyBoradUtil {
    private Context mContext;//上下文
    private KeyboardView mKeyView;//呈现虚拟键盘的视图。 它可以处理按键的呈现并检测按键和触摸动作。
    private Keyboard mBoard;//字母键盘
    private static final String TAG = "keyborad";
    private EditText mText;
    private KeyService myImeService;

    public KeyBoradUtil(KeyService service, KeyboardView keyboardView) {
        myImeService = service;
        //初始化键盘布局，下面在放进KeyboardView中去
        mBoard = new Keyboard(myImeService, R.xml.number);

        //配置KeyboardView
        try {
            mKeyView = keyboardView;
            mKeyView.setKeyboard(mBoard);
            mKeyView.setPreviewEnabled(false);//预览
            mKeyView.setOnKeyboardActionListener(keyboardActionListener);
        } catch (Exception e) {
            Log.e(TAG, "KeyboardView初始化失败");
        }

    }

    private KeyboardView.OnKeyboardActionListener keyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {
            //抬起触发
            Log.e(TAG, "onRelease: ");
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
//            Editable editable = mText.getText();//获取到edittext内部的text
//            int start = mText.getSelectionStart();
//            //抬起触发，在onRelease之前
//            //数字监听
//            String str = Character.toString((char) primaryCode);
//            editable.insert(start, str);
            myImeService.commitText(Character.toString((char) primaryCode));
        }

        @Override
        public void onText(CharSequence text) {
            //number.xml里面中key标签对里添加一个keyOutputText的属性，打印出来的就是它的值
            Log.e(TAG, "onText: ");
        }

        @Override
        public void swipeLeft() {
            //左滑
            Log.e(TAG, "swipeLeft: ");
        }

        @Override
        public void swipeRight() {
            Log.e(TAG, "swipeRight: ");
        }

        @Override
        public void swipeDown() {
            Log.e(TAG, "swipeDown: ");
        }

        @Override
        public void swipeUp() {
            Log.e(TAG, "swipeUp: ");
        }
    };
}
