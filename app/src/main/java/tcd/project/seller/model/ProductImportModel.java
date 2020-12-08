package tcd.project.seller.model;

public class ProductImportModel extends BaseResponseModel {
    /**
     * import_id : 1
     * product_name : Nước Tẩy Trang
     * product_price : 120000
     * quantity_import : 10
     * safe_stock : 2
     * employee_name : Thu Huyền
     * employee_phone : 0933217758
     * date_create : 2020-11-19 23:00:59
     * status : Y
     */

    private String import_id;
    private String product_name;
    private String product_price;
    private String quantity_import;
    private String safe_stock;
    private String employee_name;
    private String employee_phone;
    private String date_create;
    private String status;

    public String getImport_id() {
        return import_id;
    }

    public void setImport_id(String import_id) {
        this.import_id = import_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getQuantity_import() {
        return quantity_import;
    }

    public void setQuantity_import(String quantity_import) {
        this.quantity_import = quantity_import;
    }

    public String getSafe_stock() {
        return safe_stock;
    }

    public void setSafe_stock(String safe_stock) {
        this.safe_stock = safe_stock;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
