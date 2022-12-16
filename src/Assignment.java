import hib.HibernateUtil;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.AdministratorsEntity;
import Entities.HistoryEntity;
import Entities.HistoryEntityPK;
import Entities.LoginLogEntity;
import Entities.LoginLogEntityPK;
import Entities.MediaItemsEntity;
import Entities.UsersEntity;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Calendar;

public class Assignment {
    public static boolean isExistUsername (String username){
        int numOfUsers = 0;
        try{
            Session session = HibernateUtil.currentSession();
            String hqlQuery = "select username from UsersEntity where username='"+username+"'";
            numOfUsers = session.createQuery(hqlQuery).list().size();
        }
        catch (Exception e){
            e.printStackTrace();
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
            e.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
        return null;
    }
}

