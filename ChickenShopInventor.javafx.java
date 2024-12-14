import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChickenShopInventory extends Application {

    // TableView to display inventory
    private TableView<Item> tableView;

    // ObservableList to hold inventory items
    private ObservableList<Item> inventory;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Initialize the inventory list
        inventory = FXCollections.observableArrayList();

        // Initialize the TableView
        tableView = new TableView<>();
        tableView.setItems(inventory);

        // Table columns
        TableColumn<Item, String> nameColumn = new TableColumn<>("Item Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Item, Double> priceColumn = new TableColumn<>("Price (Per Kg)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(nameColumn, quantityColumn, priceColumn);

        // Input fields and buttons
        TextField nameField = new TextField();
        nameField.setPromptText("Item Name");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        // Add button functionality
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            int quantity;
            double price;
            try {
                quantity = Integer.parseInt(quantityField.getText());
                price = Double.parseDouble(priceField.getText());
                inventory.add(new Item(name, quantity, price));
                clearFields(nameField, quantityField, priceField);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Quantity and Price must be numeric values.");
            }
        });

        // Edit button functionality
        editButton.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    selectedItem.setName(nameField.getText());
                    selectedItem.setQuantity(Integer.parseInt(quantityField.getText()));
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));
                    tableView.refresh();
                    clearFields(nameField, quantityField, priceField);
                } catch (NumberFormatException ex) {
                    showAlert("Invalid Input", "Quantity and Price must be numeric values.");
                }
            } else {
                showAlert("No Selection", "Please select an item to edit.");
            }
        });

        // Delete button functionality
        deleteButton.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                inventory.remove(selectedItem);
            } else {
                showAlert("No Selection", "Please select an item to delete.");
            }
        });

        // Layout for input fields and buttons
        HBox inputLayout = new HBox(10, nameField, quantityField, priceField, addButton, editButton, deleteButton);
        inputLayout.setPadding(new Insets(10));

        // Main layout
        VBox mainLayout = new VBox(10, tableView, inputLayout);
        mainLayout.setPadding(new Insets(10));

        // Scene and Stage setup
        Scene scene = new Scene(mainLayout, 600, 400);
        stage.setTitle("Chicken Shop Inventory");
        stage.setScene(scene);
        stage.show();
    }

    // Method to clear input fields
    private void clearFields(TextField nameField, TextField quantityField, TextField priceField) {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
    }

    // Method to show alerts
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Item class to represent inventory items
    public static class Item {
        private String name;
        private int quantity;
        private double price;

        public Item(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}