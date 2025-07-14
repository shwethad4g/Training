package Day6Task;

public class Student {

    int studentId;
    String studentName;
    int Marks;

      public Student(int studentId,String studentName,int Marks){
          this.studentId=studentId;
          this.studentName=studentName;
          this.Marks=Marks;
      }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", Marks=" + Marks +
                '}';
    }
}
