package com.example.flagle;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.stream.Collectors;
import javafx.util.Duration;

import java.text.Normalizer;
import java.time.LocalDate;
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
            new Country("Reino Unido", "/flags/uk.png"),
            new Country("Afeguenistao", "/flags/afeguenistao.png"),
            new Country("Albânia", "/flags/albania.png"),
            new Country("Andorra", "/flags/andorra.png"),
            new Country("Angola", "/flags/angola.png"),
            new Country("Antígua e Barbuda", "/flags/antigua_barbuda.png"),
            new Country("Arábia Saudita", "/flags/saudi_arabia.png"),
            new Country("Argélia", "/flags/algeria.png"),
            new Country("Armênia", "/flags/armenia.png"),
            new Country("Áustria", "/flags/austria.png"),
            new Country("Azerbaijão", "/flags/azerbaijan.png"),
            new Country("Bahamas", "/flags/bahamas.png"),
            new Country("Bangladesh", "/flags/bangladesh.png"),
            new Country("Barbados", "/flags/barbados.png"),
            new Country("Barém", "/flags/bahrain.png"),
            new Country("Bélgica", "/flags/belgium.png"),
            new Country("Belize", "/flags/belize.png"),
            new Country("Benin", "/flags/benin.png"),
            new Country("Bielorrússia", "/flags/belarus.png"),
            new Country("Bolívia", "/flags/bolivia.png"),
            new Country("Bósnia e Herzegovina", "/flags/bosnia_herzegovina.png"),
            new Country("Botsuana", "/flags/botswana.png"),
            new Country("Brunei", "/flags/brunei.png"),
            new Country("Bulgária", "/flags/bulgaria.png"),
            new Country("Burkina Faso", "/flags/burkina_faso.png"),
            new Country("Burundi", "/flags/burundi.png"),
            new Country("Butão", "/flags/bhutan.png"),
            new Country("Cabo Verde", "/flags/cape_verde.png"),
            new Country("Camarões", "/flags/cameroon.png"),
            new Country("Camboja", "/flags/cambodia.png"),
            new Country("Catar", "/flags/qatar.png"),
            new Country("Cazaquistão", "/flags/kazakhstan.png"),
            new Country("Chade", "/flags/chad.png"),
            new Country("Chile", "/flags/chile.png"),
            new Country("China", "/flags/china.png"),
            new Country("Chipre", "/flags/cyprus.png"),
            new Country("Colômbia", "/flags/colombia.png"),
            new Country("Comores", "/flags/comoros.png"),
            new Country("Congo", "/flags/congo.png"),
            new Country("Coreia do Norte", "/flags/north_korea.png"),
            new Country("Coreia do Sul", "/flags/south_korea.png"),
            new Country("Costa do Marfim", "/flags/ivory_coast.png"),
            new Country("Costa Rica", "/flags/costa_rica.png"),
            new Country("Croácia", "/flags/croatia.png"),
            new Country("Cuba", "/flags/cuba.png"),
            new Country("Dinamarca", "/flags/denmark.png"),
            new Country("Djibuti", "/flags/djibouti.png"),
            new Country("Dominica", "/flags/dominica.png"),
            new Country("Egito", "/flags/egypt.png"),
            new Country("El Salvador", "/flags/el_salvador.png"),
            new Country("Emirados Árabes Unidos", "/flags/uae.png"),
            new Country("Equador", "/flags/ecuador.png"),
            new Country("Eritreia", "/flags/eritrea.png"),
            new Country("Eslováquia", "/flags/slovakia.png"),
            new Country("Eslovênia", "/flags/slovenia.png"),
            new Country("Estônia", "/flags/estonia.png"),
            new Country("Eswatini", "/flags/eswatini.png"),
            new Country("Etiópia", "/flags/ethiopia.png"),
            new Country("Fiji", "/flags/fiji.png"),
            new Country("Filipinas", "/flags/philippines.png"),
            new Country("Finlândia", "/flags/finland.png"),
            new Country("Gabão", "/flags/gabon.png"),
            new Country("Gâmbia", "/flags/gambia.png"),
            new Country("Gana", "/flags/ghana.png"),
            new Country("Geórgia", "/flags/georgia.png"),
            new Country("Granada", "/flags/grenada.png"),
            new Country("Grécia", "/flags/greece.png"),
            new Country("Guatemala", "/flags/guatemala.png"),
            new Country("Guiana", "/flags/guyana.png"),
            new Country("Guiné", "/flags/guinea.png"),
            new Country("Guiné Equatorial", "/flags/equatorial_guinea.png"),
            new Country("Guiné-Bissau", "/flags/guinea_bissau.png"),
            new Country("Haiti", "/flags/haiti.png"),
            new Country("Honduras", "/flags/honduras.png"),
            new Country("Hungria", "/flags/hungary.png"),
            new Country("Iêmen", "/flags/yemen.png"),
            new Country("Ilhas Marechal", "/flags/marshall_islands.png"),
            new Country("Ilhas Salomão", "/flags/solomon_islands.png"),
            new Country("Índia", "/flags/india.png"),
            new Country("Indonésia", "/flags/indonesia.png"),
            new Country("Irã", "/flags/iran.png"),
            new Country("Iraque", "/flags/iraq.png"),
            new Country("Irlanda", "/flags/ireland.png"),
            new Country("Islândia", "/flags/iceland.png"),
            new Country("Israel", "/flags/israel.png"),
            new Country("Jamaica", "/flags/jamaica.png"),
            new Country("Jordânia", "/flags/jordan.png"),
            new Country("Kiribati", "/flags/kiribati.png"),
            new Country("Kuwait", "/flags/kuwait.png"),
            new Country("Laos", "/flags/laos.png"),
            new Country("Lesoto", "/flags/lesotho.png"),
            new Country("Letônia", "/flags/latvia.png"),
            new Country("Líbano", "/flags/lebanon.png"),
            new Country("Libéria", "/flags/liberia.png"),
            new Country("Líbia", "/flags/libya.png"),
            new Country("Liechtenstein", "/flags/liechtenstein.png"),
            new Country("Lituânia", "/flags/lithuania.png"),
            new Country("Luxemburgo", "/flags/luxembourg.png"),
            new Country("Macedônia do Norte", "/flags/north_macedonia.png"),
            new Country("Madagáscar", "/flags/madagascar.png"),
            new Country("Malásia", "/flags/malaysia.png"),
            new Country("Malaui", "/flags/malawi.png"),
            new Country("Maldivas", "/flags/maldives.png"),
            new Country("Mali", "/flags/mali.png"),
            new Country("Malta", "/flags/malta.png"),
            new Country("Marrocos", "/flags/morocco.png"),
            new Country("Maurícia", "/flags/mauritius.png"),
            new Country("Mauritânia", "/flags/mauritania.png"),
            new Country("México", "/flags/mexico.png"),
            new Country("Mianmar", "/flags/myanmar.png"),
            new Country("Micronésia", "/flags/micronesia.png"),
            new Country("Moçambique", "/flags/mozambique.png"),
            new Country("Moldávia", "/flags/moldova.png"),
            new Country("Mônaco", "/flags/monaco.png"),
            new Country("Mongólia", "/flags/mongolia.png"),
            new Country("Montenegro", "/flags/montenegro.png"),
            new Country("Namíbia", "/flags/namibia.png"),
            new Country("Nauru", "/flags/nauru.png"),
            new Country("Nepal", "/flags/nepal.png"),
            new Country("Nicarágua", "/flags/nicaragua.png"),
            new Country("Níger", "/flags/niger.png"),
            new Country("Nigéria", "/flags/nigeria.png"),
            new Country("Noruega", "/flags/norway.png"),
            new Country("Nova Zelândia", "/flags/new_zealand.png"),
            new Country("Omã", "/flags/oman.png"),
            new Country("Países Baixos", "/flags/netherlands.png"),
            new Country("Palau", "/flags/palau.png"),
            new Country("Panamá", "/flags/panama.png"),
            new Country("Papua Nova Guiné", "/flags/papua_new_guinea.png"),
            new Country("Paquistão", "/flags/pakistan.png"),
            new Country("Paraguai", "/flags/paraguay.png"),
            new Country("Peru", "/flags/peru.png"),
            new Country("Polônia", "/flags/poland.png"),
            new Country("Quênia", "/flags/kenya.png"),
            new Country("Quirguistão", "/flags/kyrgyzstan.png"),
            new Country("República Centro-Africana", "/flags/central_african_republic.png"),
            new Country("República Democrática do Congo", "/flags/democratic_republic_congo.png"),
            new Country("República Dominicana", "/flags/dominican_republic.png"),
            new Country("República Tcheca", "/flags/czech_republic.png"),
            new Country("Romênia", "/flags/romania.png"),
            new Country("Ruanda", "/flags/rwanda.png"),
            new Country("Rússia", "/flags/russia.png"),
            new Country("Samoa", "/flags/samoa.png"),
            new Country("San Marino", "/flags/san_marino.png"),
            new Country("Santa Lúcia", "/flags/st_lucia.png"),
            new Country("São Cristóvão e Névis", "/flags/st_kitts_nevis.png"),
            new Country("São Tomé e Príncipe", "/flags/sao_tome_principe.png"),
            new Country("São Vicente e Granadinas", "/flags/st_vincent_grenadines.png"),
            new Country("Seicheles", "/flags/seychelles.png"),
            new Country("Senegal", "/flags/senegal.png"),
            new Country("Serra Leoa", "/flags/sierra_leone.png"),
            new Country("Sérvia", "/flags/serbia.png"),
            new Country("Cingapura", "/flags/singapore.png"),
            new Country("Síria", "/flags/syria.png"),
            new Country("Somália", "/flags/somalia.png"),
            new Country("Sri Lanka", "/flags/sri_lanka.png"),
            new Country("Sudão", "/flags/sudan.png"),
            new Country("Sudão do Sul", "/flags/south_sudan.png"),
            new Country("Suécia", "/flags/sweden.png"),
            new Country("Suíça", "/flags/switzerland.png"),
            new Country("Suriname", "/flags/suriname.png"),
            new Country("Tailândia", "/flags/thailand.png"),
            new Country("Tajiquistão", "/flags/tajikistan.png"),
            new Country("Tanzânia", "/flags/tanzania.png"),
            new Country("Timor-Leste", "/flags/timor_leste.png"),
            new Country("Togo", "/flags/togo.png"),
            new Country("Tonga", "/flags/tonga.png"),
            new Country("Trindade e Tobago", "/flags/trinidad_tobago.png"),
            new Country("Tunísia", "/flags/tunisia.png"),
            new Country("Turcomenistão", "/flags/turkmenistan.png"),
            new Country("Turquia", "/flags/turkey.png"),
            new Country("Tuvalu", "/flags/tuvalu.png"),
            new Country("Ucrânia", "/flags/ukraine.png"),
            new Country("Uganda", "/flags/uganda.png"),
            new Country("Uruguai", "/flags/uruguay.png"),
            new Country("Uzbequistão", "/flags/uzbekistan.png"),
            new Country("Vanuatu", "/flags/vanuatu.png"),
            new Country("Venezuela", "/flags/venezuela.png"),
            new Country("Vietnã", "/flags/vietnam.png"),
            new Country("Zâmbia", "/flags/zambia.png"),
            new Country("Zimbábue", "/flags/zimbabwe.png"),
            new Country("Gibraltar", "/flags/gibraltar.png"),
            new Country("Martinica", "/flags/martinique.png"),
            new Country("Guadalupe", "/flags/guadeloupe.png"),
            new Country("Guiana Francesa", "/flags/french_guiana.png"),
            new Country("Reunião", "/flags/reunion.png"),
            new Country("Mayotte", "/flags/mayotte.png"),
            new Country("Nova Caledônia", "/flags/new_caledonia.png"),
            new Country("Polinésia Francesa", "/flags/french_polynesia.png"),
            new Country("Wallis e Futuna", "/flags/wallis_futuna.png"),
            new Country("Saint Martin", "/flags/st_martin.png"),
            new Country("Saint Barthélemy", "/flags/st_barthelemy.png"),
            new Country("Anguilla", "/flags/anguilla.png"),
            new Country("Bermudas", "/flags/bermuda.png"),
            new Country("Ilhas Caimão", "/flags/cayman_islands.png"),
            new Country("Ilhas Virgens Britânicas", "/flags/british_virgin_islands.png"),
            new Country("Montserrat", "/flags/montserrat.png"),
            new Country("Ilhas Turcas e Caicos", "/flags/turks_caicos.png"),
            new Country("Ilhas Geórgia do Sul e Sandwich do Sul", "/flags/south_georgia.png"),
            new Country("Santa Helena", "/flags/st_helena.png"),
            new Country("Ilha de Ascensão", "/flags/ascension.png"),
            new Country("Tristão da Cunha", "/flags/tristan_da_cunha.png"),
            new Country("Ilha de Man", "/flags/isle_of_man.png"),
            new Country("Guernsey", "/flags/guernsey.png"),
            new Country("Jersey", "/flags/jersey.png"),
            new Country("Ilhas Pitcairn", "/flags/pitcairn_islands.png"),
            new Country("Ilhas Malvinas", "/flags/falkland_islands.png"),
            new Country("Porto Rico", "/flags/puerto_rico.png"),
            new Country("Guam", "/flags/guam.png"),
            new Country("Ilhas Virgens Americanas", "/flags/us_virgin_islands.png"),
            new Country("Samoa Americana", "/flags/american_samoa.png"),
            new Country("Ilhas Marianas do Norte", "/flags/northern_mariana_islands.png"),
            new Country("Aruba", "/flags/aruba.png"),
            new Country("Curaçao", "/flags/curacao.png"),
            new Country("Sint Maarten", "/flags/sint_maarten.png"),
            new Country("Caribe Holandês", "/flags/caribbean_netherlands.png"),
            new Country("Groenlândia", "/flags/greenland.png"),
            new Country("Ilhas Feroé", "/flags/faroe_islands.png"),
            new Country("Svalbard e Jan Mayen", "/flags/svalbard.png"),
            new Country("Ilha Bouvet", "/flags/bouvet_island.png"),
            new Country("Ilha Norfolk", "/flags/norfolk_island.png"),
            new Country("Ilha Christmas", "/flags/christmas_island.png"),
            new Country("Ilhas Cocos", "/flags/cocos_islands.png"),
            new Country("Ilhas Ashmore e Cartier", "/flags/ashmore_cartier.png"),
            new Country("Ilhas do Mar de Coral", "/flags/coral_sea_islands.png"),
            new Country("Território Antártico Australiano", "/flags/australian_antarctic.png"),
            new Country("Saint Pierre e Miquelon", "/flags/st_pierre_miquelon.png"),
            new Country("Terras Austrais e Antárticas Francesas", "/flags/french_southern_antarctic.png"),
            new Country("Ilha Clipperton", "/flags/clipperton_island.png"),
            new Country("Ilhas Cook", "/flags/cook_islands.png"),
            new Country("Niue", "/flags/niue.png"),
            new Country("Tokelau", "/flags/tokelau.png"),
            new Country("Dependência de Ross", "/flags/ross_dependency.png"),
            new Country("Ilha de Páscoa", "/flags/easter_island.png"),
            new Country("Ilhas Juan Fernández", "/flags/juan_fernandez.png"),
            new Country("Ilhas Canárias", "/flags/canary_islands.png"),
            new Country("Ceuta", "/flags/ceuta.png"),
            new Country("Melilha", "/flags/melilla.png"),
            new Country("Ilhas Baleares", "/flags/balearic_islands.png"),
            new Country("Açores", "/flags/azores.png"),
            new Country("Madeira", "/flags/madeira.png"),
            new Country("Sardenha", "/flags/sardinia.png"),
            new Country("Sicília", "/flags/sicily.png"),
            new Country("Åland", "/flags/aland.png"),
            new Country("Ilhas Shetland", "/flags/shetland_islands.png"),
            new Country("Ilhas Orkney", "/flags/orkney_islands.png"),
            new Country("Ilhas Hébridas", "/flags/hebrides.png"),
            new Country("Ilha de Wight", "/flags/isle_of_wight.png"),
            new Country("Ilha de Skye", "/flags/isle_of_skye.png"),
            new Country("Vaticano", "/flags/vatican.png"),
            new Country("Palestina", "/flags/palestine.png"),
            new Country("Kosovo", "/flags/kosovo.png"),
            new Country("Saara Ocidental", "/flags/western_sahara.png"),
            new Country("Taiwan", "/flags/taiwan.png"),
            new Country("Abecásia", "/flags/abkhazia.png"),
            new Country("Ossétia do Sul", "/flags/south_ossetia.png"),
            new Country("Artsaque", "/flags/artsakh.png"),
            new Country("Transnístria", "/flags/transnistria.png"),
            new Country("República Árabe Saaraui Democrática", "/flags/sahrawi_arab_democratic_republic.png"),
            new Country("Somalilândia", "/flags/somaliland.png"),
            new Country("Hong Kong", "/flags/hong_kong.png"),
            new Country("Macau", "/flags/macau.png"),
            new Country("Zanzibar", "/flags/zanzibar.png"),
            new Country("Crimeia", "/flags/crimea.png"),
            new Country("Curdistão", "/flags/kurdistan.png"),
            new Country("Tibete", "/flags/tibet.png"),
            new Country("Quebec", "/flags/quebec.png"),
            new Country("Escócia", "/flags/scotland.png"),
            new Country("Irlanda do Norte", "/flags/northern_ireland.png"),
            new Country("País de Gales", "/flags/wales.png"),
            new Country("Catalunha", "/flags/catalonia.png"),
            new Country("País Basco", "/flags/basque_country.png"),
            new Country("Galiza", "/flags/galicia.png"),
            new Country("Córsega", "/flags/corsica.png"),
            new Country("Bretanha", "/flags/brittany.png"),
            new Country("Provença", "/flags/provence.png"),
            new Country("Vêneto", "/flags/veneto.png"),
            new Country("Lombardia", "/flags/lombardy.png"),
            new Country("Tirol do Sul", "/flags/south_tyrol.png"),
            new Country("Baviera", "/flags/bavaria.png"),
            new Country("Alsácia", "/flags/alsace.png")
    );

    private Country currentCountry;
    private int score = 0;
    private int attempts = 0;
    private final int maxAttempts = 5;
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
    private Pane confettiPane;

    private ScrollPane suggestionsContainer;
    private VBox suggestionsContent;
    private List<HBox> suggestionItems;
    private HBox selectedSuggestion;

    private final int totalSectors = 6;
    private boolean[] revealedSectors = new boolean[totalSectors];

    @Override
    public void start(Stage primaryStage) {
        Font zefaniFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ZefaniStencil(uppercase)-Regular.otf"), 40);
        StackPane root = new StackPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #0F111A;");

        confettiPane = new Pane();
        confettiPane.setMouseTransparent(true);

        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setMaxWidth(600);
        mainContainer.setStyle("-fx-background-color: black; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");

        VBox headerBox = new VBox(10);
        headerBox.setAlignment(Pos.CENTER);

        titleLabel = new Label("Flagle");
        titleLabel.setFont(zefaniFont);
        titleLabel.setTextFill(Color.web("white"));
        titleLabel.setAlignment(Pos.CENTER);

        RotateTransition rotate = new RotateTransition(Duration.seconds(2), titleLabel);
        rotate.setFromAngle(-5);
        rotate.setToAngle(5);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setAutoReverse(true);
        rotate.setInterpolator(Interpolator.EASE_BOTH);
        rotate.play();

        subtitleLabel = new Label("Adivinhe o país:");
        subtitleLabel.setTextFill(Color.WHITE);
        subtitleLabel.setFont(Font.font("Arial", 18));
        subtitleLabel.setTextAlignment(TextAlignment.CENTER);

        dateLabel = new Label("Data: " + LocalDate.now());
        dateLabel.setFont(Font.font("Arial", 12));
        dateLabel.setTextFill(Color.WHITE);
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
        proximityLabel.setTextFill(Color.WHITE);
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
        guessTextField.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
        guessTextField.setOnAction(e -> checkGuess());

        initializeSuggestions();

        guessTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            showSuggestions(newValue);
        });

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

        guessBox.getChildren().addAll(inputBox, suggestionsContainer, resultLabel);

        VBox historyContainer = new VBox(10);
        historyContainer.setAlignment(Pos.CENTER);
        historyContainer.setPadding(new Insets(15));
        historyContainer.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        historyContainer.setMaxWidth(400);

        Label historyTitle = new Label("Tentativas:");
        historyTitle.setTextFill(Color.BLACK);
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
        scoreLabel.setTextFill(Color.WHITE);

        attemptsLabel = new Label("Tentativas: 0/" + maxAttempts);
        attemptsLabel.setTextFill(Color.WHITE);
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
        root.getChildren().addAll(mainContainer, confettiPane);

        startNewGame();

        Scene scene = new Scene(root, 700, 850);
        primaryStage.setTitle("Flagle - Jogo de Adivinhação");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(850);
        primaryStage.show();
    }

    private void initializeSuggestions() {
        suggestionsContent = new VBox();
        suggestionsContent.setStyle("-fx-background-color: white;");

        suggestionsContainer = new ScrollPane();
        suggestionsContainer.setContent(suggestionsContent);
        suggestionsContainer.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5;");
        suggestionsContainer.setVisible(false);
        suggestionsContainer.setManaged(false);
        suggestionsContainer.setPrefWidth(250);
        suggestionsContainer.setPrefHeight(200);
        suggestionsContainer.setFitToWidth(true);
        suggestionsContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        suggestionsContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        suggestionsContainer.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #ccc; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5;");

        suggestionItems = new ArrayList<>();
    }

    private void showSuggestions(String searchText) {
        suggestionsContent.getChildren().clear();
        suggestionItems.clear();
        selectedSuggestion = null;

        if (searchText == null || searchText.trim().isEmpty()) {
            suggestionsContainer.setVisible(false);
            suggestionsContainer.setManaged(false);
            return;
        }

        String normalizedSearch = normalizarTexto(searchText.toLowerCase());
        List<Country> filtered = countries.stream()
                .filter(country -> normalizarTexto(country.name.toLowerCase()).contains(normalizedSearch))
                .limit(15)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            suggestionsContainer.setVisible(false);
            suggestionsContainer.setManaged(false);
            return;
        }

        for (Country country : filtered) {
            HBox suggestionItem = createSuggestionItem(country);
            suggestionItems.add(suggestionItem);
            suggestionsContent.getChildren().add(suggestionItem);
        }

        int itemCount = filtered.size();
        double itemHeight = 36;
        double calculatedHeight = Math.min(itemCount * itemHeight, 200);
        suggestionsContainer.setPrefHeight(calculatedHeight + 2);

        suggestionsContainer.setVisible(true);
        suggestionsContainer.setManaged(true);

        suggestionsContainer.setVvalue(0);
    }

    private HBox createSuggestionItem(Country country) {
        HBox item = new HBox(10);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(8));
        item.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        item.setPrefWidth(230);

        ImageView flagView = new ImageView();
        try {
            Image flagImage = new Image(getClass().getResourceAsStream(country.flagPath));
            flagView.setImage(flagImage);
        } catch (Exception e) {
            flagView.setStyle("-fx-background-color: lightgray;");
        }
        flagView.setFitWidth(30);
        flagView.setFitHeight(20);
        flagView.setPreserveRatio(true);

        Label nameLabel = new Label(country.name);
        nameLabel.setFont(Font.font("Arial", 14));
        nameLabel.setTextFill(Color.BLACK);
        nameLabel.setMaxWidth(180);
        nameLabel.setWrapText(false);

        item.getChildren().addAll(flagView, nameLabel);

        item.setOnMouseEntered(e -> {
            if (item != selectedSuggestion) {
                item.setStyle("-fx-background-color: #e0e0e0; -fx-cursor: hand; -fx-border-radius: 3;");
            }
        });

        item.setOnMouseExited(e -> {
            if (item != selectedSuggestion) {
                item.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
            }
        });

        item.setOnMouseClicked(e -> {
            selectSuggestion(item, country.name);
        });

        return item;
    }

    private void selectSuggestion(HBox suggestionItem, String countryName) {
        if (selectedSuggestion != null) {
            selectedSuggestion.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        }

        suggestionItem.setStyle("-fx-background-color: #4CAF50; -fx-cursor: hand; -fx-border-radius: 3;");
        selectedSuggestion = suggestionItem;

        guessTextField.setText(countryName);

        guessTextField.requestFocus();
    }

    private void launchConfetti() {
        if (confettiPane == null) {
            System.out.println("Confetti pane não inicializado!");
            return;
        }

        confettiPane.getChildren().clear();

        Scene scene = confettiPane.getScene();
        double sceneWidth = scene.getWidth();
        double sceneHeight = scene.getHeight();

        Random random = new Random();
        int confettiCount = 250;

        for (int i = 0; i < confettiCount; i++) {
            Circle confetti = new Circle(2 + random.nextDouble() * 5);
            confetti.setFill(getRandomBrightColor(random));

            double startX = (random.nextDouble() * sceneWidth * 1.8) - (sceneWidth * 0.4);
            double startY = -random.nextInt(100);

            confetti.setLayoutX(startX);
            confetti.setLayoutY(startY);

            confettiPane.getChildren().add(confetti);

            double fallDuration = 1 + random.nextDouble() * 4;
            double horizontalSpread = (random.nextDouble() - 0.5) * 600;
            double finalY = sceneHeight + 200 + random.nextInt(500);

            Timeline fallAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(confetti.layoutYProperty(), confetti.getLayoutY()),
                            new KeyValue(confetti.layoutXProperty(), confetti.getLayoutX())
                    ),
                    new KeyFrame(Duration.seconds(fallDuration),
                            new KeyValue(confetti.layoutYProperty(), finalY),
                            new KeyValue(confetti.layoutXProperty(),
                                    confetti.getLayoutX() + horizontalSpread +
                                            Math.sin(i) * 100)
                    )
            );

            Timeline rotateAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(confetti.rotateProperty(), random.nextInt(360))
                    ),
                    new KeyFrame(Duration.seconds(0.5 + random.nextDouble() * 1.5),
                            new KeyValue(confetti.rotateProperty(),
                                    confetti.getRotate() + 360 * (1 + random.nextDouble() * 4) *
                                            (random.nextBoolean() ? 1 : -1))
                    )
            );

            fallAnimation.setCycleCount(1);
            rotateAnimation.setCycleCount(Animation.INDEFINITE);

            PauseTransition delay = new PauseTransition(
                    Duration.millis(random.nextInt(800))
            );
            delay.setOnFinished(e -> {
                fallAnimation.play();
                rotateAnimation.play();
            });
            delay.play();

            fallAnimation.setOnFinished(e -> {
                confettiPane.getChildren().remove(confetti);
            });
        }

        Timeline cleanup = new Timeline(new KeyFrame(Duration.seconds(8), e -> {
            confettiPane.getChildren().clear();
        }));
        cleanup.play();
    }

    private Color getRandomBrightColor(Random random) {
        Color[] brightColors = {
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
                Color.PURPLE, Color.ORANGE, Color.CYAN, Color.MAGENTA,
                Color.PINK, Color.LIME, Color.GOLD, Color.TURQUOISE
        };
        return brightColors[random.nextInt(brightColors.length)];
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
        suggestionsContainer.setVisible(false);
        suggestionsContainer.setManaged(false);
        selectedSuggestion = null;

        Arrays.fill(revealedSectors, false);
        updateFlagVisibility();
    }

    private void loadRandomCountry() {
        Random random = new Random();
        currentCountry = countries.get(random.nextInt(countries.size()));
    }

    private void updateFlagVisibility() {
        try {
            Image correctImage = new Image(getClass().getResourceAsStream(currentCountry.flagPath));

            int width = (int) correctImage.getWidth();
            int height = (int) correctImage.getHeight();

            int sectorsPerRow = 3;
            int sectorsPerCol = 2;
            int sectorWidth = width / sectorsPerRow;
            int sectorHeight = height / sectorsPerCol;

            WritableImage result = new WritableImage(width, height);
            PixelWriter writer = result.getPixelWriter();
            PixelReader reader = correctImage.getPixelReader();

            Color coverColor = Color.web("#303030");
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    writer.setColor(x, y, coverColor);
                }
            }

            for (int i = 0; i < totalSectors; i++) {
                if (revealedSectors[i]) {
                    int row = i / sectorsPerRow;
                    int col = i % sectorsPerRow;

                    int startX = col * sectorWidth;
                    int startY = row * sectorHeight;

                    for (int x = 0; x < sectorWidth && (startX + x) < width; x++) {
                        for (int y = 0; y < sectorHeight && (startY + y) < height; y++) {
                            Color color = reader.getColor(startX + x, startY + y);
                            writer.setColor(startX + x, startY + y, color);
                        }
                    }
                }
            }

            flagImageView.setImage(result);

        } catch (Exception e) {
            System.out.println("Erro ao carregar ou processar imagem: " + currentCountry.flagPath);
            flagImageView.setImage(null);
        }
    }

    private void revealNextSector() {
        for (int i = 0; i < totalSectors; i++) {
            if (!revealedSectors[i]) {
                revealedSectors[i] = true;
                break;
            }
        }
        updateFlagVisibility();
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
            resultLabel.setTextFill(Color.WHITE);
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

        if (proximity < 100.0) {
            revealNextSector();
        }

        if (normalizarTexto(guess.name).equals(normalizarTexto(currentCountry.name))) {
            int pointsEarned = calculatePoints(attempts);
            score += pointsEarned;

            resultLabel.setText("✓ Correto! " + currentCountry.name + "! +" + pointsEarned + " pontos");
            resultLabel.setTextFill(Color.LIGHTGREEN);
            launchConfetti();

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
            resultLabel.setTextFill(Color.ORANGERED);
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

        suggestionsContainer.setVisible(false);
        suggestionsContainer.setManaged(false);
        selectedSuggestion = null;

        updateInfoLabels();
    }

    private void updateProximity(double proximity) {
        proximityLabel.setText(String.format("Proximidade: %.0f%%", proximity));
        proximityBar.setProgress(proximity / 100.0);

        String colorStyle = proximity >= 75 ? "-fx-accent: #4CAF50;" :
                proximity >= 50 ? "-fx-accent: #FFC107;" :
                        "-fx-accent: #F44336;";
        proximityBar.setStyle(colorStyle);
    }

    private void addToHistory(String guessName, double proximity) {
        Label historyEntry = new Label(String.format("Tentativa %d: %s (%.1f%%)", attempts, guessName, proximity));
        historyEntry.setTextFill(Color.BLACK);

        Circle indicator = new Circle(5);
        if (proximity == 100) {
            indicator.setFill(Color.LIGHTGREEN);
        } else if (proximity >= 70) {
            indicator.setFill(Color.YELLOW);
        } else {
            indicator.setFill(Color.ORANGERED);
        }

        HBox entryBox = new HBox(5, indicator, historyEntry);
        entryBox.setAlignment(Pos.CENTER_LEFT);

        historyBox.getChildren().add(0, entryBox);
    }

    private void updateInfoLabels() {
        scoreLabel.setText("Pontuação: " + score);
        attemptsLabel.setText("Tentativas: " + attempts + "/" + maxAttempts);
    }

    private int calculatePoints(int currentAttempts) {
        return switch (currentAttempts) {
            case 1 -> 1000;
            case 2 -> 600;
            case 3 -> 400;
            case 4 -> 200;
            case 5 -> 100;
            default -> 0;
        };
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

                double dist = Math.sqrt(
                        Math.pow(correctColor.getRed() - guessColor.getRed(), 2) +
                                Math.pow(correctColor.getGreen() - guessColor.getGreen(), 2) +
                                Math.pow(correctColor.getBlue() - guessColor.getBlue(), 2)
                );

                if (dist < 0.2) {
                    matchingPixels++;
                }
            }
        }

        return ((double) matchingPixels / totalPixels) * 100.0;
    }

    private Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
        if (originalImage == null) return null;
        if ((int)originalImage.getWidth() == targetWidth && (int)originalImage.getHeight() == targetHeight) {
            return originalImage;
        }

        WritableImage resizedImage = new WritableImage(targetWidth, targetHeight);

        double scaleX = originalImage.getWidth() / targetWidth;
        double scaleY = originalImage.getHeight() / targetHeight;

        PixelReader reader = originalImage.getPixelReader();
        PixelWriter writer = resizedImage.getPixelWriter();

        for (int y = 0; y < targetHeight; y++) {
            for (int x = 0; x < targetWidth; x++) {
                int originalX = (int) (x * scaleX);
                int originalY = (int) (y * scaleY);

                if (originalX < originalImage.getWidth() && originalY < originalImage.getHeight()) {
                    Color color = reader.getColor(originalX, originalY);
                    writer.setColor(x, y, color);
                }
            }
        }
        return resizedImage;
    }

    private double adjustSimilarityWithFactors(double similarity, Country guess) {
        return similarity;
    }

    private double calculateFallbackProximity(Country guess) {
        return 5.0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}