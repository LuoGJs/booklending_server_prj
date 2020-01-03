import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
      Date  thatdate=new Date("2017-12-12");
       SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
       String thisdate=simpleDateFormat.format(new Date());
        System.out.println();
    }
}
