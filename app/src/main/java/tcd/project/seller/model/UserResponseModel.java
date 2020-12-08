package tcd.project.seller.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class UserResponseModel extends BaseResponseModel {
    /**
     * id : 2
     * customer_phone : 123456
     * customer_name : Trần Cảnh Dinh
     * customer_code :
     * customer_sex : male
     * customer_birthday :
     * customer_email :
     * login_type : customer
     */

    private String id;
    private String customer_phone;
    private String customer_name;
    private String customer_code;
    private String customer_sex;
    private String customer_birthday;
    private String customer_email;
    private String login_type;

    //employee
    private String username;
    private String full_name;
    private String email;
    private String phone_number;
    private String status_employee;
    private String id_type;
    private String type_account;
    private String type_description;

    @Nullable
    private RolePermissionModel[] role_permission;

    @Nullable
    public RolePermissionModel[] getRole_permission() {
        return role_permission;
    }

    public void setRole_permission(@Nullable RolePermissionModel[] role_permission) {
        this.role_permission = role_permission;
    }

    public boolean checkHaveRole(RoleType role) {

        if (role == null || TextUtils.isEmpty(role.id) || role.id.equalsIgnoreCase("null"))
            return false;

        if (role_permission != null && role_permission.length > 0) {
            for (RolePermissionModel item : role_permission) {
                if (role.isEqual(item)) {
                    return true;
                }
            }
        }

        return false;
    }

    public RolePermissionModel getDetailRolePermission(RoleType role) {
        if (role_permission != null && role_permission.length > 0) {
            for (RolePermissionModel item : role_permission) {
                if (role.isEqual(item)) {
                    return item;
                }
            }
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_sex() {
        return customer_sex;
    }

    public void setCustomer_sex(String customer_sex) {
        this.customer_sex = customer_sex;
    }

    public String getCustomer_birthday() {
        return customer_birthday;
    }

    public void setCustomer_birthday(String customer_birthday) {
        this.customer_birthday = customer_birthday;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStatus_employee() {
        return status_employee;
    }

    public void setStatus_employee(String status_employee) {
        this.status_employee = status_employee;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getType_account() {
        return type_account;
    }

    public void setType_account(String type_account) {
        this.type_account = type_account;
    }

    public String getType_description() {
        return type_description;
    }

    public void setType_description(String type_description) {
        this.type_description = type_description;
    }

    public enum RoleType {
        ACCOUNT_CONTROL("", "1"),
        ACCOUNT_CUSTOMER("", "2"),
        ACCOUNT_COMPANY("", "3"),
        ACCOUNT_PRODUCTS("", "4"),
        ACCOUNT_SO("", "5"),
        ACCOUNT_PRODUCTION("", "6"),
        ACCOUNT_STORAGE("", "7"),
        ACCOUNT_REPORTS("", "8"),
        ACCOUNT_FORCE_SIGNOUT("", "9");


        private String ten_chuc_nang;
        private String id;

        RoleType(String ten_chuc_nang, String id) {
            this.ten_chuc_nang = ten_chuc_nang;
            this.id = id;
        }

        public boolean isEqual(RoleType role) {
            return role.id.equals(id);
        }

        public boolean isEqual(RolePermissionModel role) {
            if (role == null || TextUtils.isEmpty(role.getId()) || role.getId().equalsIgnoreCase("null"))
                return false;

            return role.getId().equals(id);
        }
    }
}
