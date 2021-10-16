import java.util.ArrayList;

public class StudentList  {
    private ArrayList<Students> studentList =null;

    public StudentList() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Students stu){
        studentList.add(stu);
    }

    public ArrayList<Students> getStudentsList() {
        return studentList;
    }

    public void setStudentsList(ArrayList<Students> studentsList) {
        this.studentList = studentsList;
    }

    public static int searchStudent(Students [] stu, String name)
    {
        int result = -1;
        int i =0;
        for (Students student : stu)
        {
            if(student.getName().equals(name))
                return i;
            i++;
        }
        return result;
    }
    public  Students nameStudentSearch(String nameStudent) {
        for (int i = 0; i < studentList.size(); i++) {

            if (nameStudent.trim().equalsIgnoreCase(studentList.get(i).getName())) {

                return studentList.get(i);
            }
        }
    return null;
    }

}
