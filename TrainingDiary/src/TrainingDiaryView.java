public class TrainingDiaryView {

    public void mainMenu() {

        String welcome = "Velkommen til denne treningsdagboken! \n";
        welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
        welcome += "1 - Legg inn ny treningsøkt \n";
        welcome += "2 - Hent ut tidligere treningsøkter \n";
        welcome += "\n For å avslutte, trykk hvilken som helst annen tast";

        System.out.print(welcome);
    }

    public static void main(String[] args) {
        TrainingDiaryView main = new TrainingDiaryView();
        main.mainMenu();
    }

}
