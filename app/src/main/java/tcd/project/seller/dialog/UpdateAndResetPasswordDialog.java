package tcd.project.seller.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import tcd.project.seller.R;

public class UpdateAndResetPasswordDialog extends AppCompatDialog {

    private final Context context;
    private EditText edtNewPassword, edtNewPasswordConfirm, edtCurrentPassword;
    private View btnSubmit, btnCancel;
    private ResetPasswordDialogListener listener;
    private boolean isVisiblePassword = false;
    private boolean isVisibleConfirmPassword = false;
    private boolean isVisibleCurrentPassword = false;

    private TypeView typeView;

    public UpdateAndResetPasswordDialog(Context context, ResetPasswordDialogListener listener, TypeView typeView) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.typeView = typeView;
        setCancelable(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.layout_reset_password);

        edtCurrentPassword = findViewById(R.id.edtEmployeeCurrentPassword);
        edtNewPassword = findViewById(R.id.edtEmployeeNewPasswordLayoutReset);
        edtNewPasswordConfirm = findViewById(R.id.edtEmployeeConfirmNewPasswordLayoutReset);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        ImageView btnVisiblePassword = findViewById(R.id.btnVisibleNewPasswordLayoutReset);
        ImageView btnVisibleConfirmNewPasswordLayoutReset = findViewById(R.id.btnVisibleConfirmNewPasswordLayoutReset);
        ImageView btnVisibleCurrentPassword = findViewById(R.id.btnVisibleCurrentPassword);

        View layoutInputCurrentPassword = findViewById(R.id.layoutInputCurrentPassword);
        if (typeView == TypeView.UPDATE) {
            layoutInputCurrentPassword.setVisibility(View.VISIBLE);
        } else {
            layoutInputCurrentPassword.setVisibility(View.GONE);
        }

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

        btnVisibleCurrentPassword.setOnClickListener(v -> {
            if (!isVisibleCurrentPassword) {
                isVisibleCurrentPassword = true;
                edtCurrentPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if (!"".contentEquals(edtCurrentPassword.getText()))
                    edtCurrentPassword.setSelection(edtCurrentPassword.getText().length());
                btnVisibleCurrentPassword.setBackgroundResource(R.drawable.eyes_selected_64);
            } else {
                isVisibleCurrentPassword = false;
                edtCurrentPassword.setInputType(129);
                if (!"".contentEquals(edtCurrentPassword.getText()))
                    edtCurrentPassword.setSelection(edtCurrentPassword.getText().length());
                btnVisibleCurrentPassword.setBackgroundResource(R.drawable.ic_hide_eys);
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

        btnSubmit.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edtNewPassword.getText())) {
                edtNewPassword.setError("Nhập mật khẩu mới");
                return;
            }

            if (TextUtils.isEmpty(edtNewPasswordConfirm.getText())) {
                edtNewPasswordConfirm.setError("Xác nhận mật khẩu mới");
                return;
            }
            if (typeView == TypeView.UPDATE) {
                if (TextUtils.isEmpty(edtCurrentPassword.getText())) {
                    edtCurrentPassword.setError("Nhập mật khẩu hiện tại");
                    return;
                }
            }

            String current_password = !TextUtils.isEmpty(edtCurrentPassword.getText()) ? edtCurrentPassword.getText().toString().trim() : "";
            String new_password = edtNewPassword.getText().toString().trim();
            String new_password_confirm = edtNewPasswordConfirm.getText().toString().trim();

            if (new_password.equalsIgnoreCase(new_password_confirm)) {
                if (new_password.length() < 6){
                    Toast.makeText(context, "Mật khẩu mới gồm ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (listener != null) {
                    dismiss();
                    if (typeView == TypeView.UPDATE) {
                        listener.onSubmitUpdatePassword(current_password, new_password);
                    } else {
                        listener.onSubmitResetPassword(new_password);
                    }
                }
            } else {
                Toast.makeText(context, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(view -> {
            dismiss();
        });
    }

    public interface ResetPasswordDialogListener {
        void onSubmitResetPassword(String new_password);

        void onSubmitUpdatePassword(String current_password, String new_password);
    }

    public enum TypeView {
        RESET,
        UPDATE
    }
}
