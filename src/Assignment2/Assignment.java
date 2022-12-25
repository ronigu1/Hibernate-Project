package Assignment2;

import Assignment2.hib.HibernateUtil;
import Assignment2.Entities.HistoryEntity;
import Assignment2.Entities.LoginLogEntity;
import Assignment2.Entities.MediaItemsEntity;
import Assignment2.Entities.UsersEntity;
import Assignment2.Entities.AdministratorsEntity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.sql.Date;
import java.util.*;

public class Assignment {
//    The function returns true if the received username exists in the table USERS otherwise false.
    public static boolean isExistUsername (String username){
        int numOfUsers = 0;
        try{
            Session session = HibernateUtil.currentSession();
            String hqlQuery = "select username from UsersEntity where username='"+username+"'";
            numOfUsers = session.createQuery(hqlQuery).list().size();
        }
        catch (Exception e){
//            e.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
        return numOfUsers>0;
    }

    public static String insertUser(String username, String password,
                                    String first_name, String last_name, String day_of_birth, String
                                            month_of_birth, String year_of_birth){
//        The function checks if the username exist in the USERS table, in case of positive answer returns null else insert the user to the table USERS including registration_date field and returns the user id (USERID).
//        Use generator class="increment" for USERID.
        try{
            if(isExistUsername(username)==false){
                Date dateOfBirth = new Date (Integer.parseInt(year_of_birth) - 1900, Integer.parseInt(month_of_birth), Integer.parseInt(day_of_birth));
                Session session = HibernateUtil.currentSession();
                Transaction tx = null;
                tx = session.beginTransaction();

//                UsersEntity user = new UsersEntity(username, password, first_name, last_name, dateOfBirth);
                UsersEntity user = new UsersEntity();
                user.setUsername(username);
                user.setPassword(password);
                user.setFirstName(first_name);
                user.setLastName(last_name);
                user.setDateOfBirth(dateOfBirth);
//                LocalDate ld = java.time.LocalDate.now();
//                ZoneId defaultZoneId = ZoneId.systemDefault();
//                Date date = (Date) Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                user.setRegistrationDate(date);
                session.save(user);
                tx.commit();
                return Integer.toString(user.getUserid());
            }
        }
        catch (Exception e){
//            e.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
        return null;
    }
    public static List getTopNItems (int top_n) {
//        The function retrieves from the table MediaItems first top_n items (mid ascending order).
        Session session=null;
        List<MediaItemsEntity> topNlist=null;

        try {
            session = HibernateUtil.currentSession();
            String hqlQuery = "select items from MediaItemsEntity items order by mid asc";
            Query query  = session.createQuery(hqlQuery);
            topNlist = query.setMaxResults(top_n).list();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return topNlist;
    }
    public static String validateUser (String username, String password){
//        The function compares received values with existing ones in the database.
//        The function return first and last name of the user if the values are equal to the values in the table otherwise returns “Not Found! Could be a hacker.”.
        Session session=null;
        String userFullName = null;
        try {
            session = HibernateUtil.currentSession();
            String hqlQuery = "select user from UsersEntity user where username='"+username+"' and password = '"+password+"' ";
            Query query  = session.createQuery(hqlQuery);
            List<UsersEntity> userFullNameList = query.list();
            if (userFullNameList.size() > 0 ){
                userFullName = "" + userFullNameList.get(0).getFirstName() + " " + userFullNameList.get(0).getLastName();
            }
            else{
                userFullName = "Not Found! Could be a hacker.";
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return userFullName;
    }

    public static String validateAdministrator (String username, String password){
//        The function compares received values with existing ones in the database.
//        The function return ADMINID if the values are equal to the values in the table otherwise returns “Not Found! Could be a hacker”.
        Session session=null;
        String adminId = null;
        try {
            session = HibernateUtil.currentSession();
            String hqlQuery = "select admin from AdministratorsEntity admin where username='"+username+"' and password = '"+password+"' ";
            Query query  = session.createQuery(hqlQuery);
            List<AdministratorsEntity> adimnFullNameList = query.list();
            if (adimnFullNameList.size() > 0 ){
                adminId = ""+ adimnFullNameList.get(0).getAdminid();
            }
            else{
                adminId = "Not Found! Could be a hacker";
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return adminId;
    }

    public static void insertToHistory (String userid, String mid){
//        The function inserts the row to the History table with the current server time.
//        Print “Good job,The insertion to history table was successful! “.
        try{

            Session session = HibernateUtil.currentSession();
            Transaction tx = null;
            tx = session.beginTransaction();

            HistoryEntity history = new HistoryEntity();
            history.setUserid(Integer.parseInt(userid));
            history.setMid(Integer.parseInt(mid));
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            history.setViewtime(date);
            session.save(history);
            tx.commit();
            System.out.println("Good job,The insertion to history table was successful!");
        }
        catch (Exception e){
//            e.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
    }
    public static List getHistory (String userid){
//        The function retrieves from the tables History and MediaItems users's items.
//        The function return List of pairs sorted by LoginTime in descending order.
        List <MediaItemsEntity> mediaItems = null;
        Session session = null;
        try {
            session = HibernateUtil.currentSession();
            String hqlQuery = "select mi from HistoryEntity h,MediaItemsEntity mi, LoginLogEntity log " +
                    "where h.userid='"+ userid+ "' and h.userid=log.userid and h.mid=mi.mid and log.logintime=h.viewtime order by log.logintime desc";
            Query query  = session.createQuery(hqlQuery);
            mediaItems = query.list();
            Set miSet = new HashSet<>(mediaItems);
            mediaItems = new ArrayList<>(miSet);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return mediaItems;
    }
    public static void insertToLog (String userid){
//        The function insert the row to the LoginLog table with the current server time
//        Print “Good job, The insertion to log table was successful! “.
        try{
            Session session = HibernateUtil.currentSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            LoginLogEntity LogIn = new LoginLogEntity();
            LogIn.setUserid(Integer.parseInt(userid));
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            LogIn.setLogintime(date);
            session.save(LogIn);
            tx.commit();
            System.out.println("Good job, The insertion to log table was successful!");
        }
        catch (Exception e){
//            e.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
    }

    public static int getNumberOfRegistredUsers(int n){
//        The function receives an integer number n
//        The function retrieves from the table Users number of registered users in the past n days
//        The function returns an integer number
        Session session = null;
        int userNum = 0;

        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR,-1*n);
            java.sql.Date dateNew = new java.sql.Date( cal.getTime().getTime() );
            session = HibernateUtil.currentSession();
            String hqlQuery = "select user from UsersEntity user where registrationDate > '"+dateNew+"'";
            Query query  = session.createQuery(hqlQuery);
            List userList = query.list();
            userNum = userList.size();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return userNum;
    }

    public static List getUsers (){
//        The function retrieves from the table Users all users
        List users = null;
        Session session = null;
        try {
            session = HibernateUtil.currentSession();
            String hqlQuery = "select user from UsersEntity user ";
            Query query  = session.createQuery(hqlQuery);
            users = query.list();

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return users;
    }

    public static UsersEntity getUser (String userid){
//        The function retrieves from the table Users user's information
//        The function return objects Users
        UsersEntity user = null;
        Session session = null;
        try {
            session = HibernateUtil.currentSession();
            String hqlQuery = "select user from UsersEntity user where userid='"+userid+"'";
            Query query  = session.createQuery(hqlQuery);
            List<UsersEntity> userFullNameList = query.list();
            if (userFullNameList.size() > 0 ){
                user = userFullNameList.get(0);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return user;
    }
    public static void main(String[] args) {
//        System.out.println(Assignment.isExistUsername("ronig"));
//        System.out.println((Assignment.insertUser("ronig","12",
//                "roni", "guz", "16", "04", "1997")));
//        System.out.println((Assignment.insertUser("hodi","123",
//                "hodaya", "takele", "01", "07", "1995")));//
//        System.out.println((Assignment.insertUser("ranitg","1234",
//                "ravit", "guz", "17", "10", "1990")));
//            System.out.println((Assignment.insertUser("ranitga","1234",
//                "ravita", "guza", "17", "10", "1990")));
//        System.out.println(Assignment.getTopNItems(3));
//        System.out.println(Assignment.validateUser("ronig", "12"));
//        System.out.println(Assignment.validateAdministrator("rona", "123"));
//        Assignment.insertToHistory("2", "5");
//        Assignment.insertToHistory("3", "1");
//        Assignment.insertToLog("1");

//        System.out.println(Assignment.getNumberOfRegistredUsers(1));
//        System.out.println(Assignment.getUsers());
//        System.out.println(Assignment.getUser("1"));
//        System.out.println(Assignment.getHistory("2"));
    }

}

