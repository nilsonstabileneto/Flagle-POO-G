package com.example.flagle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.text.Normalizer;
import java.util.*;

public class flagle extends Application {

    private class Country {
        String name;
        String flagPath;

        Country(String name, String flagPath) {
            this.name = name;
            this.flagPath = flagPath;
        }
    }

    private List<Country> countries = Arrays.asList(
            new Country("Argentina", "/flags/argentina.png"),
            new Country("Brasil", "/flags/brazil.png"),
            new Country("Canadá", "/flags/canada.png"),
            new Country("Estados Unidos", "/flags/usa.png"),
            new Country("França", "/flags/france.png"),
            new Country("Alemanha", "/flags/germany.png"),
            new Country("Japão", "/flags/japan.png"),
            new Country("Austrália", "/flags/australia.png"),
            new Country("Itália", "/flags/italy.png"),
            new Country("Espanha", "/flags/spain.png"),
            new Country("Portugal", "/flags/portugal.png"),
            new Country("Reino Unido", "/flags/uk.png")
    );

    private Country currentCountry;
    private int score = 0;
    private int attempts = 0;
    private final int maxAttempts = 6;
    private int currentProximity = 0;

    private Label titleLabel;
    private Label subtitleLabel;
    private ImageView flagImageView;
    private TextField guessTextField;
    private Button guessButton;
    private Label resultLabel;
    private Label scoreLabel;
    private Label attemptsLabel;
    private Label dateLabel;
    private Label proximityLabel;
    private ProgressBar proximityBar;
    private VBox historyBox;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5f5;");

        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setMaxWidth(600);
        mainContainer.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");

        VBox headerBox = new VBox(10);
        headerBox.setAlignment(Pos.CENTER);

        titleLabel = new Label("Flagle");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKBLUE);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        subtitleLabel = new Label("Adivinhe o país:");
        subtitleLabel.setFont(Font.font("Arial", 18));
        subtitleLabel.setTextAlignment(TextAlignment.CENTER);

        dateLabel = new Label("Data: " + java.time.LocalDate.now());
        dateLabel.setFont(Font.font("Arial", 12));
        dateLabel.setTextFill(Color.GRAY);
        dateLabel.setTextAlignment(TextAlignment.CENTER);

        headerBox.getChildren().addAll(titleLabel, subtitleLabel, dateLabel);

        Separator headerSeparator = new Separator();
        headerSeparator.setPrefWidth(500);

        StackPane flagContainer = new StackPane();
        flagContainer.setAlignment(Pos.CENTER);
        flagContainer.setPadding(new Insets(10));

        flagImageView = new ImageView();
        flagImageView.setFitHeight(150);
        flagImageView.setFitWidth(250);
        flagImageView.setPreserveRatio(true);
        flagImageView.setStyle("-fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5;");

        flagContainer.getChildren().add(flagImageView);

        VBox proximityBox = new VBox(8);
        proximityBox.setAlignment(Pos.CENTER);

        proximityLabel = new Label("Proximidade: 0%");
        proximityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        proximityLabel.setTextAlignment(TextAlignment.CENTER);

        proximityBar = new ProgressBar(0);
        proximityBar.setPrefWidth(300);
        proximityBar.setPrefHeight(20);
        proximityBar.setStyle("-fx-accent: #F44336;");

        proximityBox.getChildren().addAll(proximityLabel, proximityBar);

        VBox guessBox = new VBox(10);
        guessBox.setAlignment(Pos.CENTER);

        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);

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

        inputBox.getChildren().addAll(guessTextField, guessButton);

        resultLabel = new Label("");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        resultLabel.setTextAlignment(TextAlignment.CENTER);
        resultLabel.setPrefWidth(400);

        guessBox.getChildren().addAll(inputBox, resultLabel);

        VBox historyContainer = new VBox(10);
        historyContainer.setAlignment(Pos.CENTER);
        historyContainer.setPadding(new Insets(15));
        historyContainer.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        historyContainer.setMaxWidth(400);

        Label historyTitle = new Label("Tentativas:");
        historyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        historyTitle.setTextAlignment(TextAlignment.CENTER);

        historyBox = new VBox(8);
        historyBox.setAlignment(Pos.CENTER);

        historyContainer.getChildren().addAll(historyTitle, historyBox);

        Separator footerSeparator = new Separator();
        footerSeparator.setPrefWidth(500);

        HBox footerBox = new HBox(20);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(10));

        scoreLabel = new Label("Pontuação: 0");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        attemptsLabel = new Label("Tentativas: 0/" + maxAttempts);
        attemptsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Button newGameButton = new Button("NOVO JOGO");
        newGameButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;");
        newGameButton.setOnAction(e -> startNewGame());

        footerBox.getChildren().addAll(scoreLabel, attemptsLabel, newGameButton);

        mainContainer.getChildren().addAll(
                headerBox,
                headerSeparator,
                flagContainer,
                proximityBox,
                guessBox,
                new Label(""),
                historyContainer,
                footerSeparator,
                footerBox
        );

        StackPane.setAlignment(mainContainer, Pos.CENTER);
        root.getChildren().add(mainContainer);

        startNewGame();

        Scene scene = new Scene(root, 700, 800);
        primaryStage.setTitle("Flagle - Jogo de Adivinhação");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(800);
        primaryStage.show();
    }

    private void startNewGame() {
        score = 0;
        attempts = 0;
        currentProximity = 0;
        updateInfoLabels();
        loadRandomCountry();
        resultLabel.setText("");
        guessTextField.setText("");
        guessTextField.setDisable(false);
        guessButton.setDisable(false);
        historyBox.getChildren().clear();
        updateProximity(0);

        showRandomSector();
    }

    private void loadRandomCountry() {
        Random random = new Random();
        currentCountry = countries.get(random.nextInt(countries.size()));
    }

    private void showRandomSector() {
        try {
            Image flagImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));
            Image sectorImage = extractRandomSector(flagImage);
            flagImageView.setImage(sectorImage);
        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + currentCountry.flagPath);
            flagImageView.setImage(null);
        }
    }

    private Image extractRandomSector(Image originalImage) {
        int originalWidth = (int) originalImage.getWidth();
        int originalHeight = (int) originalImage.getHeight();

        int sectorWidth = originalWidth / 4;
        int sectorHeight = originalHeight / 4;

        Random random = new Random();
        int startX = random.nextInt(originalWidth - sectorWidth);
        int startY = random.nextInt(originalHeight - sectorHeight);

        PixelReader pixelReader = originalImage.getPixelReader();
        WritableImage sectorImage = new WritableImage(pixelReader, startX, startY, sectorWidth, sectorHeight);

        return sectorImage;
    }

    private void showComparisonSector(double proximity) {
        try {
            Image correctImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));

            int totalSectors = 16;
            int sectorsToShow = (int) Math.ceil(totalSectors * (proximity / 100.0));

            Image comparisonImage = createComparisonImage(correctImage, sectorsToShow);
            flagImageView.setImage(comparisonImage);
        } catch (Exception e) {
            System.out.println("Erro ao criar imagem de comparação");
        }
    }

    private Image createComparisonImage(Image correctImage, int sectorsToShow) {
        int width = (int) correctImage.getWidth();
        int height = (int) correctImage.getHeight();
        int sectorsPerRow = 4;
        int sectorWidth = width / sectorsPerRow;
        int sectorHeight = height / sectorsPerRow;

        WritableImage result = new WritableImage(width, height);
        PixelWriter writer = result.getPixelWriter();
        PixelReader reader = correctImage.getPixelReader();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                writer.setColor(x, y, Color.LIGHTGRAY);
            }
        }

        List<Integer> sectors = new ArrayList<>();
        for (int i = 0; i < 16; i++) sectors.add(i);
        Collections.shuffle(sectors);

        for (int i = 0; i < sectorsToShow && i < sectors.size(); i++) {
            int sector = sectors.get(i);
            int row = sector / sectorsPerRow;
            int col = sector % sectorsPerRow;

            int startX = col * sectorWidth;
            int startY = row * sectorHeight;

            for (int x = startX; x < startX + sectorWidth && x < width; x++) {
                for (int y = startY; y < startY + sectorHeight && y < height; y++) {
                    Color color = reader.getColor(x, y);
                    writer.setColor(x, y, color);
                }
            }
        }

        return result;
    }

    private String normalizarTexto(String texto) {
        if (texto == null) return "";

        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        textoNormalizado = textoNormalizado.replaceAll("[^a-zA-Z0-9 ]", "");

        return textoNormalizado.toLowerCase().trim();
    }

    private void checkGuess() {
        String userGuess = guessTextField.getText().trim();

        if (userGuess.isEmpty()) {
            resultLabel.setText("Digite um palpite!");
            resultLabel.setTextFill(Color.ORANGE);
            return;
        }

        String palpiteNormalizado = normalizarTexto(userGuess);

        Optional<Country> guessedCountry = countries.stream()
                .filter(c -> normalizarTexto(c.name).equals(palpiteNormalizado))
                .findFirst();

        if (!guessedCountry.isPresent()) {
            resultLabel.setText("País não reconhecido!");
            resultLabel.setTextFill(Color.RED);
            return;
        }

        Country guess = guessedCountry.get();
        attempts++;

        double proximity;
        if (normalizarTexto(guess.name).equals(normalizarTexto(currentCountry.name))) {
            proximity = 100.0;
        } else {
            proximity = calculateProximity(guess);
        }

        currentProximity = (int) proximity;

        addToHistory(guess.name, proximity);

        updateProximity(proximity);
        showComparisonSector(proximity);

        if (normalizarTexto(guess.name).equals(normalizarTexto(currentCountry.name))) {
            int pointsEarned = calculatePoints(attempts);
            score += pointsEarned;

            resultLabel.setText("✓ Correto! " + currentCountry.name + "! +" + pointsEarned + " pontos");
            resultLabel.setTextFill(Color.GREEN);

            try {
                Image fullImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));
                flagImageView.setImage(fullImage);
            } catch (Exception e) {
            }

            guessTextField.setDisable(true);
            guessButton.setDisable(true);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                startNewGame();
            }));
            timeline.setCycleCount(1);
            timeline.play();

        } else {
            resultLabel.setText("✗ " + guess.name + " - " + String.format("%.1f", proximity) + "% de proximidade");
            resultLabel.setTextFill(Color.RED);
            guessTextField.setText("");
            guessTextField.requestFocus();

            if (attempts >= maxAttempts) {
                resultLabel.setText("✗ Fim de jogo! Era " + currentCountry.name);
                guessTextField.setDisable(true);
                guessButton.setDisable(true);

                try {
                    Image fullImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));
                    flagImageView.setImage(fullImage);
                } catch (Exception e) {
                }

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                    startNewGame();
                }));
                timeline.setCycleCount(1);
                timeline.play();
            }
        }

        updateInfoLabels();
    }

    private double calculateProximity(Country guess) {
        try {
            Image correctImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));
            Image guessImage = new Image(getClass().getResourceAsStream(guess.flagPath));

            double similarity = calculateFlagSimilarity(correctImage, guessImage);

            double adjustedSimilarity = adjustSimilarityWithFactors(similarity, guess);

            return Math.max(5, Math.min(95, adjustedSimilarity));

        } catch (Exception e) {
            return calculateFallbackProximity(guess);
        }
    }

    private double calculateFlagSimilarity(Image correctImage, Image guessImage) {
        int width = 100;
        int height = 60;

        Image resizedCorrect = resizeImage(correctImage, width, height);
        Image resizedGuess = resizeImage(guessImage, width, height);

        PixelReader correctReader = resizedCorrect.getPixelReader();
        PixelReader guessReader = resizedGuess.getPixelReader();

        int matchingPixels = 0;
        int totalPixels = width * height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color correctColor = correctReader.getColor(x, y);
                Color guessColor = guessReader.getColor(x, y);

                if (areColorsSimilar(correctColor, guessColor, 0.3)) {
                    matchingPixels++;
                }
            }
        }

        return (matchingPixels * 100.0) / totalPixels;
    }

    private boolean areColorsSimilar(Color color1, Color color2, double threshold) {
        double deltaR = Math.abs(color1.getRed() - color2.getRed());
        double deltaG = Math.abs(color1.getGreen() - color2.getGreen());
        double deltaB = Math.abs(color1.getBlue() - color2.getBlue());

        double averageDelta = (deltaR + deltaG + deltaB) / 3.0;
        return averageDelta <= threshold;
    }

    private double adjustSimilarityWithFactors(double baseSimilarity, Country guess) {
        double adjusted = baseSimilarity;

        if (getContinent(currentCountry.name).equals(getContinent(guess.name))) {
            adjusted += 15;
        }

        if (hasSimilarMainColors(currentCountry.name, guess.name)) {
            adjusted += 10;
        }

        if (hasSimilarDesignPatterns(currentCountry.name, guess.name)) {
            adjusted += 8;
        }

        if (areCountriesNeighbors(currentCountry.name, guess.name)) {
            adjusted += 12;
        }

        adjusted -= (attempts - 1) * 3;

        return adjusted;
    }

    private double calculateFallbackProximity(Country guess) {
        double baseProximity = 0;

        Map<String, List<String>> similarFlags = new HashMap<>();
        similarFlags.put("nordic", Arrays.asList("Dinamarca", "Suécia", "Noruega", "Finlândia", "Islândia"));
        similarFlags.put("tricolor_vertical", Arrays.asList("França", "Itália", "Irlanda", "Bélgica", "Romênia"));
        similarFlags.put("tricolor_horizontal", Arrays.asList("Alemanha", "Rússia", "Holanda", "Hungria", "Sérvia"));
        similarFlags.put("union_jack", Arrays.asList("Reino Unido", "Austrália", "Nova Zelândia", "Fiji"));
        similarFlags.put("stars", Arrays.asList("Estados Unidos", "Brasil", "China", "Vietnã"));
        similarFlags.put("moon_star", Arrays.asList("Turquia", "Paquistão", "Argélia", "Tunísia"));
        similarFlags.put("pan_arab", Arrays.asList("Egito", "Iraque", "Síria", "Iêmen", "Sudão"));
        similarFlags.put("pan_african", Arrays.asList("Gana", "Etiópia", "Senegal", "Camarões", "Congo"));

        for (List<String> group : similarFlags.values()) {
            if (group.contains(currentCountry.name) && group.contains(guess.name)) {
                baseProximity += 40;
                break;
            }
        }


        Map<String, List<String>> colorGroups = new HashMap<>();
        colorGroups.put("vermelho_branco", Arrays.asList("Japão", "Turquia", "Polônia", "Indonésia", "Suíça"));
        colorGroups.put("azul_branco", Arrays.asList("Argentina", "Grécia", "Israel", "Finlândia", "Somália"));
        colorGroups.put("verde_amarelo", Arrays.asList("Brasil", "Camarões", "Lituânia", "Jamaica"));
        colorGroups.put("vermelho_amarelo", Arrays.asList("Espanha", "China", "Vietnã", "Marrocos"));

        for (List<String> group : colorGroups.values()) {
            if (group.contains(currentCountry.name) && group.contains(guess.name)) {
                baseProximity += 20;
                break;
            }
        }

        return Math.max(0, Math.min(80, baseProximity));
    }

    private int calculatePoints(int attemptsUsed) {
        switch (attemptsUsed) {
            case 1: return 100;
            case 2: return 80;
            case 3: return 60;
            case 4: return 40;
            case 5: return 20;
            case 6: return 10;
            default: return 0;
        }
    }

    private void updateProximity(double proximity) {
        double displayProximity = proximity;

        proximityLabel.setText("Proximidade: " + String.format("%.1f", displayProximity) + "%");
        proximityBar.setProgress(displayProximity / 100.0);

        if (displayProximity == 100) {
            proximityBar.setStyle("-fx-accent: #00FF00;");
        } else if (displayProximity > 70) {
            proximityBar.setStyle("-fx-accent: #4CAF50;");
        } else if (displayProximity > 40) {
            proximityBar.setStyle("-fx-accent: #FF9800;");
        } else {
            proximityBar.setStyle("-fx-accent: #F44336;");
        }
    }

    private void addToHistory(String guess, double proximity) {
        Label historyItem = new Label(guess + " - " + String.format("%.1f", proximity) + "%");
        historyItem.setFont(Font.font("Arial", 12));
        historyItem.setTextAlignment(TextAlignment.CENTER);

        if (guess.equalsIgnoreCase(currentCountry.name)) {
            historyItem.setTextFill(Color.GREEN);
            historyItem.setStyle("-fx-font-weight: bold; -fx-background-color: #e8f5e8; -fx-padding: 5px; -fx-border-radius: 3px;");
        } else if (proximity > 70) {
            historyItem.setTextFill(Color.DARKGREEN);
        } else if (proximity > 40) {
            historyItem.setTextFill(Color.ORANGE);
        } else {
            historyItem.setTextFill(Color.RED);
        }

        historyBox.getChildren().add(historyItem);
    }

    private void updateInfoLabels() {
        scoreLabel.setText("Pontuação: " + score);
        attemptsLabel.setText("Tentativas: " + attempts + "/" + maxAttempts);
        dateLabel.setText("Data: " + java.time.LocalDate.now());
    }

    private String getContinent(String country) {
        Map<String, String> continents = new HashMap<>();
        continents.put("Argentina", "América do Sul");
        continents.put("Brasil", "América do Sul");
        continents.put("Canadá", "América do Norte");
        continents.put("Estados Unidos", "América do Norte");
        continents.put("México", "América do Norte");
        continents.put("França", "Europa");
        continents.put("Alemanha", "Europa");
        continents.put("Itália", "Europa");
        continents.put("Espanha", "Europa");
        continents.put("Portugal", "Europa");
        continents.put("Reino Unido", "Europa");
        continents.put("Japão", "Ásia");
        continents.put("China", "Ásia");
        continents.put("Índia", "Ásia");
        continents.put("Austrália", "Oceania");
        continents.put("Egito", "África");
        continents.put("África do Sul", "África");

        return continents.getOrDefault(country, "Desconhecido");
    }

    private boolean hasSimilarMainColors(String country1, String country2) {
        Map<String, List<String>> mainColors = new HashMap<>();
        mainColors.put("vermelho", Arrays.asList("China", "Japão", "Turquia", "Marrocos", "Peru"));
        mainColors.put("azul", Arrays.asList("Argentina", "Grécia", "Finlândia", "Estados Unidos", "Israel"));
        mainColors.put("verde", Arrays.asList("Brasil", "Paquistão", "Arábia Saudita", "México", "Itália"));
        mainColors.put("amarelo", Arrays.asList("Espanha", "Colômbia", "Suécia", "Ucrânia", "Venezuela"));

        for (List<String> countries : mainColors.values()) {
            if (countries.contains(country1) && countries.contains(country2)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSimilarDesignPatterns(String country1, String country2) {
        List<String> tricolorVertical = Arrays.asList("França", "Itália", "Irlanda", "Bélgica");
        List<String> tricolorHorizontal = Arrays.asList("Alemanha", "Rússia", "Holanda", "Hungria");
        List<String> crossFlags = Arrays.asList("Dinamarca", "Suécia", "Noruega", "Finlândia", "Suíça");
        List<String> starFlags = Arrays.asList("Estados Unidos", "China", "Vietnã", "Brasil", "Cuba");

        return (tricolorVertical.contains(country1) && tricolorVertical.contains(country2)) ||
                (tricolorHorizontal.contains(country1) && tricolorHorizontal.contains(country2)) ||
                (crossFlags.contains(country1) && crossFlags.contains(country2)) ||
                (starFlags.contains(country1) && starFlags.contains(country2));
    }

    private boolean areCountriesNeighbors(String country1, String country2) {
        Map<String, List<String>> neighbors = new HashMap<>();
        neighbors.put("Argentina", Arrays.asList("Brasil", "Chile", "Uruguai", "Paraguai", "Bolívia"));
        neighbors.put("Brasil", Arrays.asList("Argentina", "Uruguai", "Paraguai", "Bolívia", "Peru", "Colômbia"));
        neighbors.put("França", Arrays.asList("Alemanha", "Itália", "Espanha", "Bélgica", "Suíça"));
        neighbors.put("Alemanha", Arrays.asList("França", "Polônia", "Áustria", "Bélgica", "Holanda"));
        neighbors.put("Espanha", Arrays.asList("França", "Portugal"));
        neighbors.put("Portugal", Arrays.asList("Espanha"));

        return neighbors.containsKey(country1) && neighbors.get(country1).contains(country2) ||
                neighbors.containsKey(country2) && neighbors.get(country2).contains(country1);
    }

    private Image resizeImage(Image original, int newWidth, int newHeight) {
        PixelReader reader = original.getPixelReader();
        WritableImage resizedImage = new WritableImage(newWidth, newHeight);
        PixelWriter writer = resizedImage.getPixelWriter();

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                int origX = (int) (x * original.getWidth() / newWidth);
                int origY = (int) (y * original.getHeight() / newHeight);
                writer.setColor(x, y, reader.getColor(origX, origY));
            }
        }

        return resizedImage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}