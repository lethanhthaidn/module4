package vn.codegym.repository;

import vn.codegym.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements IStudentRepository {
    @Override
    public List<Student> findAll() {
        // Cách 1: DÙng hibernate chuẩn
//        Session session = null;
//        List<Student> students = null;
//        try {
//            session = ConnectionUtil.sessionFactory.openSession();
//            students = session.createQuery("FROM Student").getResultList();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return students;
        TypedQuery<Student> query = ConnectionUtil.entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Student student) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "UPDATE Student set name = :name, gender = :gender where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", student.getId());
            query.setParameter("gender", student.getGender());
            query.setParameter("name", student.getName());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Student findById(int id) {
        Transaction transaction = null;
        Session session = null;
        Student student = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }

    @Override
    public void delete(int id) {
        Session session =null;
        Transaction transaction = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Student WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session !=null){
                session.close();
            }
        }
    }
}
