package Users;

public class Teacher {
    private String teacher_id;
    private String teacher_name;
    private String teacher_gender;
    private String teacher_title;
    private String teacher_phone;
    private String teacher_password;

    public Teacher() {

    }

    public Teacher(String teacher_id, String teacher_name, String teacher_gender, String teacher_title, String teacher_phone, String teacher_password) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.teacher_gender = teacher_gender;
        this.teacher_title = teacher_title;
        this.teacher_phone = teacher_phone;
        this.teacher_password = teacher_password;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_gender() {
        return teacher_gender;
    }

    public void setTeacher_gender(String teacher_gender) {
        this.teacher_gender = teacher_gender;
    }

    public String getTeacher_title() {
        return teacher_title;
    }

    public void setTeacher_title(String teacher_title) {
        this.teacher_title = teacher_title;
    }

    public String getTeacher_phone() {
        return teacher_phone;
    }

    public void setTeacher_phone(String teacher_phone) {
        this.teacher_phone = teacher_phone;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }
}

