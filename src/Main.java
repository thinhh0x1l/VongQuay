import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();
    private static final List<String> vongQuay = new ArrayList<>();
    private static final Map<String, Integer> demPhanThuong = new HashMap<>(); // Đếm số lần mỗi phần thưởng xuất hiện
    private static int soLuongQuay;
    private static int tongPhanThuong1va2 = 0;
    static {
        addRewards("Phần thưởng 01", 1);
        addRewards("Phần thưởng 02", 4);
        addRewards("Phần thưởng 03", 10);
        addRewards("Phần thưởng 04", 15);
        addRewards("Phần thưởng 05", 15);
        addRewards("Phần thưởng 06", 15);
        addRewards("Phần thưởng 07", 15);
        addRewards("Phần thưởng 08", 25);

        // Xáo trộn mảng để đảm bảo tính ngẫu nhiên
        Collections.shuffle(vongQuay);
    }
    private static void addRewards(String reward, int percentage) {
        for (int i = 0; i < percentage; i++) {
            vongQuay.add(reward);
        }
        demPhanThuong.put(reward, 0); // Khởi tạo bộ đếm
    }
    public static String quayThuong() {
        int index;
        String phanThuong;

        //Nếu tổng Phần thưởng 1 và 2 đã trao quá 2% thì tạo ngẫu nhiên mới khác(1 và 2)
        do{
            index = random.nextInt(vongQuay.size());
            phanThuong = vongQuay.get(index);
        }while((phanThuong.equals("Phần thưởng 01")|| phanThuong.equals("Phần thưởng 02")) && (tongPhanThuong1va2+1)*1.0/soLuongQuay *100  > 2);
        if(phanThuong.equals("Phần thưởng 01")|| phanThuong.equals("Phần thưởng 02"))
            tongPhanThuong1va2++;
        demPhanThuong.put(phanThuong, demPhanThuong.get(phanThuong) + 1); // Cập nhật bộ đếm
        return phanThuong;
    }

    public static void main(String[] args) {
        System.out.print("Nhập tổng số lượt quay cho event: ");
        do{
            soLuongQuay = sc.nextInt();
        }while(soLuongQuay < 50); // Đảm bảo tất cả giải thưởng có khả năng được quay trúng
        int lanQuay = 1;
        int soPhanThuong1 = 0;
        int soPhanThuong2 = 0;
        for (int i = 0; i < soLuongQuay; i++) {
            String reward = quayThuong();
            System.out.println(lanQuay + ": " + reward);
            if(reward.equals("Phần thưởng 01")) soPhanThuong1++;
            if(reward.equals("Phần thưởng 02")) soPhanThuong2++;
            lanQuay++;
        }
        System.out.println("Số lượng Phần Thưởng 1 đã trao: " + soPhanThuong1 +" [Tỉ lệ trúng thực tế: "+demPhanThuong.get("Phần thưởng 01")*100.0/soLuongQuay  +"%]");
        System.out.println("Số lượng Phần Thưởng 2 đã trao: " + soPhanThuong2 +" [Tỉ lệ trúng thực tế: "+demPhanThuong.get("Phần thưởng 02")*100.0/soLuongQuay + "%]");
        System.out.println("Tổng số lượng Phần thưởng 1 và 2 đã trao: " +tongPhanThuong1va2+ " [Tỉ lệ phần thưởng đã trao: "+ tongPhanThuong1va2*100.0/soLuongQuay +"%]");
    }
}