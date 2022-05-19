package dao;

import model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class MovieRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Movie> findByCategoryID(Long categoryId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);

        Root<Movie> faultRecordsRoot = cq.from(Movie.class);
        Predicate idPredicate = cb.equal(faultRecordsRoot.get("category").get("id"), categoryId);
        cq.where(idPredicate);

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    public List<Movie> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
        Root<Movie> movieRoot = cq.from(Movie.class);
        cq.select(movieRoot);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

}
