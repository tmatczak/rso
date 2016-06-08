package com.example;

import com.example.ResponseObjects.*;
import com.example.service.ClientsRequest;
import com.example.util.Data42;
import com.example.util.Location;
import com.example.util.NodeInfo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class Main extends Application {

    private final static String PORT = "9000";

    private String serverAddress = "http://127.0.0.1:9000/app";
//    private String serverAddress = "http://192.168.1.3:9000/app";
    private ArrayList<NodeInfo> servers = new ArrayList<>();

    private Timer timer = new Timer();
    private static int count = 0;

    private ClientsRequest clientRequest;
    private List<JobsResponse> jobs;
    private List<LocationResponse> locationsList;
    private List<UniversityResponse> universityList;
    private List<FieldOfStudyResponse> studiesList;
    private List<ComeFromResponse> comeFromList;
    private List<OriginFromStudiesResponse> originFromStudiesList;
    private List<OriginFromUniversitiesResponse> originFromUniversitiesList;
    private List<OriginFromCountriesResponse> originFromCountriesList;
    private List<WorkingUniversitiesResponse> workingUniversitiesList;

	private Button button1 = new Button();
	private Button button2 = new Button();
	private Button button3 = new Button();
	private Button button4 = new Button();
	private Button button5 = new Button();
	private Button button6 = new Button();
	private Button button7 = new Button();
	private Button button8 = new Button();
    private Button button9 = new Button();
    private Button button10 = new Button();
    private Button button11 = new Button();
    private Button button12 = new Button();

    private final TableView<Data42> tableview = new TableView<>();
	private TableColumn universityColumn;
	private TableColumn uniNameColumn;
	private TableColumn uniYearColumn;
	private TableColumn uniLocationColumn;
	private TableColumn uniTypeColumn;
	private TableColumn nameColumn;
	private TableColumn valueColumn;
	private TableColumn locationColumn;
	private TableColumn fromColumn;
	private TableColumn comeFromColumn;
	private TableColumn villageColumn;
	private TableColumn smallTownColumn;
	private TableColumn mediumTownColumn;
	private TableColumn largeTownColumn;

	private final ObservableList<Data42> data = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
        clientRequest = new ClientsRequest();
		Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setTitle("RSO - aplikacja kliencka");
		setTable(primaryStage.getScene());
		setButtons(primaryStage.getScene());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void setButtons(Scene scene) {
		button1 = (Button) scene.lookup("#firstButton");
		button2 = (Button) scene.lookup("#secondButton");
		button3 = (Button) scene.lookup("#thirdButton");
		button4 = (Button) scene.lookup("#fourthButton");
		button5 = (Button) scene.lookup("#fifthButton");
		button6 = (Button) scene.lookup("#sixthButton");
		button7 = (Button) scene.lookup("#seventhButton");
		button8 = (Button) scene.lookup("#eighthButton");
        button9 = (Button) scene.lookup("#ninthButton");
        button10 = (Button) scene.lookup("#tenthButton");
        button11 = (Button) scene.lookup("#eleventhButton");
        button12 = (Button) scene.lookup("#twelfthButton" );

        button4.setDisable(true);
        button5.setDisable(true);
        button9.setDisable(true);

		button1.setOnAction(e -> actionSwitcher("/allCountries", 1));
        button2.setOnAction(e -> actionSwitcher("/universities", 2));
        button3.setOnAction(e -> actionSwitcher("/fieldOfStudy", 3));
        button4.setOnAction(e -> actionSwitcher("/moreThanOneFieldOfStudy/country", 4));
        button5.setOnAction(e -> actionSwitcher("/moreThanOneFieldOfStudy/university", 5));
        button6.setOnAction(e -> actionSwitcher("/orginFrom/land", 6));
        button7.setOnAction(e -> actionSwitcher("/orginFrom/countries", 7));
        button8.setOnAction(e -> actionSwitcher("/orginFrom/universities", 8));
        button9.setOnAction(e -> actionSwitcher("/orginFrom/fieldOfStudies", 9));
        button10.setOnAction(e -> actionSwitcher("/working/fieldOfStudies", 10));
        button11.setOnAction(e -> actionSwitcher("/working/countries", 11));
        button12.setOnAction(e -> actionSwitcher("/working/universities", 12));
	}

	private void setTable(Scene scene) {
		tableview.setEditable(true);

		universityColumn = new TableColumn("Uczelnia");
		uniNameColumn = setColumn("Nazwa", "uniName", 100);
		uniYearColumn = setColumn("Rok założenia", "uniYear", 100);
		uniLocationColumn = setColumn("Województwo", "uniLocation", 100);
		uniTypeColumn = setColumn("Typ", "uniType", 100);

		universityColumn.getColumns().addAll(uniNameColumn, uniYearColumn, uniTypeColumn, uniLocationColumn);

		nameColumn = setColumn("Nazwa", "name", 100);
		locationColumn = setColumn("Województwo", "location", 100);
		fromColumn = setColumn("Pochodzenie", "comeFrom", 100);
        valueColumn = setColumn("Wartość", "value", 100);

		comeFromColumn = new TableColumn("Pochodzenie");
		villageColumn = setColumn("Wieś", "village", 100);
		smallTownColumn = setColumn("Małe miasto", "smallTown", 100);
		mediumTownColumn = setColumn("Średnie miasto", "mediumTown", 100);
		largeTownColumn = setColumn("Duże miasto", "largeTown", 100);

		comeFromColumn.getColumns().addAll(villageColumn, smallTownColumn, mediumTownColumn, largeTownColumn);

		tableview.setItems(data);
		tableview.getColumns().addAll(universityColumn, nameColumn, locationColumn, fromColumn, valueColumn, comeFromColumn);
//		toggleColumnVisibility(false, false, false, false, false, false);

		VBox center = (VBox) scene.lookup("#table");
		center.getChildren().addAll(tableview);
	}

	private TableColumn setColumn(String name, String value, int width) {
		TableColumn column = new TableColumn(name);
		column.setMinWidth(width);
		column.setCellValueFactory(new PropertyValueFactory<>(value));
		return column;
	}

	private void toggleColumnVisibility(boolean first, boolean second, boolean third, boolean fourth, boolean fifth, boolean sixth) {
		universityColumn.setVisible(first);
		nameColumn.setVisible(second);
		locationColumn.setVisible(third);
		fromColumn.setVisible(fourth);
        valueColumn.setVisible(fifth);
		comeFromColumn.setVisible(sixth);
	}

    private void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void actionSwitcher(String url, int action) {
        timer.cancel();
        timer.purge();
        jobs = null;
        jobs =  clientRequest.sendRequest(serverAddress + url);
        if(jobs == null) {
            jobs =  clientRequest.sendRequest(serverAddress + url);
            if(jobs == null) {
                showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem w trakcie połączenia z serwerem. Spróbuj ponownie.");
                return;
            }
        } else {
            sleep(1000);
            switch (action) {
                case 1: // allCountries
                case 11: { // working/countries
                    timer = new Timer();
                    timer.schedule(new FirstTask(), 0, 500);
                    break;
                }
                case 2: { // universities
                    timer = new Timer();
                    timer.schedule(new SecondTask(), 0, 500);
                    break;
                }
                case 3: // fieldOfStudy
                case 10: { // working/fieldOfStudies
                    timer = new Timer();
                    timer.schedule(new ThirdTask(), 0, 500);
                    break;
                }
                case 4: { // moreThanOneFieldOfStudy
                    break;
                }
                case 5: { // moreThanOneFieldOfStudy
                    break;
                }
                case 6: { // orginFrom/land
                    timer = new Timer();
                    timer.schedule(new SixthTask(), 0, 500);
                    break;
                }
                case 7: { // orginFrom/countries
                    timer = new Timer();
                    timer.schedule(new SeventhTask(), 0, 500);
                    break;
                }
                case 8: { // orginFrom/universities
                    timer = new Timer();
                    timer.schedule(new EighthTask(), 0, 500);
                    break;
                }
                case 9: { // originFrom/fieldOfStudies todo jeszcze nie ma
                    timer = new Timer();
                    timer.schedule(new NinthTask(), 0, 500);
                    break;
                }
                case 12: { // working/universities
                    timer = new Timer();
                    timer.schedule(new TwelfthTask(), 0, 500);
                    break;
                }
            }
        }
    }

    private void showAlert(String title,String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    class FirstTask extends TimerTask {
        public void run() {
            count++;
            locationsList = null;
            locationsList = clientRequest.doLocationJob(jobs.get(0).getJobUrl());
            if (locationsList == null) {
                locationsList = clientRequest.doLocationJob(jobs.get(1).getJobUrl());
            }
            if(locationsList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(false, false, true, false, true, false);
                    for(LocationResponse response : locationsList) {
                        newData.add(new Data42(
                                null, null, null, null, null,
                                response.getLocation().name(),
                                null,
                                response.getValue(),
                                0, 0, 0, 0));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class SecondTask extends TimerTask {
        public void run() {
            count++;
            universityList = null;
            universityList = clientRequest.doUniversityJob(jobs.get(0).getJobUrl());
            if (universityList == null) {
                universityList = clientRequest.doUniversityJob(jobs.get(1).getJobUrl());
            }
            if(universityList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(true, false, false, false, false, false);
                    for(UniversityResponse response : universityList) {
                        newData.add(new Data42(
                                response.getName(),
                                response.getYerOfFundation(),
                                response.getUniversityType().toString(),
                                response.getLocation().toString(),
                                null, null, null,
                                0, 0, 0, 0, 0
                        ));
                    }
                    data.addAll(newData);
                });
                return;
            } else if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class ThirdTask extends TimerTask {
        public void run() {
            count++;
            studiesList = null;
            studiesList = clientRequest.doFieldOfStudyJob(jobs.get(0).getJobUrl());
            if (studiesList == null) {
                studiesList = clientRequest.doFieldOfStudyJob(jobs.get(1).getJobUrl());
            }
            if(studiesList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(false, true, false, false, true, false);
                    for(FieldOfStudyResponse response : studiesList) {
                        newData.add(new Data42(
                                null, null, null, null,
                                response.getName(),
                                null, null,
                                response.getVal(),
                                0, 0, 0, 0
                        ));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class SixthTask extends TimerTask {
        public void run() {
            count++;
            comeFromList = null;
            comeFromList = clientRequest.doComeFromJob(jobs.get(0).getJobUrl());
            if (comeFromList == null) {
                comeFromList = clientRequest.doComeFromJob(jobs.get(1).getJobUrl());
            }
            if(comeFromList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(false, false, false, true, true, false);
                    for(ComeFromResponse response : comeFromList) {
                        newData.add(new Data42(
                                null, null, null, null, null, null,
                                response.getComeFrom().toString(),
                                response.getVal(),
                                0, 0, 0, 0
                        ));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class SeventhTask extends TimerTask {
        public void run() {
            count++;
            originFromCountriesList = null;
            originFromCountriesList = clientRequest.doOriginFromCountriesJob(jobs.get(0).getJobUrl());
            if (originFromCountriesList == null) {
                originFromCountriesList = clientRequest.doOriginFromCountriesJob(jobs.get(1).getJobUrl());
            }
            if(originFromCountriesList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(false, false, true, false, false, true);
                    for(OriginFromCountriesResponse response : originFromCountriesList) {
                        newData.add(new Data42(
                                null, null, null, null, null,
                                response.getLocation().name(),
                                null, 0,
                                response.getComeFromDtos().get(0).getVal(),
                                response.getComeFromDtos().get(1).getVal(),
                                response.getComeFromDtos().get(2).getVal(),
                                response.getComeFromDtos().get(3).getVal()));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class EighthTask extends TimerTask {
        public void run() {
            count++;
            originFromUniversitiesList = null;
            originFromUniversitiesList = clientRequest.doOriginFromUniversitiesJob(jobs.get(0).getJobUrl());
            if (originFromUniversitiesList == null) {
                originFromUniversitiesList = clientRequest.doOriginFromUniversitiesJob(jobs.get(1).getJobUrl());
            }
            if(originFromUniversitiesList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(true, false, false, false, false, true);
                    for(OriginFromUniversitiesResponse response : originFromUniversitiesList) {
                        newData.add(new Data42(
                                response.getUniversityDto().getName(),
                                response.getUniversityDto().getYerOfFundation(),
                                response.getUniversityDto().getUniversityType().toString(),
                                response.getUniversityDto().getLocation().toString(),
                                null, null, null, 0,
                                response.getComeFromDtos().get(0).getVal(),
                                response.getComeFromDtos().get(1).getVal(),
                                response.getComeFromDtos().get(2).getVal(),
                                response.getComeFromDtos().get(3).getVal()
                        ));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class NinthTask extends TimerTask {
        public void run() {
            count++;
            originFromStudiesList = null;
            originFromStudiesList = clientRequest.doOriginFromStudiesJob(jobs.get(0).getJobUrl());
            if (originFromStudiesList == null) {
                originFromStudiesList = clientRequest.doOriginFromStudiesJob(jobs.get(1).getJobUrl());
            }
            if(originFromStudiesList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(false, true, false, false, false, true);
                    for(OriginFromStudiesResponse response : originFromStudiesList) {
                        newData.add(new Data42(
                                null, null, null, null,
                                response.getFieldOfStudy().getName(),
                                null, null, 0,
                                response.getComeFromDtos().get(0).getVal(),
                                response.getComeFromDtos().get(1).getVal(),
                                response.getComeFromDtos().get(2).getVal(),
                                response.getComeFromDtos().get(3).getVal()
                        ));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    class TwelfthTask extends TimerTask {
        public void run() {
            count++;
            workingUniversitiesList = null;
            workingUniversitiesList = clientRequest.doWorkingUniversitiesJob(jobs.get(0).getJobUrl());
            if (workingUniversitiesList == null) {
                workingUniversitiesList = clientRequest.doWorkingUniversitiesJob(jobs.get(1).getJobUrl());
            }
            if(workingUniversitiesList != null) {
                stopTask(true);
                Platform.runLater(() -> {
                    data.clear();
                    ArrayList<Data42> newData = new ArrayList<Data42>();
                    toggleColumnVisibility(true, false, false, false, true, false);
                    for(WorkingUniversitiesResponse response : workingUniversitiesList) {
                        newData.add(new Data42(
                                response.getName(),
                                response.getYerOfFundation(),
                                response.getUniversityType().toString(),
                                response.getLocation().toString(),
                                null, null, null,
                                response.getValue(),
                                0, 0, 0, 0
                        ));
                    }
                    data.addAll(newData);
                });
                return;
            }
            if(count >= 20) {
                stopTask(false);
                return;
            }
        }
    }

    private void stopTask(boolean result) {
        timer.cancel();
        timer.purge();
        if(!result) {
            Platform.runLater(() -> showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie."));
        }
    }

    private void setServer(String address) {
        serverAddress = "http://" + address + ":" + PORT + "/app";
    }

    private void getServersAddresses() {
        // todo cos
    }

    private void tryAnotherServersNode() {
        // todo tez cos
    }

//    private void actionSwitcher(String url, int action) {
//        jobs = null;
//        jobs =  clientRequest.sendRequest(serverAddress + url);
//        if(jobs == null) {
//            jobs =  clientRequest.sendRequest(serverAddress + url);
//            if(jobs == null) {
//                showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem w trakcie połączenia z serwerem. Spróbuj ponownie.");
//                return;
//            }
//        } else {
//            sleep(1000);
//            switch (action) {
//                case 1: // allCountries
//                case 11: { // working/countries
//                    locationsList = null;
//                    locationsList = clientRequest.doLocationJob(jobs.get(0).getJobUrl());
//                    if (locationsList == null) {
//                        locationsList = clientRequest.doLocationJob(jobs.get(1).getJobUrl());
//                        if (locationsList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 2: { // universities
//                    universityList = null;
//                    universityList = clientRequest.doUniversityJob(jobs.get(0).getJobUrl());
//                    if (universityList == null) {
//                        universityList = clientRequest.doUniversityJob(jobs.get(1).getJobUrl());
//                        if (universityList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 3: // fieldOfStudy
//                case 10: { // working/fieldOfStudies
//                    studiesList = null;
//                    studiesList = clientRequest.doFieldOfStudyJob(jobs.get(0).getJobUrl());
//                    if (studiesList == null) {
//                        studiesList = clientRequest.doFieldOfStudyJob(jobs.get(1).getJobUrl());
//                        if (studiesList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 4: { // moreThanOneFieldOfStudy
//                    break;
//                }
//                case 5: { // moreThanOneFieldOfStudy
//                    break;
//                }
//                case 6: { // orginFrom/land
//                    comeFromList = null;
//                    comeFromList = clientRequest.doComeFromJob(jobs.get(0).getJobUrl());
//                    if (comeFromList == null) {
//                        comeFromList = clientRequest.doComeFromJob(jobs.get(1).getJobUrl());
//                        if (comeFromList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 7: { // orginFrom/countries
//                    originFromCountriesList = null;
//                    originFromCountriesList = clientRequest.doOriginFromCountriesJob(jobs.get(0).getJobUrl());
//                    if (originFromCountriesList == null) {
//                        originFromCountriesList = clientRequest.doOriginFromCountriesJob(jobs.get(1).getJobUrl());
//                        if (originFromCountriesList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 8: { // orginFrom/universities
//                    originFromUniversitiesList = null;
//                    originFromUniversitiesList = clientRequest.doOriginFromUniversitiesJob(jobs.get(0).getJobUrl());
//                    if (originFromUniversitiesList == null) {
//                        originFromUniversitiesList = clientRequest.doOriginFromUniversitiesJob(jobs.get(1).getJobUrl());
//                        if (originFromUniversitiesList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 9: { // orginFrom/fieldOfStudies
//                    originFromStudiesList = null;
//                    originFromStudiesList = clientRequest.doOriginFromStudiesJob(jobs.get(0).getJobUrl());
//                    if (originFromStudiesList == null) {
//                        originFromStudiesList = clientRequest.doOriginFromStudiesJob(jobs.get(1).getJobUrl());
//                        if (originFromStudiesList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//                case 12: { // working/universities
//                    workingUniversitiesList = null;
//                    workingUniversitiesList = clientRequest.doWorkingUniversitiesJob(jobs.get(0).getJobUrl());
//                    if (workingUniversitiesList == null) {
//                        workingUniversitiesList = clientRequest.doWorkingUniversitiesJob(jobs.get(1).getJobUrl());
//                        if (workingUniversitiesList == null) {
//                            showAlert("Uwaga", "Problem z połączeniem", "Wystąpił problem z dostępnością danych. Spróbuj ponownie.");
//                            return;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//
//        data.clear();
//        ArrayList<Data42> newData = new ArrayList<Data42>();
//
//        switch (action) {
//            case 1: { // allCountries
//                toggleColumnVisibility(false, false, true, false, true, false);
//                for(LocationResponse response : locationsList) {
//                    newData.add(new Data42(
//                            null, null, null, null, null,
//                            response.getLocation().name(),
//                            null,
//                            response.getValue(),
//                            0, 0, 0, 0));
//                }
//                break;
//            }
//            case 2: { // universities todo podobno poprawione
//                toggleColumnVisibility(true, false, false, false, false, false);
//                for(UniversityResponse response : universityList) {
//                    newData.add(new Data42(
//                            response.getName(),
//                            response.getYerOfFundation(),
//                            response.getUniversityType().toString(),
//                            response.getLocation().toString(),
//                            null, null, null,
//                            0, 0, 0, 0, 0
//                    ));
//                }
//                break;
//            }
//            case 3: { // fieldOfStudy
//                toggleColumnVisibility(false, true, false, false, true, false);
//                for(FieldOfStudyResponse response : studiesList) {
//                    newData.add(new Data42(
//                            null, null, null, null,
//                            response.getName(),
//                            null, null,
//                            response.getVal(),
//                            0, 0, 0, 0
//                    ));
//                }
//                break;
//            }
//            case 4: { // moreThanOne todo nie ma i nie bedzie
//                break;
//            }
//            case 5: { // moreThanOne
//                break;
//            }
//            case 6: { // originFrom/land
//                toggleColumnVisibility(false, false, false, true, true, false);
//                for(ComeFromResponse response : comeFromList) {
//                    newData.add(new Data42(
//                            null, null, null, null, null, null,
//                            response.getComeFrom().toString(),
//                            response.getVal(),
//                            0, 0, 0, 0
//                    ));
//                }
//                break;
//            }
//            case 7: { // originFrom/countries
//                toggleColumnVisibility(false, false, true, false, false, true);
//                for(OriginFromCountriesResponse response : originFromCountriesList) {
//                    newData.add(new Data42(
//                            null, null, null, null, null,
//                            response.getLocation().name(),
//                            null, 0,
//                            response.getComeFromDtos().get(0).getVal(),
//                            response.getComeFromDtos().get(1).getVal(),
//                            response.getComeFromDtos().get(2).getVal(),
//                            response.getComeFromDtos().get(3).getVal()));
//                }
//                break;
//            }
//            case 8: { //originFrom/universities
//                toggleColumnVisibility(true, false, false, false, false, true);
//                for(OriginFromUniversitiesResponse response : originFromUniversitiesList) {
//                    newData.add(new Data42(
//                            response.getUniversityDto().getName(),
//                            response.getUniversityDto().getYerOfFundation(),
//                            response.getUniversityDto().getUniversityType().toString(),
//                            response.getUniversityDto().getLocation().toString(),
//                            null, null, null, 0,
//                            response.getComeFromDtos().get(0).getVal(),
//                            response.getComeFromDtos().get(1).getVal(),
//                            response.getComeFromDtos().get(2).getVal(),
//                            response.getComeFromDtos().get(3).getVal()
//                    ));
//                }
//                break;
//            }
//            case 9: { // originFrom/fieldOfStudies todo jeszcze nie ma
//                toggleColumnVisibility(false, true, false, false, false, true);
//                for(OriginFromStudiesResponse response : originFromStudiesList) {
//                    newData.add(new Data42(
//                            null, null, null, null,
//                            response.getFieldOfStudy().getName(),
//                            null, null, 0,
//                            response.getComeFromDtos().get(0).getVal(),
//                            response.getComeFromDtos().get(1).getVal(),
//                            response.getComeFromDtos().get(2).getVal(),
//                            response.getComeFromDtos().get(3).getVal()
//                    ));
//                }
//                break;
//            }
//            case 10: { // working/fieldOfStudy
//                toggleColumnVisibility(false, true, false, false, true, false);
//                for(FieldOfStudyResponse response : studiesList) {
//                    newData.add(new Data42(
//                            null, null, null, null,
//                            response.getName(),
//                            null, null,
//                            response.getVal(),
//                            0, 0, 0, 0
//                    ));
//                }
//                break;
//            }
//            case 11: { // working/countries
//                toggleColumnVisibility(false, false, true, false, true, false);
//                for(LocationResponse response : locationsList) {
//                    newData.add(new Data42(
//                            null, null, null, null, null,
//                            response.getLocation().name(),
//                            null,
//                            response.getValue(),
//                            0, 0, 0, 0));
//                }
//                break;
//            }
//            case 12: { // working/universities
//                toggleColumnVisibility(true, false, false, false, true, false);
//                for(WorkingUniversitiesResponse response : workingUniversitiesList) {
//                    newData.add(new Data42(
//                            response.getUniversityDto().getName(),
//                            response.getUniversityDto().getYerOfFundation(),
//                            response.getUniversityDto().getUniversityType().toString(),
//                            response.getUniversityDto().getLocation().toString(),
//                            null, null, null,
//                            response.getValue(),
//                            0, 0, 0, 0
//                    ));
//                }
//                break;
//            }
//        }
//        data.addAll(newData);
//    }
}
