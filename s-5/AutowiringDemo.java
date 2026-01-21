public class AutowiringDemo {
    public static void main(String[] args) {
        System.out.println("========== Spring Autowiring Demo ==========\n");
        System.out.println("This demonstration shows how Spring @Autowired annotation works\n");

        StudentDemo student = new StudentDemo();
        
        System.out.println("Student Information:");
        System.out.println("---------------------");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());
        
        System.out.println("\nAutowired Certification Information:");
        System.out.println("-----------------------------------");
        if (student.getCertification() != null) {
            System.out.println("Certification ID: " + student.getCertification().getId());
            System.out.println("Certification Name: " + student.getCertification().getName());
            System.out.println("Date of Completion: " + student.getCertification().getDateOfCompletion());
        } else {
            System.out.println("No certification assigned!");
        }

        System.out.println("\nComplete Student Object:");
        System.out.println("------------------------");
        System.out.println(student.toString());

        System.out.println("\n========== Demonstration Complete ==========");
    }
}
