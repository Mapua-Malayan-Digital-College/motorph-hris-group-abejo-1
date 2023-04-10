public class Occupation extends Employee
{

    String status;
    String position;
    String immediate_supervisor;

    Occupation (String status, String position, String immediate_supervisor)
    {
        this.status = status;
        this.position = position;
        this.immediate_supervisor = immediate_supervisor;
    }
}
