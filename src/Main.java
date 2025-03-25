import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       SellerDao sellerDao = DaoFactory.createSellerDao();
//        System.out.printf("====TEST 1 seller findbyId====\n");
//        Seller seller= sellerDao.getById(3);
//        System.out.println(seller);


        System.out.printf("====TEST 2 seller findbyId====\n");
        Department dep=new Department(2,null);
        List<Seller> list= sellerDao.finAllByDepartment(dep);
        for (Seller s:list){
            System.out.println(s);
        }

        System.out.printf("====TEST 3 seller findbyId====\n");
         list= sellerDao.finAll();
        for (Seller s:list){
            System.out.println(s);

        }
        System.out.printf("====TEST 4 seller findbyId====\n");
        Seller seller= new Seller(null, "greg","greg@gmail.com",new Date(),3000.0,dep);
        sellerDao.insert(seller);
        System.out.printf("Insert id:"+seller.getId()+"\n");
    }
}