package Users;

public class Student {
    private String student_id;
    private String username;
    private String student_number;
    private String classes;
    private String major;
    private String college;
    private String department;
    private String password;

    public Student() {
    }

    public Student(String student_id, String username, String student_number, String classes, String major, String college, String department, String password) {
        this.student_id = student_id;
        this.username = username;
        this.student_number = student_number;
        this.classes = classes;
        this.major = major;
        this.college = college;
        this.department = department;
        this.password = password;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
