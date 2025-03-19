import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentManagementSystem extends Application {

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        // GridPane layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Student Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);

        // Student Name Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        // Student ID Label
        Label idLabel = new Label("Student ID:");
        GridPane.setConstraints(idLabel, 0, 1);

        // Student ID Input
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 1);

        // Course Label
        Label courseLabel = new Label("Course:");
        GridPane.setConstraints(courseLabel, 0, 2);

        // Course Input
        TextField courseInput = new TextField();
        GridPane.setConstraints(courseInput, 1, 2);

        // Add Student Button
        Button addButton = new Button("Add Student");
        GridPane.setConstraints(addButton, 1, 3);
        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            String studentId = idInput.getText();
            String course = courseInput.getText();
            if (!name.isEmpty() && !studentId.isEmpty() && !course.isEmpty()) {
                studentList.add(new Student(name, studentId, course));
                nameInput.clear();
                idInput.clear();
                courseInput.clear();
            } else {
                showAlert("Input Error", "Please fill all fields.");
            }
        });

        // Student Table
        TableView<Student> table = new TableView<>();
        table.setItems(studentList);
        table.setEditable(true);

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(data -> data.getValue().studentIdProperty());

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(data -> data.getValue().courseProperty());

        table.getColumns().addAll(nameColumn, idColumn, courseColumn);
        GridPane.setConstraints(table, 0, 4, 2, 1);

        // Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, idLabel, idInput, courseLabel, courseInput, addButton, table);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

class Student {
    private final SimpleStringProperty name;
    private final SimpleStringProperty studentId;
    private final SimpleStringProperty course;

    public Student(String name, String studentId, String course) {
        this.name = new SimpleStringProperty(name);
        this.studentId = new SimpleStringProperty(studentId);
        this.course = new SimpleStringProperty(course);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getStudentId() {
        return studentId.get();
    }

    public SimpleStringProperty studentIdProperty() {
        return studentId;
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }
}
