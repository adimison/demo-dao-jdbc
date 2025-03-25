import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Department obj = new Department(1, "Books");


        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller= sellerDao.getById(3);
        System.out.println(seller);
    }
}