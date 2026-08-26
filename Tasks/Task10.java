class Student {
    private String firstName;
    private String lastName;
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void printFullName() {
        System.out.println(firstName + " " + lastName);
    }
}

public class Task10 {
    public static void main(String[] args) {
        Student[] students = new Student[] {
                new Student("Bhea", "Avrille"),
                new Student("Ryza", "Franco"),
                new Student("Ashmor", "Galvez"),
        };
        for (Student s : students) {
            s.printFullName();
        }
    }
}
