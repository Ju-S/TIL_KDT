package main;

import dao.CafeDAO;
import dto.CafeDTO;

import java.util.List;


//졸려 주성아  너무너무 졸려 피곤해
public class Main {
    public static void main(String[] args) {
        try {
            CafeDAO cafeDAO = new CafeDAO();

            //cafeDAO.insert(new CafeDTO(0, "Apple Juice", 6000, "y"));
            //cafeDAO.delete(1016);
            //cafeDAO.update(new CafeDTO(1017, "Apple Juice", 4500, "y"));
            List<CafeDTO> cafeDTOList = cafeDAO.selectAll();
            for(CafeDTO cafe : cafeDTOList) {
                System.out.println(cafe.getId() + " : " + cafe.getName() + " : " + cafe.getPrice() + " : " + cafe.getIceable());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("데이터 입력 도중 에러가 발생했습니다.");
            System.out.println("같은 오류가 반복될 시 관리자에게 문의주세요.");
            System.out.println("E-Mail : admin@admin.com");
        }
    }
}
