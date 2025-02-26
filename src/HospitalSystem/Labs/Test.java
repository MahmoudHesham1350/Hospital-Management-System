package HospitalSystem.Labs;

public class Test {
    private final String type;
    private final String description;
    private String result;
    private enum Status {PENDING, COMPLETED};
    private Status status;
    private final Lab lab;

    public Test(String type, String description, Lab lab) {
        this.type = type;
        this.description = description;
        this.lab = lab;
        this.status = Status.PENDING;
        this.result = "";
    }

    public void setResult(String result) {
        this.result = result;
        this.status = Status.COMPLETED;
    }

    public String getResult() {
        if (status == Status.PENDING) {
            return "Result pending";
        }
        return result;
    }

    public int getLabId() {
        return lab.getLabId();
    }

    public String getDescription() {
        return "Test type: " + type + "\nDescription: " + description;
    }
    
    @Override
    public String toString() {
        return "Test type: " + type + "\nDescription: " + description + "\nResult: " + getResult();
    }
}