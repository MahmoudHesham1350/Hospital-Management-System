import HospitalSystem.CLI;

public class Main {
    public static void main(String[] args) {
        DummyDataGenerator.generateDummyData(CLI.getHospital()); // uncomment this line to generate dummy data
        CLI.run();
    }  
}       

