import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

public class Main {

    Scanner scanner = new Scanner(System.in);

    private DBController controller;

    public Main(DBController controller) {
        setController(controller);
    }

    private void setController(DBController controller) {
        this.controller = controller;
    }

    private DBController getController() {
        return controller;
    }

    private void addTrainingSession() {
        // addTrainingSession(Date time, Integer duration, Integer shape, Integer rating, String note,
        // String sportname, String type, String weather_type, double temperature, double humidity, Integer spectators)
        // TODO: Add training session

        while (true) {
            // Loop for adding exercises
            System.out.println("* betyr obligatorisk");
            System.out.println("Legg inn antall sett*");
            int sets = Integer.parseInt(scanner.nextLine());
            System.out.println("Legg inn antall reps*");
            int reps = Integer.parseInt(scanner.nextLine());
            System.out.println("Legg inn vekt (kg)*");
            int load = Integer.parseInt(scanner.nextLine());
            System.out.println("Legg inn lengde");
            int meters = Integer.parseInt(scanner.nextLine());
            System.out.println("Legg inn varighet");
            int seconds = Integer.parseInt(scanner.nextLine());
            System.out.println("Legg inn navn");  // ??? Hvorfor ???
            String name = scanner.nextLine();
            System.out.println("Legger inn dagens dato");
            Date date = new Date();
            controller.addExercisePerformed(sets, reps, load, meters, seconds, name, date);
        }
    }

    private void collectSessions() {
        System.out.println("Hvilken øvelse vil du ha oversikt over? ");
        String exercise = scanner.next();

        try {
            List<Exercise> exercises = controller.getExercises(exercise);
            System.out.println(exercises.get(0));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addExercise() {
        System.out.print("Navn på øvelsen: ");
        String exercise = scanner.next();
        System.out.print("Beskrivelse på øvelsen: ");
        String description = scanner.next();

        try {
            controller.addExercise(exercise, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateExerciseDescription() {
        System.out.println("Hvilken øvelse vil du endre? ");
        String exercise = scanner.nextLine();
        System.out.println("Ny beskrivelse: ");
        String description = scanner.nextLine();
        try {
            controller.changeDescription(exercise, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main(new DBController());

        while(true) {
            String welcome = "Velkommen til treningsdagboken\n";
            welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
            welcome += "1 - Legg inn ny treningsøkt\n";
            welcome += "2 - Hent ut tidligere treningsøkter\n";
            welcome += "3 - Legg til ny øvelse\n";
            welcome += "4 - Oppdater øvelse med ny beskrivelse\n";

            System.out.print(welcome);
            System.out.print("> ");

            int scase = Integer.parseInt(main.scanner.nextLine());
            switch(scase) {
                case 1:

                    break;
                case 2:
                    main.collectSessions();
                    break;
                case 3:
                    main.addExercise();
                    break;
                case 4:
                    main.updateExerciseDescription();
                    break;
                default:
                    break;
            }
        }
    }

}
