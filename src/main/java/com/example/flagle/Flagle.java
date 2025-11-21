package com.example.flagle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.util.*;

public class Flagle extends Application {

    private class Country {
        String name;
        String flagPath;

        Country(String name, String flagPath) {
            this.name = name;
            this.flagPath = flagPath;
        }
    }

    private List<Country> countries = Arrays.asList(
            new Country("Brasil", "/flags/brazil.png"),
            new Country("Estados Unidos", "/flags/usa.png"),
            new Country("França", "/flags/france.png"),
            new Country("Alemanha", "/flags/germany.png"),
            new Country("Japão", "/flags/japan.png"),
            new Country("Canadá", "/flags/canada.png"),
            new Country("Austrália", "/flags/australia.png"),
            new Country("Itália", "/flags/italy.png")
    );

    private Country currentCountry;
    private int score = 0;
    private int attempts = 0;
    private final int maxAttempts = 3;

    private Label titleLabel;
    private Label subtitleLabel;
    private ImageView flagImageView;
    private TextField guessTextField;
    private Button guessButton;
    private Label resultLabel;
    private Label scoreLabel;
    private Label attemptsLabel;
    private Label dateLabel;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5f5;");

        VBox mainContainer = new VBox(15);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setMaxWidth(500);

        titleLabel = new Label("Flagle");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        titleLabel.setTextFill(Color.DARKBLUE);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        subtitleLabel = new Label("Adivinhe o país:");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        subtitleLabel.setTextAlignment(TextAlignment.CENTER);

        dateLabel = new Label("Data: " + java.time.LocalDate.now().toString());
        dateLabel.setFont(Font.font("Arial", 12));
        dateLabel.setTextFill(Color.GRAY);
        dateLabel.setTextAlignment(TextAlignment.CENTER);

        flagImageView = new ImageView();
        flagImageView.setFitHeight(180);
        flagImageView.setFitWidth(280);
        flagImageView.setPreserveRatio(true);
        flagImageView.setStyle("-fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px;");

        StackPane flagContainer = new StackPane(flagImageView);
        flagContainer.setAlignment(Pos.CENTER);

        HBox guessArea = new HBox(10);
        guessArea.setAlignment(Pos.CENTER);

        guessTextField = new TextField();
        guessTextField.setPromptText("Digite o nome do país...");
        guessTextField.setPrefWidth(250);
        guessTextField.setPrefHeight(35);
        guessTextField.setStyle("-fx-font-size: 14px;");
        guessTextField.setOnAction(e -> checkGuess());

        guessButton = new Button("ENVIAR");
        guessButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        guessButton.setPrefHeight(35);
        guessButton.setPrefWidth(100);
        guessButton.setOnAction(e -> checkGuess());

        guessArea.getChildren().addAll(guessTextField, guessButton);

        resultLabel = new Label("");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        resultLabel.setTextAlignment(TextAlignment.CENTER);
        resultLabel.setPrefWidth(400);


        VBox infoPanel = new VBox(8);
        infoPanel.setAlignment(Pos.CENTER);
        infoPanel.setStyle("-fx-background-color: #e8e8e8; -fx-padding: 15; -fx-border-radius: 8; -fx-background-radius: 8;");
        infoPanel.setMaxWidth(300);

        scoreLabel = new Label("Pontuação: 0");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scoreLabel.setTextAlignment(TextAlignment.CENTER);

        attemptsLabel = new Label("Tentativas: 0/" + maxAttempts);
        attemptsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        attemptsLabel.setTextAlignment(TextAlignment.CENTER);

        infoPanel.getChildren().addAll(scoreLabel, attemptsLabel);


        Separator separator = new Separator();
        separator.setPrefWidth(400);

        Separator separator2 = new Separator();
        separator2.setPrefWidth(400);

        mainContainer.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                dateLabel,
                separator,
                flagContainer,
                new Label(""),
                guessArea,
                new Label(""),
                resultLabel,
                new Label(""),
                infoPanel
        );

        StackPane.setAlignment(mainContainer, Pos.CENTER);
        root.getChildren().add(mainContainer);

        startNewGame();

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Flagle - Jogo de Adivinhação");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    private void startNewGame() {
        score = 0;
        attempts = 0;
        updateInfoLabels();
        loadRandomCountry();
        resultLabel.setText("");
        guessTextField.setText("");
        guessTextField.setDisable(false);
        guessButton.setDisable(false);
        resultLabel.setTextFill(Color.BLACK);

        dateLabel.setText("Data: " + java.time.LocalDate.now().toString());
    }

    private void loadRandomCountry() {
        Random random = new Random();
        currentCountry = countries.get(random.nextInt(countries.size()));

        try {
            Image flagImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));
            Image sectorImage = extractImageSector(flagImage);
            flagImageView.setImage(sectorImage);
        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + currentCountry.flagPath);
            flagImageView.setImage(null);
            flagImageView.setStyle("-fx-background-color: #dddddd; -fx-border-color: #cccccc;");
        }
    }

    private Image extractImageSector(Image originalImage) {
        int originalWidth = (int) originalImage.getWidth();
        int originalHeight = (int) originalImage.getHeight();

        int sectorWidth, sectorHeight;
                sectorWidth = originalWidth / 2;
                sectorHeight = originalHeight / 2;

        sectorWidth = Math.min(sectorWidth, originalWidth);
        sectorHeight = Math.min(sectorHeight, originalHeight);

        Random random = new Random();
        int startX = random.nextInt(originalWidth - sectorWidth);
        int startY = random.nextInt(originalHeight - sectorHeight);

        startX = Math.max(0, Math.min(startX, originalWidth - sectorWidth));
        startY = Math.max(0, Math.min(startY, originalHeight - sectorHeight));

        PixelReader pixelReader = originalImage.getPixelReader();
        WritableImage sectorImage = new WritableImage(pixelReader, startX, startY, sectorWidth, sectorHeight);

        return sectorImage;
    }

    private void checkGuess() {
        String userGuess = guessTextField.getText().trim().toLowerCase();
        String correctAnswer = currentCountry.name.toLowerCase();

        if (userGuess.isEmpty()) {
            resultLabel.setText("Digite um palpite!");
            resultLabel.setTextFill(Color.ORANGE);
            return;
        }

        attempts++;
        updateInfoLabels();

        if (userGuess.equals(correctAnswer)) {
            score += 10;
            resultLabel.setText("✓ Correto! É " + currentCountry.name + "! +10 pontos");
            resultLabel.setTextFill(Color.GREEN);

            guessTextField.setDisable(true);
            guessButton.setDisable(true);

            // Próxima rodada após 2 segundos
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                loadRandomCountry();
                attempts = 0;
                updateInfoLabels();
                guessTextField.setText("");
                guessTextField.setDisable(false);
                guessButton.setDisable(false);
                resultLabel.setText("");
            }));
            timeline.setCycleCount(1);
            timeline.play();

        } else {
            resultLabel.setText("✗ Incorreto! Tente novamente.");
            resultLabel.setTextFill(Color.RED);
            guessTextField.setText("");
            guessTextField.requestFocus();

            if (attempts >= maxAttempts) {
                resultLabel.setText("✗ Fim das tentativas! Era " + currentCountry.name);
                guessTextField.setDisable(true);
                guessButton.setDisable(true);

                // Restart
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                    loadRandomCountry();
                    attempts = 0;
                    updateInfoLabels();
                    guessTextField.setText("");
                    guessTextField.setDisable(false);
                    guessButton.setDisable(false);
                    resultLabel.setText("");
                }));
                timeline.setCycleCount(1);
                timeline.play();
            }
        }
    }

    private void updateInfoLabels() {
        scoreLabel.setText("Pontuação: " + score);
        attemptsLabel.setText("Tentativas: " + attempts + "/" + maxAttempts);
    }

    public static void main(String[] args) {
        launch(args);
    }
}