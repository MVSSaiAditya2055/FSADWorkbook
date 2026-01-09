package com.klu.app;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import com.klu.model.Product;
import com.klu.util.HibernateUtil;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int ch;
        do {
            System.out.println("\n=== WORKBOOK 2 : PRODUCT HQL MENU ===");
            System.out.println("1. Insert Products");
            System.out.println("2. Sort by Price (ASC)");
            System.out.println("3. Sort by Price (DESC)");
            System.out.println("4. Sort by Quantity (High → Low)");
            System.out.println("5. Pagination");
            System.out.println("6. Aggregate Functions");
            System.out.println("7. Group By Description");
            System.out.println("8. Filter by Price Range");
            System.out.println("9. LIKE Queries");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1: insertProducts(); break;
                case 2: sortPriceAsc(); break;
                case 3: sortPriceDesc(); break;
                case 4: sortByQuantity(); break;
                case 5: pagination(); break;
                case 6: aggregates(); break;
                case 7: groupByDescription(); break;
                case 8: filterByPriceRange(); break;
                case 9: likeQueries(); break;
            }
        } while (ch != 0);
    }
    static void insertProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        System.out.print("Name: ");
        sc.nextLine();   // clear buffer
        String name = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        Product p = new Product(name, desc, price, qty);
        s.persist(p);

        tx.commit();
        s.close();

        System.out.println("Product inserted successfully!");
    }
    static void sortPriceAsc() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = s.createQuery(
            "from Product order by price asc", Product.class);

        q.list().forEach(p ->
            System.out.println(p.getName() + " → " + p.getPrice()));

        s.close();
    }
    static void sortPriceDesc() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = s.createQuery(
            "from Product order by price desc", Product.class);

        q.list().forEach(p ->
            System.out.println(p.getName() + " → " + p.getPrice()));

        s.close();
    }
    static void sortByQuantity() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = s.createQuery(
            "from Product order by quantity desc", Product.class);

        q.list().forEach(p ->
            System.out.println(p.getName() + " → Qty: " + p.getQuantity()));

        s.close();
    }
    static void pagination() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Enter starting index: ");
        int start = sc.nextInt();

        System.out.print("Enter number of records: ");
        int size = sc.nextInt();

        Query<Product> q = s.createQuery("from Product", Product.class);
        q.setFirstResult(start);
        q.setMaxResults(size);

        q.list().forEach(p ->
            System.out.println(p.getId() + " | " + p.getName()));

        s.close();
    }
    static void aggregates() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.println("Total Products: " +
            s.createQuery("select count(*) from Product").uniqueResult());

        System.out.print("Enter minimum quantity: ");
        int q = sc.nextInt();

        System.out.println("Products with quantity > " + q + ": " +
            s.createQuery("select count(*) from Product where quantity > :q")
             .setParameter("q", q)
             .uniqueResult());

        Object[] minMax = (Object[]) s.createQuery(
            "select min(price), max(price) from Product").uniqueResult();

        System.out.println("Min Price: " + minMax[0]);
        System.out.println("Max Price: " + minMax[1]);

        s.close();
    }
    static void groupByDescription() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Object[]> list = s.createQuery("select description, count(*) from Product group by description").list();

        for (Object[] row : list) {
            System.out.println(row[0] + " → " + row[1]);
        }

        s.close();
    }
    static void filterByPriceRange() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Enter min price: ");
        double min = sc.nextDouble();

        System.out.print("Enter max price: ");
        double max = sc.nextDouble();

        Query<Product> q = s.createQuery(
            "from Product where price between :min and :max", Product.class);

        q.setParameter("min", min);
        q.setParameter("max", max);

        q.list().forEach(p ->
            System.out.println(p.getName() + " → " + p.getPrice()));

        s.close();
    }
    static void likeQueries() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.println("\n--- LIKE Queries ---");
        System.out.println("1. Names starting with letters");
        System.out.println("2. Names ending with letters");
        System.out.println("3. Names containing substring");
        System.out.println("4. Names with exact length");

        System.out.print("Choose option: ");
        int ch = sc.nextInt();
        sc.nextLine();

        Query<Product> q = null;

        switch (ch) {
            case 1:
                System.out.print("Enter starting letters: ");
                q = s.createQuery(
                    "from Product where name like :p", Product.class);
                q.setParameter("p", sc.nextLine() + "%");
                break;

            case 2:
                System.out.print("Enter ending letters: ");
                q = s.createQuery(
                    "from Product where name like :p", Product.class);
                q.setParameter("p", "%" + sc.nextLine());
                break;

            case 3:
                System.out.print("Enter substring: ");
                q = s.createQuery(
                    "from Product where name like :p", Product.class);
                q.setParameter("p", "%" + sc.nextLine() + "%");
                break;

            case 4:
                System.out.print("Enter exact length: ");
                q = s.createQuery(
                    "from Product where length(name)=:l", Product.class);
                q.setParameter("l", sc.nextInt());
                break;

            default:
                System.out.println("Invalid choice");
                s.close();
                return;
        }

        q.list().forEach(p -> System.out.println(p.getName()));
        s.close();
    }
}