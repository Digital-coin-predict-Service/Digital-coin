package digital.coin.predict.repository;

import digital.coin.predict.domain.Stock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
//    @PersistenceContext
//    private EntityManager em;
//
//    public Stock findOne(Long id) { return em.find(Stock.class, id); }
//
//    public Stock findByName(String name) {
//        return em.createQuery("select m from Stock m where m.name = :name",
//                        Stock.class)
//                .setParameter("name", name)
//                .getSingleResult();
//    }
//
//    public void save(Stock stock) {
//        em.persist(stock);
//    }
}
