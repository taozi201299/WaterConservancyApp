package com.syberos.shuili.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.syberos.shuili.utils.CommonUtils;

/**
 * Created by jidan on 18-3-22.
 */

public class CustomEdit extends EditText {
    /**
     * 输入表情前的光标位置
     */
    private int cursorPos;
    /**
     * 输入表情前EditText中的文本
     */
    private String inputAfterText;
    /**
     * 是否重置了EditText的内容
     */
    private boolean resetText = false;

    public CustomEdit(Context context) {
        super(context);
        initEditText();
    }

    public CustomEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditText();
    }

    public CustomEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditText();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEdit(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initEditText();
    }

    /**
     * 初始化函数
     */
    private void initEditText() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    cursorPos = getSelectionEnd();
                    // 这里用s.toString()而不直接用s是因为如果用s，
                    // 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
                    // inputAfterText也就改变了，那么表情过滤就失败了
                    inputAfterText= s.toString();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    if (count >= 2) {//表情符号的字符长度最小为2
                        if (CommonUtils.containsEmoji(s.toString())) {
                            resetText = true;
                            //是表情符号就将文本还原为输入表情符号之前的内容
                            setText(inputAfterText);
                            CharSequence text = getText();
                            if (text != null) {
                                Spannable spanText = (Spannable) text;
                                setSelection(cursorPos);
                            }
                        }
                    }
                } else {
                    resetText = false;
                }
            }

            /**
             * This method is called to notify you that, somewhere within
             * <code>s</code>, the text has been changed.
             * It is legitimate to make further changes to <code>s</code> from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.  But if you need to know here,
             * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
             * to mark your place and then look up from here where the span
             * ended up.
             *
             * @param s editable对象
             */
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
