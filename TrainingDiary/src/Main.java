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

    private void addExercise() {
        Scanner scanner = new Scanner(System.in);
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

    private void collectSessions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hvilken øvelse vil du ha oversikt over? ");
        String exercise = scanner.next();

        try {
            List<Exercise> exercises = controller.getExercises(exercise);
            System.out.println(exercises.get(0));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTopTen() {
        try {
            Statement statement = controller.conn.createStatement();
            System.out.println("styrke eller utholdenhet? (case sensitive)");
            String line = scanner.nextLine();
            System.out.println("Velg øvelse");
            String line2 = scanner.nextLine();

            switch (line) {
                case "styrke":
                    // Do something
                    try {
                        Statement stmt = controller.conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT training_session, Load, Reps, Sets FROM Resultat WHERE Name="+line2+" ORDER BY Load DESC limit "+10+";");
                        while(rs.next()) {
                            System.out.println("| "+rs.getDate("treningsøkt") + " | " + rs.getString("belastning") + " | " + rs.getInt("antall_rep") + " | " + rs.getInt("antall_sett") + " |");
                        }
                    }
                    catch(SQLException e){
                        System.out.println(e);
                    }

                case "utholdenhet":
                    // Do something
                    try {
                        Statement stmt = controller.conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT training_session, Distance, Duration FROM Resultat WHERE Name="+line2+" ORDER BY Duration ASC limit "+10+";");
                        while(rs.next()) {
                            System.out.println("| "+rs.getDate("treningsøkt") + " | " + rs.getString("varighet") + " | " + rs.getInt("lengde") + " |" );
                        }
                    }
                    catch(SQLException e){
                        System.out.println(e);
                    }
            }
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
            welcome += "4 - Hent topp 10 for en øvelse , sortert på dato\n";

            System.out.print(welcome);
            System.out.print("> ");

            int scase = Integer.parseInt(main.scanner.nextLine());
            switch(scase) {
                case 1:
                    main.addExercise();
                    break;
                case 2:
                    main.collectSessions();
                    break;
                case 3:
                    break;
                case 4:
                    main.getTopTen();
                    break;
                default:
                    break;
            }
        }
    }

}
