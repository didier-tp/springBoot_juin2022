package tp.util;


import java.util.ArrayList;
import java.util.List;

import tp.data.Product;


public class ProductUtil {
	
public static List<Product> initSampleProductList(){
	List<Product> productList = new ArrayList<>();
	productList.add(new Product(1L,"webcam",96.83,"usb, with sound"));
	productList.add(new Product(2L,"usb wire",6.8,"2 meters"));
	productList.add(new Product(3L,"computer x2",976.5,"i7 laptop 17inch"));
	productList.add(new Product(4L,"printer z1",156.8,"ink jet"));
	productList.add(new Product(5L,"computer y6",896.83,"i7 desktop ssd"));
	return productList;
	}

public static List<Product> initSampleProductListByCategory(String category){
	List<Product> productList = new ArrayList<>();
	switch(category) {
	case "computer":
		productList.add(new Product(3L,"computer x2",976.5,"i7 laptop 17inch"));
		productList.add(new Product(5L,"computer y6",896.83,"i7 desktop ssd"));
		productList.add(new Product(8L,"computer z8",351.3,"i3 laptop 15inch"));
		break;
    case "printer":
    	productList.add(new Product(1L,"printer z1",156.8,"ink jet"));
    	productList.add(new Product(6L,"printer z2",357.9,"laser a3"));
    	productList.add(new Product(7L,"printer zz3",251.3,"ink jet a3"));
		break;
	default:
		productList.add(new Product(2L,"usb wire",6.8,"2 meters"));
		productList.add(new Product(4L,"webcam",96.83,"usb, with sound"));
	}
	return productList;
	}


public static void displayProductList(List<Product> productList) {
	productList.forEach(p->System.out.println(p));
}






}
