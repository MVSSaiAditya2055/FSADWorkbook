package com.klu.app;
import java.util.Scanner;
import com.klu.model.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.klu.util.HibernateUtil;
public class MainApp {
	static SessionFactory factory=HibernateUtil.getSessionFactory();
	public static void main(String[] args) {
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		Scanner sc=new Scanner(System.in);
		int choice;
		do {
			System.out.println("....Main Menu....");
			System.out.println("1.Insert Products");
			System.out.println("2.Retrieve product using id");
			System.out.println("3.Update price");
			System.out.println("4.Update quantity");
			System.out.println("5.Delete product by id");
			System.out.println("6.Exit");
			System.out.print("Select your choice: ");
			choice=sc.nextInt();
			switch(choice) {
			case 1: insertProd(sc);
					break;
			case 2: viewProdbyID(sc);
					break;
			case 3: updatePrice(sc);
					break;
			case 4: updateQuant(sc);
					break;
			case 5: deleteProdbyID(sc);
					break;
			case 6: System.out.println("Thank You");
					break;
			default: System.out.println("Wrong choice");
					break;
			}
		}while(choice!=6);
		factory.close();
		session.close();
	}
	static void insertProd(Scanner sc) {
	    Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();
	    Product p = new Product();
	    System.out.print("Enter Product Name: ");
	    sc.nextLine();
	    p.setName(sc.nextLine());
	    System.out.print("Enter Description: ");
	    p.setDescription(sc.nextLine());
	    System.out.print("Enter Price: ");
	    p.setPrice(sc.nextDouble());
	    System.out.print("Enter Quantity: ");
	    p.setQuantity(sc.nextInt());
	    session.persist(p);
	    tx.commit();
	    session.close();
	    System.out.println("Product inserted successfully!");
	}
	static void viewProdbyID(Scanner sc) {
	    Session session = factory.openSession();

	    System.out.print("Enter Product ID: ");
	    int id = sc.nextInt();

	    Product p = session.get(Product.class, id);

	    if (p != null) {
	        System.out.println("Name       : " + p.getName());
	        System.out.println("Description: " + p.getDescription());
	        System.out.println("Price      : " + p.getPrice());
	        System.out.println("Quantity   : " + p.getQuantity());
	    } else {
	        System.out.println("Product not found!");
	    }

	    session.close();
	}
	static void updatePrice(Scanner sc) {
	    Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();

	    System.out.print("Enter Product ID: ");
	    int id = sc.nextInt();

	    Product p = session.get(Product.class, id);

	    if (p != null) {
	        System.out.print("Enter New Price: ");
	        p.setPrice(sc.nextDouble());
	        tx.commit();
	        System.out.println("Price updated successfully!");
	    } else {
	        System.out.println("Product not found!");
	        tx.rollback();
	    }

	    session.close();
	}
	static void updateQuant(Scanner sc) {
	    Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();

	    System.out.print("Enter Product ID: ");
	    int id = sc.nextInt();

	    Product p = session.get(Product.class, id);

	    if (p != null) {
	        System.out.print("Enter New Quantity: ");
	        p.setQuantity(sc.nextInt());
	        tx.commit();
	        System.out.println("Quantity updated successfully!");
	    } else {
	        System.out.println("Product not found!");
	        tx.rollback();
	    }

	    session.close();
	}
	static void deleteProdbyID(Scanner sc) {
	    Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();

	    System.out.print("Enter Product ID: ");
	    int id = sc.nextInt();

	    Product p = session.get(Product.class, id);

	    if (p != null) {
	        session.remove(p);
	        tx.commit();
	        System.out.println("Product deleted successfully!");
	    } else {
	        System.out.println("Product not found!");
	        tx.rollback();
	    }

	    session.close();
	}
}
