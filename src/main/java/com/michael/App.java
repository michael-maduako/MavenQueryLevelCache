package com.michael;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
    	StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata meta= new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory factory= meta.getSessionFactoryBuilder().build();
    	Session session=factory.openSession();
    	Transaction tx= session.beginTransaction(); 
    	User u0=new User();
    	User u1=new User();
    	u0.setUserName("ABC");
    	u1.setUserName("DEF");
    	session.save(u0);
    	session.save(u1);
    	tx.commit();
    	@SuppressWarnings("unchecked")
    	Query<User> query= session.createQuery(
    			"from User as user where user.userId=?0 and user.userName=?1");
    	
    	query.setInteger(0,Integer.valueOf(1));
    	query.setString(1, "Ergo");
    	query.setCacheable(true);
    	List list=query.list();
    	System.out.println(list);
    	
    }
}
