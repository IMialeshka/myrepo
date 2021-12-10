package it.mialeshka.repository;

import it.mialeshka.entity.BookSolr;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface BookSolrRepository extends SolrCrudRepository<BookSolr, String> {
}
