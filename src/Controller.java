import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private Label labelMensagem;

    @FXML
    private void cadastrarClicado() {
        String nome = campoNome.getText();
        String email = campoEmail.getText();

        if (nome.trim().isEmpty() || email.trim().isEmpty()) {
            labelMensagem.setText("Por favor, preencha todos os campos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
        } else {
            labelMensagem.setText("Cadastro realizado com sucesso!\nNome: " + nome + "\nEmail: " + email);
            labelMensagem.setStyle("-fx-text-fill: green;");

            // Limpa os campos
            campoNome.clear();
            campoEmail.clear();
        }
    }
}