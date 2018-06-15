package org.extrahour.democompany.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Long> {


}
