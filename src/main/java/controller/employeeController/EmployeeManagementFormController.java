package controller.employeeController;

import controller.ReceiptController.ReceiptFormController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrderService;
import service.custom.SupplierService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeManagementFormController implements Initializable {

    public AnchorPane pageCustomer;
    public AnchorPane pageSupplier;
    public AnchorPane pageProduct;

    public AnchorPane pageOrderManage;
    public AnchorPane pagePlaceOrder;
    public AnchorPane pageViewOrders;
    public TableColumn colCustId;
    public TableColumn colCustTitle;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public TableColumn colCustCity;
    public TableColumn colCustProvince;
    public TableColumn colCustPostalCode;
    public TableColumn colCustEmail;
    public TableColumn colCustContact;
    public TextField txtCustCity;
    public TextField txtCustName;
    public TextField txtCustEmail;
    public TextField txtCustProvince;
    public TextField txtCustPostalCode;
    public ComboBox cmbCustTitle;
    public TextArea txtCustAddress;
    public TextField txtCustContact;
    public TableView tblCustomer;
    public Label btnLogOut;
    public TextArea txtSupCompanyAddress;
    public TextField txtSupPostalCode;
    public TextField txtSupCompany;
    public TextField txtSupCompanyEmail;
    public TextField txtSupName;
    public TableColumn colSupPostalCode;
    public TableColumn colSupContact;
    public TableColumn colSupEmail;
    public TableColumn colSupAddress;
    public TableColumn colSupCompany;
    public TableColumn colSupName;
    public TableColumn colSupId;
    public TableView tblSupplier;
    public TextField txtSupContact;
    public TableView tblItem;
    public TableColumn colItemtId;
    public TableColumn colItemCategory;
    public TableColumn colItemName;
    public TableColumn colItemSize;
    public TableColumn colItemQty;
    public TableColumn colItemUnitPrice;

    public TextField txtItemName;
    public TextField txtItemUnitPrice;

    public ComboBox cmbItemCategory;

    public ComboBox cmbItemSize;
    public TextField txtItemQty;
    public ComboBox cmbSupplierId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox cmbItemCode;
    public TextField txtOrderItemQty;
    public TextField txtOrderItemSize;
    public TextField txtOrderItemCate;
    public TextField txtOrderItemUnitPrice;
    public TextField txtOrderItemName;
    public TableColumn colOrderItemCode;
    public TableColumn colOrderItemName;
    public TableColumn colOrderItemCategory;
    public TableColumn colOrderItemSize;
    public TableColumn colOrderItemQty;
    public TableColumn colOrderItemUnitPrice;
    public TableColumn colOrderItemTotal;
    public TextField txtOrderCustAddress;
    public ComboBox cmbCustId;
    public TextField txtOrderCustName;
    public TextField txtOrderCustContact;
    public TextField txtCustId;
    public TextField txtSupItemDesc;
    public TextField txtSupId;
    public Label lblNetTotal;
    public TextField txtOrderEmpId;
    public TextField txtOrderEmpEmali;
    public TextField txtItemId;
    public TextField txtOrderItemStockLev;
    public TextField txtOrderItemDiscount;
    public TableView<CartTM> tblOrderDetails;
    public Label lblOrderId;

    private AnchorPane currentPage;
    private Timeline timeline;


    //=======================================================================LOG OUT BUTTON==========================================================================
    public void btnLogOutOnAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/logInForm.fxml"))));
            btnLogOut.getScene().getWindow().hide();
            stage.setHeight(563);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //====================================================================CUSTOMER MANAGEMENT==========================================================================
    final CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    public void btnCustomerManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageCustomer;
        currentPage.setVisible(true);
    }

    private void loadTitleMenu() {
        ObservableList<Object> title = FXCollections.observableArrayList();
        title.add("Mr.");
        title.add("Mrs.");
        title.add("Miss.");
        title.add("Master.");
        cmbCustTitle.setItems(title);
    }

    private void loadCustomerTable() {
        ObservableList<Customer> customers = customerService.getAll();
        tblCustomer.setItems(customers);
    }

    private void setTextToValues(Customer newValue) {
        txtCustId.setText(newValue.getCustId());
        cmbCustTitle.setValue(newValue.getCustTitle());
        txtCustName.setText(newValue.getCustName());
        txtCustContact.setText(newValue.getCustContact());
        txtCustEmail.setText(newValue.getCustEmail());
        txtCustAddress.setText(newValue.getCustAddress());
        txtCustCity.setText(newValue.getCity());
        txtCustProvince.setText(newValue.getProvince());
        txtCustPostalCode.setText(newValue.getPostalCode());
    }

    public void clearFieldsCustomer() {
        txtCustId.setText(customerService.generateCustomerId());
        cmbCustTitle.setValue(null);
        txtCustName.setText("");
        txtCustContact.setText("");
        txtCustAddress.setText("");
        txtCustCity.setText("");
        txtCustProvince.setText("");
        txtCustPostalCode.setText("");
        txtCustEmail.setText("");
    }

    public void btnAddCustomerOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustId.getText(),
                cmbCustTitle.getValue().toString(),
                txtCustName.getText(),
                txtCustContact.getText(),
                txtCustEmail.getText(),
                txtCustAddress.getText(),
                txtCustCity.getText(),
                txtCustProvince.getText(),
                txtCustPostalCode.getText()
        );
        if (customerService.addCustomer(customer)) {
            loadCustomerTable();
            clearFieldsCustomer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Added Successfully...");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Didn't added....");
            alert.show();
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustId.getText(),
                cmbCustTitle.getValue().toString(),
                txtCustName.getText(),
                txtCustContact.getText(),
                txtCustEmail.getText(),
                txtCustAddress.getText(),
                txtCustCity.getText(),
                txtCustProvince.getText(),
                txtCustPostalCode.getText()
        );
        if (customerService.updateCustomer(customer)) {
            loadCustomerTable();
            clearFieldsCustomer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Updated Successfully..");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer didn't Updated ...");
            alert.show();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent event) {
        try {
            Customer customer = customerService.searchCustomer(txtCustId.getText());
            if (customer != null) {
                setTextToValues(customer);
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnDeleteCustomerOnAction(ActionEvent event) {
        if (customerService.deleteCustomer(txtCustId.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Deleted SuccessFully");
            alert.show();
            loadCustomerTable();
            clearFieldsCustomer();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Didn't Found");
            alert.show();
        }
    }

    public void btnCustomerClearOnAction(MouseEvent mouseEvent) {
        clearFieldsCustomer();
    }

    //====================================================================SUPPLIER MANAGEMENT==========================================================================
    final SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    public void btnSupplierManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageSupplier;
        currentPage.setVisible(true);
    }

    private void loadSupplierTable() {
        tblSupplier.setItems(FXCollections.observableArrayList(supplierService.getAll()));
    }

    private void setTextToValues(Supplier newValue) {
        txtSupId.setText(newValue.getSupplierId());
        txtSupName.setText(newValue.getSupplierName());
        txtSupCompany.setText(newValue.getSupplierCompany());
        txtSupContact.setText(newValue.getSupplierContactNumber());
        txtSupCompanyEmail.setText(newValue.getSupplierEmailAddress());
        txtSupCompanyAddress.setText(newValue.getSupplierAddress());
        txtSupPostalCode.setText(newValue.getSupplierPostalCode());
        txtSupItemDesc.setText(newValue.getSupplierItem());
    }

    public void clearFieldsSupplier() {
        txtSupId.setText(supplierService.generateSupplierId());
        txtSupName.setText("");
        txtSupCompany.setText("");
        txtSupContact.setText("");
        txtSupItemDesc.setText("");
        txtSupPostalCode.setText("");
        txtSupCompanyEmail.setText("");
        txtSupCompanyAddress.setText("");
    }

    public void btnAddSupplierOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtSupId.getText(),
                txtSupName.getText(),
                txtSupContact.getText(),
                txtSupItemDesc.getText(),
                txtSupCompany.getText(),
                txtSupCompanyAddress.getText(),
                txtSupCompanyEmail.getText(),
                txtSupPostalCode.getText()
        );
        if (supplierService.addSupplier(supplier)) {
            loadSupplierTable();
            clearFieldsSupplier();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Supplier Added Successfully...");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Supplier Didn't added....");
            alert.show();
        }
    }

    public void btnUpdateSupplierOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtSupId.getText(),
                txtSupName.getText(),
                txtSupContact.getText(),
                txtSupItemDesc.getText(),
                txtSupCompany.getText(),
                txtSupCompanyAddress.getText(),
                txtSupCompanyEmail.getText(),
                txtSupPostalCode.getText()
        );
        if (supplierService.updateSupplier(supplier)) {
            loadSupplierTable();
            clearFieldsSupplier();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Supplier Updated Successfully..");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Supplier didn't Updated ...");
            alert.show();
        }
    }

    public void btnSerachSupplierOnAction(ActionEvent event) {
        Supplier supplier = supplierService.searchSupplier(txtSupId.getText());
        if (supplier != null) {
            setTextToValues(supplier);
        } else {
            // Show an alert if no customer is found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supplier Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No Supplier found with ID: " + txtSupId.getText());
            alert.showAndWait();
        }
    }

    public void btnDeleteSupplierOnAction(ActionEvent event) {
        if (supplierService.deleteSupplier(txtSupId.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Supplier Deleted SuccessFully");
            alert.show();
            loadSupplierTable();
            clearFieldsSupplier();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Supplier Didn't Found");
            alert.show();
        }
    }

    public void btnSupplierClearOnAction(MouseEvent mouseEvent) {
        clearFieldsSupplier();
    }

    //======================================================================ITEM MANAGEMENT==========================================================================
    final ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    public void btnProductManagementOnAction(ActionEvent event) {
        loadSupplierId();
        currentPage.setVisible(false);
        currentPage = pageProduct;
        currentPage.setVisible(true);
    }

    private void loadItemTable() {
        tblItem.setItems(FXCollections.observableArrayList(itemService.getAll()));
    }

    private void loadCategoryMenu() {
        ObservableList<Object> category = FXCollections.observableArrayList();
        category.add("Ladies");
        category.add("Gents");
        category.add("Kids");
        cmbItemCategory.setItems(category);
    }

    private void loadSizeMenu() {
        ObservableList<Object> sizes = FXCollections.observableArrayList();
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizes.add("XXL");
        cmbItemSize.setItems(sizes);
    }

    private void loadSupplierId() {
        List<Supplier> suppliers = supplierService.getAll();
        ObservableList<String> supplierIds = FXCollections.observableArrayList();
        for (Supplier supplier : suppliers) {
            supplierIds.add(supplier.getSupplierId());
        }
        cmbSupplierId.setItems(supplierIds);
    }

    private void setTextToValues(Item newValue) {
        txtItemId.setText(newValue.getItemId());
        txtItemName.setText(newValue.getName());
        txtItemQty.setText(String.valueOf(newValue.getQty()));
        txtItemUnitPrice.setText(String.valueOf(newValue.getPrice()));
        cmbItemCategory.setValue(newValue.getCategory());
        cmbItemSize.setValue(newValue.getSize());
        cmbSupplierId.setValue(newValue.getSupId());
    }

    public void clearFieldsItem() {
        txtItemId.setText(itemService.generateItemId());
        txtItemName.setText("");
        cmbItemCategory.setValue("");
        cmbItemSize.setValue("");
        cmbSupplierId.setValue("");
        txtItemQty.setText("");
        txtItemUnitPrice.setText("");
    }

    public void btnAddItemOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemId.getText(),
                txtItemName.getText(),
                cmbItemCategory.getValue().toString(),
                cmbItemSize.getValue().toString(),
                cmbSupplierId.getValue().toString(),
                Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtItemQty.getText())
        );
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        if (itemService.addItem(item)) {
            loadItemTable();
            clearFieldsItem();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Added Successfully...");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to add the item.");
            alert.show();
        }
    }

    public void btnUpdateItemOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemId.getText(),
                txtItemName.getText(),
                cmbItemCategory.getValue().toString(),
                cmbItemSize.getValue().toString(),
                cmbSupplierId.getValue().toString(),
                Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtItemQty.getText())
        );
        if (itemService.updateItem(item)) {
            loadItemTable();
            clearFieldsItem();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Updated Successfully..");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item didn't Updated ...");
            alert.show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent event) {
        Item item = itemService.searchItem(txtItemId.getText());
        if (item != null) {
            setTextToValues(item);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Item Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No Item found with ID: " + txtItemId.getText());
            alert.showAndWait();
        }
    }

    public void btnDeleteItemOnAction(ActionEvent event) {
        if (itemService.deleteItem(txtItemId.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Deleted SuccessFully");
            alert.show();
            loadItemTable();
            clearFieldsItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Item Didn't Found");
            alert.show();
        }
    }

    public void btnItemClearOnAction(MouseEvent mouseEvent) {
        clearFieldsItem();
    }

    //====================================DASHBOARD MANAGEMENT==============================================================


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageOrderManage;
        currentPage.setVisible(true);
/////////////////////////////////////////////CUSTOMER///////////////////////////////////////////////////////////////////
        txtCustId.setText(customerService.generateCustomerId());
        loadTitleMenu();
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colCustContact.setCellValueFactory(new PropertyValueFactory<>("custContact"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCustPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Customer) newValue);
            }
        }));
        loadCustomerTable();
/////////////////////////////////////////////SUPPLIER///////////////////////////////////////////////////////////////////
        txtSupId.setText(supplierService.generateSupplierId());
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmailAddress"));
        colSupContact.setCellValueFactory(new PropertyValueFactory<>("supplierContactNumber"));
        colSupPostalCode.setCellValueFactory(new PropertyValueFactory<>("supplierPostalCode"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Supplier) newValue);
            }
        }));
        loadSupplierTable();
///////////////////////////////////////////////ITEM/////////////////////////////////////////////////////////////////////
        txtItemId.setText(itemService.generateItemId());
        colItemtId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Item) newValue);
            }
        }));
        loadItemTable();
        loadCategoryMenu();
        loadSizeMenu();
        loadSupplierId();
        loadDateAndTime();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //lblOrderId.setText(orderService.generateOrderId());
        colOrderItemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colOrderItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOrderItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colOrderItemSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colOrderItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOrderItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadCustomerId();
        loadItemId();
        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            if (newValue != null) {
                searchCustomer((String) newValue);
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            if (newValue != null) {
                searchItemCode((String) newValue);
            }
        });
    }

    final OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    public void btnOrderManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageOrderManage;
        currentPage.setVisible(true);
    }

    public void btnPlaceOrderOnAction(MouseEvent mouseEvent) {
        lblOrderId.setText(orderService.generateOrderId());
        loadCustomerId();
        loadItemId();
        currentPage.setVisible(false);
        currentPage = pagePlaceOrder;
        currentPage.setVisible(true);
    }

    public void btnViewOrderOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageViewOrders;
        currentPage.setVisible(true);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);
        lblDate.setText(dateNow);

        timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(currentTime.format(formatter));
        }), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void loadCustomerId() {
        ObservableList<String> customerIds = customerService.getCustomerIds();
        cmbCustId.setItems(customerIds);
    }

    public void loadItemId() {
        ObservableList<String> itemIds = itemService.getItemIds();
        for (String itemId : itemIds) {
            System.out.println(itemId);
        }
        cmbItemCode.setItems(itemIds);
    }

    private void searchItemCode(String newValue) {
        Item item = itemService.searchItem(newValue);
        txtOrderItemName.setText(item.getName());
        txtOrderItemSize.setText(item.getSize());
        txtOrderItemCate.setText(item.getCategory());
        txtOrderItemStockLev.setText(String.valueOf(item.getQty()));
        txtOrderItemUnitPrice.setText(String.valueOf(item.getPrice()));
    }

    private void searchCustomer(String newValue) {
        Customer customer = customerService.searchCustomer(newValue);
        txtOrderCustName.setText(customer.getCustName());
        txtOrderCustAddress.setText(customer.getCustAddress());
        txtOrderCustContact.setText(customer.getCustContact());
    }

    public static Double discount = 0.0;

    private void calcNetTotal() {
        Double total = 0.0;
        for (CartTM cartTM : cartTMS) {
            total += cartTM.getTotal();
        }
        if (total < 0) {
            total = 0.0;
        }
        lblNetTotal.setText(total.toString() + " /=");
    }

    public void btnAddtoCartOnAction(ActionEvent event) {
        String itemId = cmbItemCode.getValue().toString();
        String name = txtOrderItemName.getText();
        String category = txtOrderItemCate.getText();
        String size = txtOrderItemSize.getText();
        double unitPrice = Double.parseDouble(txtOrderItemUnitPrice.getText());
        int qty = Integer.parseInt(txtOrderItemQty.getText());
        int currentStock = Integer.parseInt(txtOrderItemStockLev.getText());

        if (currentStock < qty) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Invalid QTY. Stock available: " + currentStock);
            alert.show();
        } else {
            double itemDiscount = Double.parseDouble(txtOrderItemDiscount.getText());
            double total = (unitPrice * qty) - itemDiscount;
            cartTMS.add(new CartTM(itemId, name, category, size, qty, unitPrice, total));
            calcNetTotal();
            updateStockRemove(itemId, qty);
            refreshStockDisplay(itemId);
            tblOrderDetails.setItems(cartTMS);
            discount += itemDiscount;
        }
    }

    private int updateStockRemove(String itemId, int qtyToAdd) {
        Item item = itemService.searchItem(itemId);
        int updatedStock = item.getQty();
        try {
            if (item != null) {
                int currentStock = item.getQty();
                updatedStock = currentStock - qtyToAdd;
                item.setQty(updatedStock);
                itemService.updateItem(item);
                loadItemTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating stock: " + e.getMessage());
            alert.show();
        }
        return updatedStock;
    }

    private int updateStock(String itemId, int qtyToRemove) {
        Item item = itemService.searchItem(itemId);
        int updatedStock = item.getQty();
        try {
            if (item != null) {
                int currentStock = item.getQty();
                updatedStock = currentStock + qtyToRemove;
                item.setQty(updatedStock);
                itemService.updateItem(item);
                loadItemTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating stock: " + e.getMessage());
            alert.show();
        }
        return updatedStock;
    }

    private void refreshStockDisplay(String itemId) {
        Item item = itemService.searchItem(itemId);
        if (item != null) {
            txtOrderItemStockLev.setText(String.valueOf(item.getQty()));
        }
    }

    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    public void clearFieldsOrder() {
        btncClearOnAction(new ActionEvent());
        txtOrderEmpId.setText("");
        txtOrderEmpEmali.setText("");
        cmbCustId.setValue(null);
        txtOrderCustName.setText("");
        txtOrderCustAddress.setText("");
        txtOrderCustContact.setText("");
        lblOrderId.setText(orderService.generateOrderId());
    }

    public void btncClearOnAction(ActionEvent event) {
        cmbItemCode.setValue(null);
        txtOrderItemName.setText("");
        txtOrderItemCate.setText("");
        txtOrderItemStockLev.setText("");
        txtOrderItemQty.setText("");
        txtOrderItemSize.setText("");
        txtOrderItemDiscount.setText("");
        txtOrderItemDiscount.setText("");
        txtOrderItemUnitPrice.setText("");

    }

    public void btnRemoveOnAction(ActionEvent event) {
        CartTM selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String itemId = selectedItem.getItemId();
            int qtyToRemove = selectedItem.getQty();
            cartTMS.remove(selectedItem);
            updateStock(itemId, qtyToRemove);
            refreshStockDisplay(itemId);
            int qty = selectedItem.getQty();
            double unitPrice = selectedItem.getPrice();
            double total = selectedItem.getTotal();
            double removeDiscount = (qty * unitPrice) - total;
            discount -= removeDiscount;
            calcNetTotal();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select a table row to delete");
            alert.show();
        }
    }

    public void btnPlaceOrderCartOnAction(ActionEvent event) {
        if (cartTMS.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No items in the cart to place an order.");
            alert.show();
        } else {

            List<OrderDetails> orderDetailsList = new ArrayList<>();

            cartTMS.forEach(obj -> {
                orderDetailsList.add(new OrderDetails(lblOrderId.getText(), obj.getItemId(), obj.getQty(), obj.getPrice()));
            });

            Order order = new Order(lblOrderId.getText(), LocalDateTime.now(), txtOrderEmpId.getText(), txtOrderEmpEmali.getText(), orderDetailsList);
            orderService.placeOrder(order);

            Receipt receipt = new Receipt(
                    lblOrderId.getText(),
                    txtOrderCustName.getText(),
                    txtOrderCustContact.getText(),
                    txtOrderCustAddress.getText(),
                    new ArrayList<>(cartTMS),
                    discount,
                    Double.parseDouble(lblNetTotal.getText().split(" ")[0]),
                    LocalDateTime.now()
            );

            cartTMS.clear();
            lblNetTotal.setText("0/=");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!");
            alert.show();
            showReceipt(receipt);
            clearFieldsOrder();
        }
    }

    private void showReceipt(Receipt receipt) {
        Stage receiptStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/receiptForm.fxml"));
            Parent root = loader.load();

            ReceiptFormController controller = loader.getController();
            controller.initializeReceipt(receipt);

            receiptStage.setScene(new Scene(root));
            receiptStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}