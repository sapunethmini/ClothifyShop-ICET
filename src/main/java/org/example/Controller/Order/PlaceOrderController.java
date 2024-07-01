package org.example.Controller.Order;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBo;
import org.example.bo.custom.impl.userBoImpl;
import org.example.db.dbConnection;
import org.example.dto.Employee;
import org.example.dto.Order;
import org.example.dto.Product;
import org.example.dto.Supplier;
import org.example.dto.Tables.Tbcart;
import org.example.dto.Tables.supProductTb;
import org.example.util.BoType;
import org.example.util.CrudUtil;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    public AnchorPane lblname;
    public ComboBox cmbSuPid;
    public ComboBox cmbItemCode;
    public Label lblTime;
    public Label lblDate;
    public Label lblOrderID;
    public Label lblName;
    public Label lblAderess;
    public Label lblsalary;
    public Label lbldesc;
    public Label lblPackSize;
    public Label lblUprice;
    public Label lblQYT;
    public TableView tblCart;
    public TableColumn colItemCOde;
    public TableColumn colDES;
    public TableColumn colQTY;
    public TableColumn colU_price;
    public TableColumn colPrice;
    public Button btnClearOnAction;
    public Label lblTotalCharge;
    public TextField txtQTY;
    public Label lblEmpName;
    public TextField txtEmpId;
    public Label lblcompnay;
    public Label lblEmail;
    public Label lblunitPrice;
    public Label lblItemName;
    public Label lblitemName;
    public TableColumn colName;
    public TableColumn colTotal;
    public Label lblnmmn;
    public Label lblNameItem;
    public TextField txtEmpCode;

    private String itemName;
    private Double itemunitPrice;
    private Integer itemquantity;

    private  Double total;

    ObservableList<Tbcart> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductID();
        loadDateAndTime();
        generateOrderId();

        colItemCOde.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colU_price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }


    public void btnAddCartOnAction(ActionEvent event) {

            String itemCode = (String) cmbItemCode.getValue();
            String name = lblNameItem.getText();
            Double unitPrice = Double.valueOf(lblunitPrice.getText());
            Integer qtyFroCustomer = Integer.parseInt(txtQTY.getText());
            total = qtyFroCustomer * unitPrice;

            Tbcart tblCartDetails = new Tbcart(itemCode, name, qtyFroCustomer, unitPrice, total);
            Integer availbleitemQTY = Integer.parseInt(lblQYT.getText());
            if (availbleitemQTY < qtyFroCustomer) {
                new Alert(Alert.AlertType.WARNING, "stock has only" + lblQYT.getText()).show();

            }
            UpdateProductTbl();
            cartList.add(tblCartDetails);
            tblCart.setItems(cartList);
            calcNetTotal();

        }

    public void btnPlaceOrderOnAction(ActionEvent event) {

        Order orders = new Order(
                lblOrderID.getText(),
                txtEmpId.getText(),
                cmbItemCode.getValue().toString(),
                itemName,
                itemunitPrice,
                Integer.parseInt(txtQTY.getText()),
                total

        );

        System.out.println(orders);


        userBoImpl userBoll = new userBoImpl();

        userBoll.Saveorder(orders);


    }


    private void UpdateProductTbl(){

        try (ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE id='" + cmbItemCode.getValue().toString()+ "'")) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String size = resultSet.getString("size");
                Double price = resultSet.getDouble("price");
                itemquantity = resultSet.getInt("Quantity");
                System.out.println("01 ---------->"+itemquantity);
                String supplierid = resultSet.getString("Supplier_ID");


                Integer Qtynew = Integer.parseInt(txtQTY.getText());
                if(Qtynew  < itemquantity) {
                    itemquantity =  itemquantity -Qtynew;


                    System.out.println("------------------------6666666666666666" + itemquantity);

                    Product product = new Product();
                    product.setId(id);
                    product.setName(name);
                    product.setSize(size);
                    product.setPrice(price);
                    product.setQty(itemquantity);
                    product.setSupplierid(supplierid);


                    userBoImpl userBoll = new userBoImpl();
                    userBoll.updateProduct(product);
                }

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void txtAddOnAction(ActionEvent event) {
        btnAddCartOnAction(event);
    }


    private void loadDateAndTime() {

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime time = LocalTime.now();

            lblTime.setText(
                    time.getHour() + ":" + time.getMinute() + ":" + time.getSecond()
            );
        }), new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void generateOrderId() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orders");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
            String orderId = "0" + String.format("%03d", count + 1);
            while (true) {
                boolean exists = false;
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
                ps.setString(1, orderId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    exists = true;
                }
                if (!exists) {
                    lblOrderID.setText(orderId);
                    break;
                } else {
                    count++;
                    orderId = "0" + String.format("%03d", count + 1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProductID() {
        try (ResultSet resultSet = CrudUtil.execute("SELECT id FROM product")) {
            ObservableList<String> pIdList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                pIdList.add(resultSet.getString("id"));
            }

            cmbItemCode.setItems(pIdList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }





    public void txtempIdOnAcrion(ActionEvent event) {
        try {
            String query = "SELECT * FROM user WHERE id='" + txtEmpId.getText()+ "'";
            System.out.println("Query: " + query);
            ResultSet resultSet = CrudUtil.execute(query);
            if (resultSet.next()) {
                Employee userDetails = new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                System.out.println("Supplier details: " + userDetails);
                lblEmpName.setText(userDetails.getName());
                lblEmail.setText(userDetails.getEmail());
                lblcompnay.setText(userDetails.getCompany());

            } else {
                System.out.println("No records found");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnItemOnaction(ActionEvent event) {
        try {
            String query = "SELECT * FROM product WHERE id='" + cmbItemCode.getValue().toString()+ "'";
            System.out.println("Query: " + query);
            ResultSet resultSet = CrudUtil.execute(query);

            if (resultSet.next()) {
                Product itemDetails = new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)

                );
                itemName = itemDetails.getName();
                itemunitPrice = itemDetails.getPrice();
                itemquantity=itemDetails.getQty();

                System.out.println("Supplier details: " + itemDetails);
                lblNameItem.setText(itemDetails.getName());
                lblPackSize.setText(itemDetails.getSize());
                lblunitPrice.setText(String.valueOf(itemDetails.getPrice()));
                lblQYT.setText(Integer.toString(itemDetails.getQty()));

                System.out.println(itemDetails.getSize());

            } else {
                System.out.println("No records found");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void calcNetTotal() {
        double ttl = 0;
        for (Tbcart cartObj : cartList) {
            ttl += cartObj.getTotal();
        }
        lblTotalCharge.setText(String.valueOf(ttl) + "/=");
    }

    ///////////////////
}
