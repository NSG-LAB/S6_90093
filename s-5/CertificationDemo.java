public class CertificationDemo {
    private int id;
    private String name;
    private String dateOfCompletion;

    public CertificationDemo() {
        this.id = 101;
        this.name = "Java Spring Framework Certification";
        this.dateOfCompletion = "2026-01-21";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfCompletion='" + dateOfCompletion + '\'' +
                '}';
    }
}
