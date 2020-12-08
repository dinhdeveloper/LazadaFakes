package tcd.project.seller.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import b.laixuantam.myaarlibrary.helper.AppUtils;
import tcd.project.seller.R;

public class PasswordManagerDialog extends AppCompatDialog {

    private final Context context;
    private EditText edtNewPassword, edtNewPasswordConfirm, edtOldPassword;
    private View btnSubmitResetPassword, btnCancelResetPassword;
    private PasswordManagerDialogListener listener;
    private boolean isVisiblePassword = false;
    private boolean isVisibleOldPassword = false;
    private boolean isVisibleConfirmPassword = false;

    private Type typeDialog = Type.UPDATE;

    public PasswordManagerDialog(Context context, PasswordManagerDialogListener listener, Type typeDialog) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.typeDialog = typeDialog;

        setCancelable(true);
    }

    public enum Type {
        RESET,
        UPDATE
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dinh_layout_reset_password_admin);

        View layoutRootViewPasswordManager = findViewById(R.id.layoutRootViewPasswordManager);
        TextView tvPasswordManagerTitle = findViewById(R.id.tvPasswordManagerTitle);

        edtNewPassword = findViewById(R.id.edtEmployeeNewPasswordLayoutReset);
        edtOldPassword = findViewById(R.id.edtOldPasswordLayoutReset);
        edtNewPasswordConfirm = findViewById(R.id.edtEmployeeConfirmNewPasswordLayoutReset);

        btnSubmitResetPassword = findViewById(R.id.btnSubmitResetPassword);
        btnCancelResetPassword = findViewById(R.id.btnCancelResetPassword);

        View layoutInputOldPassword = findViewById(R.id.layoutInputOldPassword);
        if (typeDialog == Type.UPDATE) {
            tvPasswordManagerTitle.setText("Thay đổi mật khẩu");
            layoutInputOldPassword.setVisibility(View.VISIBLE);
        } else {
            tvPasswordManagerTitle.setText("Reset mật khẩu");
            layoutInputOldPassword.setVisibility(View.GONE);
        }

        ImageView btnVisiblePassword = findViewById(R.id.btnVisibleNewPasswordLayoutReset);
        ImageView btnVisibleOldPassword = findViewById(R.id.btnVisibleOldPasswordLayoutReset);
        ImageView btnVisibleConfirmNewPasswordLayoutReset = findViewById(R.id.btnVisibleConfirmNewPasswordLayoutReset);

        layoutRootViewPasswordManager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AppUtils.hideKeyBoard(view);
                return true;
            }
        });

        btnVisiblePassword.setOnClickListener(v -> {
            if (!isVisiblePassword) {
                isVisiblePassword = true;
                edtNewPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if (!"".contentEquals(edtNewPassword.getText()))
                    edtNewPassword.setSelection(edtNewPassword.getText().length());
                btnVisiblePassword.setBackgroundResource(R.drawable.eyes_selected_64);
            } else {
                isVisiblePassword = false;
                edtNewPassword.setInputType(129);
                if (!"".contentEquals(edtNewPassword.getText()))
                    edtNewPassword.setSelection(edtNewPassword.getText().length());
                btnVisiblePassword.setBackgroundResource(R.drawable.ic_hide_eys);
            }
        });

        btnVisibleOldPassword.setOnClickListener(v -> {
            if (!isVisibleOldPassword) {
                isVisibleOldPassword = true;
                edtOldPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if (!"".contentEquals(edtOldPassword.getText()))
                    edtOldPassword.setSelection(edtOldPassword.getText().length());
                btnVisibleOldPassword.setBackgroundResource(R.drawable.eyes_selected_64);
            } else {
                isVisibleOldPassword = false;
                edtOldPassword.setInputType(129);
                if (!"".contentEquals(edtOldPassword.getText()))
                    edtOldPassword.setSelection(edtOldPassword.getText().length());
                btnVisibleOldPassword.setBackgroundResource(R.drawable.ic_hide_eys);
            }
        });

        btnVisibleConfirmNewPasswordLayoutReset.setOnClickListener(v -> {
            if (!isVisibleConfirmPassword) {
                isVisibleConfirmPassword = true;
                edtNewPasswordConfirm.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if (!"".contentEquals(edtNewPasswordConfirm.getText()))
                    edtNewPasswordConfirm.setSelection(edtNewPasswordConfirm.getText().length());
                btnVisibleConfirmNewPasswordLayoutReset.setBackgroundResource(R.drawable.eyes_selected_64);
            } else {
                isVisibleConfirmPassword = false;
                edtNewPasswordConfirm.setInputType(129);
                if (!"".contentEquals(edtNewPasswordConfirm.getText()))
                    edtNewPasswordConfirm.setSelection(edtNewPasswordConfirm.getText().length());
                btnVisibleConfirmNewPasswordLayoutReset.setBackgroundResource(R.drawable.ic_hide_eys);
            }
        });

        btnSubmitResetPassword.setOnClickListener(v -> {
            if (typeDialog == Type.UPDATE) {
                if (TextUtils.isEmpty(edtOldPassword.getText())) {
                    edtOldPassword.setError("Nhập mật khẩu cũ");
                    return;
                }
            }
            if (TextUtils.isEmpty(edtNewPassword.getText())) {
                edtNewPassword.setError("Nhập mật khẩu mới");
                return;
            }

            if (TextUtils.isEmpty(edtNewPasswordConfirm.getText())) {
                edtNewPasswordConfirm.setError("Xác nhận mật khẩu mới");
                return;
            }
            String old_password = edtOldPassword.getText().toString().trim();
            String new_password = edtNewPassword.getText().toString().trim();
            String new_password_confirm = edtNewPasswordConfirm.getText().toString().trim();

            if (new_password.equalsIgnoreCase(new_password_confirm)) {
                if (listener != null) {
                    dismiss();

                    if (typeDialog == Type.UPDATE) {
                        listener.onSubmitChangePassword(old_password, new_password);
                    } else {
                        listener.onSubmitResetPassword(new_password);
                    }
                }
            } else {
                Toast.makeText(context, "Mật khẩu mới không trùng nhau", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelResetPassword.setOnClickListener(view -> {
            dismiss();
        });
    }

    public interface PasswordManagerDialogListener {
        void onSubmitResetPassword(String new_password);

        void onSubmitChangePassword(String old_password, String new_password);
    }

}

/**
 * final PasswordManagerDialog dialog = new PasswordManagerDialog(getContext(), new PasswordManagerDialog.PasswordManagerDialogListener() {
 *
 * @Override public void onSubmitResetPassword(String new_password) {
 * if (callback != null) {
 * callback.onRequestResetPasswordEmployee(idEmployee, new_password);
 * }
 * }
 * }, Type.RESET);
 * dialog.show();
 */
