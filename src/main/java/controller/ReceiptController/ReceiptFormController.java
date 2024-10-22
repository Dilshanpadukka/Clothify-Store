package controller.ReceiptController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CartTM;
import model.Receipt;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ReceiptFormController implements Initializable {
    private Timeline timeline;
    public Label lblOrderId;
    public Label lblCustomerName;
    public Label lblCustomerAddress;
    public TableView<CartTM> tblItems;
    public TableColumn<CartTM, String> colItemId;
    public TableColumn<CartTM, String> colItemName;
    public TableColumn<CartTM, Integer> colQty;
    public TableColumn<CartTM, Double> colPrice;
    public TableColumn<CartTM, Double> colTotal;
    public Label lblTotalPrice;
    public Label lblDiscount;
    public Label lblDate;
    public Label lblTime;

    public void initializeReceipt(Receipt receipt) {
        lblOrderId.setText(receipt.getOrderId());
        lblCustomerName.setText(receipt.getCustomerName());
        lblCustomerAddress.setText(receipt.getCustomerAddress());
        lblDiscount.setText(receipt.getDiscount().toString());
        for (CartTM item : receipt.getItems()) {
            tblItems.getItems().add(item);
        }
        lblTotalPrice.setText(receipt.getTotalPrice() + " /=");
    }

    public void handlePrintReceipt(ActionEvent event) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(
                    Paper.A4,
                    PageOrientation.PORTRAIT,
                    Printer.MarginType.EQUAL
            );

            boolean proceed = printerJob.showPrintDialog(tblItems.getScene().getWindow());
            if (proceed) {
                Node receiptNode = createReceiptNode();
                boolean success = printerJob.printPage(pageLayout, receiptNode);
                if (success) {
                    printerJob.endJob();
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                } else {
                    System.out.println("Print job failed.");
                }
            }
        }
    }

    private Node createReceiptNode() {
        VBox mainContainer = new VBox();
        mainContainer.setStyle("-fx-background-color: white;");
        mainContainer.setPrefWidth(595.0);
        mainContainer.setPrefHeight(842.0);
        mainContainer.setAlignment(Pos.TOP_CENTER);

        VBox receiptNode = new VBox();
        receiptNode.setSpacing(5);
        receiptNode.setStyle("-fx-padding: 20 20 20 20; -fx-font-family: 'Arial';");
        receiptNode.setMaxWidth(400);
        receiptNode.setAlignment(Pos.TOP_CENTER);

        // Logo
        ImageView logoView = new ImageView(new Image("file:E:/ICET/JavaFX/CourseWork/Clothify-Store/Clothify-Store/src/main/resources/image/logo.png"));
        logoView.setFitWidth(60);
        logoView.setFitHeight(60);
        logoView.setPreserveRatio(true);
        receiptNode.getChildren().add(logoView);

        // Store Name
        Label storeLabel = new Label("Clothify Store");
        storeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        receiptNode.getChildren().add(storeLabel);

        // Sales Invoice
        Label invoiceLabel = new Label("SALES INVOICE");
        invoiceLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        receiptNode.getChildren().add(invoiceLabel);

        // Add spacing
        receiptNode.getChildren().add(new Region());
        ((Region) receiptNode.getChildren().get(receiptNode.getChildren().size() - 1)).setPrefHeight(10);

        // Order Details Grid
        GridPane orderDetails = new GridPane();
        orderDetails.setHgap(50);
        orderDetails.setVgap(5);

        Label dateLabel = new Label("Date: " + lblDate.getText());
        Label timeLabel = new Label("Time: " + lblTime.getText());
        Label orderIdLabel = new Label("Order ID: " + lblOrderId.getText());
        Label customerLabel = new Label("Customer: " + lblCustomerName.getText());

        orderDetails.add(dateLabel, 0, 0);
        orderDetails.add(timeLabel, 0, 1);
        orderDetails.add(orderIdLabel, 1, 0);
        orderDetails.add(customerLabel, 1, 1);

        receiptNode.getChildren().add(orderDetails);

        // Separator
        Separator separator = new Separator();
        separator.setPrefWidth(400);
        receiptNode.getChildren().add(separator);

        // Headers
        GridPane headerGrid = new GridPane();
        headerGrid.setHgap(40);
        headerGrid.setPadding(new Insets(10, 0, 10, 0));

        Label itemHeader = new Label("Item");
        Label qtyHeader = new Label("Qty");
        Label priceHeader = new Label("Price");
        Label totalHeader = new Label("Total");

        headerGrid.add(itemHeader, 0, 0);
        headerGrid.add(qtyHeader, 1, 0);
        headerGrid.add(priceHeader, 2, 0);
        headerGrid.add(totalHeader, 3, 0);

        ColumnConstraints col1 = new ColumnConstraints(150);
        ColumnConstraints col2 = new ColumnConstraints(50);
        ColumnConstraints col3 = new ColumnConstraints(100);
        ColumnConstraints col4 = new ColumnConstraints(100);
        headerGrid.getColumnConstraints().addAll(col1, col2, col3, col4);

        receiptNode.getChildren().add(headerGrid);

        // Items
        double totalPrice = 0.0;
        for (CartTM item : tblItems.getItems()) {
            GridPane itemGrid = new GridPane();
            itemGrid.setHgap(40);
            itemGrid.getColumnConstraints().addAll(
                    new ColumnConstraints(150),
                    new ColumnConstraints(50),
                    new ColumnConstraints(100),
                    new ColumnConstraints(100)
            );

            Label itemName = new Label(item.getName());
            Label itemQty = new Label(String.valueOf(item.getQty()));
            Label itemPrice = new Label("Rs." + String.format("%.2f", item.getPrice()));
            Label itemTotal = new Label("Rs." + String.format("%.2f", item.getTotal()));

            itemGrid.add(itemName, 0, 0);
            itemGrid.add(itemQty, 1, 0);
            itemGrid.add(itemPrice, 2, 0);
            itemGrid.add(itemTotal, 3, 0);

            receiptNode.getChildren().add(itemGrid);
            totalPrice += item.getTotal();
        }

        // Bottom separator
        Separator bottomSeparator = new Separator();
        bottomSeparator.setPrefWidth(400);
        receiptNode.getChildren().add(bottomSeparator);

        // Total Amount
        Label totalLabel = new Label(String.format("Total Amount: Rs.%.2f", totalPrice));
        totalLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #FF6B00;");
        receiptNode.getChildren().add(totalLabel);

        // Add spacing before footer
        receiptNode.getChildren().add(new Region());
        ((Region) receiptNode.getChildren().get(receiptNode.getChildren().size() - 1)).setPrefHeight(20);

        // Footer
        Label thankYouLabel = new Label("Thank you for shopping with us!");
        thankYouLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        Label sloganLabel = new Label("Clothify Store - Stylish clothing, happy customers!");
        sloganLabel.setStyle("-fx-font-size: 11px;");
        Label websiteLabel = new Label("www.clothifystore.lk");
        websiteLabel.setStyle("-fx-font-size: 11px;");


        receiptNode.getChildren().addAll(thankYouLabel, sloganLabel, websiteLabel);

        mainContainer.getChildren().add(receiptNode);
        VBox.setMargin(receiptNode, new Insets(20, 0, 0, 0));

        return mainContainer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadDateAndTime();
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
}
