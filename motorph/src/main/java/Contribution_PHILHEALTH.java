public interface Contribution_PHILHEALTH {
     default float LOWER(){
        return 1_000;
    }
    default float MEDIAN(int Salary){
        return Salary * 0.03f;
    }
    default float HIGHER(){
        return 1_800;
    }
}