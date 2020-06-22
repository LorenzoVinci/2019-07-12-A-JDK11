package it.polito.tdp.food.db;

public class TestDao {

	public static void main(String[] args) {
		FoodDao dao = new FoodDao();
		
		System.out.println("Printing all the condiments...");
		System.out.println(dao.listAllCondiments().size());
		
		System.out.println("Printing all the foods...");
		System.out.println(dao.listAllFoods().size());
		
		System.out.println("Printing all the portions...");
		System.out.println(dao.listAllPortions().size());
	}

}
