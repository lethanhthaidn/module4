package vn.codegym.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import vn.codegym.model.Song;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SongRepository implements ISongRepository {
    @Override
    public List<Song> findAll() {
        TypedQuery<Song> query = ConnectionUtil.entityManager.createQuery("from Song", Song.class);
        return query.getResultList();
    }

    @Override
    public void save(Song song) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(song);
            transaction.commit();
        } catch (Exception e) {
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
    public void update(Song song) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "Update Song set name = :name, singer = :singer, kind = :kind, link = :link where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", song.getId());
            query.setParameter("name", song.getName());
            query.setParameter("singer", song.getSinger());
            query.setParameter("kind", song.getKind());
            query.setParameter("link", song.getLink());
            query.executeUpdate();
            transaction.commit();
        }catch (Exception ex) {
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
    public Song findById(int id) {
        Transaction transaction = null;
        Session session = null;
        Song song = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            song = session.get(Song.class, id);
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
        return song;
    }


    @Override
    public void delete(int id) {
        Session session =null;
        Transaction transaction = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Song WHERE id = :id";
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
