public class StudentDemo {
    private int id;
    private String name;
    private String gender;
    private CertificationDemo certification;

    public StudentDemo() {
        this.id = 1001;
        this.name = "Raj Kumar";
        this.gender = "Male";
        this.certification = new CertificationDemo();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CertificationDemo getCertification() {
        return certification;
    }

    public void setCertification(CertificationDemo certification) {
        this.certification = certification;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", certification=" + certification +
                '}';
    }
}
