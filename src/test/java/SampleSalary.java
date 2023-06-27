import com.example.fx123.CsvUtils;

public class SampleSalary {
    public static void main(String[] args) {
        float hourly_rate = 70585.05f;
        System.out.println(CsvUtils.addCommaAndTwoDecimalForFloatStr(String.valueOf(hourly_rate)));
        System.out.println(CsvUtils.addCommaToStrInt(String.valueOf(hourly_rate)));
    }
}
