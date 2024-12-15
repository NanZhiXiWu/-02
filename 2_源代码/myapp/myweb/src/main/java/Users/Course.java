package Users;

public class Course {
    private String course_name;
    private String course_id;
    private String selection_method;
    private String teacher_name;
    private String teacher_contact;
    private String course_status;
    private String course_requirements;

    public Course(){

    }

    public Course(String course_name, String course_id, String selection_method, String teacher_name, String teacher_contact, String course_status, String course_requirements) {
        this.course_name = course_name;
        this.course_id = course_id;
        this.selection_method = selection_method;
        this.teacher_name = teacher_name;
        this.teacher_contact = teacher_contact;
        this.course_status = course_status;
        this.course_requirements = course_requirements;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSelection_method() {
        return selection_method;
    }

    public void setSelection_method(String selection_method) {
        this.selection_method = selection_method;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_contact() {
        return teacher_contact;
    }

    public void setTeacher_contact(String teacher_contact) {
        this.teacher_contact = teacher_contact;
    }

    public String getCourse_status() {
        return course_status;
    }

    public void setCourse_status(String course_status) {
        this.course_status = course_status;
    }

    public String getCourse_requirements() {
        return course_requirements;
    }

    public void setCourse_requirements(String course_requirements) {
        this.course_requirements = course_requirements;
    }
}
