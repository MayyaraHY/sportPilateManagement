package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import user.Services.userService;
import user.Models.user;
import user.Models.Promo;
import user.Services.PromoService;
import user.Utils.UserSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.IOException;
import java.util.List;

public class Profile {
    @FXML
    private Label mailL;

    @FXML
    private Label nomL;

    @FXML
    private Label prenomL;
    @FXML
    private Label numTelL;
    @FXML
    private Label codesL;

    @FXML
    private ListView<Integer> codesLV;

    private final userService userService = new userService();
    private final PromoService promoService = new PromoService();
    public void initProfile() {
        // Set user's information
        if (UserSession.getId() != 0) {
            nomL.setText(UserSession.getNom());
            prenomL.setText(UserSession.getPrenom());
            mailL.setText(UserSession.getMail());
            numTelL.setText(String.valueOf(UserSession.getNumTel()));


            // Retrieve and display code promos associated with the user's ID
            List<Promo> userPromos = promoService.searchByUserId(UserSession.getId());
            for (Promo promo : userPromos) {
                codesLV.getItems().add(promo.getCode());
            }
        } else {
            System.out.println("No logged-in user found.");
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }


    public void UpdateProfile(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProfile.fxml"));
            Parent root = loader.load();
            UpdateProfile editProfileController = loader.getController();
            // Pass current user information to the edit profile controller
            editProfileController.initData(new user( UserSession.getNom(), UserSession.getPrenom(), UserSession.getMail(), UserSession.getRole(), UserSession.getMdp(), UserSession.getNumTel()));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }


    public void exportPromoToPDF(ActionEvent actionEvent) {
        Promo selectedPromo = getSelectedPromo();
       /* if (selectedPromo != null) {
            try {
                PdfWriter writer = new PdfWriter(new FileOutputStream("promo_info.pdf"));
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Add promo information to PDF
                document.add(new Paragraph("Promo Code: " + selectedPromo.getCode()));
                document.add(new Paragraph("Off Percentage: " + selectedPromo.getPourcentage()));
                document.add(new Paragraph("Expiration Date: " + selectedPromo.getValidite()));
                document.add(new Paragraph("User: " + UserSession.getNom() + " " + UserSession.getPrenom()));
                document.add(new Paragraph("Days until Expiration: " + promoService.daysUntilExpiration(selectedPromo)));

                document.close();
                System.out.println("PDF created successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a discount code");
        }*/
    }

    public Promo getSelectedPromo() {
        Integer selectedPromoCode = codesLV.getSelectionModel().getSelectedItem();
        if (selectedPromoCode != null) {
            // Retrieve the Promo object associated with the selected promo code
            return promoService.getPromoByCode(selectedPromoCode);
        } else {
            return null;
        }
    }
}
