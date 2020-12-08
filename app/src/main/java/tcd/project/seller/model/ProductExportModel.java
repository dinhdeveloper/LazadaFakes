package tcd.project.seller.model;

public class ProductExportModel extends BaseResponseModel {
    /**
     * export_id : 2
     * product_import_id : 2
     * product_name : Dầu Gội Đầu
     * quantity_export : 7
     * customer_name : Thu Thu
     * customer_phone : 9090999000
     * customer_address : 97 Man Thiện
     * date_create : 2020-10-14 23:05:49
     * type_order : 0
     * status : Y
     */

    private String export_id;
    private String product_import_id;
    private String product_name;
    private String quantity_export;
    private String customer_name;
    private String customer_phone;
    private String customer_address;
    private String date_create;
    private String type_order;
    private String status;

    public String getExport_id() {
        return export_id;
    }

    public void setExport_id(String export_id) {
        this.export_id = export_id;
    }

    public String getProduct_import_id() {
        return product_import_id;
    }

    public void setProduct_import_id(String product_import_id) {
        this.product_import_id = product_import_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity_export() {
        return quantity_export;
    }

    public void setQuantity_export(String quantity_export) {
        this.quantity_export = quantity_export;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getType_order() {
        return type_order;
    }

    public void setType_order(String type_order) {
        this.type_order = type_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
